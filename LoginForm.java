package com.diplom;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

/**
 * Created by Сергей on 13.09.2017.
 */
    public class LoginForm {
    private JTextField passField;
    private JButton loginButton;
    private JPanel LoginFrame;
    private JTextField nameField;
    private JLabel jLabelLogin;
    private static final String NAME=""; //root-name
    private static final String PASSWORD = ""; //password root DB
    private static final String URL = ""; //db url from mysql: jdbc:mysql://ip/localhost:port(3306)/dbName

    //public mainWindow()
        {

        }
    public LoginForm() {

        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //JOptionPane.showMessageDialog(null,"Login press..");
                int flag = 1;

                try {
                    Connection connection = DriverManager.getConnection(URL, NAME, PASSWORD);
                    Statement statementLogin = connection.createStatement();
                    ResultSet resultLogin = statementLogin.executeQuery("SELECT * FROM diplom.userdatabase");
                    while (resultLogin.next()) {
                        if (resultLogin.getString(2).equals(nameField.getText()) && resultLogin.getString(3).equals(passField.getText()))
                            flag = 0;
                        //break;
                        if (flag == 0) {
                            jLabelLogin.setText("Login succesful");
                        } else
                            jLabelLogin.setText("Invalid Name or password");
                    }
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
            }
        });
    }

    public static void main(String[] args) {
        JFrame panel = new JFrame("Авторизация"); //Login window title
        panel.setSize(300, 180); //resolution Login Window
        panel.setContentPane(new LoginForm().LoginFrame);
        panel.setLocationRelativeTo(null);
        panel.setVisible(true); //set Is this window visible ? (Yes)
        panel.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE); //Closing window after press 'x'

    }
}
