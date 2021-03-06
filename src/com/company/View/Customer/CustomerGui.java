package com.company.View.Customer;

import com.company.View.Login;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.sql.ResultSet;
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
    private JLabel HelloUser;
    private JButton BtMyCV;
    private JButton BtAccountOptions;
    private JButton BtAddCV;
    private JButton BtMailBox;
    private JButton BtLogOut;
    private JLabel DataLabel;
    private JPanel UserMenu;
    private JPanel DataPanel;
    private JList RecOfferList;
    private JPanel RecOffersPanel;
    private JLabel OffersLabel;
    private JButton BtShowAllOffers;
    private JScrollPane ScrollPane;
    private JPanel LogoPanel;
    private JButton BtRefresh;
    private JButton BtShowDetails;
    private Login LoginFrame;

    public CustomerGui(String login, Login loginF){
        LoginFrame = loginF;
        HelloUser.setText("     Witaj " + login + "! :)");
        setTitle("Agencja pośrednictwa pracy v. 1.0");
        setMinimumSize(new Dimension(650,550));
        setResizable(false);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setContentPane(new JLabel(new ImageIcon(Login.class.getResource("Images/cust_bg.jpg"))));
        setLayout(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date date = new Date();
        DataLabel.setText("Dziś jest: "+ dateFormat.format(date)+" ");

        //MENU
        menuBar = new JMenuBar();
        //1
        mainMenu = new JMenu("Menu");
        mainMenu.getAccessibleContext().setAccessibleDescription("Główne menu programu.");
        //zawartość pierwszego menu
        menuItem1 = new JMenuItem("Wczytaj tło...");
        menuItem1.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F1, ActionEvent.ALT_MASK));
        menuItem1.getAccessibleContext().setAccessibleDescription("Zmiana ustawień programu.");

        menuItem2 = new JMenuItem("Wyloguj...");
        menuItem2.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F1, ActionEvent.ALT_MASK));
        menuItem2.getAccessibleContext().setAccessibleDescription("Wyloguj mnie z systemu.");
       // mainMenu.add(menuItem1);
        mainMenu.add(menuItem2);

        menuBar.add(mainMenu);
        setJMenuBar(menuBar);
        UserMenu.setSize(200,255);
        UserMenu.setLocation(500 / 4 - 120, 10);
        add(UserMenu);

        DataPanel.setSize(160,26);
        DataPanel.setLocation(500 / 4 - 120, 460);
        add(DataPanel);

        RecOffersPanel.setSize(400,400);
        RecOffersPanel.setLocation(getWidth()/2 - 100, 10);
        add(RecOffersPanel);

        LogoPanel.setSize(205,165);
        LogoPanel.setLocation(getWidth()/2 - 320, 280);
        add(LogoPanel);

        BtRefresh.setSize(400,25);
        BtRefresh.setLocation(getWidth()/2 - 100, 460);
        add(BtRefresh);

        BtShowDetails.setSize(400,25);
        BtShowDetails.setLocation(getWidth()/2 - 100, 420);
        add(BtShowDetails);

        ScrollPane.setViewportView(RecOfferList);
    }
    public void ShowCGUI(){
        setVisible(true);
    }
    public void HideCGUI(){
        setVisible(false);
    }
    public void addController(ActionListener CustomerController) {
        //------------------- MENU --------------------------------
        menuItem1.setActionCommand("Wczytaj tlo...");
        menuItem1.addActionListener(CustomerController); // Opcja: Ustawienia programu...

        menuItem2.setActionCommand("Wyloguj mnie");
        menuItem2.addActionListener(CustomerController);

        BtAddCV.setActionCommand("Dodaj CV");
        BtAddCV.addActionListener(CustomerController);

        BtLogOut.setActionCommand("Wyloguj mnie");
        BtLogOut.addActionListener(CustomerController);

        BtAccountOptions.setActionCommand("Opcje Konta");
        BtAccountOptions.addActionListener(CustomerController);

        BtRefresh.setActionCommand("CustomerGUI - odswiez");
        BtRefresh.addActionListener(CustomerController);

        BtShowDetails.setActionCommand("CustomerGUI - pokaz szczegoly");
        BtShowDetails.addActionListener(CustomerController);
    }
    public void setRecOfferList(ResultSet DBList){
        DefaultListModel list = new DefaultListModel();
        try{
            while(true)
            {
                if(DBList.next())
                {
                    String str = "Branża: " + DBList.getString(1) + " Stanowisko: " + DBList.getString(2) + " Wynagrodzenie: " + DBList.getString(3) +
                            " Opis: " + DBList.getString(4);
                    list.addElement(str);
                    RecOfferList.setModel(list);
                }
                else
                    break;
            }
        } catch(Exception e){
            System.out.println(e);
        }
    }
}
