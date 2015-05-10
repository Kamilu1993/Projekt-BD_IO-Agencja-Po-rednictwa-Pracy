package com.company.Controller;

import com.company.ErrorType;
import com.company.Model.UserRole;
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
    private ShowConnectionInfo info;
    private RegisterForm RegForm;
    private ShowMessage ErrorMsg;
    ErrorType isError = new ErrorType();


    public void actionPerformed(java.awt.event.ActionEvent e) {
        JButton b = (JButton) e.getSource();
        info = new ShowConnectionInfo();
        if (b.getText() == "Zaloguj") {
            isError.Error_ = theModel.CheckInput(theLogin.GetUsername(), theLogin.GetPassword());

            if (isError.Error_ == ErrorType.ErrTypes.NO_ERRORS) {
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
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        if (theModel.TryToConnect() == false) {
                            isError.Error_ = ErrorType.ErrTypes.ERROR_WITH_DB_CONNECTION;
                            ErrorMsg.setErrorType(isError); // błąd połączenia z bazą danych.
                        }
                        info.SetTitle("Trwa logowanie...");

                        // sprawdzanie grupy do której nalezy logujący sie użytkownik
                        isError.Error_ = theModel.CheckUserRole(theLogin.GetUsername());
                        if (isError.Error_ != ErrorType.ErrTypes.UNKNOWN_ERROR && isError.Error_ != ErrorType.ErrTypes.ERROR_WITH_USER_ROLE) {
                            switch (isError.Error_)
                            {
                                case THIS_IS_CUSTOMER_ACC:
                                    System.out.println("Konto pracownika");
                                    break;
                            }
                        }
                        else
                            ErrorMsg.setErrorType(isError); // błąd połączenia z bazą danych.

                        info.HideDialog();
                    }
                };
                appThread.start();
            } else
                ErrorMsg.setErrorType(isError);
        }
        if (b.getText() == "Zarejestruj") {
            RegForm.ShowFrame();
            RegForm.addController(this);
        }
        if (b.getText() == "Zarejestruj ...") {
            isError.Error_ = theModel.CheckInput(RegForm.GetLogin(), RegForm.GetPassword(), RegForm.GetEmail());
            if (isError.Error_ == ErrorType.ErrTypes.NO_ERRORS) {
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
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        if (theModel.TryToConnect() == false) {
                            isError.Error_ = ErrorType.ErrTypes.ERROR_WITH_DB_CONNECTION;
                            ErrorMsg.setErrorType(isError); // kod błędu '4' oznacza błąd połączenia z bazą danych.
                        }
                        info.SetTitle("Nawiązano połączenie, trwa rejestracja...");
                        isError.Error_ = theModel.RegisterUserInDB(RegForm.GetLogin(), RegForm.GetPassword(), RegForm.GetEmail());
                        if (isError.Error_ == ErrorType.ErrTypes.NO_ERRORS) {
                            info.SetTitle("Rejestracja zakończona powodzeniem. Możesz się zalogować.");
                            System.out.println("Pomyślnie dodano użytkownika do bazy danych");
                        }
                        else
                            ErrorMsg.setErrorType(isError);
                        info.HideDialog();
                    }
                };
                appThread.start();
            } else
                ErrorMsg.setErrorType(isError);
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
