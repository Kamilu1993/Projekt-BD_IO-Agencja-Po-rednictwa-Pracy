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
    private JButton BtAddPhoto;

    //region DZIAŁ EDUKACJA
    private JTextField NameInput;
    private JLabel NameLabel;
    private JTextField SurnameInput;
    private JTextField AdressInput;
    private JTextField CityInput;
    private JTextField PostalCodeInput;
    private JTextField TelNumberInput;
    private JTextField EmailInput;
    private JTextField ENameInput;
    private JTextField ECourseInput;
    private JTextField ESpecInput;
    private JTextField EDateStartInput;
    private JTextField EDateEndInput;
    private JButton BtAddEducation;
    private JPanel EducationPanel;
    private JLabel EName2;
    private JTextField ENameInput2;
    private JLabel ECourse2;
    private JTextField ECourseInput2;
    private JLabel ESpec2;
    private JTextField ESpecInput2;
    private JLabel EDateStart2;
    private JTextField EDateStartInput2;
    private JLabel EDateEnd2;
    private JTextField EDateEndInput2;
    private JTextField EAdressInput;
    private JTextField EAdressInput2;
    private JLabel EAdress2;
    private JLabel EName3;
    private JLabel EAdress3;
    private JLabel ECourse3;
    private JLabel ESpec3;
    private JLabel EDateStart3;
    private JLabel EDateEnd3;
    private JTextField ENameInput3;
    private JTextField EAdressInput3;
    private JTextField ECourseInput3;
    private JTextField ESpecInput3;
    private JTextField EDateStartInput3;
    private JTextField EDateEndInput3;
    private JLabel EName4;
    private JLabel EAdress4;
    private JLabel ECourse4;
    private JLabel ESpec4;
    private JLabel EDateStart4;
    private JLabel EDateEnd4;
    private JTextField ENameInput4;
    private JTextField EAdressInput4;
    private JTextField ECourseInput4;
    private JTextField ESpecInput4;
    private JTextField EDateStartInput4;
    private JTextField EDateEndInput4;
    private JLabel EName5;
    private JLabel EAdress5;
    private JLabel ECourse5;
    private JLabel ESpec5;
    private JLabel EDateStart5;
    private JLabel EDateEnd5;
    private JTextField ENameInput5;
    private JTextField EAdressInput5;
    private JTextField ECourseInput5;
    private JTextField ESpecInput5;
    private JTextField EDateStartInput5;
    private JTextField EDateEndInput5;
    //endregion

    //region DZIAŁ DOŚWIADCZENIE
    private JPanel WorkExperiancePanel;
    private JLabel ExpLabels;
    private JTextField ExpInput;
    private ArrayList<JLabel> ExpLabelList = new ArrayList<JLabel>();
    private ArrayList<JTextField> ExpInputList = new ArrayList<JTextField>();

    private String Labels[] = {"Nazwa", "Adres", "Stanowisko", "Data rozpoczęcia", "Data zakończenia"};
    private int Exp_label_oy, Exp_label_ox, Exp_input_oy, Exp_input_ox;
    private float Exp_label_weightx, Exp_input_weightx;
    private int ExpInputID;

    private void setExpFieldLabels() {
        for(int i=0; i<5;i++) {
            ExpLabels = new JLabel(Labels[i]);
            GridBagConstraints c = new GridBagConstraints();
            c.fill = GridBagConstraints.HORIZONTAL;
            c.gridx = Exp_label_ox;
            c.gridy = Exp_label_oy;
            Exp_label_oy++;
            c.weightx = Exp_label_weightx;
          //  ExpLabelList.add(ExpLabelID, ExpLabels);
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
    private JButton BtAddWork;
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
    //endregion

    private int Education_field;
    public CVForm(){
        Education_field=1;
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

        Exp_label_oy = 0; Exp_input_oy=0;
        Exp_label_ox=0; Exp_input_ox=1;
        Exp_label_weightx=0.85f; Exp_input_weightx=2;
        ExpInputID = 0;

        BtAddWork = new JButton("Dodaj pole");
        BtAddWork.setIcon(new ImageIcon(CVForm.class.getResource("Images/addIcon.png")));
        BtAddWork.setHorizontalAlignment(JButton.RIGHT);

        addExperienceArea();
    }
    public void addController(ActionListener CustomerController) {
        //------------------- MENU --------------------------------
        BtAddEducation.setActionCommand("Dodaj pole do EDUKACJA");
        BtAddEducation.addActionListener(CustomerController);

        BtAddWork.setActionCommand("Dodaj pole do DOSWIADCZENIE");
        BtAddWork.addActionListener(CustomerController);
    }
    public void addEducationArea(){

        final Runnable paintFields = new Runnable() {
            public void run() {
                switch (Education_field){
                    case 0:
                        EName2.setVisible(true);
                        ENameInput2.setVisible(true);
                        EAdress2.setVisible(true);
                        EAdressInput2.setVisible(true);
                        ECourse2.setVisible(true);
                        ECourseInput2.setVisible(true);
                        ESpec2.setVisible(true);
                        ESpecInput2.setVisible(true);
                        EDateStart2.setVisible(true);
                        EDateStartInput2.setVisible(true);
                        EDateEnd2.setVisible(true);
                        EDateEndInput2.setVisible(true);
                        break;
                    case 1:
                        EName3.setVisible(true);
                        ENameInput3.setVisible(true);
                        EAdress3.setVisible(true);
                        EAdressInput3.setVisible(true);
                        ECourse3.setVisible(true);
                        ECourseInput3.setVisible(true);
                        ESpec3.setVisible(true);
                        ESpecInput3.setVisible(true);
                        EDateStart3.setVisible(true);
                        EDateStartInput3.setVisible(true);
                        EDateEnd3.setVisible(true);
                        EDateEndInput3.setVisible(true);
                        break;
                    case 2:
                        EName4.setVisible(true);
                        ENameInput4.setVisible(true);
                        EAdress4.setVisible(true);
                        EAdressInput4.setVisible(true);
                        ECourse4.setVisible(true);
                        ECourseInput4.setVisible(true);
                        ESpec4.setVisible(true);
                        ESpecInput4.setVisible(true);
                        EDateStart4.setVisible(true);
                        EDateStartInput4.setVisible(true);
                        EDateEnd4.setVisible(true);
                        EDateEndInput4.setVisible(true);
                        break;
                    case 3:
                        EName5.setVisible(true);
                        ENameInput5.setVisible(true);
                        EAdress5.setVisible(true);
                        EAdressInput5.setVisible(true);
                        ECourse5.setVisible(true);
                        ECourseInput5.setVisible(true);
                        ESpec5.setVisible(true);
                        ESpecInput5.setVisible(true);
                        EDateStart5.setVisible(true);
                        EDateStartInput5.setVisible(true);
                        EDateEnd5.setVisible(true);
                        EDateEndInput5.setVisible(true);
                        BtAddEducation.setVisible(false);
                        break;
                }
                EducationPanel.revalidate();
                EducationPanel.repaint();
                Education_field++;

                WorkExperiancePanel.revalidate();
            }
        };

        Thread appThread = new Thread() {
            public void run() {
                try {
                    SwingUtilities.invokeAndWait(paintFields); // odwołanie do funkcji i oczekiwanie aż skończy swoje działanie
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
        appThread.start();
    }
}