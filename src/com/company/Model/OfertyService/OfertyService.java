package com.company.Model.OfertyService;

import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;


/**
 * Created by Jagoda on 06-06-2015.
 */
public class OfertyService {

    private static Connection ActualConnection;
    private static String ULogin;

    private BranzaEntity BranzaTable = new BranzaEntity();
    private StanowiskoEntity StanowiskoTable = new StanowiskoEntity();
    private TypEtatuEntity TypEtatuTable = new TypEtatuEntity();
    private TypUmowyEntity TypUmowyTable = new TypUmowyEntity();


    public static void Prep(String login, Connection con){
        ULogin = login;
        ActualConnection = con;
    }

   public void SaveAll(JTextArea Branza, JTextArea Stanowisko, JTextArea TypEtatu, JTextArea TypUmowy){

       SaveBranza(Branza);
       SaveStanowisko(Stanowisko);
       SaveTypEtatu(TypEtatu);
       SaveTypUmowy(TypUmowy);

    }


    public void SaveBranza(JTextArea Branza){
        if(Branza.getText().length()>0) {
            BranzaTable.setName(Branza.getText());
        }
    }

    public void SaveStanowisko(JTextArea Stanowisko){
        if(Stanowisko.getText().length()>0) {
            StanowiskoTable.setName(Stanowisko.getText());
        }
    }

    public void SaveTypEtatu(JTextArea TypEtatu){
        if(TypEtatu.getText().length()>0) {
            TypEtatuTable.setName(TypEtatu.getText());
        }
    }

    public void SaveTypUmowy(JTextArea TypUmowy){
        if(TypUmowy.getText().length()>0) {
            TypUmowyTable.setName(TypUmowy.getText());
        }
    }


     public void AddAll(){
        OfertySend.AddBranza(BranzaTable);
        OfertySend.AddStanowisko(StanowiskoTable);
        OfertySend.AddTypEtatu(TypEtatuTable);
        OfertySend.AddTypUmowy(TypUmowyTable);
    }


    public BranzaEntity getBranzaInfo(){ return BranzaTable;}
    public StanowiskoEntity getStanowiskoInfo(){ return StanowiskoTable;}
    public TypEtatuEntity getTypEtatuInfo(){ return TypEtatuTable;}
    public TypUmowyEntity getTypUmowyInfo(){ return TypUmowyTable;}


}

