package com.company.View;

import com.company.Controller.Controller;
import com.company.Model.ContactService.ContactEntities.ContactEntity;
import com.company.Model.ContactService.ContactService;
import com.company.Model.UserInfo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class SeeMessages extends JDialog {
    private JPanel contentPane;
    private JList messagesJList;
    private JButton details;
    private JButton deleteMessage;
    private ContactService contactService;
    private SeeMessages self = this;

    public SeeMessages() {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(details);
        contactService = new ContactService();
        setTitle("Wiadomosci");

        UserInfo userInfo = Controller.getUserInfo();
        ArrayList<ContactEntity> messages = contactService.getMessages(userInfo.getUserId(), userInfo.getUserType());

        this.messagesJList.setListData(messages.toArray());

        this.deleteMessage.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(messagesJList.getSelectedValue() == null){
                    JOptionPane.showMessageDialog(self, "Prosze wybrac wiadomosc do usuniecia");
                } else {
                    ContactEntity selected = (ContactEntity)messagesJList.getSelectedValue();
                    int i = JOptionPane.showConfirmDialog(self, String.format("Czy na pewno chcesz usunac wiadomosc %s?", selected.getTemat().substring(0, 20) + "..."));


                }

            }
        });

        this.details.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(messagesJList.getSelectedValue() == null){
                    JOptionPane.showMessageDialog(self, "Prosze wybrac wiadomosc do usuniecia");
                } else {
                    ContactEntity selected = (ContactEntity) messagesJList.getSelectedValue();
                    MessageDetail details = new MessageDetail(selected);
                    details.setSize(new Dimension(500,500));
                    details.setVisible(true);
                }
            }
        });


// call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

// call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
    }

    private void onOK() {
// add your code here
        dispose();
    }

    private void onCancel() {
// add your code here if necessary
        dispose();
    }

    public static void main(String[] args) {
        SeeMessages dialog = new SeeMessages();
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
    }
}
