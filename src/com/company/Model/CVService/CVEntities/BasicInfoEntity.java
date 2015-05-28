package com.company.Model.CVService.CVEntities;

/**
 * Created by Bajtas on 2015-05-26.
 */
public class BasicInfoEntity {
    private String Name;
    private String Surname;
    private String BirthDate;
    private String Telephone;
    private String Address;
    private String City;
    private String PostalCode;
    private byte[] Photo;
    private String Email;
    public void setName(String Name){
        this.Name = Name;
    }
    public void setSurname(String Surname){
        this.Surname = Surname;
    }
    public void setBirthDate(String BirthDate){
        this.BirthDate = BirthDate;
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
    public void setPhoto(byte[] Photo){
        this.Photo = Photo;
    }
    public void setEmail(String Email){
        this.Email = Email;
    }
    public String getName(){
        return this.Name;
    }
    public String getSurname(){
        return this.Surname;
    }
    public String getBirthDate(){
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
