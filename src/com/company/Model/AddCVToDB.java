package com.company.Model;

import com.company.ErrorType;

import javax.swing.*;
import java.sql.Connection;
import java.util.ArrayList;

/**
 * Created by Bajtas on 2015-05-24.
 */
public class AddCVToDB {
    private static Connection ActualConnection;
    private static String ULogin;

    public static void Prep(String login, Connection con){
        ULogin = login;
        ActualConnection = con;
    }
    //region DANE PODSTAWOWE
    public static ErrorType.ErrTypes SaveBasic(ArrayList<JTextField> BasicList){
        System.out.println("Sprawdzanie danych z pola: DANE PODSTAWOWE");
        if(InputCheck.EmptyBasicInfo(BasicList)!=ErrorType.ErrTypes.NO_ERRORS)
            return ErrorType.ErrTypes.BASIC_REQUIRED_FIELDS_EMPTY;
        else
            return ErrorType.ErrTypes.NO_ERRORS;
    }
    //endregion
    public static ErrorType.ErrTypes SaveEducation(ArrayList<JTextField> EducationList){
        System.out.println("Sprawdzanie danych z pola: EDUKACJA");
        if(InputCheck.EmptyEducationInfo(EducationList)!=ErrorType.ErrTypes.NO_ERRORS)
            return ErrorType.ErrTypes.EDUCATION_REQUIRED_FIELDS_EMPTY;
        else
            return ErrorType.ErrTypes.NO_ERRORS;
    }
}

