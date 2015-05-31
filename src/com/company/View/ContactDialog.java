package com.company.View;

import com.company.Model.ContactService.ContactService;
import com.company.Model.UserInfo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class ContactDialog extends JDialog {
    private JPanel contentPane;
    private JList listaKontaktow;
    private JButton zobaczWiadomosci;
    private JButton sendMessage;
    private ContactService contactService;
    private ContactDialog self = this;

    public ContactDialog() {
        setContentPane(contentPane);
        setModal(true);
        setTitle("Lista kontaktow");
        setSize(new Dimension(400, 600));
        zobaczWiadomosci.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                showMessagesDialog();
            }
        });
        contactService = new ContactService();

        ArrayList<UserInfo> contacts = contactService.getRecipients();
        listaKontaktow.setListData(contacts.toArray());

        zobaczWiadomosci.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SeeMessages msgs = new SeeMessages();
                msgs.setSize(new Dimension(500, 500));
                msgs.setVisible(true);
            }
        });

        sendMessage.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(listaKontaktow.getSelectedValue() == null){
                    JOptionPane.showMessageDialog(self, "Prosze wybrac odbiorce wiadomosci");
                } else {
                    UserInfo info = (UserInfo)listaKontaktow.getSelectedValue();
                    MessageDetail newMessage = new MessageDetail(info);
                    newMessage.setSize(new Dimension(500, 500));
                    newMessage.setVisible(true);
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

    private void showMessagesDialog() {

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
        ContactDialog dialog = new ContactDialog();
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
    }
}
