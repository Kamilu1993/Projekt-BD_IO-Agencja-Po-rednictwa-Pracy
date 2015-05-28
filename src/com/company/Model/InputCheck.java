package com.company.Model;

import com.company.ErrorType;

import javax.swing.*;
import java.util.ArrayList;

/**
 * Created by Bajtas on 2015-05-24.
 */
public class InputCheck {
    //region LOGIN
    public static ErrorType.ErrTypes EmptyInput(String login, String pass) {
        if(login.length()==0 && pass.length()==0)
            return ErrorType.ErrTypes.EMPTY_LOGIN_AND_PASSWORD;
        else if(login.length()==0)
            return ErrorType.ErrTypes.EMPTY_LOGIN;
        else if(pass.length()==0)
            return ErrorType.ErrTypes.EMPTY_PASSWORD;
        else{
            return ErrorType.ErrTypes.NO_ERRORS;
        }
    } // Sprawdzenie czy pola :LOGIN: i :HASŁO: nie są puste
    //endregion

    //region REJESTRACJA

    public static ErrorType.ErrTypes EmptyInput(String ULogin, String UPassword, String UEmail) {
        if(ULogin.length()==0 && UPassword.length()==0 && UEmail.length()==0)
            return ErrorType.ErrTypes.EMPTY_LOGIN_PASSWORD_AND_EMAIL;
        else if(UEmail.length()==0 && UPassword.length()==0)
            return ErrorType.ErrTypes.EMPTY_PASSWORD_AND_EMAIL;
        else if(UEmail.length()==0 && ULogin.length()==0)
            return ErrorType.ErrTypes.EMPTY_LOGIN_AND_EMAIL;
        else if(ULogin.length()==0)
            return ErrorType.ErrTypes.EMPTY_LOGIN;
        else if(UPassword.length()==0)
            return ErrorType.ErrTypes.EMPTY_PASSWORD;
        else if(UEmail.length()==0)
            return ErrorType.ErrTypes.EMPTY_EMAIL;
        else
            return ErrorType.ErrTypes.NO_ERRORS;
    } // Sprawdzenie czy pola :LOGIN: , :HASŁO: i :EMAIL: nie są puste.
    public static ErrorType.ErrTypes ValidInput(String ULogin, String UPassword, String UEmail){
        // LOGIN
        System.out.println("Sprawdzanie zakazanych znaków w loginie...");
        for(int i=32;i<48;i++) {
            char c = (char)i;
            String s = "" + c;
            if(ULogin.contains(s))
                return ErrorType.ErrTypes.LOGIN_NOT_ALLOWED;
        }
        for(int i=58;i<65;i++) {
            char c = (char)i;
            String s = "" + c;
            if(ULogin.contains(s))
                return ErrorType.ErrTypes.LOGIN_NOT_ALLOWED;
        }
        for(int i=91;i<97;i++) {
            char c = (char)i;
            String s = "" + c;
            if(ULogin.contains(s))
                return ErrorType.ErrTypes.LOGIN_NOT_ALLOWED;
        }
        /* --------- HASŁO --------- */
        System.out.println("Sprawdzanie długości hasła...");
        if(PasswordService.CheckPassLength(UPassword)!= ErrorType.ErrTypes.NO_ERRORS)
            return PasswordService.CheckPassLength(UPassword);
        //EMAIL
        System.out.println("Sprawdzanie poprawności adresu email...");
        String s;
        s = "@";
        String s2 = ".", s3 = ".@", s4 = "@.";

        if(!UEmail.contains(s) || !UEmail.contains(s2))
            return ErrorType.ErrTypes.EMAIL_WRONG_INPUT;
        if(UEmail.contains(s3) || UEmail.contains(s4))
            return ErrorType.ErrTypes.EMAIL_WRONG_INPUT;
        return ErrorType.ErrTypes.NO_ERRORS;
    }
    //endregion

    //region Dodawanie CV
    public static ErrorType.ErrTypes CheckCVInputs(ArrayList<JTextField> BasicList, ArrayList<JTextField> EducationList,
                                                   ArrayList<JTextField> ExperienceList, ArrayList<JTextField> SkillsList,
                                                   ArrayList<JTextField> CoursesList, ArrayList<JTextField> InterestList)
    {
        ErrorType er = new ErrorType();
        er.Error_ = EmptyBasicInfo(BasicList);
        if(er.Error_==ErrorType.ErrTypes.NO_ERRORS){
            er.Error_ = EmptyEducationInfo(EducationList);
            if(er.Error_== ErrorType.ErrTypes.NO_ERRORS){
                return ErrorType.ErrTypes.NO_ERRORS;
            }
            else
                return er.Error_;
        }
        else
            return er.Error_;
    }
    //endregion
    //region Dodawanie CV - DANE PODSTAWOWE
    public static ErrorType.ErrTypes EmptyBasicInfo(ArrayList<JTextField> BasicList){
        for(int i=0;i<BasicList.size();i++){
            JTextField text = BasicList.get(i);
            if(text.getText().equals("") && i<7)
                return ErrorType.ErrTypes.BASIC_REQUIRED_FIELDS_EMPTY;
        }
        return ErrorType.ErrTypes.NO_ERRORS;
    }
    //endregion

    //region Dodawanie CV - EDUKACJA
    public static ErrorType.ErrTypes EmptyEducationInfo(ArrayList<JTextField> EducationList){
        for(int i=0;i<EducationList.size();i++){
            if(i%6==0) {
                if (EducationList.get(i).getText().equals("") || EducationList.get(i + 1).getText().equals("")){
                    for(int j=i+2;j<i+6;j++)
                        if (EducationList.get(j).getText().length() > 0)
                            return ErrorType.ErrTypes.EDUCATION_REQUIRED_FIELDS_EMPTY;
                }


            }
        }
        return ErrorType.ErrTypes.NO_ERRORS;
    }
    //endregion
}
