package com.company.Model.CVService;


import com.company.ErrorType;
import com.company.Model.CVService.CVEntities.*;
import com.company.View.ShowMessage;

import javax.swing.*;
import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by Bajtas on 2015-05-24.
 */
public class CVService {
    private static Connection ActualConnection;
    private static String ULogin;
    private BasicInfoEntity BasicRecord = new BasicInfoEntity();
    private EducationEntity EducationRecord = new EducationEntity();
    private ExperienceEntity ExperienceRecord = new ExperienceEntity();
    private SkillsEntity SkillsRecord = new SkillsEntity();
    private CoursesEntity CoursesRecord = new CoursesEntity();
    private InterestEntity InterestRecord = new InterestEntity();
    private ArrayList<InterestEntity> InterestTable = new ArrayList<InterestEntity>();
    private ArrayList<EducationEntity> EducationTable = new ArrayList<EducationEntity>();
    private ArrayList<ExperienceEntity> ExperienceTable = new ArrayList<ExperienceEntity>();
    private ArrayList<SkillsEntity> SkillsTable = new ArrayList<SkillsEntity>();
    private ArrayList<CoursesEntity> CoursesTable = new ArrayList<CoursesEntity>();

    private int CV_Id;
    private int BasicInfoID;
    private int EducationID;
    private int ExperienceID;

