package com.company.Model;

import com.company.ErrorType;
import com.company.Model.UsersService.UserDelete;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by Jan on 2015-05-27.
 */
public class AdminService {
    private Connection ActualConnection;
    public AdminService(Connection con){
        this.ActualConnection = con;
    }
    public ResultSet getUsers() {
        try {
            System.out.println("Pobieram liste uzytkownikow z bazy danych...");
            ResultSet rs = null;
            String sql_query = "SELECT * FROM uzytkownik";
            PreparedStatement prepStmt = ActualConnection.prepareStatement(sql_query);
            rs = prepStmt.executeQuery();
            return rs;
        } catch (SQLException ex) {
            Logger lgr = Logger.getLogger(Model.class.getName());
            lgr.log(Level.SEVERE, ex.getMessage(), ex);
        }
        ResultSet rs = null;
        return rs;
    }
    public void DeleteUser(String UserID){
        UserDelete.MarkUser(UserID, ActualConnection);
    }

}
