package com.company.View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
/*------------------------------
Klasa wyświetlająca okno logowania oraz komunikaty błędów.
Klasa nie sprawdza poprawności żadnego pola, zgodnie z wzorcem MVC nie ma ona fizycznego połączenia z klasą modelu ani
z klasą kontrolera.
--------------------------------
 */

public class Login extends JFrame {
    private JFrame LoginFrame;
    private JPasswordField PasswordField;
    private JTextField LoginField;
    private JLabel PasswordText, LoginText;
    private JButton BtLogInto;

    public void setErrorCode(int err_code) {
        switch (err_code){
            case 1:
                ErrorMessage("Proszę podać właściwy login i hasło.");
            case 2:
                ErrorMessage("Proszę podać właściwy login.");
            case 3:
                ErrorMessage("Proszę podać właściwe hasło.");
            case 4:
                ErrorMessage("Wystąpił błąd połączenia z bazą danych. Prosimy spróbować ponownie.");
        }

    } // Klasa która odczytuje kod błędu a następnie przekazuje odpowiednią informacje do wyswietlenia
    private void ErrorMessage(String ErrMessage) {
        JOptionPane.showMessageDialog(null, ErrMessage, "InfoBox: " + "lold", JOptionPane.INFORMATION_MESSAGE);
    } // Wyswietlenie komunikatow błędów
    public String GetPassword() {
        String password = String.valueOf(PasswordField.getPassword());
        return password;
    } // pobieranie hasła
    public String GetUsername() {
        String username = LoginField.getText();

        return username;
    } // pobieranie loginu
    public Login() {
        int width = 250, height = 250;
        LoginFrame = new JFrame();
        LoginFrame.setContentPane(new JLabel(new ImageIcon(Login.class.getClassLoader().getResource("blue_bg.jpg").getPath()))); // wczytanie tła
        LoginFrame.setTitle("Zaloguj się");
        LoginFrame.setVisible(true);
        LoginFrame.setLocationRelativeTo(null); // wysrodkowanie aplikacji
        LoginFrame.setSize(width, height);
        LoginFrame.setResizable(false);
        setDefaultCloseOperation(LoginFrame.EXIT_ON_CLOSE);
        //LOGIN
        SetLoginText(100,25);
        SetLoginInput(100, 25);
        // HASŁO
        SetPasswordText(100,25);
        SetPasswordInput(100, 25);
        // PRZYCISK
        SetBtLogInto(100, 35, "Zaloguj");


        //LoginFrame.getContentPane().setBackground(Color.yellow);
    } // Konstruktor tworzący okno
    public void addController(ActionListener controller){

        BtLogInto.addActionListener(controller); // nasłuchiwanie czy nie został wcisnięty przycisk "zaloguj"
    } // Metoda dodania słuchacza (kontrolera)
    /*Ustawienia wysokości/rozmiaru/pozycji pól JLabel,JButton,JTextField,JPasswordField */
    private void SetLoginInput(int width, int height){
        int FrameWidth = LoginFrame.getWidth();
        int FrameHeight = LoginFrame.getHeight();

        LoginField = new JTextField();
        //LoginField.setText("Login:");
        LoginField.setLayout(null);
        LoginField.setSize(width, height);
        LoginField.setLocation(FrameHeight/2-width/2, 90);
        LoginField.setVisible(true);
        LoginFrame.add(LoginField);
    }
    private void SetLoginText(int width, int height) {
        int FrameWidth = LoginFrame.getWidth();
        int FrameHeight = LoginFrame.getHeight();

        LoginText = new JLabel();
        LoginText.setText("Login:");
        LoginText.setForeground(Color.white);
        LoginText.setLayout(null);
        LoginText.setSize(width, height);
        LoginText.setLocation(FrameHeight/2-width/2-40, 90);
        LoginText.setVisible(true);
        LoginFrame.add(LoginText);
    }
    private void SetPasswordInput(int width, int height) {
        int FrameWidth = LoginFrame.getWidth();
        int FrameHeight = LoginFrame.getHeight();

        PasswordField = new JPasswordField();
        PasswordField.setLayout(null);
        PasswordField.setSize(width, height);
        PasswordField.setLocation(FrameHeight/2-width/2, 120);
        PasswordField.setVisible(true);
        LoginFrame.add(PasswordField);
    }
    private void SetPasswordText(int width, int height) {
        int FrameWidth = LoginFrame.getWidth();
        int FrameHeight = LoginFrame.getHeight();

        PasswordText = new JLabel();
        PasswordText.setText("Hasło:");
        PasswordText.setForeground(Color.white);
        PasswordText.setLayout(null);
        PasswordText.setSize(width, height);
        PasswordText.setLocation(FrameHeight/2-width/2-40, 118);
        PasswordText.setVisible(true);
        LoginFrame.add(PasswordText);
    }
    private void SetBtLogInto(int width, int height, String caption) {
        int FrameWidth = LoginFrame.getWidth();
        int FrameHeight = LoginFrame.getHeight();

        BtLogInto = new JButton(caption);
        BtLogInto.setLayout(null);
        LoginFrame.add(BtLogInto);
        BtLogInto.setSize(100, 25);
        BtLogInto.setLocation(FrameHeight/2-width/2, 180);
        BtLogInto.setVisible(true);
    }
}