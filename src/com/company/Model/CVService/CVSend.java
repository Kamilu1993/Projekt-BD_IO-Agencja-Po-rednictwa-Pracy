package com.company.Model.CVService;

import com.company.Model.CVService.CVEntities.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by Bajtas on 2015-05-29.
 */
public class CVSend {
    static private int BasicInfoID;
    static private int CV_Id;
    static private Connection ActualConnection;
    static public void setConnection(Connection con){
        ActualConnection = con;
    }
    static private void CreateCV(String ULogin) {
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

    //region DODANIE DO BAZY - DANE PODSTAWOWE
    static public void AddBasic(BasicInfoEntity Table, String ULogin){
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
                    CreateCV(ULogin);
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
    static public void AddEducation(ArrayList<EducationEntity> Table) {
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
                        int EducationID = rs.getInt(1);

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
    //region DODANIE DO BAZY - DOŚWIADCZENIE ZAWODOWE
    static public void AddExperience(ArrayList<ExperienceEntity> Table) {
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
                        int ExperienceID = rs.getInt(1);

                        sql_query = "INSERT INTO wiersz_dosw_zawodowe(id_dosw_zawodowe, id_cv)" + " VALUES(?, ?)";
                        pst = ActualConnection.prepareStatement(sql_query);
                        pst.setInt(1, ExperienceID);
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
    //region DODANIE DO BAZY - UMIEJĘTNOŚCI
    static public void AddSkills(ArrayList<SkillsEntity> Table) {
        for (int i = 0; i < Table.size(); i++) {
            try {
                System.out.println("Trwa dodawanie...");
                PreparedStatement pst = null;
                ResultSet rs = null;
                String sql_query = "INSERT INTO umiejetnosc(nazwa_umiejetnosci, stopien_zaawansowania) VALUES(?, ?)";
                pst = ActualConnection.prepareStatement(sql_query, PreparedStatement.RETURN_GENERATED_KEYS);
                pst.setString(1, Table.get(i).getName());
                if (Table.get(i).getGrade()!=null)
                    pst.setString(2, Table.get(i).getGrade());
                else
                    pst.setString(2,null);
                pst.executeUpdate();
                rs = pst.getGeneratedKeys();
                try {
                    if (rs.next()) {
                        System.out.println("Dodano rekord do tabeli umiejetnosci rekord o ID: " + rs.getLong(1));
                        int SkillsID = rs.getInt(1);

                        sql_query = "INSERT INTO wiersz_umiejetnosci(id_umiejetnosc, id_cv)" + " VALUES(?, ?)";
                        pst = ActualConnection.prepareStatement(sql_query);
                        pst.setInt(1, SkillsID);
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
    //region DODANIE DO BAZY - KURSY
    static public void AddCourses(ArrayList<CoursesEntity> Table) {
        for (int i = 0; i < Table.size(); i++) {
            try {
                System.out.println("Trwa dodawanie...");
                PreparedStatement pst = null;
                ResultSet rs = null;
                String sql_query = "INSERT INTO kurs(nazwa_kursu, data_rozpoczecia, data_zakonczenia) VALUES(?, ?, ?)";
                pst = ActualConnection.prepareStatement(sql_query, PreparedStatement.RETURN_GENERATED_KEYS);
                pst.setString(1, Table.get(i).getName());
                if (Table.get(i).getStartDate()!=null)
                    pst.setDate(2, Table.get(i).getStartDate());
                else
                    pst.setDate(2,null);
                if (Table.get(i).getStartDate()!=null)
                    pst.setDate(3, Table.get(i).getEndDate());
                else
                    pst.setDate(3, null);

                pst.executeUpdate();
                rs = pst.getGeneratedKeys();
                try {
                    if (rs.next()) {
                        System.out.println("Dodano rekord do tabeli umiejetnosci rekord o ID: " + rs.getLong(1));
                        int CoursesID = rs.getInt(1);

                        sql_query = "INSERT INTO wiersz_kurs(id_kurs, id_cv)" + " VALUES(?, ?)";
                        pst = ActualConnection.prepareStatement(sql_query);
                        pst.setInt(1, CoursesID);
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
    //region DODANIE DO BAZY - ZAINTERESOWANIA
    static public void AddInterest(ArrayList<InterestEntity> Table){
        for (int i = 0; i < Table.size(); i++) {
            try {
                System.out.println("Trwa dodawanie...");
                PreparedStatement pst = null;
                ResultSet rs = null;
                String sql_query = "INSERT INTO zainteresowania(nazwa_zainteresowania) VALUES(?)";
                pst = ActualConnection.prepareStatement(sql_query, PreparedStatement.RETURN_GENERATED_KEYS);
                if(Table.get(i).getName().length()>0)
                    pst.setString(1, Table.get(i).getName());
                else
                    pst.setString(1, null);
                pst.executeUpdate();
                rs = pst.getGeneratedKeys();
                try {
                    if (rs.next()) {
                        System.out.println("Dodano rekord do tabeli zainteresowania rekord o ID: " + rs.getLong(1));
                        int InterestID = rs.getInt(1);

                        sql_query = "INSERT INTO wiersz_zainteresowania(id_cv, id_zainteresowanie)" + " VALUES(?, ?)";
                        pst = ActualConnection.prepareStatement(sql_query);
                        pst.setInt(1, CV_Id);
                        pst.setInt(2, InterestID);
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
}
