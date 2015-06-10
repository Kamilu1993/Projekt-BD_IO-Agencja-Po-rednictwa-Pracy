package com.company.Model;

import com.company.ErrorType;
import com.company.Model.CVService.InputCheck;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by Bajtas on 2015-05-10.
 */
public class CustomerService {
    private Connection ActualConnection;
    private final String user_login;
    private ErrorType isError = new ErrorType();
    public CustomerService(Connection con, String username) {
        ActualConnection = con;
        user_login=username;
    }
    public ErrorType.ErrTypes AccountOptions_ChangePass(String OldPass, String NewPass, String NewPass2){
        String user_pass_in_db="";
        try {
            ResultSet rs = null;
            String sql_query = "SELECT user_pw FROM uzytkownik WHERE user_login=?";
            PreparedStatement prepStmt = ActualConnection.prepareStatement(sql_query);
            prepStmt.setString(1,user_login);
            rs = prepStmt.executeQuery();
            if(rs.next()) // przejscie do kolejnego wiersza poniewaz standardowo zwracane s¹ informacje z wiersza '0'
                user_pass_in_db=rs.getString("user_pw");
        } catch (SQLException ex) {
            Logger lgr = Logger.getLogger(CustomerService.class.getName());//
            lgr.log(Level.SEVERE, ex.getMessage(), ex);
            return ErrorType.ErrTypes.UNKNOWN_ERROR;
        }
        if(user_pass_in_db.length()>0) {
            PasswordService haslo = new PasswordService();
            try {
                haslo.CheckPassword(OldPass, user_pass_in_db);
                PasswordService pascheck = new PasswordService();
                isError.Error_ = pascheck.CheckPassLength(NewPass);
                if(pascheck.CheckPassLength(NewPass)!= ErrorType.ErrTypes.NO_ERRORS)
                return isError.Error_;
                if(pascheck.CheckPassMatch(NewPass, NewPass2)!= ErrorType.ErrTypes.NO_ERRORS)
                    return ErrorType.ErrTypes.PASSWORD_DOESNT_MATCH;
                else{
                    String sql_query = "UPDATE uzytkownik SET user_pw='"+PasswordService.encrypt(NewPass)+"' WHERE user_pw='"+PasswordService.encrypt(OldPass)+"' AND user_login='"+user_login+"'";
                    PreparedStatement prepStmt = ActualConnection.prepareStatement(sql_query);
                    prepStmt.execute();
                    JOptionPane.showMessageDialog(null, "Hasło zostało zmienione, możesz się zalogować korzystając z nowego hasła. :)", "Komunikat", JOptionPane.INFORMATION_MESSAGE);
                }
            } catch (Exception e){
                    return ErrorType.ErrTypes.WRONG_PASSWORD;
            }
        }
      return ErrorType.ErrTypes.NO_ERRORS;
    }
    public byte[] addCVPhoto(){
        PhotoService LoadFile = new PhotoService();
        LoadFile.ShowFileDialog();
        byte[] array = LoadFile.getImageByteArray();
        if(array!=null)
            return array;
        return null;
    }
    public String GetUsername(){
        return user_login;
    }
    public Connection GetConnection(){
        return ActualConnection;
    }
    public String getUserID(){
        ResultSet rs = null;
        try {

            String sql_query = "SELECT user_id FROM uzytkownik WHERE user_login='"+user_login+"'";
            PreparedStatement prepStmt = ActualConnection.prepareStatement(sql_query);
            rs = prepStmt.executeQuery();
            if(rs.next())
                return rs.getString(1);
            }
         catch (Exception e){
             System.out.println("Wystąpił błąd przy obieraniu ID użytkownika...");
        }
        return null;
    }
    public ResultSet getOffers() {
        int user_id=0;
        try {
            System.out.println("Pobieram zaproponowane oferty z bazy danych...");
            ResultSet rs = null;
            String sql_query = "SELECT id_klient FROM klient, uzytkownik WHERE klient.user_id=uzytkownik.user_id AND uzytkownik.user_login=?";
            PreparedStatement prepStmt = ActualConnection.prepareStatement(sql_query);
            prepStmt.setString(1, user_login);
            rs = prepStmt.executeQuery();
            if(rs.next())
                user_id = rs.getInt(1);
            rs = null;
            sql_query = "SELECT nazwa_branzy, nazwa_stanowiska, wynagrodzenie, opis FROM " +
                    "zaproponowana_oferta, oferta_pracy, branza, stanowisko WHERE " +
                    "zaproponowana_oferta.id_klient=? AND " +
                    "oferta_pracy.id_oferty=zaproponowana_oferta.id_oferta_pracy AND " +
                    "oferta_pracy.id_branza=branza.id_branza AND " +
                    "stanowisko.id_stanowiska=oferta_pracy.id_stanowisko";
            prepStmt = ActualConnection.prepareStatement(sql_query);
            prepStmt.setInt(1, user_id);
            rs = prepStmt.executeQuery();
            return rs;
        } catch (SQLException ex) {
            Logger lgr = Logger.getLogger(Model.class.getName());
            lgr.log(Level.SEVERE, ex.getMessage(), ex);
        }
        return null;
    }
    public void ChangeEmail(String NewEmail){

        try {
            System.out.println("Zmieniam EMAIL...");
            String sql_query = "UPDATE uzytkownik SET email=? WHERE user_login=?";
            PreparedStatement prepStmt = ActualConnection.prepareStatement(sql_query);
            prepStmt.setString(1, NewEmail);
            prepStmt.setString(2, user_login);
            prepStmt.execute();
        } catch (SQLException ex) {
            Logger lgr = Logger.getLogger(Model.class.getName());
            lgr.log(Level.SEVERE, ex.getMessage(), ex);
        }
    }
}
