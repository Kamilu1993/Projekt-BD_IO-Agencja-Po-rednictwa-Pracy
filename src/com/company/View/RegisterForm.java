package com.company.View;

import javax.swing.*;
import java.awt.event.ActionListener;

/**
 * KLASA FORMULARZA REJESTRACJI
 */
public class RegisterForm extends JFrame{
    JFrame RegForm;
    JLabel LoginText, PasswordText, EmailText;
    JTextField LoginInput, EmailInput;
    JPasswordField PasswordInput;
    JButton BtReg;
    public void addController(ActionListener controller){

        BtReg.addActionListener(controller); // nasłuchiwanie czy nie został wcisnięty przycisk "zarejestruj ..."
    } // Metoda dodania słuchacza (kontrolera)
    public RegisterForm() {
        int width = 180, height = 200;
        RegForm = new JFrame();
        RegForm.setContentPane(new JLabel(new ImageIcon(RegisterForm.class.getResource("Images/regform_bg.jpg").getPath()))); // wczytanie tła
        RegForm.setTitle("Formularz rejestracji");
        RegForm.setVisible(false);
        RegForm.setLocationRelativeTo(null); // wysrodkowanie aplikacji
        RegForm.setSize(width, height);
        RegForm.setResizable(false);
        setDefaultCloseOperation(RegForm.EXIT_ON_CLOSE);
        SetLabels();
        SetFields();
        SetBtReg();
    }
    public void ShowFrame()
    {
        RegForm.setVisible(true);
    }
    public void HideFrame()
    {
        RegForm.setVisible(false);
    }
    public String GetLogin() {
        return LoginInput.getText();
    }
    public String GetPassword() {
        return PasswordInput.getText();
    }
    public String GetEmail() {
        return EmailInput.getText();
    }
    private void SetBtReg() {
        BtReg = new JButton();
        BtReg.setText("Zarejestruj ...");
        BtReg.setVisible(true);
        BtReg.setSize(110,25);
        BtReg.setLocation(RegForm.getWidth()/2-BtReg.getWidth()/2,125);
        RegForm.add(BtReg);
    }
    private void SetFields() {
        // TUTAJ ZMIEŃ
        LoginInput = new JTextField();
        LoginInput.setVisible(true);
        LoginInput.setSize(100,25);
        LoginInput.setLocation(RegForm.getWidth()/2-40,30);
        RegForm.add(LoginInput);

        PasswordInput = new JPasswordField();
        PasswordInput.setVisible(true);
        PasswordInput.setSize(100,25);
        PasswordInput.setLocation(RegForm.getWidth()/2-40,90);
        RegForm.add(PasswordInput);

        EmailInput = new JTextField();
        EmailInput.setVisible(true);
        EmailInput.setSize(100,25);
        EmailInput.setLocation(RegForm.getWidth()/2-40,60);
        RegForm.add(EmailInput);
    }
    private void SetLabels() {
        JLabel info = new JLabel("Formularz rejestracji");
        info.setVisible(true);
        info.setSize(120, 35);
        info.setLocation(RegForm.getWidth()/2-info.getWidth()/2,2);
        RegForm.add(info);

        LoginText = new JLabel("Login:");
        LoginText.setVisible(true);
        LoginText.setSize(100, 25);
        LoginText.setLocation(RegForm.getWidth()/2-80,30);
        RegForm.add(LoginText);

        PasswordText = new JLabel("Email:");
        PasswordText.setVisible(true);
        PasswordText.setSize(100,25);
        PasswordText.setLocation(RegForm.getWidth()/2-80,60);
        RegForm.add(PasswordText);

        EmailText = new JLabel("Hasło:");
        EmailText.setVisible(true);
        EmailText.setSize(100,25);
        EmailText.setLocation(RegForm.getWidth()/2-80,90);
        RegForm.add(EmailText);
    }
}
