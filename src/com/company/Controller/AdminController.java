package com.company.Controller;

import com.company.ErrorType;
import com.company.Model.AdminService;
import com.company.Model.CVService.CVService;
import com.company.Model.Model;
import com.company.View.AdminGui;
import com.company.View.Login;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Jan on 2015-05-27.
 */
public class AdminController implements ActionListener{
    AdminService Admin_Service;
    AdminGui Admin_Gui;
    AdminController(AdminGui gui, AdminService serv){
        this.Admin_Service = serv;
        this.Admin_Gui = gui;
        //gui.setLogin(login);
        gui.setVisible(true);
        this.Admin_Gui.controller(this);
        Admin_Gui.setUsers(Admin_Service.getUsers());
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        //------------------ TUTAJ JAK SIE KLIKNIE ----------------------------------
        if(e.getActionCommand().equals("WYLOGUJ")) {
            Admin_Gui.setVisible(false);
            Admin_Gui = null;
            Admin_Service = null;
            Controller MainController = new Controller(new Login(), new Model());
        }
        if(e.getActionCommand().equals("ODSWIEZ")) {
            System.out.println("Odświeżam...");
            Admin_Gui.setUsers(Admin_Service.getUsers());
        }
        if(e.getActionCommand().equals("USUN")){
            System.out.println("Kliknieto przycisk USUN.");
            Admin_Service.DeleteUser(Admin_Gui.getSelectedUserFromList());
        }
    }
}
