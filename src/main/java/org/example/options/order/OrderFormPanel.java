package org.example.options.order;


import org.example.api.ClientApi;
import org.example.api.OrderApi;
import org.example.api.ProductApi;
import org.example.api.ShoppingApi;
import org.example.api.dto.request.OrderRequestDTO;
import org.example.api.dto.request.ShoppingRequestDTO;
import org.example.api.dto.response.ClientResponseDTO;
import org.example.api.dto.response.OrderResponseDTO;
import org.example.api.dto.response.ProductResponseDTO;
import org.example.api.dto.response.ShoppingResponseDTO;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
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

// Divisória abaixo do campo Status
        gbc.gridwidth = 6;
        gbc.gridx = 0;
        gbc.gridy = 7;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        JSeparator separator = new JSeparator(SwingConstants.HORIZONTAL);
        formPanel.add(separator, gbc);

// Inicializar o painel de produto (inicialmente invisível)
        JPanel productPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbcProduct = new GridBagConstraints();
        gbcProduct.insets = new Insets(5, 5, 5, 5); // Espaçamento entre os componentes
        gbcProduct.fill = GridBagConstraints.HORIZONTAL;
        gbcProduct.anchor = GridBagConstraints.WEST; // Alinhar os componentes à esquerda

// Adicionar campos de Produto ID e Título
        JLabel productIdLabel = new JLabel("Produto ID:");
        gbcProduct.gridx = 0;
        gbcProduct.gridy = 0;
        productPanel.add(productIdLabel, gbcProduct);

        JTextField productIdField = new JTextField(8);
        gbcProduct.gridx = 1;
        gbcProduct.gridy = 0;
        productPanel.add(productIdField, gbcProduct);

        JLabel titleLabel = new JLabel("Título:");
        gbcProduct.gridx = 2;
        gbcProduct.gridy = 0;
        productPanel.add(titleLabel, gbcProduct);

        JTextField titleField = new JTextField(8);
        titleField.setEditable(false);
        gbcProduct.gridx = 3;
        gbcProduct.gridy = 0;
        productPanel.add(titleField, gbcProduct);

// Adicionar campos de Quantidade e Preço na linha abaixo
        JLabel qtyLabel = new JLabel("Qtd:");
        gbcProduct.gridx = 0;
        gbcProduct.gridy = 1;
        productPanel.add(qtyLabel, gbcProduct);

        JTextField qtyField = new JTextField(8);
        gbcProduct.gridx = 1;
        gbcProduct.gridy = 1;
        productPanel.add(qtyField, gbcProduct);

        JLabel priceLabel = new JLabel("Preço:");
        gbcProduct.gridx = 2;
        gbcProduct.gridy = 1;
        productPanel.add(priceLabel, gbcProduct);

        JTextField priceField = new JTextField(8);
        priceField.setEditable(false);
        gbcProduct.gridx = 3;
        gbcProduct.gridy = 1;
        productPanel.add(priceField, gbcProduct);

// Botões Buscar e Adicionar
        JButton productSearchButton = new JButton("Buscar");
        gbcProduct.gridx = 4;
        gbcProduct.gridy = 0;
        productPanel.add(productSearchButton, gbcProduct);

        JButton addProductButton = new JButton("Adicionar");
        gbcProduct.gridx = 4;
        gbcProduct.gridy = 1;
        productPanel.add(addProductButton, gbcProduct);

        // Inicialmente o painel de produto é invisível
        productPanel.setVisible(false);

        // Adicionar o painel de produto ao formulário
        gbc.gridx = 0;
        gbc.gridy = 8;
        gbc.gridwidth = 6;
        formPanel.add(productPanel, gbc);

// Colunas para a tabela de produtos
        String[] productColumnNames = {"Produto ID", "Título", "Preço", "Quantidade", "Subtotal"};
        DefaultTableModel productTableModel = new DefaultTableModel(productColumnNames, 0);
        JTable productTable = new JTable(productTableModel);

// Definir a altura da tabela para mostrar até 10 linhas
        productTable.setPreferredScrollableViewportSize(new Dimension(500, productTable.getRowHeight() * 10));
        productTable.setFillsViewportHeight(true);

// Adiciona a tabela de produtos a um JScrollPane
        JScrollPane productScrollPane = new JScrollPane(productTable);

// Adiciona a tabela de produtos ao painel
        gbc.gridx = 0;
        gbc.gridy = 11;
        gbc.gridwidth = 6;
        formPanel.add(productScrollPane, gbc);
        gbc.gridwidth = 1; // Resetar o valor de gridwidth







        // Ação para o botão "Buscar"
        searchButton.addActionListener(e -> {
            // Lógica para buscar o cliente pelo ID
            Long id = Long.parseLong(idField.getText());
            ClientApi clientApi = new ClientApi();
            ClientResponseDTO clientData = clientApi.findClientById(id);

            // Se o cliente for encontrado, preencha os campos
            if (clientData != null) {
                idField.setEditable(false);
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

                // Exibir o painel de produto
                productPanel.setVisible(true);
            } else {
                // Erro: Mostrar mensagem de erro
                JOptionPane.showMessageDialog(null,
                        "Erro ao criar o pedido.",
                        "Erro", JOptionPane.ERROR_MESSAGE);
            }
        });

        // Ação para o botão "Buscar" no painel de produtos
        productSearchButton.addActionListener(e -> {
            Long productId = Long.parseLong(productIdField.getText());
            ProductApi productApi = new ProductApi();
            ProductResponseDTO productData = productApi.findProductById(productId);

            // Se o produto for encontrado, preencha os campos
            if (productData != null) {
                titleField.setText(productData.getTitle());
                priceField.setText(productData.getPrice().toString());
                qtyField.setText("1"); // Resetar a quantidade
            }
        });

        // Ação para o botão "Adicionar"
        addProductButton.addActionListener(e -> {

            Long productId = Long.parseLong(productIdField.getText());
            ShoppingRequestDTO shoppingRequestDTO = new ShoppingRequestDTO();
            shoppingRequestDTO.setOrderId(Long.parseLong(orderIdField.getText()));
            shoppingRequestDTO.setProductId(productId);
            shoppingRequestDTO.setQuantity(Integer.parseInt(qtyField.getText()));
            ShoppingApi shoppingApi = new ShoppingApi();
            ShoppingResponseDTO data = shoppingApi.addProductToOrder(shoppingRequestDTO);

            // Adicionar os valores na tabela de produtos
            Object[] rowData = {productId, productIdField.getText(), data.getQuantity(), priceField.getText(), data.getSubtotal()};
            productTableModel.addRow(rowData);

            productIdField.setText("");
            qtyField.setText("1");
            titleField.setText("");
            priceField.setText("");


        });

        return formPanel;
    }
}
