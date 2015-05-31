package com.company.Model.ContactService.ContactEntities;

import java.util.Date;

/**
 * Created by Rafa³ on 31.05.2015.
 */
public class ContactEntity {
    private int id;
    private String temat;
    private String tresc;
    private Date dataWyslania;
    private int idPracownika;
    private int idKlienta;

    public ContactEntity(){
        temat = null;
        tresc = null;
        dataWyslania = new Date();
        idPracownika = 0;
        idKlienta = 0;
    }

    public ContactEntity(int id, String temat, String tresc, Date dataWyslania, int idPracownika, int idKlienta){
        this.id = id;
        this.temat = temat;
        this.tresc = tresc;
        this.dataWyslania = dataWyslania;
        this.idPracownika = idPracownika;
        this.idKlienta = idKlienta;
    }
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTemat() {
        return temat;
    }

    public void setTemat(String temat) {
        this.temat = temat;
    }

    public String getTresc() {
        return tresc;
    }

    public void setTresc(String tresc) {
        this.tresc = tresc;
    }

    public Date getDataWyslania() {
        return dataWyslania;
    }

    public void setDataWyslania(Date dataWyslania) {
        this.dataWyslania = dataWyslania;
    }

    public int getIdPracownika() {
        return idPracownika;
    }

    public void setIdPracownika(int idPracownika) {
        this.idPracownika = idPracownika;
    }

    public int getIdKlienta() {
        return idKlienta;
    }

    public void setIdKlienta(int idKlienta) {
        this.idKlienta = idKlienta;
    }


}
