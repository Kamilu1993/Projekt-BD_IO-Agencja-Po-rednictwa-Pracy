package com.company.Controller;

import com.company.ErrorType;
import com.company.Model.*;
import com.company.Model.CVService.InputCheck;
import com.company.View.*;
import com.company.View.AdminGui;
import com.company.View.Customer.CustomerGui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 *
 */

public class Controller implements ActionListener, KeyListener{
    private Login theLogin;
    private Model theModel;
    private ShowConnectionInfo info = new ShowConnectionInfo(); // Stowrzenie okna obsługującego komunikaty dotyczące logowania
    private RegisterForm RegForm;
    private ShowMessage ErrorMsg;
    private PasswordService PasswordEncrypter = new PasswordService();
    ErrorType isError = new ErrorType();
    private void StartLogIn() {
        RegForm.HideFrame(); // Ukrycie okna rejestracji

        //region Wstępne sprawdzanie poprawności wprowadzonych danych.
        isError.Error_ = InputCheck.EmptyInput(theLogin.GetUsername(), theLogin.GetPassword());
        //endregion

        if (isError.Error_ == ErrorType.ErrTypes.NO_ERRORS) {

            //region Funkcja wątku roboczego, odpowiedzialna za pokazanie okna informacyjnego o stanie aplikacji.
            final Runnable dialogShow = new Runnable() {
                public void run() {
                    info.ShowDialog();
                }
            };
            //endregion

            //region Nowy wątek roboczy - sprawdzający poprawność wprowadzonych danych.
            Thread appThread = new Thread() {
                public void run() {
                    info.run();
                    info.SetTitle("Proszę czekać.. Trwa łączenie z bazą danych...");
                    try {
                        SwingUtilities.invokeAndWait(dialogShow); // odwołanie do funkcji i oczekiwanie aż skończy swoje działanie
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    // Połączenie z bazą danych
                    if (theModel.TryToConnect() == false) {
                        isError.Error_ = ErrorType.ErrTypes.ERROR_WITH_DB_CONNECTION;
                        ErrorMsg.setErrorType(isError); // błąd połączenia z bazą danych.
                    }

                    info.SetTitle("Trwa logowanie...");
                    // sprawdzanie grupy do której nalezy logujący sie użytkownik
                    isError.Error_ = theModel.CheckUserRole(theLogin.GetUsername());
                    if (isError.Error_ != ErrorType.ErrTypes.UNKNOWN_ERROR && isError.Error_ != ErrorType.ErrTypes.ERROR_WITH_USER_ROLE
                            && isError.Error_ != ErrorType.ErrTypes.USER_DOESNT_EXIST) {
                        switch (isError.Error_) {
                            case THIS_IS_CUSTOMER_ACC:
                                // Sprawdzenie czy hasło sie zgadza
                                try {
                                    PasswordService.CheckPassword(theLogin.GetPassword(), theModel.GetUserPasswordFromDB(theLogin.GetUsername()));
                                    PasswordEncrypter = null;
                                } catch (Exception e) {
                                    isError.Error_ = ErrorType.ErrTypes.WRONG_PASSWORD;
                                    ErrorMsg.setErrorType(isError);
                                    break;
                                }
                                //############################################################//
                                        /* ŁADOWANIE OKNA APLIKACJI DLA KLIENTA */
                                info.SetTitle("Zalogowano pomyślnie... Trwa ładowanie aplikacji...");
                                theLogin.HideLoginFrame();

                                CustomerGui Customer_GUI = new CustomerGui(theLogin.GetUsername(), theLogin);
                                CustomerService Customer_SERVICE = new CustomerService(theModel.GetConnection(), theLogin.GetUsername());
                                theLogin = null;
                                theModel = null;
                                CustomerController Customer_CONTROLLER = new CustomerController(Customer_GUI, Customer_SERVICE);
                                Customer_CONTROLLER = null;
                                break;
                            case THIS_IS_EMPLOYEE_ACC:
                                PracownikGui.Prepare(theModel.GetConnection(), theLogin.GetUsername());
                                AktualizacjaOfert.Prep(theLogin.GetUsername(), theModel.GetConnection());
                                PracownikInc gui = new PracownikInc(); // INC !!!
                                gui.setVisible(true);
                                theLogin.HideLoginFrame();
                                break;
                            case THIS_IS_ADMIN_ACC:
                                AdminController AdminFrame = new AdminController(new AdminGui(theLogin.GetUsername()), new AdminService(theModel.GetConnection()));
                                break;
                        }
                    } else
                        ErrorMsg.setErrorType(isError); // błąd połączenia z bazą danych.

                    info.HideDialog();
                }
            };
            appThread.start();
            //endregion
        }
        else
            ErrorMsg.setErrorType(isError);
        info.HideDialog();
    }

    public void actionPerformed(ActionEvent e) {
        JButton b = (JButton) e.getSource(); // pobranie źródła klikniętego przycisku

        //region Nacisnięcie przycisku "ZALOGUJ".
        if (b.getText() == "Zaloguj")
            StartLogIn();
        //endregion

        //region Naciśnięcie przycisku "ZAREJESTRUJ"
        if (b.getText() == "Zarejestruj") {
            RegForm.ShowFrame();
            RegForm.addController(this);
        }
        //endregion

        //region Naciśnięcie przycisku "ZAREJESTRUJ ..."
        if (b.getText() == "Zarejestruj ...") {
            isError.Error_ = InputCheck.EmptyInput(RegForm.GetLogin(), RegForm.GetPassword(), RegForm.GetEmail());
            if(isError.Error_!= ErrorType.ErrTypes.NO_ERRORS)
                ErrorMsg.setErrorType(isError);
            else
                isError.Error_= InputCheck.ValidInput(RegForm.GetLogin(), RegForm.GetPassword(), RegForm.GetEmail());

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
                        String EncryptedPasswordInDB = "";
                        try {
                            SwingUtilities.invokeAndWait(run);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        if (theModel.TryToConnect() == false) {
                            isError.Error_ = ErrorType.ErrTypes.ERROR_WITH_DB_CONNECTION;
                            ErrorMsg.setErrorType(isError); // błąd połączenia z bazą danych
                        }
                        info.SetTitle("Nawiązano połączenie, trwa rejestracja...");
                        try
                        {
                            EncryptedPasswordInDB = PasswordEncrypter.encrypt(RegForm.GetPassword());
                        }
                        catch (Exception e)
                        {
                            System.out.println("Wystąpił błąd z szyfrowaniem hasła!");
                        }
                        isError.Error_ = theModel.RegisterUserInDB(RegForm.GetLogin(), EncryptedPasswordInDB, RegForm.GetEmail());
                        if (isError.Error_ == ErrorType.ErrTypes.NO_ERRORS) {
                            info.SetTitle("Rejestracja zakończona powodzeniem...");
                            isError.Error_ = ErrorType.ErrTypes.REGISTER_SUCCESS;
                            ErrorMsg.setErrorType(isError);
                            RegForm.HideFrame();
                            info.HideDialog();
                            System.out.println("Pomyślnie dodano użytkownika do bazy danych! :)");
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
        //endregion
    }

    //-------------------------------------------------------------------------------------------------------//

    public Controller(Login l, Model m) {
        this.theLogin = l;
        this.theModel = m;

        this.theLogin.addController(this);
        this.theLogin.addKeyListener(this);
        ErrorMsg = new ShowMessage();
        RegForm = new RegisterForm();
    }//konstruktur kontrollera

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            System.out.println("Naciśnięto przycisk ENTER.");
            StartLogIn();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }
}
