package com.company.Model.OfertyService;

import com.company.ErrorType;
import com.company.View.ShowMessage;
import javafx.scene.control.Tab;

import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by Jagoda on 06-06-2015.
 */
public class OfertySend {
    static private Connection ActualConnection;
    static public void setConnection(Connection con){
        ActualConnection = con;
    }
    private static int IDBranza, IDStanowisko, IDTypEtatu, IDTypUmowy;
    static public void AddBranza(BranzaEntity Table) {
        if(Table.getName()!=null) {
            try {
                PreparedStatement pst = null;
                ResultSet rs = null;
                String sql_query = "INSERT INTO branza(nazwa_branzy) VALUES(?)";
                pst = ActualConnection.prepareStatement(sql_query, PreparedStatement.RETURN_GENERATED_KEYS);
                pst.setString(1, Table.getName());
                pst.executeUpdate();
                rs = pst.getGeneratedKeys();
                rs.next();
                IDBranza = rs.getInt(1);
                System.out.println("Dodano nowy rekord do tabeli branza ID: "+IDBranza);

            } catch (SQLException ex) {
                Logger lgr = Logger.getLogger(OfertyService.class.getName());
                lgr.log(Level.SEVERE, ex.getMessage(), ex);
            }
        }
        }


    static public void AddStanowisko(StanowiskoEntity Table) {
        if(Table.getName()!=null) {
            try {
                PreparedStatement pst = null;
                ResultSet rs = null;
                String sql_query = "INSERT INTO stanowisko(nazwa_stanowiska) VALUES(?)";
                pst = ActualConnection.prepareStatement(sql_query, PreparedStatement.RETURN_GENERATED_KEYS);
                pst.setString(1, Table.getName());
                pst.executeUpdate();
                rs = pst.getGeneratedKeys();
                rs.next();
                IDStanowisko = rs.getInt(1);
            } catch (SQLException ex) {
                Logger lgr = Logger.getLogger(OfertyService.class.getName());
                lgr.log(Level.SEVERE, ex.getMessage(), ex);
            }
        }
    }

    static public void AddTypEtatu(TypEtatuEntity Table) {
        if(Table.getName()!=null) {
            try {
                PreparedStatement pst = null;
                ResultSet rs = null;
                String sql_query = "INSERT INTO typ_etatu(nazwa_typ_etatu) VALUES(?)";
                pst = ActualConnection.prepareStatement(sql_query, PreparedStatement.RETURN_GENERATED_KEYS);
                pst.setString(1, Table.getName());
                pst.executeUpdate();
                rs = pst.getGeneratedKeys();
                rs.next();
                IDTypEtatu = rs.getInt(1);
            } catch (SQLException ex) {
                Logger lgr = Logger.getLogger(OfertyService.class.getName());
                lgr.log(Level.SEVERE, ex.getMessage(), ex);
            }
        }
    }

    static public void AddTypUmowy(TypUmowyEntity Table) {
        if(Table.getName()!=null) {
            try {
                PreparedStatement pst = null;
                ResultSet rs = null;
                String sql_query = "INSERT INTO typ_umowy(nazwa_typu_umowy) VALUES(?)";
                pst = ActualConnection.prepareStatement(sql_query, PreparedStatement.RETURN_GENERATED_KEYS);
                pst.setString(1, Table.getName());
                pst.executeUpdate();
                rs = pst.getGeneratedKeys();
                rs.next();
                IDTypUmowy = rs.getInt(1);
            } catch (SQLException ex) {
                Logger lgr = Logger.getLogger(OfertyService.class.getName());
                lgr.log(Level.SEVERE, ex.getMessage(), ex);
            }
        }
    }

    public static void all(String Wynagrodzenie, String Aktywna){
        int aktywna;
        try {
            aktywna = Integer.parseInt(Aktywna);
        }catch (Exception e){
            ErrorType er = new ErrorType();
            er.Error_ = ErrorType.ErrTypes.PAYMENT_NOT_NUMBER;
            ShowMessage msg = new ShowMessage();
            msg.setErrorType(er);
            er = null;
            msg = null;
            return;
        }
        int wynagrodzenie;
        try{
            wynagrodzenie = Integer.parseInt(Wynagrodzenie);
        }catch (Exception e){
            ErrorType er = new ErrorType();
            er.Error_ = ErrorType.ErrTypes.PAYMENT_NOT_NUMBER;
            ShowMessage msg = new ShowMessage();
            msg.setErrorType(er);
            er = null;
            msg = null;
            return;
        }

        if(wynagrodzenie>=0) {
            try {
                PreparedStatement pst = null;
                ResultSet rs = null;

                String sql_query = "INSERT INTO oferta_pracy(id_branza, id_stanowisko, id_typ_umowy, id_typ_etatu, wynagrodzenie, aktywna) VALUES(?,?,?,?,?,?)";
                pst = ActualConnection.prepareStatement(sql_query, PreparedStatement.RETURN_GENERATED_KEYS);
                pst.setInt(1, IDBranza);
                pst.setInt(2, IDStanowisko);
                pst.setInt(3, IDTypUmowy);
                pst.setInt(4, IDStanowisko);

                pst.setInt(5, wynagrodzenie);
                pst.setInt(6, aktywna);
                pst.executeUpdate();
                rs = pst.getGeneratedKeys();

            } catch (SQLException ex) {
                Logger lgr = Logger.getLogger(OfertyService.class.getName());
                lgr.log(Level.SEVERE, ex.getMessage(), ex);
            }
        }
    }
    }

