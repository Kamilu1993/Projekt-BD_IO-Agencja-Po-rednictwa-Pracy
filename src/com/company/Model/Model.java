package com.company.Model;

import javax.swing.*;
import java.sql.DriverManager;
import java.sql.Connection;

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
            return 0;
        else
            return isError;
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
}
