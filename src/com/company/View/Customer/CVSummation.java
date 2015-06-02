package com.company.View.Customer;

import com.company.Controller.CustomerController;
import com.company.Model.CVService.CVEntities.*;

import javax.swing.*;
import java.awt.*;
import java.awt.Font;
import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;

import com.itextpdf.text.*;
import com.itextpdf.text.Image;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfWriter;


/**
 * Created by Bajtas on 2015-05-29.
 */
public class CVSummation extends JFrame{
    private JScrollPane ScrollBars2;
    private JPanel RootPanel;
    private JButton BtSendCV;
    private JButton BtCancelCV;
    private JButton BtSaveCV;
    private JLabel PhotoLabel;
    private JPanel BasicInfoPanel;
    private JPanel EducationPanel;
    private JPanel SkillsPanel;
    private JPanel ExperiencePanel;
    private JPanel CoursesPanel;
    private JPanel InterestPanel;

    public void setFrame(BasicInfoEntity BasicRecord, ArrayList<EducationEntity> EducationTable,
                       ArrayList<SkillsEntity> SkillsTable, ArrayList<ExperienceEntity> ExperienceTable,
                       ArrayList<CoursesEntity> CoursesTable, ArrayList<InterestEntity> InterestTable,
                       byte[] Photo){
        setTitle("Dodawanie nowego CV - podsumowanie");
        setMinimumSize(new Dimension(500,500)); // minimalny rozmiar okna
        setResizable(true); // mozliwosc roszerzania wlaczona

        setLocationRelativeTo(null); // wysrodkowanie okna na ekranie
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        //setContentPane(new JLabel(new ImageIcon(CVSummation.class.getResource("Images/sumCVBG.jpg")))); // pobranie tła

        setLayout(new BorderLayout()); // ustawienie typu wygladu
        ScrollBars2 = new JScrollPane(RootPanel, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        ScrollBars2.setMinimumSize(new Dimension(100,100));
        ScrollBars2.setPreferredSize(new Dimension(100, 100));
        ScrollBars2.setMaximumSize(new Dimension(100, 100));
        ScrollBars2.setVisible(true);
        ScrollBars2.getViewport().setOpaque(false);
        ScrollBars2.setBorder(null);
        ScrollBars2.setOpaque(false);
        add(ScrollBars2, BorderLayout.CENTER);
        setExtendedState(getExtendedState() | JFrame.MAXIMIZED_BOTH);


        if(Photo != null)
            setPhoto(Photo);
        setBasicInfo(BasicRecord);
        setEducation(EducationTable);
        setSkills(SkillsTable);
        setExperience(ExperienceTable);
        setCourses(CoursesTable);
        setInterest(InterestTable);
    }
    private void setBasicInfo(BasicInfoEntity BasicRecord){
        int Basic_label_oy = 0;
        int fontsize = 16;
        String Labels[] = {"Imi\u0119: ", "Nazwisko: ", "Data urodzenia: ", "Adres: ", "Miejscowość: ", "Kod pocztowy: ", "Nr kom: "};
        for(int i=0; i<Labels.length;i++) {
            JLabel basic = new JLabel(Labels[i], SwingConstants.LEFT);
            basic.setFont(new Font("Serif", Font.PLAIN, fontsize));
            GridBagConstraints c = new GridBagConstraints();
            c.fill = GridBagConstraints.HORIZONTAL;
            c.gridx = 0;
            c.gridy = Basic_label_oy;
            Basic_label_oy++;
            c.weightx = 0.85f;
            BasicInfoPanel.add(basic, c);
        }
        Basic_label_oy=0;
        JLabel basicinfo = new JLabel(BasicRecord.getName());
        basicinfo.setFont(new Font("Serif", Font.PLAIN, fontsize));
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 1;
        c.gridy = Basic_label_oy;
        Basic_label_oy++;
        c.weightx = 2f;
        BasicInfoPanel.add(basicinfo, c);

        basicinfo = new JLabel(BasicRecord.getSurname());
        basicinfo.setFont(new Font("Serif", Font.PLAIN, fontsize));
        c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 1;
        c.gridy = Basic_label_oy;
        Basic_label_oy++;
        c.weightx = 2f;
        BasicInfoPanel.add(basicinfo, c);

        basicinfo = new JLabel(BasicRecord.getBirthDate().toString());
        basicinfo.setFont(new Font("Serif", Font.PLAIN, fontsize));
        c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 1;
        c.gridy = Basic_label_oy;
        Basic_label_oy++;
        c.weightx = 2f;
        BasicInfoPanel.add(basicinfo, c);

        basicinfo = new JLabel(BasicRecord.getAddress());
        basicinfo.setFont(new Font("Serif", Font.PLAIN, fontsize));
        c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 1;
        c.gridy = Basic_label_oy;
        Basic_label_oy++;
        c.weightx = 2f;
        BasicInfoPanel.add(basicinfo, c);

        basicinfo = new JLabel(BasicRecord.getCity());
        basicinfo.setFont(new Font("Serif", Font.PLAIN, fontsize));
        c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 1;
        c.gridy = Basic_label_oy;
        Basic_label_oy++;
        c.weightx = 2f;
        BasicInfoPanel.add(basicinfo, c);

        basicinfo = new JLabel(BasicRecord.getPostalCode());
        basicinfo.setFont(new Font("Serif", Font.PLAIN, fontsize));
        c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 1;
        c.gridy = Basic_label_oy;
        Basic_label_oy++;
        c.weightx = 2f;
        BasicInfoPanel.add(basicinfo, c);

        basicinfo = new JLabel(BasicRecord.getTelephone());
        basicinfo.setFont(new Font("Serif", Font.PLAIN, fontsize));
        c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 1;
        c.gridy = Basic_label_oy;
        Basic_label_oy++;
        c.weightx = 2f;
        BasicInfoPanel.add(basicinfo, c);

        if(BasicRecord.getEmail().length()>0) {
            String str = "Email:";
            JLabel basic = new JLabel(str, SwingConstants.LEFT);
            basic.setFont(new Font("Serif", Font.PLAIN, fontsize));
            c = new GridBagConstraints();
            c.fill = GridBagConstraints.HORIZONTAL;
            c.gridx = 0;
            c.gridy = Basic_label_oy;
            c.weightx = 0.85f;
            BasicInfoPanel.add(basic, c);

            basic = new JLabel(BasicRecord.getEmail());
            basic.setFont(new Font("Serif", Font.PLAIN, fontsize));
            c = new GridBagConstraints();
            c.fill = GridBagConstraints.HORIZONTAL;
            c.gridx = 1;
            c.gridy = Basic_label_oy;
            Basic_label_oy++;
            c.weightx = 2f;
            BasicInfoPanel.add(basic, c);
        }


    }
    private void setPhoto(byte[] Photo){
        PhotoLabel.setIcon(new ImageIcon(Photo));
        repaint();
        revalidate();
    }
    private void setEducation(ArrayList<EducationEntity> Table) {
        int label_oy = 0;
        int fontsize = 16;
        // "Data urodzenia: ", "Adres: ", "Miejscowość: ", "Kod pocztowy: ", "Nr kom: "};
        for (int i = 0; i < Table.size(); i++) {
            if (Table.get(i).getName().length() > 0) {
                EducationPanel.setVisible(true);
                String Labels[] = {"Nazwa: ", "Adres: "};
                JLabel basic = new JLabel(Labels[0], SwingConstants.RIGHT);
                basic.setFont(new Font("Serif", Font.PLAIN, fontsize));
                GridBagConstraints c = new GridBagConstraints();
                c.fill = GridBagConstraints.HORIZONTAL;
                c.gridx = 0;
                c.gridy = label_oy;
                c.weightx = 0.85f;
                EducationPanel.add(basic, c);

                basic = new JLabel(Table.get(i).getName());
                basic.setFont(new Font("Serif", Font.PLAIN, fontsize));
                c = new GridBagConstraints();
                c.fill = GridBagConstraints.HORIZONTAL;
                c.gridx = 1;
                c.gridy = label_oy;
                c.weightx = 2f;
                EducationPanel.add(basic, c);
                label_oy++;

                basic = new JLabel(Labels[1], SwingConstants.RIGHT);
                basic.setFont(new Font("Serif", Font.PLAIN, fontsize));
                c = new GridBagConstraints();
                c.fill = GridBagConstraints.HORIZONTAL;
                c.gridx = 0;
                c.gridy = label_oy;
                c.weightx = 0.85f;
                EducationPanel.add(basic, c);

                basic = new JLabel(Table.get(i).getAddress());
                basic.setFont(new Font("Serif", Font.PLAIN, fontsize));
                c = new GridBagConstraints();
                c.fill = GridBagConstraints.HORIZONTAL;
                c.gridx = 1;
                c.gridy = label_oy;
                c.weightx = 2f;
                EducationPanel.add(basic, c);
                label_oy++;

                if(Table.get(i).getSubject().length()>0){
                    basic = new JLabel("Kierunek: ", SwingConstants.RIGHT);
                    basic.setFont(new Font("Serif", Font.PLAIN, fontsize));
                    c = new GridBagConstraints();
                    c.fill = GridBagConstraints.HORIZONTAL;
                    c.gridx = 0;
                    c.gridy = label_oy;
                    c.weightx = 0.85f;
                    EducationPanel.add(basic, c);

                    basic = new JLabel(Table.get(i).getSubject());
                    basic.setFont(new Font("Serif", Font.PLAIN, fontsize));
                    c = new GridBagConstraints();
                    c.fill = GridBagConstraints.HORIZONTAL;
                    c.gridx = 1;
                    c.gridy = label_oy;
                    c.weightx = 2f;
                    EducationPanel.add(basic, c);
                    label_oy++;
                }
                if(Table.get(i).getSpecialization().length()>0){
                    basic = new JLabel("Specjalizacja: ", SwingConstants.RIGHT);
                    basic.setFont(new Font("Serif", Font.PLAIN, fontsize));
                    c = new GridBagConstraints();
                    c.fill = GridBagConstraints.HORIZONTAL;
                    c.gridx = 0;
                    c.gridy = label_oy;
                    c.weightx = 0.85f;
                    EducationPanel.add(basic, c);

                    basic = new JLabel(Table.get(i).getSpecialization());
                    basic.setFont(new Font("Serif", Font.PLAIN, fontsize));
                    c = new GridBagConstraints();
                    c.fill = GridBagConstraints.HORIZONTAL;
                    c.gridx = 1;
                    c.gridy = label_oy;
                    c.weightx = 2f;
                    EducationPanel.add(basic, c);
                    label_oy++;
                }
                if(Table.get(i).getStartDate()!=null){
                    basic = new JLabel("Data rozpoczęcia: ", SwingConstants.RIGHT);
                    basic.setFont(new Font("Serif", Font.PLAIN, fontsize));
                    c = new GridBagConstraints();
                    c.fill = GridBagConstraints.HORIZONTAL;
                    c.gridx = 0;
                    c.gridy = label_oy;
                    c.weightx = 0.85f;
                    EducationPanel.add(basic, c);

                    basic = new JLabel(Table.get(i).getStartDate().toString());
                    basic.setFont(new Font("Serif", Font.PLAIN, fontsize));
                    c = new GridBagConstraints();
                    c.fill = GridBagConstraints.HORIZONTAL;
                    c.gridx = 1;
                    c.gridy = label_oy;
                    c.weightx = 2f;
                    EducationPanel.add(basic, c);
                    label_oy++;
                }
                if(Table.get(i).getEndDate()!=null){
                    basic = new JLabel("Data zakończenia: ", SwingConstants.RIGHT);
                    basic.setFont(new Font("Serif", Font.PLAIN, fontsize));
                    c = new GridBagConstraints();
                    c.fill = GridBagConstraints.HORIZONTAL;
                    c.gridx = 0;
                    c.gridy = label_oy;
                    c.weightx = 0.85f;
                    EducationPanel.add(basic, c);

                    basic = new JLabel(Table.get(i).getEndDate().toString());
                    basic.setFont(new Font("Serif", Font.PLAIN, fontsize));
                    c = new GridBagConstraints();
                    c.fill = GridBagConstraints.HORIZONTAL;
                    c.gridx = 1;
                    c.gridy = label_oy;
                    c.weightx = 2f;
                    EducationPanel.add(basic, c);
                    label_oy++;
                }
            }
        }
    }
    private void setSkills(ArrayList<SkillsEntity> Table) {
        int label_oy = 0;
        int fontsize = 16;
        for (int i = 0; i < Table.size(); i++) {
            if (Table.get(i).getName()!=null) {
                SkillsPanel.setVisible(true);
                String Labels = "Nazwa: ";
                JLabel basic = new JLabel(Labels, SwingConstants.RIGHT);
                basic.setFont(new Font("Serif", Font.PLAIN, fontsize));
                GridBagConstraints c = new GridBagConstraints();
                c.fill = GridBagConstraints.HORIZONTAL;
                c.gridx = 0;
                c.gridy = label_oy;
                c.weightx = 0.85f;
                SkillsPanel.add(basic, c);

                basic = new JLabel(Table.get(i).getName());
                basic.setFont(new Font("Serif", Font.PLAIN, fontsize));
                c = new GridBagConstraints();
                c.fill = GridBagConstraints.HORIZONTAL;
                c.gridx = 1;
                c.gridy = label_oy;
                c.weightx = 2f;
                SkillsPanel.add(basic, c);
                label_oy++;

                if(Table.get(i).getGrade()!=null) {
                    Labels = "Poziom zaawansowania: ";
                    basic = new JLabel(Labels, SwingConstants.RIGHT);
                    basic.setFont(new Font("Serif", Font.PLAIN, fontsize));
                    c = new GridBagConstraints();
                    c.fill = GridBagConstraints.HORIZONTAL;
                    c.gridx = 0;
                    c.gridy = label_oy;
                    c.weightx = 0.85f;
                    SkillsPanel.add(basic, c);

                    basic = new JLabel(Table.get(i).getGrade());
                    basic.setFont(new Font("Serif", Font.PLAIN, fontsize));
                    c = new GridBagConstraints();
                    c.fill = GridBagConstraints.HORIZONTAL;
                    c.gridx = 1;
                    c.gridy = label_oy;
                    c.weightx = 2f;
                    SkillsPanel.add(basic, c);
                    label_oy++;
                }
            }
        }
    }
    private void setExperience(ArrayList<ExperienceEntity> Table) {
        int label_oy = 0;
        int fontsize = 16;
        for (int i = 0; i < Table.size(); i++) {
            if (Table.get(i).getName()!= null) {
                ExperiencePanel.setVisible(true);
                String Labels = "Nazwa: ";
                JLabel basic = new JLabel(Labels, SwingConstants.RIGHT);
                basic.setFont(new Font("Serif", Font.PLAIN, fontsize));
                GridBagConstraints c = new GridBagConstraints();
                c.fill = GridBagConstraints.HORIZONTAL;
                c.gridx = 0;
                c.gridy = label_oy;
                c.weightx = 0.85f;
                ExperiencePanel.add(basic, c);

                basic = new JLabel(Table.get(i).getName());
                basic.setFont(new Font("Serif", Font.PLAIN, fontsize));
                c = new GridBagConstraints();
                c.fill = GridBagConstraints.HORIZONTAL;
                c.gridx = 1;
                c.gridy = label_oy;
                c.weightx = 2f;
                ExperiencePanel.add(basic, c);
                label_oy++;

                Labels = "Adres: ";
                basic = new JLabel(Labels, SwingConstants.RIGHT);
                basic.setFont(new Font("Serif", Font.PLAIN, fontsize));
                c = new GridBagConstraints();
                c.fill = GridBagConstraints.HORIZONTAL;
                c.gridx = 0;
                c.gridy = label_oy;
                c.weightx = 0.85f;
                ExperiencePanel.add(basic, c);

                basic = new JLabel(Table.get(i).getAddress());
                basic.setFont(new Font("Serif", Font.PLAIN, fontsize));
                c = new GridBagConstraints();
                c.fill = GridBagConstraints.HORIZONTAL;
                c.gridx = 1;
                c.gridy = label_oy;
                c.weightx = 2f;
                ExperiencePanel.add(basic, c);
                label_oy++;

                if(Table.get(i).getPosition().length()>0) {
                    Labels = "Stanowisko: ";
                    basic = new JLabel(Labels, SwingConstants.RIGHT);
                    basic.setFont(new Font("Serif", Font.PLAIN, fontsize));
                    c = new GridBagConstraints();
                    c.fill = GridBagConstraints.HORIZONTAL;
                    c.gridx = 0;
                    c.gridy = label_oy;
                    c.weightx = 0.85f;
                    ExperiencePanel.add(basic, c);

                    basic = new JLabel(Table.get(i).getPosition());
                    basic.setFont(new Font("Serif", Font.PLAIN, fontsize));
                    c = new GridBagConstraints();
                    c.fill = GridBagConstraints.HORIZONTAL;
                    c.gridx = 1;
                    c.gridy = label_oy;
                    c.weightx = 2f;
                    ExperiencePanel.add(basic, c);
                    label_oy++;
                }
                else if(Table.get(i).getStartDate()!=null) {
                    Labels = "Data rozpoczęcia: ";
                    basic = new JLabel(Labels, SwingConstants.RIGHT);
                    basic.setFont(new Font("Serif", Font.PLAIN, fontsize));
                    c = new GridBagConstraints();
                    c.fill = GridBagConstraints.HORIZONTAL;
                    c.gridx = 0;
                    c.gridy = label_oy;
                    c.weightx = 0.85f;
                    ExperiencePanel.add(basic, c);

                    basic = new JLabel(Table.get(i).getStartDate().toString());
                    basic.setFont(new Font("Serif", Font.PLAIN, fontsize));
                    c = new GridBagConstraints();
                    c.fill = GridBagConstraints.HORIZONTAL;
                    c.gridx = 1;
                    c.gridy = label_oy;
                    c.weightx = 2f;
                    ExperiencePanel.add(basic, c);
                    label_oy++;
                }
                else if(Table.get(i).getEndDate()!=null) {
                    Labels = "Data zakończenia: ";
                    basic = new JLabel(Labels, SwingConstants.RIGHT);
                    basic.setFont(new Font("Serif", Font.PLAIN, fontsize));
                    c = new GridBagConstraints();
                    c.fill = GridBagConstraints.HORIZONTAL;
                    c.gridx = 0;
                    c.gridy = label_oy;
                    c.weightx = 0.85f;
                    ExperiencePanel.add(basic, c);

                    basic = new JLabel(Table.get(i).getEndDate().toString());
                    basic.setFont(new Font("Serif", Font.PLAIN, fontsize));
                    c = new GridBagConstraints();
                    c.fill = GridBagConstraints.HORIZONTAL;
                    c.gridx = 1;
                    c.gridy = label_oy;
                    c.weightx = 2f;
                    ExperiencePanel.add(basic, c);
                    label_oy++;
                }
            }
        }
    }
    private void setCourses(ArrayList<CoursesEntity> Table) {
        int label_oy = 0;
        int fontsize = 16;
        for (int i = 0; i < Table.size(); i++) {
            if (Table.get(i).getName().length() > 0) {
                CoursesPanel.setVisible(true);
                String Labels = "Nazwa: ";
                JLabel basic = new JLabel(Labels, SwingConstants.RIGHT);
                basic.setFont(new Font("Serif", Font.PLAIN, fontsize));
                GridBagConstraints c = new GridBagConstraints();
                c.fill = GridBagConstraints.HORIZONTAL;
                c.gridx = 0;
                c.gridy = label_oy;
                c.weightx = 0.85f;
                CoursesPanel.add(basic, c);

                basic = new JLabel(Table.get(i).getName());
                basic.setFont(new Font("Serif", Font.PLAIN, fontsize));
                c = new GridBagConstraints();
                c.fill = GridBagConstraints.HORIZONTAL;
                c.gridx = 1;
                c.gridy = label_oy;
                c.weightx = 2f;
                CoursesPanel.add(basic, c);
                label_oy++;

               if(Table.get(i).getStartDate()!= null) {
                    basic = new JLabel("Data rozpoczęcia", SwingConstants.RIGHT);
                    basic.setFont(new Font("Serif", Font.PLAIN, fontsize));
                    c = new GridBagConstraints();
                    c.fill = GridBagConstraints.HORIZONTAL;
                    c.gridx = 0;
                    c.gridy = label_oy;
                    c.weightx = 0.85f;
                    CoursesPanel.add(basic, c);

                    basic = new JLabel(Table.get(i).getStartDate().toString());
                    basic.setFont(new Font("Serif", Font.PLAIN, fontsize));
                    c = new GridBagConstraints();
                    c.fill = GridBagConstraints.HORIZONTAL;
                    c.gridx = 1;
                    c.gridy = label_oy;
                    c.weightx = 2f;
                    CoursesPanel.add(basic, c);
                    label_oy++;
                }
                else if(Table.get(i).getEndDate()!= null) {
                    Labels = "Data zakończenia: ";
                    basic = new JLabel(Labels, SwingConstants.RIGHT);
                    basic.setFont(new Font("Serif", Font.PLAIN, fontsize));
                    c = new GridBagConstraints();
                    c.fill = GridBagConstraints.HORIZONTAL;
                    c.gridx = 0;
                    c.gridy = label_oy;
                    c.weightx = 0.85f;
                    CoursesPanel.add(basic, c);

                    basic = new JLabel(Table.get(i).getEndDate().toString());
                    basic.setFont(new Font("Serif", Font.PLAIN, fontsize));
                    c = new GridBagConstraints();
                    c.fill = GridBagConstraints.HORIZONTAL;
                    c.gridx = 1;
                    c.gridy = label_oy;
                    c.weightx = 2f;
                    CoursesPanel.add(basic, c);
                    label_oy++;
                }
            }
        }
    }
    private void setInterest(ArrayList<InterestEntity> Table) {
        int label_oy = 0;
        int fontsize = 16;
        for (int i = 0; i < Table.size(); i++) {
            if (Table.get(i).getName().length() > 0) {
                InterestPanel.setVisible(true);
                String Labels = "Nazwa: ";
                JLabel basic = new JLabel(Labels, SwingConstants.RIGHT);
                basic.setFont(new Font("Serif", Font.PLAIN, fontsize));
                GridBagConstraints c = new GridBagConstraints();
                c.fill = GridBagConstraints.HORIZONTAL;
                c.gridx = 0;
                c.gridy = label_oy;
                c.weightx = 0.85f;
                InterestPanel.add(basic, c);

                basic = new JLabel(Table.get(i).getName());
                basic.setFont(new Font("Serif", Font.PLAIN, fontsize));
                c = new GridBagConstraints();
                c.fill = GridBagConstraints.HORIZONTAL;
                c.gridx = 1;
                c.gridy = label_oy;
                c.weightx = 2f;
                InterestPanel.add(basic, c);
                label_oy++;
            }
        }
    }
    public void addController(CustomerController controller){
        BtCancelCV.setActionCommand("CV - ANULUJ");
        BtCancelCV.addActionListener(controller);

        BtSaveCV.setActionCommand("CV - ZAPISZ");
        BtSaveCV.addActionListener(controller);

        BtSendCV.setActionCommand("CV - WYSLIJ");
        BtSendCV.addActionListener(controller);
    }
}
