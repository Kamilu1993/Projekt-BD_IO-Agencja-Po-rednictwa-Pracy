package com.company.View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * Created by Bajtas on 2015-05-16.
 */

public class CVForm extends JFrame{
    private JPanel RootPanel;
    private JPanel BasicInfoPanel;
    private JScrollPane ScrollBars2;
    private JPanel CVMainPanel;
    private JPanel TitlePanel;

    //region ZDJĘCIE
    private JButton BtAddPhoto;
    private JLabel PhotoLabel;
    public void setPhoto(byte[] array){
        PhotoLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        PhotoLabel.setIcon(new ImageIcon(array));
        PhotoLabel.repaint();
        PhotoLabel.revalidate();
        repaint();
        revalidate();
    }
    //endregion

    //region DZIAŁ DANE PODSTAWOWE
    private JPanel BasicPanel;

    private JLabel BasicLabels;
    private JTextField BasicInput;
    private ArrayList<JTextField> BasicInputList = new ArrayList<JTextField>();

    private int Basic_label_oy, Basic_label_ox, Basic_input_oy, Basic_input_ox;
    private float Basic_label_weightx, Basic_input_weightx;
    private int BasicInputID;

    private void setBasicFieldLabels() {
        String Labels[] = {"Imię*", "Nazwisko*", "Data urodzenia*", "Adres*", "Miejscowość*", "Kod pocztowy*", "Nr kom*", "Email"};
        for(int i=0; i<Labels.length;i++) {
            BasicLabels = new JLabel(Labels[i]);
            GridBagConstraints c = new GridBagConstraints();
            c.fill = GridBagConstraints.HORIZONTAL;
            c.gridx = Basic_label_ox;
            c.gridy = Basic_label_oy;
            Basic_label_oy++;
            c.weightx = Basic_label_weightx;
            BasicPanel.add(BasicLabels, c);
        }
    } // ustawienie napisów przy każdym z pól tekstowych
    private void setBasicFieldInput() {
        for(int i=0; i<8;i++) {
            BasicInput = new JTextField();
            GridBagConstraints c = new GridBagConstraints();
            c.fill = GridBagConstraints.HORIZONTAL;
            c.gridx = Basic_input_ox;
            c.gridy = Basic_input_oy;
            Basic_input_oy++;
            c.weightx = Basic_input_weightx;
            BasicInputList.add(BasicInputID, BasicInput);
            BasicInputID++;
            BasicPanel.add(BasicInput, c);
        }
    }
    public void addBasicArea() {
        setBasicFieldLabels();
        setBasicFieldInput();
        revalidate();
    }
    public ArrayList<JTextField> GetBasicList(){
        return BasicInputList;
    }
    //endregion

    //region DZIAŁ EDUKACJA

    private JPanel EducationPanel;

    private JLabel EducationLabels;
    private JTextField EducationInput;
    private ArrayList<JTextField> EducationInputList = new ArrayList<JTextField>();
    private JButton BtAddEducation;

    private int Education_label_oy, Education_label_ox, Education_input_oy, Education_input_ox;
    private float Education_label_weightx, Education_input_weightx;
    private int EducationInputID;

    private void setEducationFieldLabels() {
        String Labels[] = {"Nazwa*", "Adres*", "Kierunek","Specjalizacja", "Data rozpoczęcia", "Data zakończenia"};
        for(int i=0; i<Labels.length;i++) {
            EducationLabels = new JLabel(Labels[i]);
            GridBagConstraints c = new GridBagConstraints();
            c.fill = GridBagConstraints.HORIZONTAL;
            c.gridx = Education_label_ox;
            c.gridy = Education_label_oy;
            Education_label_oy++;
            c.weightx = Education_label_weightx;
            EducationPanel.add(EducationLabels, c);
        }
    } // ustawienie napisów przy każdym z pól tekstowych
    private void setEducationFieldInput() {
        for(int i=0; i<6;i++) {
            EducationInput = new JTextField();
            GridBagConstraints c = new GridBagConstraints();
            c.fill = GridBagConstraints.HORIZONTAL;
            c.gridx = Education_input_ox;
            c.gridy = Education_input_oy;
            Education_input_oy++;
            c.weightx = Education_input_weightx;
            EducationInputList.add(EducationInputID, EducationInput);
            EducationInputID++;
            EducationPanel.add(EducationInput, c);
        }
    }
    public void addEducationArea() {
        setEducationFieldLabels();
        setEducationFieldInput();
        setEducationBt();
        revalidate();
    }
    private void setEducationBt(){

        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.LAST_LINE_END;
        c.anchor = GridBagConstraints.LAST_LINE_END;
        c.insets.top = 10;
        c.gridx = 1;
        c.weightx = 2;
        c.gridy = Education_input_oy+1;
        EducationPanel.add(BtAddEducation, c);
    }
    public ArrayList<JTextField> GetEducationList(){
        return EducationInputList;
    }
    //endregion

