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
public class CustomerService {
    private Connection ActualConnection;
    private String user_login;
    private ErrorType isError = new ErrorType();
    public CustomerService(Connection con, String username) {
        ActualConnection = con;
        user_login=username;
    }
    public ErrorType.ErrTypes AccountOptions_ChangePass(String OldPass, String NewPass, String NewPass2){
        String user_pass_in_db="";
        try {
            ResultSet rs = null;
            String sql_query = "SELECT user_pw FROM uzytkownik WHERE user_login=?";
            PreparedStatement prepStmt = ActualConnection.prepareStatement(sql_query);

            prepStmt.setString(1,user_login);
            rs = prepStmt.executeQuery();
            if(rs.next()) // przejscie do kolejnego wiersza poniewaz standardowo zwracane s¹ informacje z wiersza '0'
                    user_pass_in_db=rs.getString("user_pw");
        } catch (SQLException ex) {
            Logger lgr = Logger.getLogger(CustomerService.class.getName());
            lgr.log(Level.SEVERE, ex.getMessage(), ex);
            return ErrorType.ErrTypes.UNKNOWN_ERROR;
        }
        if(!user_pass_in_db.equals("")) {
            PasswordService haslo = new PasswordService();
            try {
                haslo.CheckPassword(OldPass, user_pass_in_db);
                PasswordService pascheck = new PasswordService();
                isError.Error_ = pascheck.CheckPassLength(NewPass);
                if(pascheck.CheckPassLength(NewPass)!= ErrorType.ErrTypes.NO_ERRORS)
                    return isError.Error_;
                if(pascheck.CheckPassMatch(NewPass, NewPass2)!= ErrorType.ErrTypes.NO_ERRORS)
                    return ErrorType.ErrTypes.PASSWORD_DOESNT_MATCH;
                else{
                    String sql_query = "UPDATE `uzytkownik` SET `user_pw`='"+NewPass+"' WHERE `user_pw`='"+OldPass+"';";
                    PreparedStatement prepStmt = ActualConnection.prepareStatement(sql_query);
                }
            } catch (Exception e){
                return ErrorType.ErrTypes.WRONG_PASSWORD;
            }

        }
        return ErrorType.ErrTypes.NO_ERRORS;


    }
}
