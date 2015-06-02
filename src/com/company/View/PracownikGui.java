package com.company.View;
import com.company.View.Customer.CustomerGui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.ArrayList;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;


/**
 * Created by Jagoda on 25-05-2015.
 */

public class PracownikGui extends JFrame{

    private JPanel Panelek;
    private JButton Zamykanko;
    private JTextArea Tekscik;
    private JComboBox Kombo;
    private JLabel Wybor;
    private JLabel Dodaj;
    private JButton DodajOferte;
    private JButton Publikacja;
    private JTextArea Tytul;
    private JLabel DodajTytul;
    private JButton Przegladaj;
    private JLabel Usun;
    private JComboBox tytulyDoUsuniecia;
    private JButton Skasuj;
    private JLabel Cyfry;

    public String tytul;
    public String text;

    private static Connection ActualConnection;
    private static String user_login;

    ArrayList Lista = new ArrayList();




     static public void Prepare(Connection con, String username) {

        ActualConnection = con;
        user_login = username;
    }



    public PracownikGui() {

        setMinimumSize(new Dimension(600, 600));
        setLocationRelativeTo(null);
        setContentPane(Panelek);
        setResizable(false);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("Panel pracownika: zarzadzanie ofertami pracy");
        setContentPane(new JLabel(new ImageIcon(CustomerGui.class.getResource("Images/pink2.jpg"))));

        Tekscik.setLineWrap(true);
        Tekscik.setWrapStyleWord(true);

        Tekscik();
        Tytul();
        Zamykanko();
        Kombo();
        Wybor();
        Dodaj();
        DodajOferte();
        Publikacja();
        DodajTytul();
        Przegladaj();
        Usun();
        tytulyDoUsuniecia();
        Skasuj();
        Cyfry();


        Zamykanko.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (e.getActionCommand().equals("Zamknij")) {
                    System.exit(0);
                }
            }
        });


        Przegladaj.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (e.getActionCommand().equals("Przegladaj oferty pracy")) {
                    Oferty gui = new Oferty();
                    gui.setVisible(true);
                }
            }
        });

        //--------------------------DODAWANIE NOWEJ OFERTY DO COMBOBOXA--------------------------//
        DodajOferte.addActionListener(new ActionListener() { // ZAPISYWANIE DODANEJ OFERTY PRACY
            public void actionPerformed(ActionEvent e) {
                if (e.getActionCommand().equals("Zapisz")) {
                    tytul = Tytul.getText();
                    text = Tekscik.getText();

                    if (!tytul.equals("") && !text.equals("")) {

                            Lista.add(text);
                            tytulyDoUsuniecia.addItem(tytul);
                            /* PreparedStatement pst = null;
                            ResultSet rs = null;
                            String sql_query = "INSERT INTO oferta_pracy(opis)" + " VALUES(?)";
                            try {
                                pst = ActualConnection.prepareStatement(sql_query, PreparedStatement.RETURN_GENERATED_KEYS);
                                pst.executeUpdate();
                                rs = pst.getGeneratedKeys();
                            } catch (SQLException e1) {
                                e1.printStackTrace();
                            }

                        } */


                        Tekscik.setText(null);
                        Tytul.setText(null);
                    }


            }}

        });

        //--------------------------ZAPISYWANIE W BAZIE--------------------------//

        Skasuj.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (e.getActionCommand().equals("Skasuj oferte")) {

                    //String value=tytulyDoUsuniecia.getSelectedItem().toString();
                    //DELETE FROM oferta_pracy WHERE id_oferty='...'
                }
            }
        });
    }

       //--------------------------ELEMENTY FORMY--------------------------//

    private void Zamykanko() { //GUZIK DO ZAMYKANIA FORMY
        Zamykanko = new JButton("Zamknij");
        add(Zamykanko);
        Zamykanko.setSize(80, 25);
        Zamykanko.setLocation(20, 520); //1 wartosc w prawo, druga wartosc w dol
    }

    public void Tekscik() { //TEXTAREA DO OFERTY PRACY

        Tekscik = new JTextArea(2, 10);
        Tekscik.setSize(300, 300);
        Tekscik.setLocation(20, 70);
        Tekscik.setEditable(true);
        Tekscik.setLineWrap(true);
        add(Tekscik);
    }

    public void Tytul() { //TEXTAREA DO TYTULU OFERTY PRACY
        Tytul.setSize(30, 20);
        add(Tytul);
        Tytul.setLocation(20, 30);
        Tytul.setColumns(2);
        Tytul.setLineWrap(true);
        Tytul.setWrapStyleWord(false);
    }

    public void Kombo() {
        Kombo.addItem("Budowa/remonty");
        Kombo.addItem("Edukacja");
        Kombo.addItem("IT/telekomunikacja");
        Kombo.addItem("Kasjer/ekspedient");
        Kombo.addItem("Kierowca/kurier");
        Kombo.addItem("Obsluga klienta/call center");
        Kombo.addItem("Sprzedaz/przedstawiciel handlowy");
        Kombo.addItem("Pozostale oferty pracy");
        Kombo.setSize(200, 20);
        Kombo.setLocation(20, 400);
        add(Kombo);
    }

    private void Wybor() { //LABEL DO WYBORU KATEGORII
        Wybor.setSize(150, 20);
        Wybor.setLocation(20, 380);
        Wybor.setVisible(true);
        Wybor.setEnabled(true);
        add(Wybor);
    }

    private void Dodaj() { //LABEL DO NAPISANIA NOWEJ OFERTY PRACY
        Dodaj.setSize(200, 20);
        Dodaj.setLocation(20, 50);
        Dodaj.setVisible(true);
        Dodaj.setEnabled(true);
        add(Dodaj);
    }

    private void Cyfry() { //LABEL DO CYFR
        Cyfry.setSize(100, 20);
        Cyfry.setLocation(53, 30);
        Cyfry.setVisible(true);
        Cyfry.setEnabled(true);
        add(Cyfry);
    }

    private void DodajTytul() { //LABEL DO NAPISANIA TYTULU
        DodajTytul.setSize(200, 20);
        DodajTytul.setLocation(20, 7);
        DodajTytul.setVisible(true);
        DodajTytul.setEnabled(true);
        add(DodajTytul);
    }


    private void DodajOferte() { //BUTTON DO ZAPISANIA OFERTY
        DodajOferte.setSize(80, 25);
        DodajOferte.setLocation(20, 430);
        add(DodajOferte);
    }

    private void Publikacja() { //BUTTON DO PUBLIKACJI OFERTY
        Publikacja.setSize(80, 25);
        Publikacja.setLocation(120, 430);
        add(Publikacja);
    }

    private void Przegladaj() { //BUTTON DO PRZEGLADANIA OFERT PRACY
        Przegladaj.setSize(200, 25);
        Przegladaj.setLocation(350, 120);
        add(Przegladaj);
    }

    private void Usun() { //LABEL DO WYCOFYWANIA OFERTY
        Usun.setSize(200, 20);
        Usun.setLocation(350, 7);
        Usun.setVisible(true);
        Usun.setEnabled(true);
        add(Usun);
    }

    private void tytulyDoUsuniecia() { //COMBOBOX do przechwytywania tytulow
        tytulyDoUsuniecia.setSize(200, 20);
        tytulyDoUsuniecia.setLocation(350, 30);
        add(tytulyDoUsuniecia);

    }

    private void Skasuj() { //BUTTON DO PRZEGLADANIA OFERT PRACY
        Skasuj.setSize(200, 25);
        Skasuj.setLocation(350, 70);
        add(Skasuj);
    }
}






