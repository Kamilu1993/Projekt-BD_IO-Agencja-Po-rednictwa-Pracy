package com.company.Model.CVService.CVEntities;

/**
 * Created by Bajtas on 2015-05-26.
 */
public class SkillsEntity {
    private String Name; // nazwa
    private String Grade; // stopień zaawansowania
    public SkillsEntity(){
        Name=null;
        Grade=null;
    }
    public void setName(String Name){
        this.Name = Name;
    }
    public void setGrade(String Grade){
        this.Grade = Grade;
    }
    public String getName(){
        return this.Name;
    }
    public String getGrade(){
        return this.Grade;
    }
}
