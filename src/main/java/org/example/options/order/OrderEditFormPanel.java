package org.example.options.order;


import org.example.api.ClientApi;
import org.example.api.OrderApi;
import org.example.api.ProductApi;
import org.example.api.ShoppingApi;
import org.example.api.dto.request.ShoppingRequestDTO;
import org.example.api.dto.response.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.math.BigDecimal;
import java.util.List;

public class OrderEditFormPanel {

    private int currentPage = 0;
    private final int pageSize = 20;

    public JPanel createEditOrderFormPanel() {
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
        idField.setEditable(false);
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

        JLabel orderId = new JLabel("ID Pedido:");
        gbc.gridx = 0;
        gbc.gridy = 5;
        orderId.setVisible(true);
        formPanel.add(orderId, gbc);

        JTextField orderIdField = new JTextField(8);
        orderIdField.setEditable(true);
        gbc.gridx = 1;
        gbc.gridy = 5;
        orderIdField.setVisible(true);
        formPanel.add(orderIdField, gbc);

        JLabel totalValue = new JLabel("Valor Total:");
        gbc.gridx = 2;
        gbc.gridy = 5;
        totalValue.setVisible(true);
        formPanel.add(totalValue, gbc);

        JTextField totalValueField = new JTextField(8);
        totalValueField.setEditable(false);
        gbc.gridx = 3;
        gbc.gridy = 5;
        totalValueField.setVisible(true);
        formPanel.add(totalValueField, gbc);

        JLabel activeOrder = new JLabel("Status:");
        gbc.gridx = 4;
        gbc.gridy = 5;
        activeOrder.setVisible(true);
        formPanel.add(activeOrder, gbc);

        JCheckBox activeOrderCheckBox = new JCheckBox();
        activeOrderCheckBox.setEnabled(false);
        gbc.gridx = 5;
        gbc.gridy = 5;
        activeOrderCheckBox.setVisible(true);
        formPanel.add(activeOrderCheckBox, gbc);

        // Botões de ação
        JButton searchButton = new JButton("Buscar");

        gbc.gridwidth = 1; // Reseta para 1 para botões individuais
        gbc.gridx = 0;
        gbc.gridy = 6;
        formPanel.add(searchButton, gbc);

//        gbc.gridx = 1;
//        formPanel.add(startOrderButton, gbc);

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
        String[] productColumnNames = {"Produto ID", "Título", "Quantidade", "Preço", "Subtotal"};
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


// Adicionar botão "Finalizar Pedido"
        JButton finalizeOrderButton = new JButton("Finalizar Pedido");
        finalizeOrderButton.setVisible(false);
        gbc.gridx = 3;
        gbc.gridy = 13;
        formPanel.add(finalizeOrderButton, gbc);

        // Adicionar botão "Finalizar Pedido"
        JButton closeOrderButton = new JButton("Fechar Pedido");
        closeOrderButton.setVisible(false);
        gbc.gridx = 3;
        gbc.gridy = 14;
        formPanel.add(closeOrderButton, gbc);


        // Ação para o botão "Buscar"
        searchButton.addActionListener(e -> {

            Long idOrder = Long.parseLong(orderIdField.getText());
            OrderApi orderApi = new OrderApi();
            OrderResponseDTO response = orderApi.findOrderById(idOrder);
            if (response != null) {
                Long clientId = response.getClientId();
                orderIdField.setEditable(false);
                totalValueField.setText(response.getTotalValue().toString());
                activeOrderCheckBox.setSelected(response.getFinished());

                ClientApi clientApi = new ClientApi();
                ClientResponseDTO clientData = clientApi.findClientById(clientId);

                this.updateTableData(productTableModel, idOrder);

                // Se o cliente for encontrado, preencha os campos
                if (clientData != null) {
                    idField.setText(clientData.getId().toString());
                    nameField.setText(clientData.getName());
                    limitField.setText(clientData.getCreditLimit().toString());

                    productPanel.setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(null,
                            "Pedido não encontrado!",
                            "Erro", JOptionPane.ERROR_MESSAGE);
                }
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

            if (titleField.getText().isEmpty()) {
                productSearchButton.doClick();
            }

            Long productId = Long.parseLong(productIdField.getText());
            ShoppingRequestDTO shoppingRequestDTO = new ShoppingRequestDTO();
            shoppingRequestDTO.setOrderId(Long.parseLong(orderIdField.getText()));
            shoppingRequestDTO.setProductId(productId);
            shoppingRequestDTO.setQuantity(Integer.parseInt(qtyField.getText()));
            ShoppingApi shoppingApi = new ShoppingApi();
            ShoppingResponseDTO data = shoppingApi.addProductToOrder(shoppingRequestDTO);

            // Adicionar os valores na tabela de produtos
            Object[] rowData = {productId, titleField.getText(), data.getQuantity(), priceField.getText(), data.getSubtotal()};
            productTableModel.addRow(rowData);

            productIdField.setText("");
            qtyField.setText("1");
            titleField.setText("");
            priceField.setText("");

            finalizeOrderButton.setVisible(true);
            closeOrderButton.setVisible(true);

            BigDecimal totalValueOrder = totalValueField.getText().isEmpty() ? new BigDecimal(0) : new BigDecimal(totalValueField.getText());
            totalValueOrder = totalValueOrder.add(data.getSubtotal());
            totalValueField.setText(totalValueOrder.toString());
        });

        // Ação para o botão "Confirmar Pedido" no painel de pedidos
        finalizeOrderButton.addActionListener(e -> {
            Long id = Long.parseLong(orderIdField.getText());
            OrderApi orderApi = new OrderApi();
            OrderResponseDTO orderData = orderApi.confirmOrder(id);

            // Se o produto for encontrado, preencha os campos
            if (orderData != null) {
                searchButton.doClick();

                productIdField.setEditable(false);
                productIdField.setText("");
                titleField.setEditable(false);
                titleField.setText("");
                qtyField.setEditable(false);
                qtyField.setText("");
                activeOrderCheckBox.setSelected(true);
            }
        });

        // Ação para o botão "Fechar Pedido"
        closeOrderButton.addActionListener(e -> {

            // Limpar os campos de cliente
            idField.setText("");
            nameField.setText("");
            limitField.setText("");

            // Esconder e limpar os campos do pedido
            orderId.setVisible(true);
            orderIdField.setVisible(true);
            orderIdField.setEditable(true);
            orderIdField.setText("");

            totalValue.setVisible(true);
            totalValueField.setVisible(true);
            totalValueField.setText("");

            activeOrder.setVisible(true);
            activeOrderCheckBox.setVisible(true);
            activeOrderCheckBox.setSelected(false);

            // Esconder o painel de produtos
            productPanel.setVisible(false);

            // Limpar a tabela de produtos
            productTableModel.setRowCount(0); // Isso remove todas as linhas da tabela

            // Atualizar a interface para refletir as mudanças
            productPanel.revalidate();
            productPanel.repaint();

            finalizeOrderButton.setVisible(false);
            closeOrderButton.setVisible(false);
        });

        return formPanel;
    }

    private void updateTableData(DefaultTableModel tableModel, Long orderId) {
        // Limpar a tabela
        tableModel.setRowCount(0);

        // Buscar a página de pedidos da API
        ShoppingApi shoppingApi = new ShoppingApi();
        List<ShoppingProductResponseDTO> items = shoppingApi.findAllByOrderId(currentPage, pageSize, orderId);

        // Adicionar os dados na tabela
        for (ShoppingProductResponseDTO item : items) {
            Object[] rowData = {
                    item.getProductId(),
                    item.getTitle(),
                    item.getQuantity(),
                    item.getPrice(),
                    item.getSubtotal()
            };
            tableModel.addRow(rowData);
        }
    }
}