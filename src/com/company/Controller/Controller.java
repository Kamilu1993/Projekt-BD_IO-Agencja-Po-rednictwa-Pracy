package com.company.Controller;

import com.company.View.*;
import com.company.Model.Model;

import javax.swing.*;
import java.awt.event.ActionListener;

/**
 *
 */
public class Controller implements ActionListener{
    private Login theLogin;
    private Gui theGui;
    private Model theModel;
    ShowConnectionInfo info;
    RegisterForm RegForm;
    ShowMessage ErrorMsg;


    public void actionPerformed(java.awt.event.ActionEvent e){
        JButton b = (JButton)e.getSource();
        info = new ShowConnectionInfo();
       if(b.getText()=="Zaloguj")
       {
           int isInputGood;
           isInputGood = theModel.CheckInput(theLogin.GetUsername(),theLogin.GetPassword());

           if(isInputGood==0){
               final Runnable run = new Runnable() {
                   public void run() {

                       info.ShowDialog();
                   }
               };
               Thread appThread = new Thread() {
                   public void run() {
                       info.run();
                       info.SetTitle("Proszę czekać.. Trwa łączenie z bazą danych...");
                       try {
                           SwingUtilities.invokeAndWait(run);
                       }
                       catch (Exception e) {
                           e.printStackTrace();
                       }
                       if(theModel.TryToConnect()==false)
                           ErrorMsg.setErrorCode(4); // kod błędu '4' oznacza błąd połączenia z bazą danych.
                       info.HideDialog();
                   }
               };
               appThread.start();
           }
           else
               ErrorMsg.setErrorCode(isInputGood);
       }
        if(b.getText()=="Zarejestruj")
        {
            RegForm.ShowFrame();
            RegForm.addController(this);
        }
        if(b.getText()=="Zarejestruj ...")
        {

            int isInputGood;
            isInputGood = theModel.CheckInput(RegForm.GetLogin(),RegForm.GetPassword(),RegForm.GetEmail());
            if(isInputGood==0) {
                final Runnable run = new Runnable() {
                    public void run() {
                        info.ShowDialog();
                    }
                };
                Thread appThread = new Thread() {
                    public void run() {
                        info.run();
                        info.SetTitle("Proszę czekać.. Trwa łączenie z bazą danych...");
                        try {
                            SwingUtilities.invokeAndWait(run);
                        }
                        catch (Exception e) {
                            e.printStackTrace();
                        }
                        if(theModel.TryToConnect()==false)
                            ErrorMsg.setErrorCode(4); // kod błędu '4' oznacza błąd połączenia z bazą danych.
                        info.SetTitle("Nawiązano połączenie, trwa rejestracja...");
                        int alreadyInDB = theModel.RegisterUserInDB(RegForm.GetLogin(), RegForm.GetPassword(), RegForm.GetEmail());
                        if(alreadyInDB==0)
                            System.out.println("Pomyślnie dodano użytkownika do bazy danych");
                        else
                            ErrorMsg.setErrorCode(alreadyInDB);


                        info.HideDialog();
                    }
                };
                appThread.start();
            }
            else
                ErrorMsg.setErrorCode(isInputGood);
        }
    }

    //------------------------//
    public Controller(Gui g, Login l, Model m) {
        this.theLogin = l;
        this.theGui = g;
        this.theModel = m;

        this.theLogin.addController(this);
        ErrorMsg = new ShowMessage();
        RegForm = new RegisterForm();
       // System.exit(0);
    }// Konstruktor tworzący główne okno aplikacji
    public void getAction()
    {

        //return 0;
    }
    public void ShowConnectionInfo()
    {

    }
    public void StartApp()
    {

    }
}
