package com.company.Controller;

import com.company.View.Gui;
import com.company.View.Login;
import com.company.Model.Model;
import com.company.View.ShowConnectionInfo;

import javax.swing.*;
import java.awt.event.ActionListener;

/**
 *
 */
public class Controller extends SwingWorker implements ActionListener{
    private Login theLogin;
    private Gui theGui;
    private Model theModel;
    ShowConnectionInfo info;

    protected Integer doInBackground() throws Exception {
        // Start
        publish("Start");
        setProgress(1);

        // More work was done
        publish("More work was done");
        setProgress(10);

        // Complete
        publish("Complete");
        setProgress(100);
        return 1;
    }

    public void actionPerformed(java.awt.event.ActionEvent e){
        JButton b = (JButton)e.getSource();
       if(b.getText()=="Zaloguj")
       {
           info = new ShowConnectionInfo();

           int isInputGood;
           isInputGood = theModel.CheckInput(theLogin.GetUsername(),theLogin.GetPassword());

           if(isInputGood==0){
               final Runnable doHelloWorld = new Runnable() {
                   public void run() {
                       info.run();
                       info.ShowDialog();
                   }
               };
               Thread appThread = new Thread() {
                   public void run() {
                       try {
                           SwingUtilities.invokeAndWait(doHelloWorld);
                       }
                       catch (Exception e) {
                           e.printStackTrace();
                       }
                       theModel.TryToConnect();




                       info.HideDialog();
                   }
               };
               appThread.start();
           }
           else
               theLogin.setErrorCode(isInputGood);

       }

        //JOptionPane.showMessageDialog(null, b.getText(), "InfoBox: " + "Tytul", JOptionPane.INFORMATION_MESSAGE);
    }

    //------------------------//
    public Controller(Gui g, Login l, Model m) {
        this.theLogin = l;
        this.theGui = g;
        this.theModel = m;

        this.theLogin.addController(this);
       // System.exit(0);
    }// Konstruktor tworzący główne okno aplikacji
    public void getAction()
    {

        //return 0;
    }
    public void ShowConnectionInfo()
    {

    }
    public void StartApp()
    {

    }
}
