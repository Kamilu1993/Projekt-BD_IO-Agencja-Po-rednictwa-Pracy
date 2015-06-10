package com.company.Model.CVService;

import com.company.ErrorType;
import com.company.Model.Model;
import com.company.Model.PasswordService;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

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
                                                   ArrayList<JTextField> CoursesList)
    {
        //ErrorType er = new ErrorType();
        if(EmptyBasicInfo(BasicList) == ErrorType.ErrTypes.BASIC_REQUIRED_FIELDS_EMPTY)
            return ErrorType.ErrTypes.BASIC_REQUIRED_FIELDS_EMPTY;
        else if(EmptyBasicInfo(BasicList) == ErrorType.ErrTypes.WRONG_DATE_FORMAT)
            return ErrorType.ErrTypes.WRONG_DATE_FORMAT;
        else if (EmptyEducationInfo(EducationList) == ErrorType.ErrTypes.EDUCATION_REQUIRED_FIELDS_EMPTY)
            return ErrorType.ErrTypes.EDUCATION_REQUIRED_FIELDS_EMPTY;
        else if(EmptyEducationInfo(EducationList) == ErrorType.ErrTypes.WRONG_DATE_FORMAT)
            return ErrorType.ErrTypes.WRONG_DATE_FORMAT;
        else if(EmptyExperienceInfo(ExperienceList) == ErrorType.ErrTypes.EXPERIENCE_REQUIRED_FIELDS_EMPTY)
            return ErrorType.ErrTypes.EXPERIENCE_REQUIRED_FIELDS_EMPTY;
        else if(EmptyExperienceInfo(ExperienceList) == ErrorType.ErrTypes.WRONG_DATE_FORMAT)
            return ErrorType.ErrTypes.WRONG_DATE_FORMAT;
        else if(EmptySkillsInfo(SkillsList) == ErrorType.ErrTypes.EXPERIENCE_REQUIRED_FIELDS_EMPTY)
            return ErrorType.ErrTypes.SKILLS_REQUIRED_FIELDS_EMPTY;
        else if(EmptyCoursesInfo(CoursesList) == ErrorType.ErrTypes.COURSES_REQUIRED_FIELDS_EMPTY)
            return ErrorType.ErrTypes.COURSES_REQUIRED_FIELDS_EMPTY;
        else if(EmptyCoursesInfo(CoursesList) == ErrorType.ErrTypes.WRONG_DATE_FORMAT)
            return ErrorType.ErrTypes.WRONG_DATE_FORMAT;
        else
            return ErrorType.ErrTypes.NO_ERRORS;
    }
    //endregion
    //region Dodawanie CV - DANE PODSTAWOWE
    public static ErrorType.ErrTypes EmptyBasicInfo(ArrayList<JTextField> BasicList){
        Date date = null;
        for(int i=0;i<BasicList.size();i++){
            JTextField text = BasicList.get(i);
            if(text.getText().equals("") && i<7)
                return ErrorType.ErrTypes.BASIC_REQUIRED_FIELDS_EMPTY;
            }
        if(BasicList.get(2).getText().length()>0) {
            try {
                SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
                date = df.parse(BasicList.get(2).getText());
            } catch (Exception e) {
                System.out.println(e);
                return ErrorType.ErrTypes.WRONG_DATE_FORMAT;
            }
        }
        if(date.toString().equals("Thu Nov 15 00:00:00 CET 14"))
            return ErrorType.ErrTypes.WRONG_DATE_FORMAT;
        return ErrorType.ErrTypes.NO_ERRORS;
    }
    //endregion
    //region Dodawanie CV - EDUKACJA
    public static ErrorType.ErrTypes EmptyEducationInfo(ArrayList<JTextField> EducationList){
        for(int i=0;i<EducationList.size();i++){
            if(i%6==0) {
                if (EducationList.get(i).getText().equals("") || EducationList.get(i + 1).getText().equals("")){
                    for(int j=i+2;j<i+6;j++) {
                        if (EducationList.get(j).getText().length() > 0)
                            return ErrorType.ErrTypes.EDUCATION_REQUIRED_FIELDS_EMPTY;
                    }
                }
                if(EducationList.get(i+4).getText().length()>0){
                    try{
                        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
                        Date date = null;
                        date = df.parse(EducationList.get(i+4).getText());
                    }catch(Exception e) {
                        System.out.println(e);
                        return ErrorType.ErrTypes.WRONG_DATE_FORMAT;
                    }
                }
                else if(EducationList.get(i+5).getText().length()>0){
                    try{
                        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
                        Date date = null;
                        date = df.parse(EducationList.get(i+5).getText());
                    }catch(Exception e) {
                        System.out.println(e);
                        return ErrorType.ErrTypes.WRONG_DATE_FORMAT;
                    }
                }
            }
        }
        return ErrorType.ErrTypes.NO_ERRORS;
    }
    //endregion
    //region Dodawanie CV - DOŚWIADCZENIE
    public static ErrorType.ErrTypes EmptyExperienceInfo(ArrayList<JTextField> ExperienceList){
        for(int i=0;i<ExperienceList.size();i++){
            if(i%5==0) {
                if(ExperienceList.get(i).getText().length()>0 && ExperienceList.get(i + 1).getText().length() ==0)
                    return ErrorType.ErrTypes.EXPERIENCE_REQUIRED_FIELDS_EMPTY;
                if(ExperienceList.get(i).getText().length()==0 && ExperienceList.get(i + 1).getText().length()>0)
                    return ErrorType.ErrTypes.EXPERIENCE_REQUIRED_FIELDS_EMPTY;
                if (ExperienceList.get(i).getText().length()==0 || ExperienceList.get(i + 1).getText().length() ==0){
                    for(int j=i+2;j<i+5;j++)
                        if (ExperienceList.get(j).getText().length() > 0)
                            return ErrorType.ErrTypes.EXPERIENCE_REQUIRED_FIELDS_EMPTY;
                }
                if(ExperienceList.get(i+3).getText().length()>0){
                    try{
                        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
                        Date date = null;
                        date = df.parse(ExperienceList.get(i+3).getText());
                    }catch(Exception e) {
                        System.out.println(e);
                        return ErrorType.ErrTypes.WRONG_DATE_FORMAT;
                    }
                }
                else if(ExperienceList.get(i+4).getText().length()>0){
                    try{
                        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
                        Date date = null;
                        date = df.parse(ExperienceList.get(i+4).getText());
                    }catch(Exception e) {
                        System.out.println(e);
                        return ErrorType.ErrTypes.WRONG_DATE_FORMAT;
                    }
                }

            }
        }
        return ErrorType.ErrTypes.NO_ERRORS;
    }
    //endregion
    //region Dodawanie CV - UMIEJĘTNOŚCI
    public static ErrorType.ErrTypes EmptySkillsInfo(ArrayList<JTextField> SkillsList){
        for(int i=0;i<SkillsList.size();i++){
            if(i%2==0) {
                if (SkillsList.get(i).getText().equals("") && SkillsList.get(i+1).getText().length()>0){
                    return ErrorType.ErrTypes.SKILLS_REQUIRED_FIELDS_EMPTY;
                }
            }
        }
        return ErrorType.ErrTypes.NO_ERRORS;
    }
    //endregion
    //region Dodawanie CV - KURSY
    public static ErrorType.ErrTypes EmptyCoursesInfo(ArrayList<JTextField> CoursesList){
        for(int i=0;i<CoursesList.size();i++){
            if(i%3==0) {
                if (CoursesList.get(i).getText().equals("") && CoursesList.get(i+1).getText().length()>0)
                    return ErrorType.ErrTypes.COURSES_REQUIRED_FIELDS_EMPTY;
                if(CoursesList.get(i + 1).getText().length()>0){
                    try{
                        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
                        Date date = null;
                        date = df.parse(CoursesList.get(i+1).getText());
                    }catch(Exception e) {
                        System.out.println(e);
                        return ErrorType.ErrTypes.WRONG_DATE_FORMAT;
                    }
                }
                else if(CoursesList.get(i+2).getText().length()>0){
                    try{
                        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
                        Date date = null;
                        date = df.parse(CoursesList.get(i+2).getText());
                    }catch(Exception e) {
                        System.out.println(e);
                        return ErrorType.ErrTypes.WRONG_DATE_FORMAT;
                    }
                }
            }
        }
        return ErrorType.ErrTypes.NO_ERRORS;
    }
    //endregion
    public static ErrorType.ErrTypes CheckUserEmail(String email, Connection ActualConnection) {
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
    public static ErrorType.ErrTypes CheckUserEmailInput(String email){
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
}
