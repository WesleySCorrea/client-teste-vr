package org.example.options.order;

import org.example.api.OrderApi;
import org.example.api.dto.response.OrderResponseDTO;

import javax.swing.*;
import java.awt.*;

public class OrderDeleteFormPanel {

    public JPanel deleteOrderFormPanel() {
        // Criar o painel de formulário
        JPanel formPanel = new JPanel();
        formPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 6, 5); // Espaçamento entre os componentes
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.WEST; // Alinhar os componentes à esquerda

        // Campo ID (entrada manual)
        JLabel idLabel = new JLabel("Order ID:");
        gbc.gridx = 0;
        gbc.gridy = 0;
        formPanel.add(idLabel, gbc);

        JTextField idField = new JTextField(15); // Permitir entrada de ID manualmente
        gbc.gridx = 1;
        gbc.gridy = 0;
        formPanel.add(idField, gbc);

        // Campos do formulário (apenas visualização)
        JLabel clientIdLabel = new JLabel("Cliente ID:");
        gbc.gridx = 0;
        gbc.gridy = 1;
        formPanel.add(clientIdLabel, gbc);

        JTextField clientIdField = new JTextField(15);
        clientIdField.setEditable(false);
        gbc.gridx = 1;
        gbc.gridy = 1;
        formPanel.add(clientIdField, gbc);

        JLabel totalValueLabel = new JLabel("Valor Total:");
        gbc.gridx = 0;
        gbc.gridy = 2;
        formPanel.add(totalValueLabel, gbc);

        JTextField totalValueField = new JTextField(15);
        totalValueField.setEditable(false);
        gbc.gridx = 1;
        gbc.gridy = 2;
        formPanel.add(totalValueField, gbc);

        JLabel fineshedLabel = new JLabel("Pedido Finalizado:");
        gbc.gridx = 0;
        gbc.gridy = 3;
        formPanel.add(fineshedLabel, gbc);

        JCheckBox finishedField = new JCheckBox();
        finishedField.setEnabled(false);
        gbc.gridx = 1;
        gbc.gridy = 3;
        formPanel.add(finishedField, gbc);

        // Botões de ação
        JButton searchButton = new JButton("Buscar");
        JButton deleteButton = new JButton("Deletar");
        JButton clearButton = new JButton("Limpar");

        gbc.gridwidth = 1;
        gbc.gridx = 0;
        gbc.gridy = 5;
        formPanel.add(searchButton, gbc);

        gbc.gridx = 1;
        formPanel.add(deleteButton, gbc);

        gbc.gridx = 2;
        formPanel.add(clearButton, gbc);

        // Ação para o botão "Buscar"
        searchButton.addActionListener(e -> {
            // Lógica para buscar o produto pelo ID
            Long id = Long.parseLong(idField.getText());
            OrderApi orderApi = new OrderApi();
            OrderResponseDTO orderData = orderApi.findOrderById(id);

            // Se o produto for encontrado, preencha os campos
            if (orderData != null) {
                clientIdField.setText(orderData.getClientId().toString());
                totalValueField.setText(orderData.getTotalValue().toString());
                finishedField.setSelected(orderData.getFinished());
            }
        });

        // Ação para o botão "Deletar"
        deleteButton.addActionListener(e -> {
            // Confirmar a deleção
            int response = JOptionPane.showConfirmDialog(null, "Tem certeza que deseja deletar este pedido?", "Confirmar Deleção", JOptionPane.YES_NO_OPTION);
            if (response == JOptionPane.YES_OPTION) {
                Long id = Long.parseLong(idField.getText());
                OrderApi orderApi = new OrderApi();
                boolean isDeleted = orderApi.deleteOrder(id);

                // Se o pedido foi deletado com sucesso, mostrar mensagem de sucesso e limpar o formulário
                if (isDeleted) {
                    JOptionPane.showMessageDialog(null,
                            "Pedido deletado com sucesso!",
                            "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                    idField.setText("");
                    clientIdField.setText("");
                    totalValueField.setText("");
                    finishedField.setText("");
                }
            }
        });

        // Ação para o botão "Limpar"
        clearButton.addActionListener(e -> {
            idField.setText("");
            clientIdField.setText("");
            totalValueField.setText("");
            finishedField.setText("");
        });

        return formPanel;
    }
}
