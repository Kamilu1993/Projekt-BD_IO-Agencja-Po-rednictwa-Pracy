package com.company.Model.CVService.CVEntities;

/**
 * Created by Bajtas on 2015-05-26.
 */
public class CoursesEntity {
    private String Name;
    private String StartDate;
    private String EndDate;
    public void setName(String Name){
        this.Name = Name;
    }
    public void setStartDate(String StartDate){
        this.StartDate=StartDate;
    }
    public void setEndDate(String EndDate){
        this.EndDate = EndDate;
    }
    public String getName(){
        return this.Name;
    }
    public String getStartDate(){
        return this.StartDate;
    }
    public String getEndDate(){
        return this.EndDate;
    }
}
