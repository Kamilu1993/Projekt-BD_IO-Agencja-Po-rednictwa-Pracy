package com.company.View;

import com.company.Controller.Controller;
import com.company.Model.ContactService.ContactEntities.ContactEntity;
import com.company.Model.ContactService.ContactService;
import com.company.Model.UserInfo;
import com.company.Model.UserType;

import javax.naming.ldap.Control;
import javax.swing.*;
import java.awt.event.*;
import java.util.Calendar;

public class MessageDetail extends JDialog {
    private JPanel contentPane;
    private JButton zamknijButton;
    private JTextField tematText;
    private JTextArea trescText;
    private ContactEntity message;
    MessageDetail self = this;

    public MessageDetail() {
        setContentPane(contentPane);
        setModal(true);
    }

    public MessageDetail(ContactEntity message) {
        this();
        this.message = message;
        setTitle("Szczegoly wiadomosci");
        this.trescText.setText(message.getTresc());
        this.tematText.setText(message.getTemat());

        this.zamknijButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                self.dispose();
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

    public MessageDetail(UserInfo info){
        this();
        setTitle("Wiadomosc do: " + info.getUserName());
        message = new ContactEntity();
        UserInfo currentUserInfo = Controller.getUserInfo();
        message.setIdKlienta(info.getUserId());
        message.setIdPracownika(currentUserInfo.getUserId());
        trescText.setEditable(true);
        tematText.setEditable(true);


        zamknijButton.setText("Wyslij");;
        zamknijButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ContactService service = new ContactService();
                message.setTemat(tematText.getText());
                message.setTresc(trescText.getText());
                message.setDataWyslania(Calendar.getInstance().getTime());
                service.sendMessage(message);
            }
        });


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
        MessageDetail dialog = new MessageDetail();
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
    }
}
