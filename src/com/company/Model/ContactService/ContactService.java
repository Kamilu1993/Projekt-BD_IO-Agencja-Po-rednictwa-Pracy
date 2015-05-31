package com.company.Model.ContactService;

import com.company.Controller.Controller;
import com.company.Model.ContactService.ContactEntities.ContactEntity;
import com.company.Model.UserInfo;
import com.company.Model.UserType;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by Rafa³ on 31.05.2015.
 */
public class ContactService {

    private Connection ActualConnection;

    public ContactService() {
        ActualConnection = ConnectToDB();
    }

    public void validateEntity(ContactEntity e) throws Exception {
        if (e.getIdPracownika() <= 0) {
            throw new Exception("Nie wybrano pracownika docelowego");
        } else if (e.getTemat().equals(null) || e.getTemat().equals("")) {
            throw new Exception("Nie wybrano tematu wiadomoœci");
        } else if (e.getTresc().equals(null) || e.getTresc().equals("")) {
            throw new Exception("Nie wpisano treœci wiadomoœæi");
        } else if (e.getIdKlienta() <= 0) {
            throw new Exception("Nie wybrano klienta docelowego");
        }
    }


    public void sendMessage(ContactEntity entity) {
        try {
            ResultSet rs = null;
            String sql_query = "INSERT INTO Kontakty(Temat, Tresc, Data_Wyslania, ID_pracownik, ID_klient) VALUES(?,?,?,?,?)";
            PreparedStatement prepStmt = ActualConnection.prepareStatement(sql_query);
            java.sql.Date data = new java.sql.Date(entity.getDataWyslania().getYear(), entity.getDataWyslania().getMonth(), entity.getDataWyslania().getDay());
            prepStmt.setString(1, entity.getTemat());
            prepStmt.setString(2, entity.getTresc());
            prepStmt.setDate(3, data);
            prepStmt.setInt(4, entity.getIdPracownika());
            prepStmt.setInt(5, entity.getIdKlienta());
            rs = prepStmt.executeQuery();
        } catch (SQLException ex) {
            logException(ex);
        }
    }

    public void deleteMessage(ContactEntity entity) {
        try {
            ResultSet rs = null;
            String sql_query = "DELETE KONTAKTY WHERE ID_Kontakt = ?";
            PreparedStatement prepStmt = ActualConnection.prepareStatement(sql_query);
            prepStmt.setInt(1, entity.getId());
            rs = prepStmt.executeQuery();
        } catch (SQLException ex) {
            logException(ex);
        }
    }

    public ArrayList<ContactEntity> getMessages(int userId, UserType userType) {
        ArrayList<ContactEntity> messages = new ArrayList<ContactEntity>();
        try {
            ResultSet rs = null;
            String sql_query = "";
            if(userType == UserType.ADMINISTRATOR || userType == UserType.PRACOWNIK){
                sql_query = "SELECT * FROM Kontakty WHERE ID_pracownik = ?";
            }else if(userType == userType.KLIENT){
                sql_query = "SELECT * FROM Kontakty where ID_Klient = ?";
            }

            PreparedStatement stmt = ActualConnection.prepareStatement(sql_query);
            stmt.setInt(1, userId);
            rs = stmt.executeQuery();

            while(rs.next()){
                messages.add(new ContactEntity());
            }

        }catch (SQLException ex){
            logException(ex);
        }

        return messages;
    }


    //pobieranie kontaktow
    public ArrayList<UserInfo> getRecipients() {
        UserType currentUserType = Controller.getUserInfo().getUserType();
        UserType resultUserType = UserType.PRACOWNIK;
        ArrayList<UserInfo> contacts = new ArrayList<UserInfo>();
        try{
            ResultSet rs = null;
            String sql_query = "";

            if(currentUserType == UserType.PRACOWNIK || currentUserType == UserType.ADMINISTRATOR) {
                sql_query = "SELECT u.user_id, u.user_login FROM Klient k ";
                sql_query += "JOIN Uzytkownik u on u.user_id = k.ID_klient";
                resultUserType = UserType.KLIENT;

            }
            else if(currentUserType == UserType.KLIENT){
                sql_query = "SELECT u.user_id, u.user_login FROM Pracownik p ";
                sql_query += "JOIN Uzytkownik u on u.user_id = p.ID_pracownik";
                resultUserType = UserType.PRACOWNIK;
            }

            PreparedStatement prepStmt = ActualConnection.prepareStatement(sql_query);
            rs = prepStmt.executeQuery();

            while(rs.next()) {
                contacts.add(new UserInfo(rs.getInt("user_id"), rs.getString("user_login"), resultUserType));
            }

            return contacts;
        }
        catch (SQLException ex){
            logException(ex);
        }
        return contacts;
    }

    private void logException(Exception ex){
        Logger lgr = Logger.getLogger(ContactService.class.getName());
        lgr.log(Level.SEVERE, ex.getMessage(), ex);
    }



    private Connection ConnectToDB() {
        Connection c = null;
        try {
            System.out.println("Wczytuje sterownik JDBC...");
            Class.forName("org.postgresql.Driver");
            c = DriverManager.getConnection("jdbc:postgresql://ec2-54-163-238-96.compute-1.amazonaws.com:5432/d67ebkto7j5lvk?ssl=true&sslfactory=org.postgresql.ssl.NonValidatingFactory",
                    "vqaihlhpyfrtzr", "6oDG2S_0bHQpioBwVQSJ0jA2Yi");
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        System.out.println("Po³¹czenie z baz¹ nawi¹zane! :)");
        return c;
    }


}
