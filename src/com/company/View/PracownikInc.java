package com.company.View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Jagoda on 26-05-2015.
 */
public class PracownikInc extends JFrame {
    private JButton Zarzadzanie;
    private JButton Aktualizuj;
    private JPanel Panel;
    private JButton Zamknij;



    public PracownikInc() {

        setMinimumSize(new Dimension(300, 300));
        setLocationRelativeTo(null);
        setContentPane(Panel);
        setResizable(false);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setTitle("Panel pracownika");
        setContentPane(new JLabel(new ImageIcon(Login.class.getResource("Images/pink2.jpg"))));

     Zarzadzanie(); Aktualizuj(); Zamknij();

        Zarzadzanie.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (e.getActionCommand().equals("Zarzadzaj ofertami pracy")) {
                    PracownikGui gui = new PracownikGui();
                    gui.setVisible(true);
                }
            }
        });
        Zamknij.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (e.getActionCommand().equals("Zamknij")) {
                    System.exit(0);
                }
            }
        });
        Aktualizuj.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (e.getActionCommand().equals("Aktualizuj oferty pracy")) {
                    AktualizacjaOfert gui1 = new AktualizacjaOfert();
                    gui1.setVisible(true);
                }
            }
        });
    }

   private void Zarzadzanie(){

       Zarzadzanie = new JButton("Zarzadzaj ofertami pracy");
       add(Zarzadzanie);
       Zarzadzanie.setSize(200, 25);
       Zarzadzanie.setLocation(50, 20);
}
    private void Aktualizuj(){

        Aktualizuj = new JButton("Aktualizuj oferty pracy");
        add(Aktualizuj);
        Aktualizuj.setSize(200, 25);
        Aktualizuj.setLocation(50, 70);
    }

    private void Zamknij(){

        Zamknij = new JButton("Zamknij");
        add(Zamknij);
        Zamknij.setSize(200, 25);
        Zamknij.setLocation(50, 220);
    }

}