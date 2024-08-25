package org.example.options.client;

import org.example.api.ClientApi;
import org.example.api.dto.response.ClientResponseDTO;

import javax.swing.*;
import java.awt.*;

public class ClientDeleteFormPanel {

    public JPanel deleteClientFormPanel() {
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

        // Campos do formulário (apenas visualização)
        JLabel nameLabel = new JLabel("Nome:");
        gbc.gridx = 0;
        gbc.gridy = 1;
        formPanel.add(nameLabel, gbc);

        JTextField nameField = new JTextField(15);
        nameField.setEditable(false);
        gbc.gridx = 1;
        gbc.gridy = 1;
        formPanel.add(nameField, gbc);

        JLabel lastNameLabel = new JLabel("Sobrenome:");
        gbc.gridx = 0;
        gbc.gridy = 2;
        formPanel.add(lastNameLabel, gbc);

        JTextField lastNameField = new JTextField(15);
        lastNameField.setEditable(false);
        gbc.gridx = 1;
        gbc.gridy = 2;
        formPanel.add(lastNameField, gbc);

        JLabel limitLabel = new JLabel("Limite:");
        gbc.gridx = 0;
        gbc.gridy = 3;
        formPanel.add(limitLabel, gbc);

        JTextField limitField = new JTextField(15);
        limitField.setEditable(false);
        gbc.gridx = 1;
        gbc.gridy = 3;
        formPanel.add(limitField, gbc);

        JLabel dueDayLabel = new JLabel("Dia Vencimento:");
        gbc.gridx = 0;
        gbc.gridy = 4;
        formPanel.add(dueDayLabel, gbc);

        JTextField dueDayField = new JTextField(15);
        dueDayField.setEditable(false);
        gbc.gridx = 1;
        gbc.gridy = 4;
        formPanel.add(dueDayField, gbc);

        // Botões de ação
        JButton searchButton = new JButton("Buscar");
        JButton deleteButton = new JButton("Deletar");
        JButton clearButton = new JButton("Limpar");

        gbc.gridwidth = 1; // Reseta para 1 para botões individuais
        gbc.gridx = 0;
        gbc.gridy = 5;
        formPanel.add(searchButton, gbc);

        gbc.gridx = 1;
        formPanel.add(deleteButton, gbc);

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

        // Ação para o botão "Deletar"
        deleteButton.addActionListener(e -> {
            // Confirmar a deleção
            int response = JOptionPane.showConfirmDialog(null, "Tem certeza que deseja deletar este cliente?", "Confirmar Deleção", JOptionPane.YES_NO_OPTION);
            if (response == JOptionPane.YES_OPTION) {
                Long id = Long.parseLong(idField.getText());
                ClientApi clientApi = new ClientApi();
                boolean isDeleted = clientApi.deleteClient(id);

                // Se o cliente foi deletado com sucesso, mostrar mensagem de sucesso e limpar o formulário
                if (isDeleted) {
                    JOptionPane.showMessageDialog(null,
                            "Cliente deletado com sucesso!",
                            "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                    idField.setText("");
                    nameField.setText("");
                    lastNameField.setText("");
                    limitField.setText("");
                    dueDayField.setText("");
                }
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
