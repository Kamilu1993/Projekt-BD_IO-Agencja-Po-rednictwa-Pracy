package com.company.View;

import com.company.Controller.AdminController;
import com.company.View.Customer.CustomerGui;
import com.sun.xml.internal.fastinfoset.util.CharArray;

import javax.swing.*;
import java.awt.*;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Jan on 2015-05-27.
 */
public class AdminGui extends JFrame {
    private String Login;
    private JPanel RootPanel;
    private JLabel DataLabel;
    private JList UsersList;
    private JButton DeleteBt;
    private JButton RefreshBt;
    private JButton OptionsBt;
    private JButton wylogujButton;
    private JButton BanBt;

    public AdminGui(String login){
        this.Login = login;
        setTitle("Panel administratora v. 0.23");
        setMinimumSize(new Dimension(650, 550));
        setResizable(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // okno na srodku
        setContentPane(new JLabel(new ImageIcon(Login.class.getResource("Images/cust_bg.jpg")))); // wczzytywanie tla
        setTitle("Formularz dodawania nowego CV");
        setMinimumSize(new Dimension(500, 500)); // minimalny rozmiar okna
        setResizable(true); // mozliwosc roszerzania wlaczona

        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date date = new Date();
        DataLabel.setText("Dzisiejsza data: " + dateFormat.format(date) + " ");
        setContentPane(RootPanel);
    }
    public void controller(AdminController controller){
        wylogujButton.setActionCommand("WYLOGUJ");
        wylogujButton.addActionListener(controller);

        RefreshBt.setActionCommand("ODSWIEZ");
        RefreshBt.addActionListener(controller);

        DeleteBt.setActionCommand("USUN");
        DeleteBt.addActionListener(controller);
    }
    public void setUsers(ResultSet user){
        DefaultListModel list = new DefaultListModel();
        try{
            while(true)
            {
                if(user.next())
                {
                    String str = "ID: " + user.getString(1) + " Login: " + user.getString(2) + " Email: " + user.getString(4);
                    list.addElement(str);
                    UsersList.setModel(list);
                }
                else
                    break;
            }
            } catch(Exception e){
                System.out.println(e);
        }
    }
    public String getSelectedUserFromList(){
        String row = UsersList.getSelectedValue().toString();
        char[] checkedrow = row.toCharArray();
        int i = 0;
        String ID = "";
        while(checkedrow[i]!='\0'){
            if(checkedrow[i] == ':')
                for(int j=i+2;j<checkedrow.length;j++){
                    if(checkedrow[j]!=' ')
                        ID+= checkedrow[j];
                    else
                        return ID;
                }
            i++;
        }
        return "";
    }
}