    //region DZIAŁ DOŚWIADCZENIE
    private JPanel WorkExperiancePanel;

    private JLabel ExpLabels;
    private JTextField ExpInput;
    private ArrayList<JTextField> ExpInputList = new ArrayList<JTextField>();
    private JButton BtAddWork;

    private int Exp_label_oy, Exp_label_ox, Exp_input_oy, Exp_input_ox;
    private float Exp_label_weightx, Exp_input_weightx;
    private int ExpInputID;

    private void setExpFieldLabels() {
        String Labels[] = {"Nazwa", "Adres", "Stanowisko", "Data rozpoczęcia", "Data zakończenia"};
        for(int i=0; i<5;i++) {
            ExpLabels = new JLabel(Labels[i]);
            GridBagConstraints c = new GridBagConstraints();
            c.fill = GridBagConstraints.HORIZONTAL;
            c.gridx = Exp_label_ox;
            c.gridy = Exp_label_oy;
            Exp_label_oy++;
            c.weightx = Exp_label_weightx;
            WorkExperiancePanel.add(ExpLabels, c);
        }
    } // ustawienie napisów przy każdym z pól tekstowych
    private void setExpFieldInput() {
        for(int i=0; i<5;i++) {
            ExpInput = new JTextField();
            GridBagConstraints c = new GridBagConstraints();
            c.fill = GridBagConstraints.HORIZONTAL;
            c.gridx = Exp_input_ox;
            c.gridy = Exp_input_oy;
            Exp_input_oy++;
            c.weightx = Exp_input_weightx;
            ExpInputList.add(ExpInputID, ExpInput);
            ExpInputID++;
            WorkExperiancePanel.add(ExpInput, c);
        }
    }
    public void addExperienceArea() {
        setExpFieldLabels();
        setExpFieldInput();
        setExpBt();
        revalidate();
    }
    private void setExpBt(){

        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.LAST_LINE_END;
        c.anchor = GridBagConstraints.LAST_LINE_END;
        c.insets.top = 10;
        c.gridx = 1;
        c.weightx = 2;
        c.gridy = Exp_input_oy+1;
        WorkExperiancePanel.add(BtAddWork, c);
    }
    public ArrayList<JTextField> GetExperienceList(){
        return ExpInputList;
    }
    //endregion

    //region DZIAŁ UMIEJĘTNOŚCI
    private JPanel SkillsPanel;

    private JLabel SkillsLabels;
    private JTextField SkillsInput;
    private ArrayList<JTextField> SkillsInputList = new ArrayList<JTextField>();
    private JButton BtAddSkill;

    private int Skills_label_oy, Skills_label_ox, Skills_input_oy, Skills_input_ox;
    private float Skills_label_weightx, Skills_input_weightx;
    private int SkillsInputID;

    private void setSkillsFieldLabels() {
        String Labels[] = {"Nazwa", "Stopień zaawansowania"};
        int nr = Labels.length;
        for(int i=0; i<2;i++) {
            SkillsLabels = new JLabel(Labels[i]);
            GridBagConstraints c = new GridBagConstraints();
            c.fill = GridBagConstraints.HORIZONTAL;
            c.gridx = Skills_label_ox;
            c.gridy = Skills_label_oy;
            Skills_label_oy++;
            c.weightx = Skills_label_weightx;
            SkillsPanel.add(SkillsLabels, c);
        }
    } // ustawienie napisów przy każdym z pól tekstowych
    private void setSkillsFieldInput() {
        for(int i=0; i<2;i++) {
            SkillsInput = new JTextField();
            GridBagConstraints c = new GridBagConstraints();
            c.fill = GridBagConstraints.HORIZONTAL;
            c.gridx = Skills_input_ox;
            c.gridy = Skills_input_oy;
            Skills_input_oy++;
            c.weightx = Skills_input_weightx;
            SkillsInputList.add(SkillsInputID, SkillsInput);
            SkillsInputID++;
            SkillsPanel.add(SkillsInput, c);
        }
    }
    public void addSkillsArea() {
        setSkillsFieldLabels();
        setSkillsFieldInput();
        setSkillsBt();
        revalidate();
    }
    private void setSkillsBt(){
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.LAST_LINE_END;
        c.anchor = GridBagConstraints.LAST_LINE_END;
        c.insets.top = 10;
        c.gridx = 1;
        c.weightx = 2;
        c.gridy = Skills_input_oy+1;
        SkillsPanel.add(BtAddSkill, c);
    }
    public ArrayList<JTextField> GetSkillsList(){
        return SkillsInputList;
    }
    //endregion

