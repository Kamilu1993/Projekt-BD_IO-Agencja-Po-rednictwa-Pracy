package com.company.Model.CVService.CVEntities;

import java.sql.Date;

/**
 * Created by Bajtas on 2015-05-26.
 */
public class EducationEntity {
    private String Name; // nazwa
    private String Address; // adres
    private String Subject; // kierunek
    private String Specialization; // specjalizacja
    private Date StartDate;
    private Date EndDate;
    public EducationEntity(){
        Name = null;
        Address = null;
        Subject = null;
        Specialization = null;
        StartDate = null;
        EndDate = null;
    }
    public void setName(String Name){
        this.Name = Name;
    }
    public void setSubject(String Subject){
        this.Subject = Subject;
    }
    public void setSpecialization(String Specialization){
        this.Specialization = Specialization;
    }
    public void setStartDate(String StartDate){
        if(StartDate.length()>0)
            this.StartDate=java.sql.Date.valueOf(StartDate);
    }
    public void setEndDate(String EndDate){
        if(EndDate.length()>0)
            this.EndDate = java.sql.Date.valueOf(EndDate);
    }
    public void setAddress(String Address){
        this.Address = Address;
    }
    public String getName(){
        return this.Name;
    }
    public String getSubject(){
        return this.Subject;
    }
    public String getSpecialization(){
        return this.Specialization;
    }
    public String getAddress(){
        return this.Address;
    }
    public Date getStartDate(){
        return this.StartDate;
    }
    public Date getEndDate(){
        return this.EndDate;
    }
}
