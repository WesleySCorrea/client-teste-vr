package org.example.options.client;

import org.example.api.ClientApi;
import org.example.api.dto.request.ClientRequestDTO;
import org.example.api.dto.response.ClientResponseDTO;

import javax.swing.*;
import java.awt.*;
import java.math.BigDecimal;

public class ClientEditFormPanel {

    public JPanel createEditClientFormPanel() {
        // Criar o painel de formulário
        JPanel formPanel = new JPanel();
        formPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 6, 5); // Espaçamento entre os componentes
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.WEST; // Alinhar os componentes à esquerda

        // Campo ID (entrada manual)
        JLabel idLabel = new JLabel("ID:");
        gbc.gridx = 0;
        gbc.gridy = 0;
        formPanel.add(idLabel, gbc);

        JTextField idField = new JTextField(15); // Permitir entrada de ID manualmente
        gbc.gridx = 1;
        gbc.gridy = 0;
        formPanel.add(idField, gbc);

        // Campos do formulário
        JLabel nameLabel = new JLabel("Nome:");
        gbc.gridx = 0;
        gbc.gridy = 1;
        formPanel.add(nameLabel, gbc);

        JTextField nameField = new JTextField(15);
        gbc.gridx = 1;
        gbc.gridy = 1;
        formPanel.add(nameField, gbc);

        JLabel lastNameLabel = new JLabel("Sobrenome:");
        gbc.gridx = 0;
        gbc.gridy = 2;
        formPanel.add(lastNameLabel, gbc);

        JTextField lastNameField = new JTextField(15);
        gbc.gridx = 1;
        gbc.gridy = 2;
        formPanel.add(lastNameField, gbc);

        JLabel limitLabel = new JLabel("Limite:");
        gbc.gridx = 0;
        gbc.gridy = 3;
        formPanel.add(limitLabel, gbc);

        JTextField limitField = new JTextField(15);
        gbc.gridx = 1;
        gbc.gridy = 3;
        formPanel.add(limitField, gbc);

        JLabel dueDayLabel = new JLabel("Dia Vencimento:");
        gbc.gridx = 0;
        gbc.gridy = 4;
        formPanel.add(dueDayLabel, gbc);

        JTextField dueDayField = new JTextField(15);
        gbc.gridx = 1;
        gbc.gridy = 4;
        formPanel.add(dueDayField, gbc);

        // Botões de ação
        JButton searchButton = new JButton("Buscar");
        JButton submitButton = new JButton("Atualizar");
        JButton clearButton = new JButton("Limpar");

        gbc.gridwidth = 1; // Reseta para 1 para botões individuais
        gbc.gridx = 0;
        gbc.gridy = 5;
        formPanel.add(searchButton, gbc);

        gbc.gridx = 1;
        formPanel.add(submitButton, gbc);

        gbc.gridx = 2;
        formPanel.add(clearButton, gbc);

        // Ação para o botão "Buscar"
        searchButton.addActionListener(e -> {
            // Lógica para buscar o cliente pelo ID
            Long id = Long.parseLong(idField.getText());
            ClientApi clientApi = new ClientApi();
            ClientResponseDTO clientData = clientApi.findClientById(id);

            // Se o cliente for encontrado, preencha os campos
            if (clientData != null) {
                nameField.setText(clientData.getName());
                lastNameField.setText(clientData.getLastName());
                limitField.setText(clientData.getCreditLimit().toString());
                dueDayField.setText(clientData.getDueDate().toString());
            }
        });

        // Ação para o botão "Atualizar"
        submitButton.addActionListener(e -> {
            // Capturar valores dos campos
            Long id = Long.parseLong(idField.getText());
            String name = nameField.getText();
            String lastName = lastNameField.getText();
            BigDecimal limit = new BigDecimal(limitField.getText());
            Integer dueDay = Integer.parseInt(dueDayField.getText());

            if (dueDay >= 1 && dueDay <= 31) {
            // Criação da DTO com os valores capturados
            ClientRequestDTO clientRequestDTO = new ClientRequestDTO();
            clientRequestDTO.setName(name);
            clientRequestDTO.setLastName(lastName);
            clientRequestDTO.setCreditLimit(limit);
            clientRequestDTO.setDueDate(dueDay);

            // Chamar a API para atualizar os dados do cliente
            ClientApi clientApi = new ClientApi();
            ClientResponseDTO updatedClient = clientApi.updateClient(id, clientRequestDTO);

            // Se o cliente foi atualizado com sucesso, mostrar mensagem de sucesso
            if (updatedClient != null) {
                JOptionPane.showMessageDialog(null,
                        "Cliente atualizado com sucesso!",
                        "Sucesso", JOptionPane.INFORMATION_MESSAGE);
            }
        } else {
            dueDayField.requestFocus();
            dueDayField.setText("");
            JOptionPane.showMessageDialog(null,
                    "Dia de vencimento deve estar entre 1 e 31.",
                    "Erro", JOptionPane.ERROR_MESSAGE);
        }
        });

        // Ação para o botão "Limpar"
        clearButton.addActionListener(e -> {
            idField.setText("");
            nameField.setText("");
            lastNameField.setText("");
            limitField.setText("");
            dueDayField.setText("");
        });

        return formPanel;
    }
}