    //region DZIAŁ KURSY
    private JPanel CoursesPanel;

    private JLabel CoursesLabels;
    private JTextField CoursesInput;
    private ArrayList<JTextField> CoursesInputList = new ArrayList<JTextField>();
    private JButton BtAddCourses;

    private int Courses_label_oy, Courses_label_ox, Courses_input_oy, Courses_input_ox;
    private float Courses_label_weightx, Courses_input_weightx;
    private int CoursesInputID;

    private void setCoursesFieldLabels() {
        String Labels[] = {"Nazwa", "Data rozpoczęcia", "Data zakończenia"};
        int nr = Labels.length;
        for(int i=0; i<nr;i++) {
            CoursesLabels = new JLabel(Labels[i]);
            GridBagConstraints c = new GridBagConstraints();
            c.fill = GridBagConstraints.HORIZONTAL;
            c.gridx = Courses_label_ox;
            c.gridy = Courses_label_oy;
            Courses_label_oy++;
            c.weightx = Courses_label_weightx;
            CoursesPanel.add(CoursesLabels, c);
        }
    } // ustawienie napisów przy każdym z pól tekstowych
    private void setCoursesFieldInput() {
        for(int i=0; i<3;i++) {
            CoursesInput = new JTextField();
            GridBagConstraints c = new GridBagConstraints();
            c.fill = GridBagConstraints.HORIZONTAL;
            c.gridx = Courses_input_ox;
            c.gridy = Courses_input_oy;
            Courses_input_oy++;
            c.weightx = Courses_input_weightx;
            CoursesInputList.add(CoursesInputID, CoursesInput);
            CoursesInputID++;
            CoursesPanel.add(CoursesInput, c);
        }
    }
    public void addCoursesArea() {
        setCoursesFieldLabels();
        setCoursesFieldInput();
        setCoursesBt();
        revalidate();
    }
    private void setCoursesBt(){
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.LAST_LINE_END;
        c.anchor = GridBagConstraints.LAST_LINE_END;
        c.insets.top = 10;
        c.gridx = 1;
        c.weightx = 2;
        c.gridy = Courses_input_oy+1;
        CoursesPanel.add(BtAddCourses, c);
    }
    public ArrayList<JTextField> GetCoursesList(){
        return CoursesInputList;
    }
    //endregion

    //region DZIAŁ ZAINTERESOWANIA
    private JPanel InterestPanel;
    private JButton BtClose;
    private JButton BtAddCV;

    private JLabel InterestLabels;
    private JTextField InterestInput;
    private ArrayList<JTextField> InterestInputList = new ArrayList<JTextField>();
    private JButton BtAddInterest;

    private int Interest_label_oy, Interest_label_ox, Interest_input_oy, Interest_input_ox;
    private float Interest_label_weightx, Interest_input_weightx;
    private int InterestInputID;

    private void setInterestFieldLabels() {
        String Labels[] = {"Nazwa"};
        int nr = Labels.length;
        for(int i=0; i<nr;i++) {
            InterestLabels = new JLabel(Labels[i]);
            GridBagConstraints c = new GridBagConstraints();
            c.fill = GridBagConstraints.HORIZONTAL;
            c.gridx = Interest_label_ox;
            c.gridy = Interest_label_oy;
            Interest_label_oy++;
            c.weightx = Interest_label_weightx;
            InterestPanel.add(InterestLabels, c);
        }
    } // ustawienie napisów przy każdym z pól tekstowych
    private void setInterestFieldInput() {
        for(int i=0; i<1;i++) {
            InterestInput = new JTextField();
            GridBagConstraints c = new GridBagConstraints();
            c.fill = GridBagConstraints.HORIZONTAL;
            c.gridx = Interest_input_ox;
            c.gridy = Interest_input_oy;
            Interest_input_oy++;
            c.weightx = Interest_input_weightx;
            InterestInputList.add(InterestInputID, CoursesInput);
            InterestInputID++;
            InterestPanel.add(InterestInput, c);
        }
    }
    public void addInterestArea() {
        setInterestFieldLabels();
        setInterestFieldInput();
        setInterestBt();
        revalidate();
    }
    private void setInterestBt(){
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.LAST_LINE_END;
        c.anchor = GridBagConstraints.LAST_LINE_END;
        c.insets.top = 10;
        c.gridx = 1;
        c.weightx = 2;
        c.gridy = Interest_input_oy+1;
        InterestPanel.add(BtAddInterest, c);
    }
    public ArrayList<JTextField> GetInterestList(){
        return InterestInputList;
    }
    //endregion

