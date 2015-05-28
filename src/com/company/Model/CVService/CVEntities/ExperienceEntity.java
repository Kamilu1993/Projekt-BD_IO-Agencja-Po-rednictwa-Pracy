package com.company.Model.CVService.CVEntities;

import java.sql.Date;

/**
 * Created by Bajtas on 2015-05-26.
 */
public class ExperienceEntity {
    private String Name; // nazwa
    private String Address; // adres
    private String Position; // stanowisko
    private Date StartDate;
    private Date EndDate;
    public ExperienceEntity(){
        Name = null;
        Address = null;
        Position = null;
        StartDate = null;
        EndDate = null;
    }
    public void setName(String Name){
        this.Name = Name;
    }
    public void setPosition(String Position){
        this.Position = Position;
    }
    public void setStartDate(String StartDate){
        this.StartDate=java.sql.Date.valueOf(StartDate);
    }
    public void setEndDate(String EndDate){
        this.EndDate = java.sql.Date.valueOf(EndDate);
    }
    public void setAddress(String Address){
        this.Address = Address;
    }
    public String getName(){
        return this.Name;
    }
    public String getPosition(){
        return this.Position;
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
