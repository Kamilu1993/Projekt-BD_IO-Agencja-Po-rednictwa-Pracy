package com.company.View;
import com.company.ErrorType;
import com.company.Model.CVService.CVService;
import com.company.Model.OfertyService.OfertySend;
import com.company.Model.OfertyService.OfertyService;

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


/**
 * Created by Jagoda on 25-05-2015.
 */

public class PracownikGui extends JFrame{

    private JPanel Panelek;
    private JButton Zamykanko;
    private JLabel Dodaj;
    private JButton DodajOferte;
    private JButton Przegladaj;
    private JLabel Usun;
    private JComboBox tytulyDoUsuniecia;
    private JButton Skasuj;
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
    private JButton Odswiez;

    public String text;

    private static Connection ActualConnection;
    private static String user_login;


     static public void Prepare(Connection con, String username) {

        ActualConnection = con;
        user_login = username;
    }



    public PracownikGui() {
        setMinimumSize(new Dimension(600, 400));
        setLocationRelativeTo(null);
        setContentPane(Panelek);
        setResizable(false);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Panel pracownika: zarzadzanie ofertami pracy");
        setContentPane(new JLabel(new ImageIcon(Login.class.getResource("Images/pink2.jpg"))));

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

        Zamykanko();
        Odswiezanko();
        DodajOferte();
        Przegladaj();
        Usun();
        tytulyDoUsuniecia();
        Skasuj();

        setOffers();


        Zamykanko.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (e.getActionCommand().equals("Zamknij")) {
                    dispose();
                }
            }
        });

        Odswiez.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (e.getActionCommand().equals("Odswiez")) {
                    setOffers();
                }
            }
        });


        Przegladaj.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (e.getActionCommand().equals("Przegladaj oferty pracy")) {
                    Oferty.Prepare(ActualConnection, user_login);
                    Oferty gui = new Oferty();
                    gui.setVisible(true);
                }
            }
        });

        Skasuj.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (e.getActionCommand().equals("Skasuj oferte")) {
                    Usuwanie();
                }
            }
        });

        //--------------------------DODAWANIE NOWEJ OFERTY DO BAZY DANYCH--------------------------//

        DodajOferte.addActionListener(new ActionListener() { // ZAPISYWANIE DODANEJ OFERTY PRACY
            public void actionPerformed(ActionEvent e) {
                if (e.getActionCommand().equals("Zapisz")) {
                    OfertyService service = new OfertyService();
                    service.SaveAll(getBranzaInfo(), getTextNazwaStanowiskaInfo(), getTextNazwaEtatuInfo(), getTextNazwaUmowy());
                    OfertySend.setConnection(ActualConnection);
                    OfertySend.AddBranza(service.getBranzaInfo());
                    OfertySend.AddStanowisko(service.getStanowiskoInfo());
                    OfertySend.AddTypUmowy(service.getTypUmowyInfo());
                    OfertySend.AddTypEtatu(service.getTypEtatuInfo());
                    //
                    //;
                    OfertySend.all(textNazwaWynagrodzenie.getText(), textNazwaAktywna.getText());
                    }}

        });

    }
    // ---------------------------- POBIERANIE WARTOSCI Z P�L FORMULARZA --------------
    public JTextArea getBranzaInfo(){ return branza; }

    public JTextArea getTextNazwaStanowiskaInfo(){
        return textNazwaStanowiska;
    }

    public JTextArea getTextNazwaEtatuInfo(){ return textNazwaEtatu; }

    public JTextArea getTextNazwaUmowy(){ return textNazwaUmowy; }


       //--------------------------ELEMENTY FORMULARZA--------------------------//

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
        branza.setEditable(true);
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
        textNazwaStanowiska.setEditable(true);
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
        textNazwaUmowy.setEditable(true);
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
        textNazwaEtatu.setEditable(true);
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
        textNazwaWynagrodzenie.setEditable(true);
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
        textNazwaAktywna.setEditable(true);
        textNazwaAktywna.setLineWrap(true);
        add(textNazwaAktywna);
    }

    private void setOffers(){
        tytulyDoUsuniecia.removeAllItems();
        ResultSet rs = null;
        try {
            PreparedStatement pst = null;
            String sql_query = "SELECT id_oferty, nazwa_branzy  FROM oferta_pracy, branza WHERE" +
                    " oferta_pracy.id_branza=branza.id_branza";
            pst = ActualConnection.prepareStatement(sql_query);
            pst.execute();
            rs = pst.getResultSet();
        } catch (SQLException ex) {
                Logger lgr = Logger.getLogger(CVService.class.getName());
                lgr.log(Level.SEVERE, ex.getMessage(), ex);
                ErrorType er = new ErrorType();
                er.Error_ = ErrorType.ErrTypes.ALREADY_DELETED;
                ShowMessage msg = new ShowMessage();
                msg.setErrorType(er);
                msg = null;
                er = null;
            }
        while(true){
            try{
                if(rs.next()){
                    tytulyDoUsuniecia.addItem("ID:"+rs.getInt(1)+" Branża:"+rs.getString(2));
                }
                else
                    break;
            }catch(SQLException ex){
                Logger lgr = Logger.getLogger(CVService.class.getName());
                lgr.log(Level.SEVERE, ex.getMessage(), ex);
            }

        }
    }

    private void Zamykanko() { //GUZIK DO ZAMYKANIA FORMY
        Zamykanko = new JButton("Zamknij");
        add(Zamykanko);
        Zamykanko.setSize(80, 25);
        Zamykanko.setLocation(120, 300); //1 wartosc w prawo, druga wartosc w dol
    }

    private void Odswiezanko() { //GUZIK DO ZAMYKANIA FORMY
        add(Odswiez);
        Odswiez.setActionCommand("Odswiez");
        Odswiez.setSize(200, 25);
        Odswiez.setLocation(350, 200);
    }

    private void DodajOferte() { //BUTTON DO ZAPISANIA OFERTY
        DodajOferte.setSize(80, 25);
        DodajOferte.setLocation(20, 300);
        add(DodajOferte);
    }

    private void Przegladaj() { //BUTTON DO PRZEGLADANIA OFERT PRACY
        Przegladaj.setSize(200, 25);
        Przegladaj.setLocation(350, 140);
        add(Przegladaj);
    }

    private void Usun() { //LABEL DO WYCOFYWANIA OFERTY
        Usun.setSize(200, 20);
        Usun.setLocation(350, 20);
        Usun.setVisible(true);
        Usun.setEnabled(true);
        add(Usun);
    }

    private void Usuwanie(){
        String str = tytulyDoUsuniecia.getSelectedItem().toString();
        char str2[] = str.toCharArray();
        String IDstr="";
        for(int i=3;i<str2.length;i++) {
            if (str2[i] == ' ')
                break;
            IDstr += str2[i];
        }
        int IDOferty = Integer.parseInt(IDstr);
        try {
            System.out.println("Sprawdzam czy ID oferty: " + IDOferty + " istnieje już w bazie...");
            ResultSet rs = null;
            PreparedStatement prepStmt;
            prepStmt = ActualConnection.prepareStatement("DELETE FROM oferta_pracy WHERE id_oferty = ?");
            prepStmt.setInt(1, IDOferty);
            prepStmt.execute();
        }
        catch (SQLException ex) {
            Logger lgr = Logger.getLogger(PracownikGui.class.getName());
            lgr.log(Level.SEVERE, ex.getMessage(), ex);
        }
        setOffers();
    }

    private void tytulyDoUsuniecia() { //COMBOBOX do przechwytywania tytulow
        tytulyDoUsuniecia.setSize(200, 20);
        tytulyDoUsuniecia.setLocation(350, 50);
        add(tytulyDoUsuniecia);

    }

    private void Skasuj() { //BUTTON DO KASOWANIA OFERTY
        Skasuj.setSize(200, 25);
        Skasuj.setLocation(350, 90);
        Skasuj.setActionCommand("Skasuj oferte");
        add(Skasuj);
    }

}






