package com.company.Model;

import com.company.ErrorType;

import java.sql.*;
import java.util.logging.Logger;
import java.util.logging.Level;

public class Model {
    private boolean isConnected = false;
    private Connection ActualConnection;
    private ErrorType isError = new ErrorType();
    public boolean TryToConnect() {
        Connection temp = ConnectToDB();
        if(temp!=null)
        {
            ActualConnection = temp;
            return true;
        }
        return false;
    }
    public ErrorType.ErrTypes CheckInput(String login, String password) {
        return CheckForEmptyInput(login, password);
    }
    public ErrorType.ErrTypes CheckInput(String login, String password, String email) {
        isError.Error_ = CheckForEmptyInput(login, password, email);
        if(isError.Error_ == ErrorType.ErrTypes.NO_ERRORS)
            return CheckForValidInput(login,password,email);
        else
            return isError.Error_;
    }
    private ErrorType.ErrTypes CheckForValidInput(String login, String password, String email) {
        // LOGIN
        for(int i=32;i<48;i++) {
            char c = (char)i;
            String s = "" + c;
            if(login.contains(s))
                return ErrorType.ErrTypes.LOGIN_NOT_ALLOWED;
        }
        for(int i=58;i<65;i++) {
            char c = (char)i;
            String s = "" + c;
            if(login.contains(s))
                return ErrorType.ErrTypes.LOGIN_NOT_ALLOWED;
        }
        for(int i=91;i<97;i++) {
            char c = (char)i;
            String s = "" + c;
            if(login.contains(s))
                return ErrorType.ErrTypes.LOGIN_NOT_ALLOWED;
        }
        /* --------- HASŁO --------- */
        if(password.length()<5)
            return ErrorType.ErrTypes.PASSWORD_TOO_SHORT;
        //  ZNAK ' " '
        int i = 34;
        char c = (char)i;
        String s = "" + c;
        if(password.contains(s))
            return ErrorType.ErrTypes.PASSWORD_CONTAINS_NOT_ALLOWED_CHARACTERS;
        // SPACJA
        s = " ";
        if(password.contains(s))
            return ErrorType.ErrTypes.PASSWORD_CONTAINS_NOT_ALLOWED_CHARACTERS;
        for(i=39;i<48;i++) {
            c = (char)i;
            s = "" + c;
            if(password.contains(s))
                return ErrorType.ErrTypes.PASSWORD_CONTAINS_NOT_ALLOWED_CHARACTERS;
        }
        for(i=58;i<64;i++) {
            c = (char)i;
            s = "" + c;
            if(password.contains(s))
                return ErrorType.ErrTypes.PASSWORD_CONTAINS_NOT_ALLOWED_CHARACTERS;
        }
        //EMAIL
        s = "@";
        String s2 = ".", s3 = ".@", s4 = "@.";

        if(!email.contains(s) || !email.contains(s2))
            return ErrorType.ErrTypes.EMAIL_WRONG_INPUT;
        if(email.contains(s3) || email.contains(s4))
            return ErrorType.ErrTypes.EMAIL_WRONG_INPUT;
        return ErrorType.ErrTypes.NO_ERRORS;
    }
    private ErrorType.ErrTypes CheckForEmptyInput(String login, String password) {
        if(login.length()==0 && password.length()==0)
            return ErrorType.ErrTypes.EMPTY_LOGIN_AND_PASSWORD;
        else if(login.length()==0)
            return ErrorType.ErrTypes.EMPTY_LOGIN;
        else if(password.length()==0)
            return ErrorType.ErrTypes.EMPTY_PASSWORD;
        else
            return ErrorType.ErrTypes.NO_ERRORS;
    } // Sprawdzenie czy pola :LOGIN: i :HASŁO: nie są puste; Zwraca false(gdy ktores pole jest puste) i true(w przeciwym wypadku).
    private ErrorType.ErrTypes CheckForEmptyInput(String login, String password, String email) {
        if(login.length()==0 && password.length()==0 && email.length()==0)
            return ErrorType.ErrTypes.EMPTY_LOGIN_PASSWORD_AND_EMAIL;
        if(email.length()==0 && password.length()==0)
            return ErrorType.ErrTypes.EMPTY_PASSWORD_AND_EMAIL;
        else if(login.length()==0)
            return ErrorType.ErrTypes.EMPTY_LOGIN;
        else if(password.length()==0)
            return ErrorType.ErrTypes.EMPTY_PASSWORD;
        else if(email.length()==0)
            return ErrorType.ErrTypes.EMPTY_EMAIL;
        else
            return ErrorType.ErrTypes.NO_ERRORS;
    } // Sprawdzenie czy pola :LOGIN: i :HASŁO: nie są puste; Zwraca false(gdy ktores pole jest puste) i true(w przeciwym wypadku).
    private Connection ConnectToDB() {
        Connection c = null;
        try {
            Class.forName("org.postgresql.Driver");
            //c = DriverManager.getConnection("jdbc:postgresql://ec2-107-22-166-233.compute-1.amazonaws.com:5432/daeiamnmlhnm33?ssl=true&sslfactory=org.postgresql.ssl.NonValidatingFactory","uynyesjiyhkzym","BQjzHKYEp9s9h35H7dBxMWTJkf");
            c = DriverManager.getConnection("jdbc:postgresql://ec2-54-163-238-96.compute-1.amazonaws.com:5432/d67ebkto7j5lvk?ssl=true&sslfactory=org.postgresql.ssl.NonValidatingFactory",
                    "vqaihlhpyfrtzr","6oDG2S_0bHQpioBwVQSJ0jA2Yi");
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        System.out.println("Połączenie z bazą nawiązane! :)");
        return c;
    }
    public ErrorType.ErrTypes RegisterUserInDB(String login, String password, String email) {
        isError.Error_ = CheckUserLogin(login);
        if(isError.Error_ != ErrorType.ErrTypes.NO_ERRORS) // sprawdzenie czy uzytkownik o podanym loginie jest juz w bazie
            return isError.Error_;
        isError.Error_ = CheckUserEmail(email);
        if(isError.Error_ != ErrorType.ErrTypes.NO_ERRORS)
            return isError.Error_;
        else {
            AddNewUser(login, password, email);
            return ErrorType.ErrTypes.NO_ERRORS;
        }
    }
    private void AddNewUser(String login, String password, String email) {
       /* try {
            Statement st = null;
            ResultSet rs = null;
            String stm = "SELECT email FROM uzytkownik WHERE email='"+email+"'";

            st = ActualConnection.createStatement();
            rs = st.executeQuery(stm);
            if(rs.next()) // przejscie do kolejnego wiersza poniewaz standardowo zwracane są informacje z wiersza '0'
                if(rs.getString("email").equals(email))
                    return 13;
        } catch (SQLException ex) {
            Logger lgr = Logger.getLogger(Model.class.getName());
            lgr.log(Level.SEVERE, ex.getMessage(), ex);
            return 999;
        }
        return 0;
        PreparedStatement pst = null;

        try {
            String stm = "INSERT INTO uzytkownik(user_login, user_pw, email) VALUES(?, ?, ?)";
            pst = con.prepareStatement(stm);
            pst.setInt(1, id);
            pst.setString(2, author);
            pst.executeUpdate();

        } catch (SQLException ex) {
            Logger lgr = Logger.getLogger(Prepared.class.getName());
            lgr.log(Level.SEVERE, ex.getMessage(), ex);*/
    }
    private ErrorType.ErrTypes CheckUserEmail(String email) {
        try {
            Statement st = null;
            ResultSet rs = null;
            String stm = "SELECT email FROM uzytkownik WHERE email='"+email+"'";

            st = ActualConnection.createStatement();
            rs = st.executeQuery(stm);
            if(rs.next()) // przejscie do kolejnego wiersza poniewaz standardowo zwracane są informacje z wiersza '0'
                if(rs.getString("email").equals(email))
                    return ErrorType.ErrTypes.EMAIL_ALREADY_EXIST;
        } catch (SQLException ex) {
            Logger lgr = Logger.getLogger(Model.class.getName());
            lgr.log(Level.SEVERE, ex.getMessage(), ex);
            return ErrorType.ErrTypes.UNKNOWN_ERROR;
        }
        return ErrorType.ErrTypes.NO_ERRORS;
    }
    private ErrorType.ErrTypes CheckUserLogin(String login) {
        try {
            Statement st = null;
            ResultSet rs = null;
            String stm = "SELECT user_login FROM uzytkownik WHERE user_login='"+login+"'";

            st = ActualConnection.createStatement();
            rs = st.executeQuery(stm);
            if(rs.next()) // przejscie do kolejnego wiersza poniewaz standardowo zwracane są informacje z wiersza '0'
                if(rs.getString("user_login").equals(login))
                    return ErrorType.ErrTypes.USER_ALREADY_EXIST;
        } catch (SQLException ex) {
            Logger lgr = Logger.getLogger(Model.class.getName());
            lgr.log(Level.SEVERE, ex.getMessage(), ex);
            return ErrorType.ErrTypes.UNKNOWN_ERROR;
        }
        return ErrorType.ErrTypes.NO_ERRORS;
    }
}
