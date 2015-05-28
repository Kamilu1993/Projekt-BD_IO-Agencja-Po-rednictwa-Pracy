package com.company.Controller;

import com.company.ErrorType;
import com.company.Model.CVService.CVService;
import com.company.Model.CustomerService;
import com.company.Model.InputCheck;
import com.company.Model.Model;
import com.company.View.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Bajtas on 2015-05-10.
 */
public class CustomerController implements ActionListener {
    CustomerGui Customer_GUI;
    CustomerService Customer_SERVICE;
    CVForm AddCVForm;
    AccountSettings SettingsForm;
    ShowMessage ErrorMsg = new ShowMessage();

    public CustomerController(CustomerGui gui, CustomerService service)
    {
        this.Customer_GUI = gui;
        this.Customer_SERVICE = service;

        Customer_GUI.ShowCGUI();
        Customer_GUI.addController(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //------------------ PRZYCISKI MENU UŻYTKOWNIKA ----------------------------------
        if(e.getActionCommand().equals("Zapisz jako...")) {
        }
        else if(e.getActionCommand().equals("Wyloguj mnie")) {
            Customer_GUI.HideCGUI();
            Customer_GUI = null;
            Customer_SERVICE = null;
            Controller MainController = new Controller(new Login(), new Model());
        }
        else if(e.getActionCommand().equals("Dodaj CV")){
            AddCVForm = new CVForm();
            AddCVForm.setVisible(true);
            AddCVForm.addController(this);
        }
        //---------------------------------------------------------------------------
        // ------------------ NOWE CV -----------------------------------------------
        else if(e.getActionCommand().equals("Dodaj pole do EDUKACJA"))
            AddCVForm.addEducationArea();
        else if(e.getActionCommand().equals("Dodaj pole do DOSWIADCZENIE"))
            AddCVForm.addExperienceArea();
        else if(e.getActionCommand().equals("Dodaj pole do UMIEJETNOSCI"))
            AddCVForm.addSkillsArea();
        else if(e.getActionCommand().equals("Dodaj pole do KURSY"))
            AddCVForm.addCoursesArea();
        else if(e.getActionCommand().equals("Dodaj pole do ZAINTERESOWANIA"))
            AddCVForm.addInterestArea();
        else if(e.getActionCommand().equals("Dodaj zdjecie CV")){
            byte[] array = Customer_SERVICE.addCVPhoto();
            if(array!=null)
                AddCVForm.setPhoto(array);
        }
        else if(e.getActionCommand().equals("Wyjdz bez zapisywania")) {
            int reply = JOptionPane.showConfirmDialog(null, "<html><b>Czy napewno chcesz wyjść <br>bez zapisania wprowadzonych zmian?</b></html>", "Wyjdź bez zapisywania", JOptionPane.YES_NO_OPTION);
            if (reply == JOptionPane.YES_OPTION) {
                AddCVForm.dispose();
            }
        }
        else if(e.getActionCommand().equals("Dodaj CV do bazy danych")) {
            ErrorType er=new ErrorType();
            er.Error_ = InputCheck.CheckCVInputs(AddCVForm.GetBasicList(), AddCVForm.GetEducationList(), AddCVForm.GetExperienceList(),
                    AddCVForm.GetSkillsList(), AddCVForm.GetCoursesList());
            if(er.Error_== ErrorType.ErrTypes.NO_ERRORS){
                CVService.Prep(Customer_SERVICE.GetUsername(), Customer_SERVICE.GetConnection());
                CVService CVModel = new CVService();
                CVModel.SaveAll(AddCVForm.GetBasicList(), AddCVForm.GetEducationList(), AddCVForm.GetExperienceList(),
                        AddCVForm.GetSkillsList(), AddCVForm.GetCoursesList(), AddCVForm.GetInterestList());
            }
            else
                ErrorMsg.setErrorType(er);
        }
        //--------------------------------------------------------------------

        // ----------- OPCJE KONTA - KAMIL ZALEWSKI -------------------------
        else if(e.getActionCommand().equals("Opcje Konta")){
            SettingsForm = new AccountSettings();
            SettingsForm.setVisible(true);
            SettingsForm.AddController(this);
        }
        else if(e.getActionCommand().equals("ZmienHaslo")){
            ErrorType er=new ErrorType();
            er.Error_=Customer_SERVICE.AccountOptions_ChangePass(SettingsForm.getOldPass(), SettingsForm.getNewPass(), SettingsForm.getNewPass2());
            ErrorMsg.setErrorType(er);
        } else if (e.getActionCommand().equals("ZmienEmail")){
            System.out.println("Nacisnieto przycisk zmien email");
        }
        //----------------------------------------------------------------------
        //endregion
    }
}
