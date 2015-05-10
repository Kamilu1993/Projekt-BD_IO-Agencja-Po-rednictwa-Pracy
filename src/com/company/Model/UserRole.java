package com.company.Model;

import com.company.ErrorType;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by Bajtas on 2015-05-10.
 */
public class UserRole {
    private ErrorType isError = new ErrorType();
    public ErrorType.ErrTypes CheckUserGroup(String user_login, Connection ActualConnection)
    {
        Integer user_id = 0;
        try {
            ResultSet rs = null;
            String sql_query = "SELECT user_id FROM uzytkownik WHERE user_login=?";
            PreparedStatement prepStmt = ActualConnection.prepareStatement(sql_query);
            prepStmt.setString(1,user_login);
            rs = prepStmt.executeQuery();
            if(rs.next()) // przejscie do kolejnego wiersza poniewaz standardowo zwracane są informacje z wiersza '0'
                user_id = rs.getInt("user_id");

            // sprawdzenie czy jest to konto klienta
            sql_query = "SELECT user_id FROM klient WHERE user_id=?";
            prepStmt = ActualConnection.prepareStatement(sql_query);
            prepStmt.setInt(1,user_id);
            rs = prepStmt.executeQuery();
            if(rs.next())
                if(rs.getInt("user_id") == user_id)
                    return ErrorType.ErrTypes.THIS_IS_CUSTOMER_ACC;

            // sprawdzenie czy jest to konto pracownika
            sql_query = "SELECT user_id FROM pracownik WHERE user_id=?";
            prepStmt = ActualConnection.prepareStatement(sql_query);
            prepStmt.setInt(1,user_id);
            rs = prepStmt.executeQuery();
            if(rs.next() && rs.getInt("user_id") == user_id)
                return ErrorType.ErrTypes.THIS_IS_EMPLOYEE_ACC;

            // sprawdzenie czy jest to konto administratora
            sql_query = "SELECT user_id FROM administrator WHERE user_id=?";
            prepStmt = ActualConnection.prepareStatement(sql_query);
            prepStmt.setInt(1,user_id);
            rs = prepStmt.executeQuery();
            if(rs.next() && rs.getInt("user_id") == user_id)
                return ErrorType.ErrTypes.THIS_IS_ADMIN_ACC;

        } catch (SQLException ex) {
            Logger lgr = Logger.getLogger(Model.class.getName());
            lgr.log(Level.SEVERE, ex.getMessage(), ex);
            return ErrorType.ErrTypes.UNKNOWN_ERROR;
        }
        return ErrorType.ErrTypes.ERROR_WITH_USER_ROLE;
    }
}
