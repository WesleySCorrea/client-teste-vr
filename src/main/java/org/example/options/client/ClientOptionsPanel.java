package org.example.options.client;

import lombok.Getter;
import org.example.ui.Home;

import javax.swing.*;
import java.awt.*;

@Getter
public class ClientOptionsPanel {
    private final Home home;
    private JButton addClientButton;
    private JButton editClientButton;
    private JButton deleteClientButton;
    private JButton listClientsButton;

    public ClientOptionsPanel(Home home) {
        this.home = home;
    }

    public JPanel createClientOptionsPanel() {
        // Painel para os botões de opções
        JPanel optionsPanel = new JPanel();
        optionsPanel.setLayout(new BoxLayout(optionsPanel, BoxLayout.Y_AXIS));
        optionsPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Criar os botões para as opções de cliente
        addClientButton = new JButton("Cadastrar Cliente");
        editClientButton = new JButton("Editar Cliente");
        deleteClientButton = new JButton("Excluir Cliente");
        listClientsButton = new JButton("Listar Clientes");

        // Define largura fixa para os botões
        Dimension buttonSize = new Dimension(150, 30);
        addClientButton.setMaximumSize(buttonSize);
        editClientButton.setMaximumSize(buttonSize);
        deleteClientButton.setMaximumSize(buttonSize);
        listClientsButton.setMaximumSize(buttonSize);

        // Alinhamento à esquerda
        addClientButton.setAlignmentX(Component.LEFT_ALIGNMENT);
        editClientButton.setAlignmentX(Component.LEFT_ALIGNMENT);
        deleteClientButton.setAlignmentX(Component.LEFT_ALIGNMENT);
        listClientsButton.setAlignmentX(Component.LEFT_ALIGNMENT);

        // Adicionar botões ao painel de opções
        optionsPanel.add(addClientButton);
        optionsPanel.add(Box.createRigidArea(new Dimension(0, 10))); // Espaçamento entre os botões
        optionsPanel.add(editClientButton);
        optionsPanel.add(Box.createRigidArea(new Dimension(0, 10))); // Espaçamento entre os botões
        optionsPanel.add(deleteClientButton);
        optionsPanel.add(Box.createRigidArea(new Dimension(0, 10))); // Espaçamento entre os botões
        optionsPanel.add(listClientsButton);

        return optionsPanel;
    }
}

