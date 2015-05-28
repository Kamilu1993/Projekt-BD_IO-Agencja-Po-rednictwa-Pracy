package com.company.Model.CVService.CVEntities;

/**
 * Created by Bajtas on 2015-05-26.
 */
public class ExperienceEntity {
    private String Name; // nazwa
    private String Address; // adres
    private String Position; // stanowisko
    private String StartDate;
    private String EndDate;
    public void setName(String Name){
        this.Name = Name;
    }
    public void setPosition(String Position){
        this.Position = Position;
    }
    public void setStartDate(String StartDate){
        this.StartDate=StartDate;
    }
    public void setEndDate(String EndDate){
        this.EndDate = EndDate;
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
    public String getStartDate(){
        return this.StartDate;
    }
    public String getEndDate(){
        return this.EndDate;
    }
}
