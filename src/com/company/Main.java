package com.company;


import com.company.Controller.Controller;
import com.company.Model.Model;
import com.company.View.Login;
import com.company.View.ShowMessage;
import sun.rmi.runtime.Log;

import javax.swing.*;
import javax.swing.plaf.metal.MetalLookAndFeel;
import javax.swing.plaf.metal.OceanTheme;

public class Main
{
    public static void main(String[] args)
    {
        try{
            UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
            MetalLookAndFeel.setCurrentTheme(new OceanTheme());
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
