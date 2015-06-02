package com.company.Model.CVService;

import com.company.Model.CVService.CVEntities.*;

import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;


/**
 * Created by Bajtas on 2015-05-24.
 */
public class CVService {
    private static Connection ActualConnection;
    private static String ULogin;
    private BasicInfoEntity BasicRecord = new BasicInfoEntity();
    private EducationEntity EducationRecord = new EducationEntity();
    private InterestEntity InterestRecord = new InterestEntity();
    private ArrayList<InterestEntity> InterestTable = new ArrayList<InterestEntity>();
    private ArrayList<EducationEntity> EducationTable = new ArrayList<EducationEntity>();
    private ArrayList<ExperienceEntity> ExperienceTable = new ArrayList<ExperienceEntity>();
    private ArrayList<SkillsEntity> SkillsTable = new ArrayList<SkillsEntity>();
    private ArrayList<CoursesEntity> CoursesTable = new ArrayList<CoursesEntity>();

    private int CV_Id;
    private int BasicInfoID;
    private int EducationID;

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
        System.out.println("Adres: " + BasicRecord.getAddress());
        BasicRecord.setCity(BasicList.get(4).getText());
        System.out.println("Miejscowość: "+BasicRecord.getCity());
        BasicRecord.setPostalCode(BasicList.get(5).getText());
        System.out.println("Kod pocztowy: " + BasicRecord.getPostalCode());
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
            ExperienceEntity ExperienceRecord = new ExperienceEntity();
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
            SkillsEntity record = new SkillsEntity();
            if(SkillsList.get(i).getText().length()>0)
                record.setName(SkillsList.get(i).getText());
            System.out.println("Nazwa: " + record.getName());
            if(SkillsList.get(i + 1).getText().length()>0)
                record.setGrade(SkillsList.get(i + 1).getText());
            System.out.println("Stopień zaawansowania: " + record.getGrade());
            if(record.getName()!=null)
                SkillsTable.add(record);
            System.out.println("-------------------------------------------------");
            i+=1;
        }
    }
    //endregion
    //region ZAPISANIE KURSÓW
    public void SaveCourses(ArrayList<JTextField> CoursesList){
        for(int i=0;i<CoursesList.size();i++) {
            CoursesEntity CoursesRecord = new CoursesEntity();
            CoursesRecord.setName(CoursesList.get(i).getText());
            System.out.println("Nazwa: " + CoursesRecord.getName());
            CoursesRecord.setStartDate(CoursesList.get(i + 1).getText());
            System.out.println("Data rozpoczęcia: " + CoursesRecord.getStartDate());
            CoursesRecord.setEndDate(CoursesList.get(i + 2).getText());
            System.out.println("Data zakończenia: "+CoursesRecord.getEndDate());
            if(CoursesRecord.getName().length()>0)
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
                if(InterestRecord.getName().length()>0)
                    InterestTable.add(InterestRecord);
                System.out.println("-------------------------------------------------");
            }
        }
    }
    //endregion
    //----------------------------------------------------------------------------------------
    //region DODANIE DO BAZY
    public void AddAll(){
        CVSend.setConnection(ActualConnection);
        CVSend.AddBasic(BasicRecord, ULogin);
        CVSend.AddEducation(EducationTable);
        CVSend.AddExperience(ExperienceTable);
        CVSend.AddSkills(SkillsTable);
        CVSend.AddCourses(CoursesTable);
        CVSend.AddInterest(InterestTable);
    }
    //endregion

    public BasicInfoEntity getBasicInfo(){
        return BasicRecord;
    }
    public ArrayList<EducationEntity> getEducationInfo(){
        return EducationTable;
    }
    public ArrayList<SkillsEntity> getSkillsInfo(){ return SkillsTable;}
    public ArrayList<ExperienceEntity> getExperienceInfo(){ return ExperienceTable;}
    public ArrayList<CoursesEntity> getCoursesInfo() {return CoursesTable;}
    public ArrayList<InterestEntity> getInterestInfo() {return InterestTable;}
}