    public CVForm(){
        setTitle("Formularz dodawania nowego CV");
        setMinimumSize(new Dimension(500,500)); // minimalny rozmiar okna
        setResizable(true); // mozliwosc roszerzania wlaczona

        setLocationRelativeTo(null); // wysrodkowanie okna na ekranie
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setContentPane(new JLabel(new ImageIcon(CVForm.class.getResource("Images/addCV_bg.jpg")))); // pobranie tła

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

        //region DZIAŁ DOŚWIADCZENIE ZAWODOWE
        Basic_label_oy = 0; Basic_input_oy=0;
        Basic_label_ox=0; Basic_input_ox=1;
        Basic_label_weightx=0.85f; Basic_input_weightx=2;
        BasicInputID = 0;
        //endregion
        //region DZIAŁ EDUKACJA
        Education_label_oy = 0; Education_input_oy=0;
        Education_label_ox=0; Education_input_ox=1;
        Education_label_weightx=0.85f; Education_input_weightx=2;
        EducationInputID = 0;

        BtAddEducation = new JButton("Dodaj pole");
        BtAddEducation.setIcon(new ImageIcon(CVForm.class.getResource("Images/addIcon.png")));
        BtAddEducation.setHorizontalAlignment(JButton.RIGHT);
        //endregion

        //region DZIAŁ DOŚWIADCZENIE ZAWODOWE
        Exp_label_oy = 0; Exp_input_oy=0;
        Exp_label_ox=0; Exp_input_ox=1;
        Exp_label_weightx=0.85f; Exp_input_weightx=2;
        ExpInputID = 0;

        BtAddWork = new JButton("Dodaj pole");
        BtAddWork.setIcon(new ImageIcon(CVForm.class.getResource("Images/addIcon.png")));
        BtAddWork.setHorizontalAlignment(JButton.RIGHT);
        //endregion
        //region DZIAŁ UMIEJĘTNOŚCI
        Skills_label_oy = 0; Skills_input_oy=0;
        Skills_label_ox=0; Skills_input_ox=1;
        Skills_label_weightx=0.85f; Skills_input_weightx=2;
        SkillsInputID = 0;

        BtAddSkill = new JButton("Dodaj pole");
        BtAddSkill.setIcon(new ImageIcon(CVForm.class.getResource("Images/addIcon.png")));
        BtAddSkill.setHorizontalAlignment(JButton.RIGHT);
        //endregion
        //region DZIAŁ KURSY
        Courses_label_oy = 0; Courses_input_oy=0;
        Courses_label_ox=0; Courses_input_ox=1;
        Courses_label_weightx=0.85f; Courses_input_weightx=2;
        CoursesInputID = 0;

        BtAddCourses = new JButton("Dodaj pole");
        BtAddCourses.setIcon(new ImageIcon(CVForm.class.getResource("Images/addIcon.png")));
        BtAddCourses.setHorizontalAlignment(JButton.RIGHT);
        //endregion
        //region DZIAŁ ZAINTERESOWANIA
        Interest_label_oy = 0; Interest_input_oy=0;
        Interest_label_ox=0; Interest_input_ox=1;
        Interest_label_weightx=0.85f; Interest_input_weightx=2;
        InterestInputID = 0;

        BtAddInterest = new JButton("Dodaj pole");
        BtAddInterest.setIcon(new ImageIcon(CVForm.class.getResource("Images/addIcon.png")));
        BtAddInterest.setHorizontalAlignment(JButton.RIGHT);
        //endregion

        addBasicArea();
        addEducationArea();
        addInterestArea();
        addCoursesArea();
        addExperienceArea();
        addSkillsArea();
        setExtendedState(getExtendedState() | JFrame.MAXIMIZED_BOTH);
    }
    public void addController(ActionListener CustomerController) {
        //------------------- MENU --------------------------------
        BtAddEducation.setActionCommand("Dodaj pole do EDUKACJA");
        BtAddEducation.addActionListener(CustomerController);

        BtAddWork.setActionCommand("Dodaj pole do DOSWIADCZENIE");
        BtAddWork.addActionListener(CustomerController);

        BtAddPhoto.setActionCommand("Dodaj zdjecie CV");
        BtAddPhoto.addActionListener(CustomerController);

        BtAddSkill.setActionCommand("Dodaj pole do UMIEJETNOSCI");
        BtAddSkill.addActionListener(CustomerController);

        BtAddCourses.setActionCommand("Dodaj pole do KURSY");
        BtAddCourses.addActionListener(CustomerController);

        BtAddInterest.setActionCommand("Dodaj pole do ZAINTERESOWANIA");
        BtAddInterest.addActionListener(CustomerController);

        BtAddCV.setActionCommand("Dodaj CV do bazy danych");
        BtAddCV.addActionListener(CustomerController);

        BtClose.setActionCommand("Wyjdz bez zapisywania");
        BtClose.addActionListener(CustomerController);
    }

}
