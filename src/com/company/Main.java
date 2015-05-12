package com.company;


import com.company.Controller.Controller;
import com.company.Model.Model;
import com.company.View.Login;
import sun.rmi.runtime.Log;

public class Main
{
    public static void main(String[] args)
    {
        Login LoginFrame = new Login();
        Model MainModel = new Model();

        Controller MainController = new Controller(LoginFrame, MainModel);
        MainController = null;
        LoginFrame = null;
        MainModel = null;
    }
}
