package com.company.View;

import com.company.Controller.CustomerController;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Kamil on 2015-05-18.
 */
public class AccountSettings extends JFrame {
    private JPasswordField OldPass;
    private JPasswordField NewPass;
    private JPasswordField NewPass2;
    private JButton ChangePass;
    private JTextField NewEmail;
    private JButton ChangeEmail;
    private JPanel MainContainer;

    public AccountSettings(){
        setLocationRelativeTo(null);
        setMinimumSize(new Dimension(400, 300));
        setContentPane(MainContainer);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setTitle("Opcje Konta");
    }

    public void AddController(CustomerController object) {
        ChangePass.setActionCommand("ZmienHaslo");
        ChangePass.addActionListener(object);
        ChangeEmail.setActionCommand("ZmienEmail");
        ChangeEmail.addActionListener(object);
    }
    public String getOldPass(){
        return String.valueOf(OldPass.getPassword());
    }
    public String getNewPass(){
        return String.valueOf(NewPass.getPassword());
    }
    public String getNewPass2(){
        return String.valueOf(NewPass2.getPassword());
    }
}
