package com.company.Controller;

import com.company.Model.CustomerService;
import com.company.View.CustomerGui;

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
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
