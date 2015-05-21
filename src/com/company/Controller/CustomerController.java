package com.company.Controller;

import com.company.ErrorType;
import com.company.Model.CustomerService;
import com.company.Model.Model;
import com.company.View.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Bajtas on 2015-05-10.
 */
public class CustomerController implements ActionListener {
    CustomerGui Customer_GUI;
    CustomerService Customer_SERVICE;
    CVForm AddCVForm;
    AccountSettings SettingsForm;
    ShowMessage ErrorMsg = new ShowMessage();



    public CustomerController(CustomerGui gui, CustomerService service)
    {

        this.Customer_GUI = gui;
        this.Customer_SERVICE = service;

        Customer_GUI.ShowCGUI();
        Customer_GUI.addController(this);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equals("Zapisz jako...")) {

        }
        else if(e.getActionCommand().equals("Wyloguj mnie")) {
            Customer_GUI.HideCGUI();
            Customer_GUI = null;
            Customer_SERVICE = null;
            Controller MainController = new Controller(new Login(), new Model());
        }
        else if(e.getActionCommand().equals("Dodaj CV")){
            AddCVForm = new CVForm();
            AddCVForm.setVisible(true);
            AddCVForm.addController(this);
        }
        else if(e.getActionCommand().equals("Dodaj pole do EDUKACJA")){
            AddCVForm.addEducationArea();
        }
        else if(e.getActionCommand().equals("Opcje Konta")){
            SettingsForm = new AccountSettings();
            SettingsForm.setVisible(true);
            SettingsForm.AddController(this);
        }
        else if(e.getActionCommand().equals("ZmienHaslo")){
            ErrorType er=new ErrorType();
            er.Error_=Customer_SERVICE.AccountOptions_ChangePass(SettingsForm.getOldPass(), SettingsForm.getNewPass(), SettingsForm.getNewPass2());
            ErrorMsg.setErrorType(er);
        }
        else if(e.getActionCommand().equals("ZmienEmail")){
            System.out.println("Nacisnieto przycisk zmien email");
        }
    }
}
