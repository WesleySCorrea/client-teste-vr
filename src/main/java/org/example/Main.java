package org.example;

import org.example.login.HomeLogin;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            HomeLogin homeLoginFrame = new HomeLogin();
            homeLoginFrame.setVisible(true);
        });
    }
}