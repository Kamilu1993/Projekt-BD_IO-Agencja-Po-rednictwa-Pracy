package com.company.Model.CVService.CVEntities;

import java.sql.Date;

/**
 * Created by Bajtas on 2015-05-26.
 */
public class CoursesEntity {
    private String Name;
    private Date StartDate;
    private Date EndDate;
    public void setName(String Name){
        this.Name = Name;
    }
    public void setStartDate(String StartDate){
        if(StartDate.length()>0)
            this.StartDate=java.sql.Date.valueOf(StartDate);
    }
    public void setEndDate(String EndDate){
        if(EndDate.length()>0)
            this.EndDate = java.sql.Date.valueOf(EndDate);
    }
    public String getName(){
        return this.Name;
    }
    public Date getStartDate(){
        return this.StartDate;
    }
    public Date getEndDate(){
        return this.EndDate;
    }
}
