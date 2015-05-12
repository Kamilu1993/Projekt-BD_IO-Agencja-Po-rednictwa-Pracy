package com.company.View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Bajtas on 2015-05-12.
 */
public class CustomerGui extends JFrame{
    private JMenuBar menuBar;
    private JMenu mainMenu;
    private JMenuItem menuItem1/*Zapisz jako...*/, menuItem2/**/, menuItem3, menuItem4, menuItem5, menuItem6;
    private JPanel RootPanel;
    private JLabel BgImage;
    private JLabel HelloUser;
    private JButton mojeCVButton;
    private JButton opcjeKontaButton1;
    private JButton dodajCVButton;
    private JButton skrzynkaPocztowaButton;
    private JPanel UserMenu;
    private JButton BtLogOut;
    private JLabel DataLabel;
    private Login LoginFrame;

    public CustomerGui(String login, Login loginF){
        LoginFrame = loginF;
        HelloUser.setText("     Witaj "+login+"! :)");
        setTitle("Agencja pośrednictwa pracy v. 0.23");
        setMinimumSize(new Dimension(500,500));
        setResizable(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        ImageIcon icon = new ImageIcon(RegisterForm.class.getResource("Images/cust_bg.jpg").getPath());
        BgImage.setIcon(icon);
        RootPanel.setOpaque(false);
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date date = new Date();
        DataLabel.setText("Dziś jest: "+ dateFormat.format(date)+" ");

        //MENU
        menuBar = new JMenuBar();
        //1
        mainMenu = new JMenu("Plik");
        mainMenu.getAccessibleContext().setAccessibleDescription("Główne menu programu.");
        //zawartość pierwszego menu
        menuItem1 = new JMenuItem("Zapisz jako...");
        menuItem1.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F1, ActionEvent.ALT_MASK));
        menuItem1.getAccessibleContext().setAccessibleDescription("Zapisanie otrzymanych wyników do pliku .txt");
        mainMenu.add(menuItem1);

        menuBar.add(mainMenu);
        setJMenuBar(menuBar);
        setContentPane(RootPanel);
    }
    public void ShowCGUI(){
        setVisible(true);
    }
    public void HideCGUI(){
        setVisible(false);
    }
    public void addController(ActionListener CustomerController) {
        //------------------- MENU --------------------------------
        menuItem1.setActionCommand("Zapisz jako...");
        menuItem1.addActionListener(CustomerController); // Opcja: Zapisz jako...

        BtLogOut.setActionCommand("Wyloguj mnie");
        BtLogOut.addActionListener(CustomerController);
    }
}
