package com.company.Controller;

import com.company.View.Gui;

import java.awt.*;


/**
 *
 */
public class Controller {
    private enum Actions {CLOSE_APP};
    private Gui MainWindow; // klasa glownego okna
    /* Wymiary maksymalne okna */
    private int MaxWidth = 300;
    private int MaxHeight = 300;
    /* Wymiary minimalne okna */
    private int MinWidth = 600;
    private int MinHeight = 600;
    //------------------------//
    public Controller()
    {
        Dimension MaxSize = new Dimension(MaxWidth, MaxHeight);
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
