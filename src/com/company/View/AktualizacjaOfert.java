package com.company.View;

import com.company.Model.Model;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.lang.Integer;


/**
 * Created by bartek_w on 2015-06-06.
 */
public class AktualizacjaOfert extends JFrame{
    private JButton Aktualizuj;
    private JComboBox comboBox1;
    private JTextArea Wymagania;
    private JTextArea Obowiazki;
    private JTextField Wynagrodzenie;
    private JTextField MiejscePracy;
    private JTextField Stanowisko;
    private JPanel MainPane;

    String dane;

    private static Connection ActualConnection;
    private static String user_login;

    public AktualizacjaOfert(){
        setContentPane(MainPane);
        setMinimumSize(new Dimension(500,500));
        WczytajOferty();

        Aktualizuj.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (e.getActionCommand().equals("Aktualizuj")) {
                    AktualizujDane();
                }
            }
        });
    }

    public static void Prep(String login, Connection con){
        user_login = login;
        ActualConnection = con;
    }

    public void WczytajOferty(){
        int user_id=0;
        try {
            System.out.println("Wczytuje oferty...");
            ResultSet rs = null;
            String sql_query;
            PreparedStatement prepStmt;

            sql_query = "SELECT id_oferty FROM oferta_pracy";
            prepStmt = ActualConnection.prepareStatement(sql_query);
            rs = prepStmt.executeQuery();

            JComboBox comboBox1 = new JComboBox();
            while(rs.next())
            {
                dane=rs.getString(1);
                comboBox1.addItem(dane);
            }
        } catch (SQLException ex) {
            Logger lgr = Logger.getLogger(Model.class.getName());
            lgr.log(Level.SEVERE, ex.getMessage(), ex);
        }
    }

    public ResultSet AktualizujDane(){
        if(Wymagania.getText() != "")
        {
            int user_id=0;
            try {
                System.out.println("Aktualizuje wymagania...");
                ResultSet rs = null;
                String sql_query;
                PreparedStatement prepStmt;

                sql_query = "UPDATE wymaganie SET nazwa_wymagania="+Wymagania.getText()+" FROM wiersz_wymagan, oferta_pracy WHERE wiersz_wymagan.id_wymagania=wymaganie.id_wymagania AND wiersz_wymagan.id_oferty=oferta_pracy.id_oferty AND oferta_pracy.id_oferty="+Integer.parseInt(comboBox1.getSelectedItem().toString());
                prepStmt = ActualConnection.prepareStatement(sql_query);
                prepStmt.setInt(1, user_id);
                rs = prepStmt.executeQuery();
                return rs;
            } catch (SQLException ex) {
                Logger lgr = Logger.getLogger(Model.class.getName());
                lgr.log(Level.SEVERE, ex.getMessage(), ex);
            }
        }

        if(Obowiazki.getText() != "")
        {
            int user_id=0;
            try {
                System.out.println("Aktualizuje obowiazki...");
                ResultSet rs = null;
                String sql_query;
                PreparedStatement prepStmt;

                sql_query = "UPDATE obowiazki SET nazwa_obowiazku="+Obowiazki.getText()+" FROM wiersz_obowiazki, oferta_pracy WHERE wiersz_obowiazki.id_obowiazki=obowiazki.id_obowiazki AND wiersz_obowiazki.id_oferty=oferta_pracy.id_oferty AND oferta_pracy.id_oferty="+Integer.parseInt(comboBox1.getSelectedItem().toString());
                prepStmt = ActualConnection.prepareStatement(sql_query);
                prepStmt.setInt(1, user_id);
                rs = prepStmt.executeQuery();
                return rs;
            } catch (SQLException ex) {
                Logger lgr = Logger.getLogger(Model.class.getName());
                lgr.log(Level.SEVERE, ex.getMessage(), ex);
            }
        }

        if(Wynagrodzenie.getText() != "")
        {
            int user_id=0;
            try {
                System.out.println("Aktualizuje wynagrodzenie...");
                ResultSet rs = null;
                String sql_query;
                PreparedStatement prepStmt;

                sql_query = "UPDATE wynagrodzenie SET Wynagodzenie="+Wynagrodzenie.getText()+" FROM Wynagrodzenie, oferta_pracy WHERE wynagrodzenie.Wynagrodzenie=wynagrodzenie.Wynagrodzenie AND Wynagrodzenie.id_oferty=oferta_pracy.id_oferty AND oferta_pracy.id_oferty="+Integer.parseInt(comboBox1.getSelectedItem().toString());
                prepStmt = ActualConnection.prepareStatement(sql_query);
                prepStmt.setInt(1, user_id);
                rs = prepStmt.executeQuery();
                return rs;
            } catch (SQLException ex) {
                Logger lgr = Logger.getLogger(Model.class.getName());
                lgr.log(Level.SEVERE, ex.getMessage(), ex);
            }
        }

        if(Stanowisko.getText() != "")
        {
            int user_id=0;
            try {
                System.out.println("Aktualizuje stanowisko...");
                ResultSet rs = null;
                String sql_query;
                PreparedStatement prepStmt;

                sql_query = "UPDATE stanowisko SET nazwa_stanowiska="+Stanowisko.getText()+" FROM stanowisko, oferta_pracy WHERE stanowisko.id_stanowisko=stnowisko.id_stanowisko AND stanowisko.id_stanowisko=oferta_pracy.id_oferty AND oferta_pracy.id_oferty="+Integer.parseInt(comboBox1.getSelectedItem().toString());
                prepStmt = ActualConnection.prepareStatement(sql_query);
                prepStmt.setInt(1, user_id);
                rs = prepStmt.executeQuery();
                return rs;
            } catch (SQLException ex) {
                Logger lgr = Logger.getLogger(Model.class.getName());
                lgr.log(Level.SEVERE, ex.getMessage(), ex);
            }
        }
        return null;
    }
}
