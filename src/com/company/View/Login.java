package com.company.View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
/*------------------------------
Klasa wyświetlająca okno logowania.
Klasa nie sprawdza poprawności żadnego pola, zgodnie z wzorcem MVC nie ma ona fizycznego połączenia z klasą modelu ani
z klasą kontrolera.
--------------------------------
 */

public class Login extends JFrame{
    private JFrame LoginFrame;
    private JPasswordField PasswordField;
    private JTextField LoginField;
    private JButton BtLogInto, BtRegInto;

    public String GetPassword() {
        String password = String.valueOf(PasswordField.getPassword());
        return password;
    } // pobieranie hasła
    public String GetUsername() {
        String username = LoginField.getText();

        return username;
    } // pobieranie loginu
    public void HideLoginFrame()
    {
        LoginFrame.setVisible(false);
    }
    public Login() {
        int width = 250, height = 250;
        LoginFrame = new JFrame();
        LoginFrame.setContentPane(new JLabel(new ImageIcon(Login.class.getResource("Images/blue_bg.jpg").getPath()))); // wczytanie tła
        LoginFrame.setTitle("Zaloguj się");
        LoginFrame.setVisible(true);
        LoginFrame.setLocationRelativeTo(null); // wysrodkowanie aplikacji
        LoginFrame.setSize(width, height);
        LoginFrame.setResizable(false);
        LoginFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        //REJESTRACJA
        SetInfoText(100,25);
        //LOGIN
        SetLoginText(100,25);
        SetLoginInput(100, 25);
        // HASŁO
        SetPasswordText(100,25);
        SetPasswordInput(100, 25);
        // PRZYCISK
        SetBtRegInto(100,"Zarejestruj");
        SetBtLogInto(100,"Zaloguj");

        //LoginFrame.getContentPane().setBackground(Color.yellow);
    } // Konstruktor tworzący okno
    public void addKeyListener(KeyListener controller) {
        LoginFrame.addKeyListener(controller);
        BtLogInto.addKeyListener(controller);
        LoginField.addKeyListener(controller);
        PasswordField.addKeyListener(controller);
    }
    public void addController(ActionListener controller){
        BtLogInto.addActionListener(controller); // nasłuchiwanie czy nie został wcisnięty przycisk "zaloguj"
        BtRegInto.addActionListener(controller); // nasłuchiwanie czy nie został wcisnięty przycisk "zarejestruj"
    } // Metoda dodania słuchacza (kontrolera)
    /*Ustawienia wysokości/rozmiaru/pozycji pól JLabel,JButton,JTextField,JPasswordField */
    private void SetLoginInput(int width, int height){
        int FrameHeight = LoginFrame.getHeight();
        LoginField = new JTextField();
        LoginField.setSize(width, height);
        LoginField.setLocation(FrameHeight/2-width/2, 90);
        LoginField.setVisible(true);
        LoginFrame.add(LoginField);
    }
    private void SetInfoText(int width, int height) {
        int FrameHeight = LoginFrame.getHeight();
        JLabel infoText = new JLabel();
        infoText.setText("Nie masz konta?");
        infoText.setForeground(Color.white);
        infoText.setSize(width, height);
        infoText.setLocation(FrameHeight / 2 - width / 2, 10);
        LoginFrame.add(infoText);
    }
    private void SetLoginText(int width, int height) {
        int FrameHeight = LoginFrame.getHeight();
        JLabel loginText = new JLabel();
        loginText.setText("Login:");
        loginText.setForeground(Color.white);
        loginText.setSize(width, height);
        loginText.setLocation(FrameHeight / 2 - width / 2 - 40, 90);
        LoginFrame.add(loginText);
    }
    private void SetPasswordInput(int width, int height) {
        int FrameHeight = LoginFrame.getHeight();
        PasswordField = new JPasswordField();
        PasswordField.setSize(width, height);
        PasswordField.setLocation(FrameHeight/2-width/2, 120);
        LoginFrame.add(PasswordField);
    }
    private void SetPasswordText(int width, int height) {
        int FrameHeight = LoginFrame.getHeight();
        JLabel passwordText = new JLabel();
        passwordText.setText("Hasło:");
        passwordText.setForeground(Color.white);
        passwordText.setSize(width, height);
        passwordText.setLocation(FrameHeight / 2 - width / 2 - 40, 118);
        LoginFrame.add(passwordText);
    }
    private void SetBtLogInto(int width,  String caption) {
        int FrameHeight = LoginFrame.getHeight();
        BtLogInto = new JButton(caption);
        LoginFrame.add(BtLogInto);
        BtLogInto.setSize(100, 25);
        BtLogInto.setLocation(FrameHeight/2-width/2, 180);
    }
    private void SetBtRegInto(int width,  String caption) {
        int FrameHeight = LoginFrame.getHeight();

        BtRegInto = new JButton(caption);
        LoginFrame.add(BtRegInto);
        BtRegInto.setSize(100, 25);
        BtRegInto.setLocation(FrameHeight/2-width/2, 30);
    }
}