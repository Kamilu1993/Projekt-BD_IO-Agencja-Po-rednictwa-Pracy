package com.company.View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Jagoda on 25-05-2015.
 */
public class Oferty extends JFrame {

    private JPanel Panelek;
    private JButton przeglad;
    private JButton close;

    private JLabel Dodaj;
    private JLabel nazwaStanowiska;
    private JLabel nazwaUmowy;
    private JLabel nazwaEtatu;
    private JLabel nazwaWynagrodzenie;
    private JLabel nazwaAktywna;
    private JTextArea branza;
    private JTextArea textNazwaStanowiska;
    private JTextArea textNazwaAktywna;
    private JTextArea textNazwaEtatu;
    private JTextArea textNazwaUmowy;
    private JTextArea textNazwaWynagrodzenie;
    private JButton prawo;
    private JButton lewo;
    private ResultSet wynik = null;

    private static Connection ActualConnection;
    private static String user_login;


    static public void Prepare(Connection con, String username) {

        ActualConnection = con;
        user_login = username;
    }

    public Oferty(){

        setMinimumSize(new Dimension(420, 370));
        setLocationRelativeTo(null);
        setContentPane(Panelek);
        setResizable(false);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setTitle("Aktualne oferty");
        setContentPane(new JLabel(new ImageIcon(Login.class.getResource("Images/pink.jpg"))));

        przeglad();
        close();

        branza();
        Dodaj();
        nazwaStanowiska();
        nazwaAktywna();
        nazwaEtatu();
        nazwaUmowy();
        nazwaWynagrodzenie();
        textNazwaStanowiska();
        textNazwaAktywna();
        textNazwaEtatu();
        textNazwaUmowy();
        textNazwaWynagrodzenie();

        prawo.setSize(new Dimension(80, 25)); // >> vv
        prawo.setActionCommand(">>");
        prawo.setLocation(190, 300);
        add(prawo); //270

        lewo.setSize(new Dimension(80, 25));
        lewo.setActionCommand("<<");
        lewo.setLocation(20, 300);
        add(lewo);


        close.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (e.getActionCommand().equals("<html><center>Zamknij</center></html>")) {
                    dispose();
                }
            }
        });
        przeglad.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (e.getActionCommand().equals("Przegladaj")){
                    try {

                        PreparedStatement pst;
                        String sql_query = "SELECT id_oferty, nazwa_branzy, nazwa_stanowiska, nazwa_typ_etatu, nazwa_typu_umowy, wynagrodzenie, aktywna  FROM oferta_pracy, branza, stanowisko, typ_umowy, typ_etatu WHERE" +
                            " oferta_pracy.id_branza=branza.id_branza AND oferta_pracy.id_stanowisko=stanowisko.id_stanowiska AND oferta_pracy.id_typ_umowy=typ_umowy.id_typ_umowy AND oferta_pracy.id_typ_etatu=typ_etatu.id_typ_etatu";
                        pst = ActualConnection.prepareStatement(sql_query, ResultSet.TYPE_SCROLL_SENSITIVE,
                                ResultSet.CONCUR_UPDATABLE );
                        wynik = pst.executeQuery();
                        if(wynik.next()){
                                branza.setText(wynik.getString(2));
                                textNazwaStanowiska.setText(wynik.getString(3));
                                textNazwaEtatu.setText(wynik.getString(4));
                                textNazwaUmowy.setText(wynik.getString(5));
                                textNazwaWynagrodzenie.setText(wynik.getString(6));
                                textNazwaAktywna.setText(wynik.getString(7));
                            }

                    } catch (SQLException e1) {
                        e1.printStackTrace();
                    }
                }
            }

        });
        prawo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (e.getActionCommand().equals(">>")){
                    try {
                            if(wynik.next()){
                                branza.setText(wynik.getString(2));
                                textNazwaStanowiska.setText(wynik.getString(3));
                                textNazwaEtatu.setText(wynik.getString(4));
                                textNazwaUmowy.setText(wynik.getString(5));
                                textNazwaWynagrodzenie.setText(wynik.getString(6));
                                textNazwaAktywna.setText(wynik.getString(7));
                            }
                    } catch (SQLException e1) {
                        e1.printStackTrace();
                    }
                }
            }
        });
        lewo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (e.getActionCommand().equals("<<")){
                    try {
                        if(wynik.previous()){ //prawda ze proste? xd tak :D
                            branza.setText(wynik.getString(2));
                            textNazwaStanowiska.setText(wynik.getString(3));
                            textNazwaEtatu.setText(wynik.getString(4));
                            textNazwaUmowy.setText(wynik.getString(5));
                            textNazwaWynagrodzenie.setText(wynik.getString(6));
                            textNazwaAktywna.setText(wynik.getString(7));
                        }
                    } catch (SQLException e1) {
                        e1.printStackTrace();
                    }
                }
            }
        });
    }


    private void Dodaj() { //LABEL BRANZA
        Dodaj.setSize(200, 20);
        Dodaj.setLocation(20, 20);
        Dodaj.setVisible(true);
        Dodaj.setEnabled(true);
        add(Dodaj);
    }

    public void branza() { //TEXTAREA DO BRANZY
        branza = new JTextArea(2, 10);
        branza.setSize(250, 20);
        branza.setLocation(20, 40);
        branza.setEditable(false);
        branza.setLineWrap(true);
        add(branza);
    }

    private void nazwaStanowiska() { //LABEL STANOWISKO
        nazwaStanowiska.setSize(200, 20);
        nazwaStanowiska.setLocation(20, 60);
        nazwaStanowiska.setVisible(true);
        nazwaStanowiska.setEnabled(true);
        add(nazwaStanowiska);
    }

    private void textNazwaStanowiska(){ //TEXTAREA STANOWISKO
        textNazwaStanowiska = new JTextArea(2, 10);
        textNazwaStanowiska.setSize(250, 20);
        textNazwaStanowiska.setLocation(20, 80);
        textNazwaStanowiska.setEditable(false);
        textNazwaStanowiska.setLineWrap(true);
        add(textNazwaStanowiska);
    }

    private void nazwaUmowy() { //LABEL UMOWA
        nazwaUmowy.setSize(200, 20);
        nazwaUmowy.setLocation(20, 100);
        nazwaUmowy.setVisible(true);
        nazwaUmowy.setEnabled(true);
        add(nazwaUmowy);
    }

    private void textNazwaUmowy(){ //TEXTAREA UMOWA
        textNazwaUmowy = new JTextArea(2, 10);
        textNazwaUmowy.setSize(250, 20);
        textNazwaUmowy.setLocation(20, 120);
        textNazwaUmowy.setEditable(false);
        textNazwaUmowy.setLineWrap(true);
        add(textNazwaUmowy);
    }

    private void nazwaEtatu() { //LABEL ETAT
        nazwaEtatu.setSize(200, 20);
        nazwaEtatu.setLocation(20, 140);
        nazwaEtatu.setVisible(true);
        nazwaEtatu.setEnabled(true);
        add(nazwaEtatu);
    }

    private void textNazwaEtatu(){ //TEXTAREA ETAT
        textNazwaEtatu = new JTextArea(2, 10);
        textNazwaEtatu.setSize(250, 20);
        textNazwaEtatu.setLocation(20, 160);
        textNazwaEtatu.setEditable(false);
        textNazwaEtatu.setLineWrap(true);
        add(textNazwaEtatu);
    }

    private void nazwaWynagrodzenie() { //LABEL WYNAGRODZENIE
        nazwaWynagrodzenie.setSize(200, 20);
        nazwaWynagrodzenie.setLocation(20, 180);
        nazwaWynagrodzenie.setVisible(true);
        nazwaWynagrodzenie.setEnabled(true);
        add(nazwaWynagrodzenie);
    }

    private void textNazwaWynagrodzenie(){ //TEXTAREA WYNAGRODZENIE
        textNazwaWynagrodzenie = new JTextArea(2, 10);
        textNazwaWynagrodzenie.setSize(250, 20);
        textNazwaWynagrodzenie.setLocation(20, 200);
        textNazwaWynagrodzenie.setEditable(false);
        textNazwaWynagrodzenie.setLineWrap(true);
        add(textNazwaWynagrodzenie);
    }

    private void nazwaAktywna() { //LABEL STATUS PRACY
        nazwaAktywna.setSize(200, 20);
        nazwaAktywna.setLocation(20, 220);
        nazwaAktywna.setVisible(true);
        nazwaAktywna.setEnabled(true);
        add(nazwaAktywna);
    }


    private void textNazwaAktywna(){ //TEXTAREA STATUS PRACY
        textNazwaAktywna = new JTextArea(2, 10);
        textNazwaAktywna.setSize(250, 20);
        textNazwaAktywna.setLocation(20, 240);
        textNazwaAktywna.setEditable(false);
        textNazwaAktywna.setLineWrap(true);
        add(textNazwaAktywna);
    }


    private void przeglad() { //button do przegladania
        przeglad.setSize(100, 50);
        przeglad.setLocation(300, 40);
        add(przeglad);
    }


    private void close() { //button do zamykania
        close.setText("<html><center>Zamknij</center></html>");
        close.setSize(100,25);
        close.setLocation(300, 120);
        add(close);
    }
}

