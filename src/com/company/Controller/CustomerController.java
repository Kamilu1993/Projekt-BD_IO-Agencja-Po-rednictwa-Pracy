package com.company.Controller;

import com.company.Model.CustomerService;
import com.company.Model.Model;
import com.company.View.CustomerGui;
import com.company.View.Login;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Bajtas on 2015-05-10.
 */
public class CustomerController implements ActionListener {
    CustomerGui Customer_GUI;
    CustomerService Customer_SERVICE;

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
        if(e.getActionCommand().equals("Wyloguj mnie")) {
            Customer_GUI.HideCGUI();
            Customer_GUI = null;
            Customer_SERVICE = null;
            Controller MainController = new Controller(new Login(), new Model());
            
        }
    }
}
