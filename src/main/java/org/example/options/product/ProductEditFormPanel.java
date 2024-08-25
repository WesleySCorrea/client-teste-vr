package org.example.options.product;

import org.example.api.ProductApi;
import org.example.api.dto.request.ProductRequestDTO;
import org.example.api.dto.response.ProductResponseDTO;

import javax.swing.*;
import java.awt.*;
import java.math.BigDecimal;

public class ProductEditFormPanel {

    public JPanel createEditProductFormPanel() {
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
        JLabel titleLabel = new JLabel("title:");
        gbc.gridx = 0;
        gbc.gridy = 1;
        formPanel.add(titleLabel, gbc);

        JTextField titleField = new JTextField(15);
        gbc.gridx = 1;
        gbc.gridy = 1;
        formPanel.add(titleField, gbc);

        JLabel descriptionLabel = new JLabel("Description:");
        gbc.gridx = 0;
        gbc.gridy = 2;
        formPanel.add(descriptionLabel, gbc);

        JTextField descriptionField = new JTextField(15);
        gbc.gridx = 1;
        gbc.gridy = 2;
        formPanel.add(descriptionField, gbc);

        JLabel priceLabel = new JLabel("Preço:");
        gbc.gridx = 0;
        gbc.gridy = 3;
        formPanel.add(priceLabel, gbc);

        JTextField priceField = new JTextField(15);
        gbc.gridx = 1;
        gbc.gridy = 3;
        formPanel.add(priceField, gbc);

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
            ProductApi productApi = new ProductApi();
            ProductResponseDTO productData = productApi.findProductById(id);

            // Se o cliente for encontrado, preencha os campos
            if (productData != null) {
                titleField.setText(productData.getTitle());
                descriptionField.setText(productData.getDescription());
                priceField.setText(productData.getPrice().toString());
            }
        });

        // Ação para o botão "Atualizar"
        submitButton.addActionListener(e -> {
            // Capturar valores dos campos
            Long id = Long.parseLong(idField.getText());
            String title = titleField.getText();
            String description = descriptionField.getText();
            BigDecimal price = new BigDecimal(priceField.getText());

            // Criação da DTO com os valores capturados
            ProductRequestDTO productRequestDTO = new ProductRequestDTO();
            productRequestDTO.setTitle(title);
            productRequestDTO.setDescription(description);
            productRequestDTO.setPrice(price);

            // Chamar a API para atualizar os dados do cliente
            ProductApi productApi = new ProductApi();
            ProductResponseDTO updateProduct = productApi.updateProduct(id, productRequestDTO);

            // Se o cliente foi atualizado com sucesso, mostrar mensagem de sucesso
            if (updateProduct != null) {
                JOptionPane.showMessageDialog(null,
                        "Cliente atualizado com sucesso!",
                        "Sucesso", JOptionPane.INFORMATION_MESSAGE);
            }
        });

        // Ação para o botão "Limpar"
        clearButton.addActionListener(e -> {
            idField.setText("");
            titleField.setText("");
            descriptionField.setText("");
            priceField.setText("");
        });

        return formPanel;
    }
}
