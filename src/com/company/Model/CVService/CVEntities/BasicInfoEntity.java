package com.company.Model.CVService.CVEntities;

import java.sql.Date;

/**
 * Created by Bajtas on 2015-05-26.
 */
public class BasicInfoEntity {
    private String Name;
    private String Surname;
    private Date BirthDate;
    private String Telephone;
    private String Address;
    private String City;
    private String PostalCode;
    private static byte[] Photo;
    private String Email;
    public BasicInfoEntity(){
        Name=null;
        Surname=null;
        BirthDate=null;
        Telephone=null;
        Address=null;
        City=null;
        Photo=null;
        Email=null;
    }
    public void setName(String Name){
        this.Name = Name;
    }
    public void setSurname(String Surname){
        this.Surname = Surname;
    }
    public void setBirthDate(String BirthDate){
        this.BirthDate = java.sql.Date.valueOf(BirthDate);
    }
    public void setTelephone(String Telephone){
        this.Telephone=Telephone;
    }
    public void setAddress(String Address){
        this.Address = Address;
    }
    public void setCity(String City){
        this.City = City;
    }
    public void setPostalCode(String PostalCode){
        this.PostalCode = PostalCode;
    }
    public static void setPhoto(byte[] ph){
        Photo = ph;
    }
    public static byte[] getPhoto() {return Photo;}
    public void setEmail(String Email){
        this.Email = Email;
    }
    public String getName(){
        return this.Name;
    }
    public String getSurname(){
        return this.Surname;
    }
    public Date getBirthDate(){
        return this.BirthDate;
    }
    public String getAddress(){
        return this.Address;
    }
    public String getCity(){
        return this.City;
    }
    public String getPostalCode(){
        return this.PostalCode;
    }
    public String getTelephone(){
        return this.Telephone;
    }
    public String getEmail(){
        return this.Email;
    }
}
