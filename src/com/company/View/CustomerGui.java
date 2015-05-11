package com.company.View;

import sun.rmi.runtime.Log;

import javax.swing.*;
import java.awt.*;

/**
 * Klasa okna aplikacji dla konta 'KLIENTA'
 */
public class CustomerGui extends JFrame{
    private JFrame CustomerFrame;
    private JLabel HelloUser;
    private JButton BtLogOut, BtMyCVs, BtAddNewCV;
    //--------------------------METODY--------------------------------//
    public CustomerGui(String user_login){
        int width = 500, height = 500;
        CustomerFrame = new JFrame();
        CustomerFrame.setContentPane(new JLabel(new ImageIcon(RegisterForm.class.getResource("Images/cust_bg.jpg").getPath()))); // wczytanie tła
        CustomerFrame.setTitle("Agencja pośrednictwa pracy v. 0.22a");
        CustomerFrame.setVisible(false);
        CustomerFrame.setLocationRelativeTo(null); // wysrodkowanie aplikacji
        CustomerFrame.setSize(width, height);
        CustomerFrame.setResizable(true);
        CustomerFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);


        Container cont = CustomerFrame.getContentPane();
        cont.setLayout(new BoxLayout(cont, BoxLayout.PAGE_AXIS));
        cont.setPreferredSize(new Dimension(100,100));

        HelloUser = new JLabel("Witaj "+user_login+"!");
        BtLogOut = new JButton("Wyloguj...");
        BtMyCVs = new JButton("Moje CV");
        BtAddNewCV = new JButton("Dodaj nowe CV");

        cont.add(HelloUser);
        cont.add(BtAddNewCV);
        cont.add(BtMyCVs);
        cont.add(BtLogOut);
    }
    public void ShowCGUI(){
        CustomerFrame.setVisible(true);
    }
    public void HideCGUI(){
        CustomerFrame.setVisible(false);
    }
}
