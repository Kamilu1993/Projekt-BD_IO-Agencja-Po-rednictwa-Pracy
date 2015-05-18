package com.company.View;

import com.sun.xml.internal.ws.api.pipe.FiberContextSwitchInterceptor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * Created by Bajtas on 2015-05-16.
 */

public class CVForm extends JFrame{
    private JPanel RootPanel;
    private JPanel BasicInfoPanel;
    private JScrollPane ScrollBars2;
    private JPanel CVMainPanel;
    private JPanel TitlePanel;
    private JButton dodajZdjęcieButton;

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
    private JLabel ExpName, ExpAdress;
    private JTextField ExpNameInput, ExpAdressInput;
    //endregion

    private JPanel WorkExperiancePanel;
    private JLabel EducationName1;
    private int Education_field;
    private JButton BtAddWork;

    public CVForm(){
        Education_field = 1;
        setTitle("Formularz dodawania nowego CV");
        setMinimumSize(new Dimension(500,500));
        setResizable(true);

        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        setContentPane(new JLabel(new ImageIcon(CVForm.class.getResource("Images/addCV_bg.jpg"))));

        setLayout(new BorderLayout());
        ScrollBars2 = new JScrollPane(RootPanel, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        ScrollBars2.setMinimumSize(new Dimension(100,100));
        ScrollBars2.setPreferredSize(new Dimension(100, 100));
        ScrollBars2.setMaximumSize(new Dimension(100, 100));
        ScrollBars2.setVisible(true);
        ScrollBars2.getViewport().setOpaque(false);
        ScrollBars2.setBorder(null);
        ScrollBars2.setOpaque(false);
        add(ScrollBars2, BorderLayout.CENTER);

        //region INICJALIZACJA PÓL DZIAŁU DOŚWIADCZENIE ZAWODOWE
        ExpName = new JLabel("Nazwa");
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 0;
        c.weightx = 0.85;
        WorkExperiancePanel.add(ExpName, c);

        ExpNameInput = new JTextField();
        ExpNameInput.setVisible(true);
        ExpNameInput.setName("ExpNameInput1");
        c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 1;
        c.weightx = 2;
        c.gridy = 0;
        WorkExperiancePanel.add(ExpNameInput, c);

        ExpAdress = new JLabel("Adres");
        c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 1;
        c.weightx = 0.85;
        WorkExperiancePanel.add(ExpAdress, c);

        ExpAdressInput = new JTextField();
        ExpAdressInput.setVisible(true);
        ExpAdressInput.setName("ExpAdressInput1");
        c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 1;
        c.weightx = 2;
        c.gridy = 1;
        WorkExperiancePanel.add(ExpAdressInput, c);

        /*BtAddWork = new JButton("Dodaj pole");
        BtAddWork.setIcon(new ImageIcon(CVForm.class.getResource("Images/addIcon.png")));
        BtAddWork.setHorizontalAlignment(JButton.RIGHT);
        c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;
        //c.anchor = GridBagConstraints.LAST_LINE_END;
        c.insets = new Insets(10,120,0,0);
        c.gridx = 1;
        c.gridy = 3;

        //endregion

        WorkExperiancePanel.add(BtAddWork, c);*/

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
