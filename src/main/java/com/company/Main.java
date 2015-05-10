package com.company;

import com.company.Controller.Controller;
import com.company.View.Login;
import com.company.View.Gui;
import com.company.Model.Model;

public class Main
{
    public static void main(String[] args)
    {
        Login LoginFrame = new Login();
        Gui GuiFrame = new Gui();
        Model MainModel = new Model();

        Controller MainController = new Controller(GuiFrame, LoginFrame, MainModel);

        MainController.StartApp();
    }
}