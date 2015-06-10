package com.company.Model.UsersService;

import com.company.ErrorType;
import com.company.Model.UserRole;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by Jan on 2015-05-28.
 */
public class UserDelete{
    public static void MarkUser(String ID, Connection ActualConnection)
    {
        int iID = Integer.parseInt(ID);
        try {
            System.out.println("Sprawdzam czy uzytkownik o ID: " + ID + " istnieje już w bazie...");
            ResultSet rs = null;
            PreparedStatement prepStmt = ActualConnection.prepareStatement("SELECT user_login FROM uzytkownik WHERE user_id = ?");
            prepStmt.setInt(1, iID);
            rs = prepStmt.executeQuery();
            if (rs.next())
            {
                UserRole role = new UserRole();
                if(role.CheckUserGroup(rs.getString(1), ActualConnection) == ErrorType.ErrTypes.THIS_IS_CUSTOMER_ACC){
                    try {
                        rs = null;
                        prepStmt = ActualConnection.prepareStatement("DELETE FROM klient WHERE user_id = ?");
                        prepStmt.setInt(1, iID);
                        prepStmt.execute();
                        prepStmt = ActualConnection.prepareStatement("DELETE FROM uzytkownik WHERE user_id = ?");
                        prepStmt.setInt(1, iID);
                        prepStmt.execute();
                    }catch (SQLException ex) {
                        Logger lgr = Logger.getLogger(UserDelete.class.getName());
                        lgr.log(Level.SEVERE, ex.getMessage(), ex);
                    }
                }

            }
        }
        catch (SQLException ex) {
            Logger lgr = Logger.getLogger(UserDelete.class.getName());
            lgr.log(Level.SEVERE, ex.getMessage(), ex);
        }

    }
}
