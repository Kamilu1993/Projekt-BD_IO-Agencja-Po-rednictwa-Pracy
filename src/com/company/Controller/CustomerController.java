package com.company.Controller;

import com.company.Model.CustomerService;
import com.company.View.CustomerGui;

import java.sql.Connection;

/**
 * Created by Bajtas on 2015-05-10.
 */
public class CustomerController {
    CustomerGui Customer_GUI;
    CustomerService Customer_SERVICE;
    public CustomerController(CustomerGui gui, CustomerService service)
    {
        this.Customer_GUI = gui;
        this.Customer_SERVICE = service;
    }
}
