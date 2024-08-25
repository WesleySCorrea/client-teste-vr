package org.example.options.client;


import org.example.dto.ClientApi;
import org.example.dto.request.ClientRequestDTO;
import org.example.dto.response.ClientResponseDTO;

import javax.swing.*;
import java.awt.*;
import java.math.BigDecimal;

public class ClientFormPanel {

    public JPanel createClientFormPanel() {
        // Criar o painel de formulário
        JPanel formPanel = new JPanel();
        formPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 6, 5); // Espaçamento entre os componentes
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.WEST; // Alinhar os componentes à esquerda

        // Campos do formulário
        JLabel nameLabel = new JLabel("Nome:");
        gbc.gridx = 0;
        gbc.gridy = 0;
        formPanel.add(nameLabel, gbc);

        JTextField nameField = new JTextField(15);
        gbc.gridx = 1;
        gbc.gridy = 0;
        formPanel.add(nameField, gbc);

        JLabel lastNameLabel = new JLabel("Sobrenome:");
        gbc.gridx = 0;
        gbc.gridy = 1;
        formPanel.add(lastNameLabel, gbc);

        JTextField lastNameField = new JTextField(15);
        gbc.gridx = 1;
        gbc.gridy = 1;
        formPanel.add(lastNameField, gbc);

        JLabel limitLabel = new JLabel("Limite:");
        gbc.gridx = 0;
        gbc.gridy = 2;
        formPanel.add(limitLabel, gbc);

        JTextField limitField = new JTextField(15);
        gbc.gridx = 1;
        gbc.gridy = 2;
        formPanel.add(limitField, gbc);

        JLabel dueDayLabel = new JLabel("Dia Vencimento:");
        gbc.gridx = 0;
        gbc.gridy = 3;
        formPanel.add(dueDayLabel, gbc);

        JTextField dueDayField = new JTextField(15);
        gbc.gridx = 1;
        gbc.gridy = 3;
        formPanel.add(dueDayField, gbc);

        // Botões de ação
        JButton submitButton = new JButton("Enviar");
        JButton clearButton = new JButton("Limpar");

        gbc.gridwidth = 1; // Reseta para 1 para botões individuais
        gbc.gridx = 0;
        gbc.gridy = 4;
        formPanel.add(submitButton, gbc);

        gbc.gridx = 1;
        formPanel.add(clearButton, gbc);

        // Ação para o botão "Enviar"
        submitButton.addActionListener(e -> {
            // Capturar valores dos campos
            String name = nameField.getText();
            String lastName = lastNameField.getText();
            BigDecimal limit = new BigDecimal(limitField.getText());
            Integer dueDay = Integer.parseInt(dueDayField.getText());

            // Criação da DTO com os valores capturados
            ClientRequestDTO clientRequestDTO = new ClientRequestDTO();
            clientRequestDTO.setName(name);
            clientRequestDTO.setLastName(lastName);
            clientRequestDTO.setCreditLimit(limit);
            clientRequestDTO.setDueDate(dueDay);

            // Enviar a DTO para o endpoint REST e obter a resposta
            ClientApi clientApi = new ClientApi();
            ClientResponseDTO response = clientApi.sendClientData(clientRequestDTO);

            if (response != null) {
                // Sucesso: Mostrar o objeto de resposta em um JOptionPane
                JOptionPane.showMessageDialog(null,
                        "Cliente cadastrado com sucesso!\n" +
                                "ID: " + response.getId() + "\n" +
                                "Nome: " + response.getName() + "\n" +
                                "Sobrenome: " + response.getLastName() + "\n" +
                                "Limite: " + response.getCreditLimit() + "\n" +
                                "Dia Vencimento: " + response.getDueDate() + "\n" +
                                "Ativo: " + response.getActive(),
                        "Sucesso", JOptionPane.INFORMATION_MESSAGE);

                // Limpar o formulário após o cadastro bem-sucedido
                nameField.setText("");
                lastNameField.setText("");
                limitField.setText("");
                dueDayField.setText("");
            } else {
                // Erro: Mostrar mensagem de erro
                JOptionPane.showMessageDialog(null,
                        "Erro ao cadastrar cliente.",
                        "Erro", JOptionPane.ERROR_MESSAGE);
            }
        });

        // Ação para o botão "Limpar"
        clearButton.addActionListener(e -> {
            nameField.setText("");
            lastNameField.setText("");
            limitField.setText("");
            dueDayField.setText("");
        });

        return formPanel;
    }
}
