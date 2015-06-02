package com.company.Model;

import com.company.ErrorType;

import java.sql.*;
import java.util.logging.Logger;
import java.util.logging.Level;

public class Model {
    private boolean isConnected = false;
    private Connection ActualConnection;
    private ErrorType isError = new ErrorType();
    //------------------------------------------------------------------------------------------//
    public boolean TryToConnect() {
        System.out.println("Trwa nawiązywanie połączenia");
        Connection temp = ConnectToDB();
        if(temp!=null)
        {
            System.out.println("Nawiązanie połączenia zakończone powodzeniem!");
            ActualConnection = temp;
            return true;
        }
        System.out.println("Wystąpił błąd połączenia z bazą danych!");
        return false;
    } // Ustanowienie połączenia z bazą danych...
    public ErrorType.ErrTypes CheckInput(String login, String password, String email) {

        //isError.Error_ = CheckForEmptyInput(login, password, email);
        if(isError.Error_ == ErrorType.ErrTypes.NO_ERRORS)
            return CheckForValidInput(login,password,email);
        else {
            System.out.println("Błąd!");
            return isError.Error_;
        }
    }
    private ErrorType.ErrTypes CheckForValidInput(String login, String password, String email) {
        // LOGIN
        System.out.println("Sprawdzanie zakazanych znaków w loginie...");
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
        System.out.println("Sprawdzanie długości hasła...");
        PasswordService pascheck = new PasswordService();
        isError.Error_ = pascheck.CheckPassLength(password);
        if(pascheck.CheckPassLength(password)!= ErrorType.ErrTypes.NO_ERRORS)
            return isError.Error_;
        //EMAIL
        System.out.println("Sprawdzanie poprawności adresu email...");
        String s;
        s = "@";
        String s2 = ".", s3 = ".@", s4 = "@.";

        if(!email.contains(s) || !email.contains(s2))
            return ErrorType.ErrTypes.EMAIL_WRONG_INPUT;
        if(email.contains(s3) || email.contains(s4))
            return ErrorType.ErrTypes.EMAIL_WRONG_INPUT;
        System.out.println("Testy zakończone powodzeniem, rozpoczynam rejestracje...");
        return ErrorType.ErrTypes.NO_ERRORS;
    }
    private Connection ConnectToDB() {
        Connection c = null;
        try {
            System.out.println("Wczytuje sterownik JDBC...");
            Class.forName("org.postgresql.Driver");
            c = DriverManager.getConnection("jdbc:postgresql://ec2-54-163-238-96.compute-1.amazonaws.com:5432/d67ebkto7j5lvk?ssl=true&sslfactory=org.postgresql.ssl.NonValidatingFactory",
                    "vqaihlhpyfrtzr","6oDG2S_0bHQpioBwVQSJ0jA2Yi");
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        System.out.println("Połączenie z bazą nawiązane! :)");
        return c;
    } // Połączenie z bazą danych
    public ErrorType.ErrTypes RegisterUserInDB(String login, String password, String email) {
        System.out.println("Rozpoczynam sprawdzanie zawartości pól...");
        isError.Error_ = CheckUserLogin(login);
        if(isError.Error_ != ErrorType.ErrTypes.NO_ERRORS) // sprawdzenie czy uzytkownik o podanym loginie jest juz w bazie
            return isError.Error_;
        isError.Error_ = CheckUserEmail(email);
        if(isError.Error_ != ErrorType.ErrTypes.NO_ERRORS)
            return isError.Error_;
        else {
            System.out.println("Wszystko się zgadza, rozpoczynam rejestracje użytkownika: "+login);
            AddNewUser(login, password, email);
            return ErrorType.ErrTypes.NO_ERRORS;
        }
    } // Klasa rozpoczynająca sprawdzanie poprawności wprowadzonych danych
    private void AddNewUser(String login, String password, String email) {
        try {
            System.out.println("Trwa dodawanie użytkownika: "+login+" do bazy danych...");
            PreparedStatement pst = null;
            ResultSet rs = null;
            String sql_query = "INSERT INTO uzytkownik(user_login, user_pw, email) VALUES(?, ?, ?)";


            pst = ActualConnection.prepareStatement(sql_query);
            pst.setString(1, login);
            pst.setString(2, password);
            pst.setString(3, email);
            pst.executeUpdate();

            /* Nowo zarejestrowani użytkownicy mają role 'klientów'
            * DODANIE NOWEGO UŻYTKOWNIKA DO TABELI KLIENCI */
            int user_id=0;
            sql_query = "SELECT user_id FROM uzytkownik WHERE user_login=?"; // pobranie id_uzytkownika
            pst = ActualConnection.prepareStatement(sql_query);
            pst.setString(1, login);
            rs = pst.executeQuery();
            if(rs.next())
                user_id = rs.getInt("user_id");
            sql_query = "INSERT INTO klient(user_id) VALUES(?)";
            pst = ActualConnection.prepareStatement(sql_query);
            pst.setInt(1, user_id);
            pst.executeUpdate();
            System.out.println("Użytkownik: "+login+" ID: "+user_id);
        } catch (SQLException ex) {
            Logger lgr = Logger.getLogger(Model.class.getName());
            lgr.log(Level.SEVERE, ex.getMessage(), ex);
        }
    } // Dodanie użytkownika do bazy danych
    private ErrorType.ErrTypes CheckUserEmail(String email) {
        try {
            System.out.println("Sprawdzam czy email: "+email+" istnieje już w bazie...");
            ResultSet rs = null;
            String sql_query = "SELECT email FROM uzytkownik WHERE email=?";
            PreparedStatement prepStmt = ActualConnection.prepareStatement(sql_query);

            prepStmt.setString(1,email);
            rs = prepStmt.executeQuery();
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
            System.out.println("Sprawdzam czy login: "+login+" istnieje już w bazie...");
            ResultSet rs = null;
            String sql_query = "SELECT user_login FROM uzytkownik WHERE user_login=?";
            PreparedStatement prepStmt = ActualConnection.prepareStatement(sql_query);

            prepStmt.setString(1,login);
            rs = prepStmt.executeQuery();
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
    public ErrorType.ErrTypes CheckUserRole(String login) {
        System.out.println("Sprawdzam typ logującego się użytkownika -> "+login);
        UserRole WhoIsThis = new UserRole();
        return WhoIsThis.CheckUserGroup(login, ActualConnection);
    } // sprawdzenie typu konta użytkownika
    public String GetUserPasswordFromDB(String login) {
        try {
            ResultSet rs = null;
            String sql_query = "SELECT user_pw FROM uzytkownik WHERE user_login=?";
            PreparedStatement prepStmt = ActualConnection.prepareStatement(sql_query);

            prepStmt.setString(1,login);
            rs = prepStmt.executeQuery();
            rs.next(); // przejscie do kolejnego wiersza poniewaz standardowo zwracane są informacje z wiersza '0'
            return rs.getString("user_pw");
        } catch (SQLException ex) {
            Logger lgr = Logger.getLogger(Model.class.getName());
            lgr.log(Level.SEVERE, ex.getMessage(), ex);
        }
        return "";
    } // pobranie hasła z bazy danych
    public Connection GetConnection()    {
        return ActualConnection;
    }
}
