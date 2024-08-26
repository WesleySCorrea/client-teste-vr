package org.example.options.client;

import lombok.Getter;

import javax.swing.*;
import java.awt.*;

@Getter
public class ClientOptionsPanel {

    private JButton addClientButton;
    private JButton editClientButton;
    private JButton deleteClientButton;
    private JButton listClientsButton;

    public JPanel createClientOptionsPanel() {
        // Painel para os botões de opções
        JPanel optionsPanel = new JPanel();
        optionsPanel.setLayout(new BoxLayout(optionsPanel, BoxLayout.Y_AXIS));
        optionsPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Criar os botões para as opções de cliente
        this.addClientButton = new JButton("Cadastrar Cliente");
        this.editClientButton = new JButton("Editar Cliente");
        this.deleteClientButton = new JButton("Excluir Cliente");
        this.listClientsButton = new JButton("Listar Clientes");

        // Define largura fixa para os botões
        Dimension buttonSize = new Dimension(150, 30);
        this.addClientButton.setMaximumSize(buttonSize);
        this.editClientButton.setMaximumSize(buttonSize);
        this.deleteClientButton.setMaximumSize(buttonSize);
        this.listClientsButton.setMaximumSize(buttonSize);

        // Alinhamento à esquerda
        this.addClientButton.setAlignmentX(Component.LEFT_ALIGNMENT);
        this.editClientButton.setAlignmentX(Component.LEFT_ALIGNMENT);
        this.deleteClientButton.setAlignmentX(Component.LEFT_ALIGNMENT);
        this.listClientsButton.setAlignmentX(Component.LEFT_ALIGNMENT);

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