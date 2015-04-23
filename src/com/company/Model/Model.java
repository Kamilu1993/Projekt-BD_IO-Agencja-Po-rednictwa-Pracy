package com.company.Model;

import jdk.nashorn.internal.ir.SplitNode;

import javax.swing.*;
import java.net.SocketPermission;
import java.sql.*;
import java.util.logging.Logger;
import java.util.logging.Level;

public class Model {
    private boolean isConnected = false;
    private Connection ActualConnection;
    public boolean TryToConnect() {
        Connection temp = ConnectToDB();
        if(temp!=null)
        {
            ActualConnection = temp;
            return true;
        }
        return false;
    }
    public int CheckInput(String login, String password) {
        int isError = CheckForEmptyInput(login, password);
        if(isError == 0)
        {
            return 0;
        }
        else
            return isError;
    }
    public int CheckInput(String login, String password, String email) {
        int isError = CheckForEmptyInput(login, password, email);
        if(isError == 0)
        {
            isError = CheckForValidInput(login,password,email);
            if(isError == 0)
                return 0;
            else
                return isError;
        }
        else
            return isError;
    }
    private int CheckForValidInput(String login, String password, String email) {
        // LOGIN
        for(int i=32;i<48;i++) {
            char c = (char)i;
            String s = "" + c;
            if(login.contains(s))
                return 8;
        }
        for(int i=58;i<65;i++) {
            char c = (char)i;
            String s = "" + c;
            if(login.contains(s))
                return 8;
        }
        for(int i=91;i<97;i++) {
            char c = (char)i;
            String s = "" + c;
            if(login.contains(s))
                return 8;
        }
        /* --------- HASŁO ---------

         ZNAK ' " '       */
        if(password.length()<5)
            return 11;
        int i = 34;
        char c = (char)i;
        String s = "" + c;
        if(password.contains(s))
            return 9;
        // SPACJA
        i = 32;
        c = (char)i;
        s = "" + c;
        if(password.contains(s))
            return 9;
        for(i=39;i<48;i++) {
            c = (char)i;
            s = "" + c;
            if(password.contains(s))
                return 9;
        }
        for(i=58;i<64;i++) {
            c = (char)i;
            s = "" + c;
            if(password.contains(s))
                return 9;
        }
        //EMAIL
        s = "@";
        String s2 = ".";
        if(!email.contains(s) || !email.contains(s2))
            return 10;
        return 0;
    }
    private int CheckForEmptyInput(String login, String password) {
        if(login.length()==0 && password.length()==0)
        {

            return 1;
        }
        else if(login.length()==0)
        {

            return 2;
        }
        else if(password.length()==0)
        {

            return 3;
        }
        else
            return 0;
    } // Sprawdzenie czy pola :LOGIN: i :HASŁO: nie są puste; Zwraca false(gdy ktores pole jest puste) i true(w przeciwym wypadku).
    private int CheckForEmptyInput(String login, String password, String email) {
        if(login.length()==0 && password.length()==0 && email.length()==0)
        {
            return 5;
        }
        if(email.length()==0 && password.length()==0)
        {
            return 7;
        }
        else if(login.length()==0)
        {

            return 2;
        }
        else if(password.length()==0)
        {
            return 3;
        }
        else if(email.length()==0)
        {
            return 6;
        }
        else
            return 0;
    } // Sprawdzenie czy pola :LOGIN: i :HASŁO: nie są puste; Zwraca false(gdy ktores pole jest puste) i true(w przeciwym wypadku).
    private Connection ConnectToDB() {
        Connection c = null;
        try {
            Class.forName("org.postgresql.Driver");
            c = DriverManager.getConnection("jdbc:postgresql://ec2-107-22-166-233.compute-1.amazonaws.com:5432/daeiamnmlhnm33?ssl=true&sslfactory=org.postgresql.ssl.NonValidatingFactory","uynyesjiyhkzym","BQjzHKYEp9s9h35H7dBxMWTJkf");
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        System.out.println("Połączenie z bazą nawiązane! :)");
        return c;
    }
    public int RegisterUserInDB(String login, String password, String email)
    {
        int ErrInt;
        ErrInt = CheckUserLogin(login);
        if(ErrInt!=0) // sprawdzenie czy uzytkownik o podanym loginie jest juz w bazie
            return ErrInt;
        else
            return 0;
    }
    private int CheckUserLogin(String login) {
        try {
            Statement st = null;
            ResultSet rs = null;
            String stm = "SELECT user_login FROM uzytkownik WHERE user_login='"+login+"'";

            st = ActualConnection.createStatement();
            rs = st.executeQuery(stm);
            rs.next(); // przejscie do kolejnego wiersza poniewaz standardowo zwracane są informacje z wiersza '0'
            if(rs.getString("user_login").equals(login))
                return 12;
        } catch (SQLException ex) {
            Logger lgr = Logger.getLogger(Model.class.getName());
            lgr.log(Level.SEVERE, ex.getMessage(), ex);
            return 999;
        }
        return 0;
    }
}
