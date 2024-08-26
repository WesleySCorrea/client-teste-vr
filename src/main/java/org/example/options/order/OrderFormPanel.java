package org.example.options.order;


import org.example.api.ClientApi;
import org.example.api.OrderApi;
import org.example.api.dto.request.OrderRequestDTO;
import org.example.api.dto.response.ClientResponseDTO;
import org.example.api.dto.response.OrderResponseDTO;

import javax.swing.*;
import java.awt.*;

public class OrderFormPanel {

    public JPanel createOrderFormPanel() {
        // Criar o painel de formulário
        JPanel formPanel = new JPanel();
        formPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 6, 5); // Espaçamento entre os componentes
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.WEST; // Alinhar os componentes à esquerda

        // Campo ID (entrada manual)
        JLabel idLabel = new JLabel("ID Cliente:");
        gbc.gridx = 0;
        gbc.gridy = 0;
        formPanel.add(idLabel, gbc);

        JTextField idField = new JTextField(8); // Permitir entrada de ID manualmente
        gbc.gridx = 1;
        gbc.gridy = 0;
        formPanel.add(idField, gbc);

        // Campos do formulário (apenas visualização)
        JLabel nameLabel = new JLabel("Nome:");
        gbc.gridx = 2;
        gbc.gridy = 0;
        formPanel.add(nameLabel, gbc);

        JTextField nameField = new JTextField(8);
        nameField.setEditable(false);
        gbc.gridx = 3;
        gbc.gridy = 0;
        formPanel.add(nameField, gbc);

        JLabel limitLabel = new JLabel("Limite:");
        gbc.gridx = 4;
        gbc.gridy = 0;
        formPanel.add(limitLabel, gbc);

        JTextField limitField = new JTextField(8);
        limitField.setEditable(false);
        gbc.gridx = 5;
        gbc.gridy = 0;
        formPanel.add(limitField, gbc);

        // Botões de ação
        JButton searchButton = new JButton("Buscar");
        JButton startOrderButton = new JButton("Iniciar");
        JButton clearButton = new JButton("Limpar");

        JLabel orderId = new JLabel("ID Pedido:");
        gbc.gridx = 0;
        gbc.gridy = 6;
        formPanel.add(orderId, gbc);

        JTextField orderIdField = new JTextField(8);
        orderIdField.setEditable(false);
        gbc.gridx = 1;
        gbc.gridy = 6;
        formPanel.add(orderIdField, gbc);

        JLabel totalValue = new JLabel("Valor Total:");
        gbc.gridx = 2;
        gbc.gridy = 6;
        formPanel.add(totalValue, gbc);

        JTextField totalValueField = new JTextField(8);
        totalValueField.setEditable(false);
        gbc.gridx = 3;
        gbc.gridy = 6;
        formPanel.add(totalValueField, gbc);

        JLabel activeOrder = new JLabel("Status:");
        gbc.gridx = 4;
        gbc.gridy = 6;
        formPanel.add(activeOrder, gbc);

        JCheckBox activeOrderCheckBox = new JCheckBox();
        activeOrderCheckBox.setEnabled(false);
        gbc.gridx = 5;
        gbc.gridy = 6;
        formPanel.add(activeOrderCheckBox, gbc);

        gbc.gridwidth = 1; // Reseta para 1 para botões individuais
        gbc.gridx = 0;
        gbc.gridy = 5;
        formPanel.add(searchButton, gbc);

        gbc.gridx = 1;
        formPanel.add(startOrderButton, gbc);

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
                limitField.setText(clientData.getCreditLimit().toString());
            }
        });

        // Ação para o botão "Enviar"
        startOrderButton.addActionListener(e -> {
            // Capturar valores dos campos
            Long id = Long.parseLong(idField.getText());

            // Criação da DTO com os valores capturados
            OrderRequestDTO orderRequestDTO = new OrderRequestDTO();
            orderRequestDTO.setClientId(id);

            // Enviar a DTO para o endpoint REST e obter a resposta
            OrderApi orderApi = new OrderApi();
            OrderResponseDTO response = orderApi.sendOrderData(orderRequestDTO);

            if (response != null) {
                // Sucesso: Mostrar o objeto de resposta em um JOptionPane
                JOptionPane.showMessageDialog(null,
                        "Pedido cadastrado com sucesso!",
                        "Sucesso", JOptionPane.INFORMATION_MESSAGE);

                orderIdField.setText(response.getOrderId().toString());
                totalValueField.setText(response.getTotalValue().toString());
                activeOrderCheckBox.setSelected(response.getFinished());
            } else {
                // Erro: Mostrar mensagem de erro
                JOptionPane.showMessageDialog(null,
                        "Erro ao criar o pedido.",
                        "Erro", JOptionPane.ERROR_MESSAGE);
            }
        });

        // Ação para o botão "Limpar"
        clearButton.addActionListener(e -> {
            idField.setText("");
        });

        return formPanel;
    }
}
