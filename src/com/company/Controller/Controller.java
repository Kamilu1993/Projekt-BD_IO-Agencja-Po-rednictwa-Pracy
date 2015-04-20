package com.company.Controller;

import com.company.View.Gui;

import java.awt.*;


/**
 *
 */
public class Controller {
    private enum Actions {CLOSE_APP};
    private Gui MainWindow; // klasa glownego okna
    /* Wymiary minimalne okna */
    private int MinWidth = 600;
    private int MinHeight = 600;
    //------------------------//
    public Controller() // Konstruktor kontrolera tworzacy glowne okno aplikacji
    {
        Dimension MinSize = new Dimension(MinWidth, MinHeight);
        MainWindow = new Gui();
        MainWindow.pack();
        MainWindow.setResizable(false);
        MainWindow.setMinimumSize(MinSize);
        MainWindow.setVisible(true);
        System.exit(0);
    }
    public int getAction()
    {
        return 0;
    }
}
