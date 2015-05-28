package com.company.Model.CVService.CVEntities;

/**
 * Created by Bajtas on 2015-05-26.
 */
public class EducationEntity {
    private String Name; // nazwa
    private String Address; // adres
    private String Subject; // kierunek
    private String Specialization; // specjalizacja
    private String StartDate;
    private String EndDate;
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
    public String getSubject(){
        return this.Subject;
    }
    public String getSpecialization(){
        return this.Specialization;
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
