package com.company.View;

import com.company.View.Customer.CustomerGui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Jagoda on 25-05-2015.
 */
public class Oferty extends JFrame {

    private JPanel Panelek;
    private JTextArea Off;
    private JLabel wyb;
    private JComboBox box;
    private JTextArea id;
    private JButton przeglad;
    private JButton next;
    private JButton prev;
    private JLabel idof;
    private JButton details;
    private JButton close;

    public Oferty(){

        setMinimumSize(new Dimension(500, 500));
        setLocationRelativeTo(null);
        setContentPane(Panelek);
        setResizable(false);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setTitle("Aktualne oferty");
        setContentPane(new JLabel(new ImageIcon(CustomerGui.class.getResource("Images/pink.jpg"))));

        Off();
        box();
        wyb();
        id();
        przeglad();
        prev();
        next();
        idof();
        details();
        close();

        close.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (e.getActionCommand().equals("<html><center>Zamknij</center></html>")) {
                    System.exit(0);
                }
            }
        });
    }

    private void Off(){ //textarea do wyswietlania ofert
        Off.setSize(300, 300);
        add(Off);
        Off.setLocation(60, 60);
        Off.setEditable(false);

    }

    public void id() { //textarea do wyswietlania id
        id.setSize(30, 20);
        add(id);
        id.setLocation(60, 30);
        id.setColumns(2);
        id.setLineWrap(true);
        id.setWrapStyleWord(false);
        id.setEditable(false);
    }

    private void box() {
        box.addItem("Budowa/remonty");
        box.addItem("Edukacja");
        box.addItem("IT/telekomunikacja");
        box.addItem("Kasjer/ekspedient");
        box.addItem("Kierowca/kurier");
        box.addItem("Obsluga klienta/call center");
        box.addItem("Sprzedaz/przedstawiciel handlowy");
        box.addItem("Pozostale oferty pracy");
        box.setSize(200, 20);
        box.setLocation(110, 10);
        add(box);
    }


    private void wyb(){ //label wybierz kategorie
        wyb.setSize(150, 20);
        wyb.setLocation(10, 10); //prawo/dol
        wyb.setVisible(true);
        wyb.setEnabled(true);
        add(wyb);
    }

    private void idof(){ //label wybierz kategorie
        idof.setSize(100, 20);
        idof.setLocation(10, 30); //prawo/dol
        idof.setVisible(true);
        idof.setEnabled(true);
        add(idof);
    }

    private void przeglad() { //button do przegladania
        przeglad.setSize(100, 25);
        przeglad.setLocation(330, 9);
        add(przeglad);
        // action do pobierania oferty z okreslonej kategorii odnosnie tego ktora pozycja w comboboxie ustawiona
    }

    private void prev() { //button do poprzedniej oferty
        prev.setText("<html><center>Poprzednia<br />oferta</center></html>");
        prev.setSize(80, 50); //szerokosc, wysokosc
        prev.setLocation(390, 100);
        add(prev);
        // przechodzenie ofertami w lewo
    }

    private void next() { //button do nastepnej oferty
        next.setText("<html><center>Nastepna<br />oferta</center></html>");
        next.setSize(80, 50);
        next.setLocation(390, 170);
        add(next);
        // przechodzenie ofertami w prawo
    }

    private void details() { //button do szczegolow oferty
        details.setText("<html><center>Zobacz<br />szczegoly<br />oferty</center></html>");
        details.setSize(100, 50);
        details.setLocation(380, 240);
        add(details);
    }

    private void close() { //button do nastepnej oferty
        close.setText("<html><center>Zamknij</center></html>");
        close.setSize(100,25);
        close.setLocation(10, 440);
        add(close);
    }
}

