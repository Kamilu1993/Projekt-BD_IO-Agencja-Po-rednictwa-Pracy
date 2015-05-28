package com.company.Model.CVService;


import com.company.Model.CVService.CVEntities.*;
import com.company.Model.InputCheck;

import javax.swing.*;
import java.sql.Connection;
import java.util.ArrayList;

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
    private ArrayList<BasicInfoEntity> BasicInfoTable = new ArrayList<BasicInfoEntity>();
    private ArrayList<EducationEntity> EducationTable = new ArrayList<EducationEntity>();
    private ArrayList<ExperienceEntity> ExperienceTable = new ArrayList<ExperienceEntity>();
    private ArrayList<SkillsEntity> SkillsTable = new ArrayList<SkillsEntity>();
    private ArrayList<CoursesEntity> CoursesTable = new ArrayList<CoursesEntity>();

    public static void Prep(String login, Connection con){
        ULogin = login;
        ActualConnection = con;
    }
    public void SaveAll(ArrayList<JTextField> BasicList, ArrayList<JTextField> EducationList,
                               ArrayList<JTextField> ExperienceList, ArrayList<JTextField> SkillsList,
                               ArrayList<JTextField> CoursesList, ArrayList<JTextField> InterestList){
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
    }
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
        BasicInfoTable.add(BasicRecord);
    }
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
            System.out.println("Data rozpoczęcia: " + EducationRecord.getStartDate());
            EducationRecord.setEndDate(EducationList.get(i + 5).getText());
            System.out.println("Data zakończenia: " + EducationRecord.getEndDate());
            EducationTable.add(EducationRecord);
            System.out.println("------------------------------------------");
            i+=5;
        }
    }
    public void SaveExperience(ArrayList<JTextField> ExperienceList){
        for(int i=0;i<ExperienceList.size();i++) {
            ExperienceRecord.setName(ExperienceList.get(i).getText());
            System.out.println("Nazwa: " + ExperienceRecord.getName());
            ExperienceRecord.setAddress(ExperienceList.get(i + 1).getText());
            System.out.println("Adres: " + ExperienceRecord.getAddress());
            ExperienceRecord.setPosition(ExperienceList.get(i + 2).getText());
            System.out.println("Stanowisko: " + ExperienceRecord.getPosition());
            ExperienceRecord.setStartDate(ExperienceList.get(i + 3).getText());
            System.out.println("Data rozpoczęcia: " + ExperienceRecord.getStartDate());
            ExperienceRecord.setEndDate(ExperienceList.get(i + 4).getText());
            System.out.println("Data zakończenia: " + ExperienceRecord.getEndDate());
            ExperienceTable.add(ExperienceRecord);
            System.out.println("-------------------------------------------------");
            i+=4;
        }
    }
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
    public void SaveInterest(ArrayList<JTextField> InterestList){
        for(int i=0;i<InterestList.size();i++) {
            InterestRecord.setName(InterestList.get(i).getText());
            System.out.println("Nazwa: " + InterestRecord.getName());
            InterestTable.add(InterestRecord);
            System.out.println("-------------------------------------------------");
        }
    }
}

