package org.example.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HomeLogin extends JFrame {

    private final JTextField userField;
    private final JPasswordField passField;

    public HomeLogin() {
        setTitle("Login");
        setSize(850, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(new JLabel("Username:"), gbc);

        userField = new JTextField(20);
        gbc.gridx = 1;
        panel.add(userField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(new JLabel("Password:"), gbc);

        passField = new JPasswordField(20);
        gbc.gridx = 1;
        panel.add(passField, gbc);

        JButton loginButton = new JButton("Login");
        gbc.gridx = 1;
        gbc.gridy = 2;
        panel.add(loginButton, gbc);


        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = userField.getText();
                String password = new String(passField.getPassword());

                if (username.equals("") && password.equals("")) {
                    dispose();
                    Home home = new Home();
                    home.setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(HomeLogin.this, "Invalid credentials", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        add(panel);
    }
}