    public static void Prep(String login, Connection con){
        ULogin = login;
        ActualConnection = con;
    }
    //region ZAPISZ WSZYSTKO
    public void SaveAll(ArrayList<JTextField> BasicList, ArrayList<JTextField> EducationList,
                               ArrayList<JTextField> ExperienceList, ArrayList<JTextField> SkillsList,
                               ArrayList<JTextField> CoursesList, ArrayList<JTextField> InterestList, byte[] photo){
        System.out.println("-------------DANE PODSTAWOWE---------------");
        SaveBasic(BasicList);
        System.out.println("----------------------EDUKACJA--------------");
        SaveEducation(EducationList);
        System.out.println("----------------------DOŚWIADCZENIE ZAWODOWE--------------");
        SaveExperience(ExperienceList);
        System.out.println("----------------------UMIEJĘTNOŚCI--------------");
        SaveSkills(SkillsList);
        System.out.println("----------------------KURSY--------------");
        SaveCourses(CoursesList);
        System.out.println("---------------------ZAINTERESOWANIA--------------");
        SaveInterest(InterestList);
        System.out.println("Zapisywanie zdjecia....");
        BasicInfoEntity.setPhoto(photo);
    }
    //endregion
    //region ZAPISANIE DANYCH PODSTAWOWYCH
    public void SaveBasic(ArrayList<JTextField> BasicList){
        BasicRecord.setName(BasicList.get(0).getText());
        System.out.println("Imię: " + BasicRecord.getName());
        BasicRecord.setSurname(BasicList.get(1).getText());
        System.out.println("Nazwisko: "+BasicRecord.getSurname());
        BasicRecord.setBirthDate(BasicList.get(2).getText());
        System.out.println("Data ur.: "+BasicRecord.getBirthDate());
        BasicRecord.setAddress(BasicList.get(3).getText());
        System.out.println("Adres: "+BasicRecord.getAddress());
        BasicRecord.setCity(BasicList.get(4).getText());
        System.out.println("Miejscowość: "+BasicRecord.getCity());
        BasicRecord.setPostalCode(BasicList.get(5).getText());
        System.out.println("Kod pocztowy: "+BasicRecord.getPostalCode());
        BasicRecord.setTelephone(BasicList.get(6).getText());
        System.out.println("Nr kom: "+BasicRecord.getTelephone());
        BasicRecord.setEmail(BasicList.get(7).getText());
        System.out.println("Email: "+BasicRecord.getEmail());
    }
    //endregion
    //region ZAPISANIE EDUKACJI
    public void SaveEducation(ArrayList<JTextField> EducationList){
        for(int i=0;i<EducationList.size();i++) {
            EducationRecord.setName(EducationList.get(i).getText());
            System.out.println("Nazwa: " + EducationRecord.getName());
            EducationRecord.setAddress(EducationList.get(i + 1).getText());
            System.out.println("Adres: " + EducationRecord.getAddress());
            EducationRecord.setSubject(EducationList.get(i + 2).getText());
            System.out.println("Kierunek: " + EducationRecord.getSubject());
            EducationRecord.setSpecialization(EducationList.get(i + 3).getText());
            System.out.println("Specjalizacja: " + EducationRecord.getSpecialization());
            EducationRecord.setStartDate(EducationList.get(i + 4).getText());
            EducationRecord.setEndDate(EducationList.get(i + 5).getText());
            System.out.println("Data rozpoczęcia: " + EducationRecord.getStartDate());
            System.out.println("Data zakończenia: " + EducationRecord.getEndDate());
            if(EducationList.get(i).getText().length()>0 && EducationList.get(i+1).getText().length()>0)
                EducationTable.add(EducationRecord);
            System.out.println("------------------------------------------");
            i+=5;
        }
    }
    //endregion
    //region ZAPISANIE DOŚWIADCZENIA ZAWODOWEGO
    public void SaveExperience(ArrayList<JTextField> ExperienceList){
        for(int i=0;i<ExperienceList.size();i++) {
            ExperienceRecord.setName(ExperienceList.get(i).getText());
            System.out.println("Nazwa: " + ExperienceRecord.getName());
            ExperienceRecord.setAddress(ExperienceList.get(i + 1).getText());
            System.out.println("Adres: " + ExperienceRecord.getAddress());
            ExperienceRecord.setPosition(ExperienceList.get(i + 2).getText());
            System.out.println("Stanowisko: " + ExperienceRecord.getPosition());
            if(ExperienceList.get(i + 3).getText().length()>0)
                ExperienceRecord.setStartDate(ExperienceList.get(i + 3).getText());
            System.out.println("Data rozpoczęcia: " + ExperienceRecord.getStartDate());
            if(ExperienceList.get(i + 4).getText().length()>0)
                ExperienceRecord.setEndDate(ExperienceList.get(i + 4).getText());
            System.out.println("Data zakończenia: " + ExperienceRecord.getEndDate());
            if(ExperienceList.get(i).getText().length()>0 && ExperienceList.get(i+1).getText().length()>0)
                ExperienceTable.add(ExperienceRecord);
            System.out.println("-------------------------------------------------");
            i+=4;
        }
    }
    //endregion
    //region ZAPISANIE UMIEJĘTNOŚCI
    public void SaveSkills(ArrayList<JTextField> SkillsList){
        for(int i=0;i<SkillsList.size();i++) {
            SkillsRecord.setName(SkillsList.get(i).getText());
            System.out.println("Nazwa: " + SkillsRecord.getName());
            SkillsRecord.setGrade(SkillsList.get(i + 1).getText());
            System.out.println("Stopień zaawansowania: " + SkillsRecord.getGrade());
            SkillsTable.add(SkillsRecord);
            System.out.println("-------------------------------------------------");
            i+=1;
        }
    }
    //endregion
    //region ZAPISANIE KURSÓW
    public void SaveCourses(ArrayList<JTextField> CoursesList){
        for(int i=0;i<CoursesList.size();i++) {
            CoursesRecord.setName(CoursesList.get(i).getText());
            System.out.println("Nazwa: " + CoursesRecord.getName());
            CoursesRecord.setStartDate(CoursesList.get(i + 1).getText());
            System.out.println("Data rozpoczęcia: " + CoursesRecord.getStartDate());
            CoursesRecord.setEndDate(CoursesList.get(i + 2).getText());
            System.out.println("Data zakończenia: "+CoursesRecord.getEndDate());
            CoursesTable.add(CoursesRecord);
            System.out.println("-------------------------------------------------");
            i+=2;
        }
    }
    //endregion
    //region ZAPISANIE ZAINTERESOWAŃ
    public void SaveInterest(ArrayList<JTextField> InterestList){
        for(int i=0;i<InterestList.size();i++) {
            if(InterestList.get(i).getText().length()>0) {
                InterestRecord.setName(InterestList.get(i).getText());
                System.out.println("Nazwa: " + InterestRecord.getName());
                InterestTable.add(InterestRecord);
                System.out.println("-------------------------------------------------");
            }
        }
    }
    //endregion
    //----------------------------------------------------------------------------------------
    private void CreateCV() {
        int UId = 0;
        try {
            System.out.println("Trwa dodawanie...");
            PreparedStatement pst = null;
            ResultSet rs = null;
            String sql_query = "SELECT id_klient FROM klient, uzytkownik WHERE user_login = ? AND klient.user_id =uzytkownik.user_id";
            pst = ActualConnection.prepareStatement(sql_query);
            pst.setString(1, ULogin);
            rs = pst.executeQuery();
            if (rs.next())
                UId = rs.getInt(1);
            sql_query = "INSERT INTO cv(id_klient, id_dane_podst, data_utworzenia) VALUES(?, ?, ?)";
            pst = ActualConnection.prepareStatement(sql_query, PreparedStatement.RETURN_GENERATED_KEYS);
            pst.setInt(1, UId);
            pst.setInt(2, BasicInfoID);
            java.sql.Timestamp  sqlDate = new java.sql.Timestamp(new java.util.Date().getTime());
            pst.setTimestamp(3, sqlDate);
            System.out.println("Data utworzenia CV: "+new Timestamp(System.currentTimeMillis()));
            pst.execute();
            rs = pst.getGeneratedKeys();
            if(rs.next())
                CV_Id = rs.getInt(1);
        }catch (SQLException ex) {
            Logger lgr = Logger.getLogger(CVService.class.getName());
            lgr.log(Level.SEVERE, ex.getMessage(), ex);
        }
    }
    public void AddAll(){
        AddBasic(BasicRecord);
        CreateCV();
        AddEducation(EducationTable);
        AddExperience(ExperienceTable);
    }
    //region DODANIE DO BAZY - DANE PODSTAWOWE
    public void AddBasic(BasicInfoEntity Table){
        try {
            System.out.println("Trwa dodawanie...");
            PreparedStatement pst = null;
            ResultSet rs = null;
            String sql_query = "INSERT INTO dane_podstawowe(imie, nazwisko, data_urodzenia, nr_tel, adres," +
                    "miejscowosc,kod_pocztowy,zdjecie,email) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?)";
            pst = ActualConnection.prepareStatement(sql_query,PreparedStatement.RETURN_GENERATED_KEYS);
            pst.setString(1, Table.getName());
            pst.setString(2, Table.getSurname());
            pst.setDate(3, Table.getBirthDate());
            pst.setString(4, Table.getTelephone());
            pst.setString(5, Table.getAddress());
            pst.setString(6, Table.getCity());
            pst.setString(7, Table.getPostalCode());
            if(BasicInfoEntity.getPhoto()!=null)
                pst.setBytes(8, BasicInfoEntity.getPhoto());
            else
                pst.setBytes(8, null);
            pst.setString(9, Table.getEmail());
            pst.executeUpdate();
            rs = pst.getGeneratedKeys();
            try {
                if (rs.next()) {
                   System.out.println("Dodano rekord do tabeli dane podstawowe o ID: " + rs.getLong(1));
                    BasicInfoID = rs.getInt(1);
                }

            }catch (SQLException ex) {
                Logger lgr = Logger.getLogger(CVService.class.getName());
                lgr.log(Level.SEVERE, ex.getMessage(), ex);
            }
            } catch (SQLException ex) {
            Logger lgr = Logger.getLogger(CVService.class.getName());
            lgr.log(Level.SEVERE, ex.getMessage(), ex);
        }
    }
    //endregion
    //region DODANIE DO BAZY - EDUKACJA
    public void AddEducation(ArrayList<EducationEntity> Table) {
        for (int i = 0; i < Table.size(); i++) {
            try {
                System.out.println("Trwa dodawanie...");
                PreparedStatement pst = null;
                ResultSet rs = null;
                String sql_query = "INSERT INTO wyksztalcenie(nazwa_uczelni, adres_uczelni, data_rozpoczecia, " +
                        "data_zakonczenia, kierunek, specjalizacja)" + " VALUES(?, ?, ?, ?, ?, ?)";
                pst = ActualConnection.prepareStatement(sql_query, PreparedStatement.RETURN_GENERATED_KEYS);
                pst.setString(1, Table.get(i).getName());
                pst.setString(2, Table.get(i).getAddress());
                pst.setDate(3, Table.get(i).getStartDate());
                pst.setDate(4, Table.get(i).getEndDate());

                if(Table.get(i).getSubject().length()>0)
                    pst.setString(5, Table.get(i).getSubject());
                else
                    pst.setString(5, null);

                if(Table.get(i).getSpecialization().length()>0)
                    pst.setString(6, Table.get(i).getSpecialization());
                else
                    pst.setString(6, null);

                pst.executeUpdate();
                rs = pst.getGeneratedKeys();
                try {
                    if (rs.next()) {
                        System.out.println("Dodano rekord do tabeli wyksztalcenie o ID: " + rs.getLong(1));
                        EducationID = rs.getInt(1);

                        sql_query = "INSERT INTO wiersz_wyksztalcenie(id_wyksztalcenie, id_cv)" + " VALUES(?, ?)";
                        pst = ActualConnection.prepareStatement(sql_query);
                        pst.setInt(1, EducationID);
                        pst.setInt(2, CV_Id);
                        pst.execute();
                    }
                } catch (SQLException ex) {
                    Logger lgr = Logger.getLogger(CVService.class.getName());
                    lgr.log(Level.SEVERE, ex.getMessage(), ex);
                }
            } catch (SQLException ex) {
                Logger lgr = Logger.getLogger(CVService.class.getName());
                lgr.log(Level.SEVERE, ex.getMessage(), ex);
            }
        }
    }
    //endregion
    public void AddExperience(ArrayList<ExperienceEntity> Table) {
        for (int i = 0; i < Table.size(); i++) {
            try {
                System.out.println("Trwa dodawanie...");
                PreparedStatement pst = null;
                ResultSet rs = null;
                String sql_query = "INSERT INTO doswiadczenie_zawodowe(nazwa_pracodawcy, adres_pracodawcy, data_rozpoczecia, " +
                        "data_zakonczenia, stanowisko)" + " VALUES(?, ?, ?, ?, ?)";
                pst = ActualConnection.prepareStatement(sql_query, PreparedStatement.RETURN_GENERATED_KEYS);
                pst.setString(1, Table.get(i).getName());
                pst.setString(2, Table.get(i).getAddress());
                pst.setDate(3, Table.get(i).getStartDate());
                pst.setDate(4, Table.get(i).getEndDate());

                if (Table.get(i).getPosition().length()>0)
                    pst.setString(5, Table.get(i).getPosition());
                else
                    pst.setString(5, null);

                pst.executeUpdate();
                rs = pst.getGeneratedKeys();
                try {
                    if (rs.next()) {
                        System.out.println("Dodano rekord do tabeli doswiadczenie zawodowe o ID: " + rs.getLong(1));
                        ExperienceID = rs.getInt(1);
                    }

                } catch (SQLException ex) {
                    Logger lgr = Logger.getLogger(CVService.class.getName());
                    lgr.log(Level.SEVERE, ex.getMessage(), ex);
                }
            } catch (SQLException ex) {
                Logger lgr = Logger.getLogger(CVService.class.getName());
                lgr.log(Level.SEVERE, ex.getMessage(), ex);
            }
        }
    }
}

