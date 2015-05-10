package com.company.View;

import com.company.Main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * GLOWNA KLASA TWORZACA OKNO
 */
public class Gui extends JFrame
{
    private JFrame MainFrame;
    private String AppName;
    private JButton BtConnectToDB;
    private int ButtonClicked=0;


    public Gui()
    {
        AppName = "System obslugi agencji posrednictwa pracy";
    } // Konstruktor domyślny
    public Gui(String name)
    {
        AppName = name;
    } // Konstruktor inicjalizujacy
    public void CreateWin() {
        MainFrame = new JFrame();
        SetIcon(); // funkcja ustawiajaca ikone aplikacji
         /* Wymiary minimalne okna */
        int MinWidth = 500;
        int MinHeight = 500;
        Dimension MinSize = new Dimension(MinWidth, MinHeight);
        MainFrame.setResizable(false);
        MainFrame.setMinimumSize(MinSize);
        MainFrame.setVisible(true);
        MainFrame.setTitle(AppName);
        MainFrame.setLayout(null);

        SetButtons();
        SetButtonsListeners();
    }
    public void SetButtonsListeners()
    {
        BtConnectToDB.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                SetButtonClicked(BtConnectToDB);
            }
        });
    }
    private void SetIcon() {

    }
    private void SetButtonClicked(JButton button)
    {
        ButtonClicked = 1;
    }
    private void SetBtConnectToDB(int width, int height, String caption) {
        int WinWidth = MainFrame.getWidth();
        int WinHeight = MainFrame.getHeight();

        BtConnectToDB = new JButton(caption);
        BtConnectToDB.setLayout(null);
        MainFrame.add(BtConnectToDB);
        BtConnectToDB.setSize(265, 25);
        BtConnectToDB.setLocation(WinWidth/2-width/2, 430);
        BtConnectToDB.setVisible(true);
    } // Ustawienia przycisku łączenia się z bazą danych
    private void SetButtons() {
        //Przycisk 1, odpowiadajacy za laczenie sie z baza danych
        SetBtConnectToDB(265,25, "Zaloguj się");

        //-------------------------------
        /*BtConnect.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                BtConnect.setVisible(false);
            }
        });*/
    }
}
