package com.company;


import com.company.Controller.Controller;
import com.company.Model.Model;
import com.company.View.Login;

import javax.swing.*;

public class Main
{
    public static void main(String[] args)
    {
        System.out.println("Trwa inicjalizacja aplikacji.");

        try{
            System.out.println("Wczytywanie skórki aplikacji.");
            UIManager.setLookAndFeel("com.jtattoo.plaf.smart.SmartLookAndFeel");
            System.out.println("Skórka wczytana pomyślnie!");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Błąd!", "Wystąpił błąd związany z wyglądem aplikacji!", JOptionPane.OK_OPTION);
        }
        Login LoginFrame = new Login();
        Model MainModel = new Model();

        Controller MainController = new Controller(LoginFrame, MainModel);
        MainController = null;
        LoginFrame = null;
        MainModel = null;
    }
}
