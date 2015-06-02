package com.company.Model.CVService;

import com.company.Model.CVService.CVEntities.*;
import com.itextpdf.text.Document;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfWriter;

import javax.swing.*;
import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;

/**
 * Created by Bajtas on 2015-06-02.
 */
public class CVSave {
    static public void SaveAs(BasicInfoEntity Basic, byte[] Photo, ArrayList<EducationEntity> Education, ArrayList<ExperienceEntity> Experience,
                       ArrayList<SkillsEntity> Skills, ArrayList<CoursesEntity> Courses, ArrayList<InterestEntity> Interest){
        JFileChooser fileChooser = new JFileChooser();
        int i = fileChooser.showSaveDialog(null);
        if (i == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            // save to file
            SavePDF(Basic, file.getPath()+".pdf", Photo, Education, Experience, Skills, Courses, Interest);
        }
    }
    static public void SavePDF(BasicInfoEntity BasicInfoRecord, String File, byte[] Photo, ArrayList<EducationEntity> Education,
                        ArrayList<ExperienceEntity> Experience, ArrayList<SkillsEntity> Skills, ArrayList<CoursesEntity> Courses
            , ArrayList<InterestEntity> Interest) {
        Document document = new Document(PageSize.A4.rotate());
        try {
            PdfWriter.getInstance(document, new FileOutputStream(File));
            document.open();
            BaseFont bf = BaseFont.createFont("c:/windows/fonts/arial.ttf",
                    BaseFont.CP1250, BaseFont.EMBEDDED);
            com.itextpdf.text.Font font = new com.itextpdf.text.Font(bf, 32);
            document.add(new Paragraph("Curriculum Vitae", font));
            if(Photo!=null) {
                Image img = Image.getInstance(Photo);
                img.setBorder(1);
                img.setAbsolutePosition(620f, 350f);
                document.add(img);
            }

            font = new com.itextpdf.text.Font(bf, 16);
            document.add(new Paragraph("Dane podstawowe: ", font));
            font = new com.itextpdf.text.Font(bf, 14);
            document.add(new Paragraph("       Imię: " + BasicInfoRecord.getName(), font));
            document.add(new Paragraph("       Nazwisko: " + BasicInfoRecord.getSurname(), font));
            document.add(new Paragraph("       Data urodzenia: " + BasicInfoRecord.getBirthDate(), font));
            document.add(new Paragraph("       Adres: " + BasicInfoRecord.getAddress(), font));
            document.add(new Paragraph("       Miejscowość: " + BasicInfoRecord.getCity(), font));
            if(Education.size()>0){
                font = new com.itextpdf.text.Font(bf, 16);
                document.add(new Paragraph("Wykształcenie: ", font));
                font = new com.itextpdf.text.Font(bf, 14);
                for(int i=0;i<Education.size();i++){
                    document.add(new Paragraph("       Nazwa: " + Education.get(i).getName(), font));
                    document.add(new Paragraph("       Adres: " + Education.get(i).getAddress(), font));
                    if(Education.get(i).getSubject().length()>0)
                        document.add(new Paragraph("       Kierunek: " + Education.get(i).getSubject(), font));
                    if(Education.get(i).getSpecialization().length()>0)
                        document.add(new Paragraph("       Specjalizacja: " + Education.get(i).getSpecialization(), font));
                    if(Education.get(i).getStartDate()!=null)
                        document.add(new Paragraph("       Data rozpoczęcia: " + Education.get(i).getStartDate().toString(), font));
                    if(Education.get(i).getEndDate()!=null)
                        document.add(new Paragraph("       Data zakończenia: " + Education.get(i).getEndDate().toString() , font));
                }
            }
            if(Experience.size()>0){
                font = new com.itextpdf.text.Font(bf, 16);
                document.add(new Paragraph("Doświadczenie: ", font));
                font = new com.itextpdf.text.Font(bf, 14);
                for(int i=0;i<Experience.size();i++){
                    document.add(new Paragraph("       Nazwa: " + Experience.get(i).getName(), font));
                    document.add(new Paragraph("       Adres: " + Experience.get(i).getAddress(), font));
                    if(Experience.get(i).getPosition().length()>0)
                        document.add(new Paragraph("       Stanowisko: " + Experience.get(i).getPosition(), font));
                    if(Experience.get(i).getStartDate()!=null)
                        document.add(new Paragraph("       Data rozpoczęcia: " + Experience.get(i).getStartDate().toString(), font));
                    if(Experience.get(i).getEndDate()!=null)
                        document.add(new Paragraph("       Data zakończenia: " + Experience.get(i).getEndDate().toString() , font));
                }
            }
            if(Skills.size()>0) {
                font = new com.itextpdf.text.Font(bf, 16);
                document.add(new Paragraph("Umiejętności: ", font));
                font = new com.itextpdf.text.Font(bf, 14);
                for (int i = 0; i < Skills.size(); i++) {
                    if(Skills.get(i).getName()!=null)
                        document.add(new Paragraph("       Nazwa: " + Skills.get(i).getName(), font));
                    if (Skills.get(i).getGrade()!=null)
                        document.add(new Paragraph("       Stopień zaawansowania: " + Experience.get(i).getPosition(), font));
                }
            }
            if(Interest.size()>0) {
                font = new com.itextpdf.text.Font(bf, 16);
                document.add(new Paragraph("Zainteresowania: ", font));
                font = new com.itextpdf.text.Font(bf, 14);
                String str ="";
                for (int i = 0; i < Skills.size(); i++) {
                    if(i == Skills.size()-1) {
                        str += Interest.get(i).getName();
                        break;
                    }
                    if (Interest.get(i).getName().length() > 0)
                        str+=Interest.get(i).getName()+", ";
                }
                document.add(new Paragraph(str));
            }

        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        document.close();
    }
}
