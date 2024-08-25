package org.example.options.product;


import org.example.api.ProductApi;
import org.example.api.dto.request.ProductRequestDTO;
import org.example.api.dto.response.ProductResponseDTO;

import javax.swing.*;
import java.awt.*;
import java.math.BigDecimal;

public class ProductFormPanel {

    public JPanel createProductFormPanel() {
        // Criar o painel de formulário
        JPanel formPanel = new JPanel();
        formPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 6, 5); // Espaçamento entre os componentes
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.WEST; // Alinhar os componentes à esquerda

        // Campos do formulário
        JLabel titleLabel = new JLabel("Titlo:");
        gbc.gridx = 0;
        gbc.gridy = 0;
        formPanel.add(titleLabel, gbc);

        JTextField titleField = new JTextField(15);
        gbc.gridx = 1;
        gbc.gridy = 0;
        formPanel.add(titleField, gbc);

        JLabel descriptionLabel = new JLabel("Descrição:");
        gbc.gridx = 0;
        gbc.gridy = 1;
        formPanel.add(descriptionLabel, gbc);

        JTextField descriptionField = new JTextField(15);
        gbc.gridx = 1;
        gbc.gridy = 1;
        formPanel.add(descriptionField, gbc);

        JLabel priceLabel = new JLabel("Preço:");
        gbc.gridx = 0;
        gbc.gridy = 2;
        formPanel.add(priceLabel, gbc);

        JTextField priceField = new JTextField(15);
        gbc.gridx = 1;
        gbc.gridy = 2;
        formPanel.add(priceField, gbc);

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
            String title = titleField.getText();
            String despription = descriptionField.getText();
            BigDecimal price = new BigDecimal(priceField.getText());

            // Criação da DTO com os valores capturados
            ProductRequestDTO productRequestDTO = new ProductRequestDTO();
            productRequestDTO.setTitle(title);
            productRequestDTO.setDescription(despription);
            productRequestDTO.setPrice(price);

            // Enviar a DTO para o endpoint REST e obter a resposta
            ProductApi productApi = new ProductApi();
            ProductResponseDTO response = productApi.sendProductData(productRequestDTO);

            if (response != null) {
                // Sucesso: Mostrar o objeto de resposta em um JOptionPane
                JOptionPane.showMessageDialog(null,
                        "Produto cadastrado com sucesso!\n" +
                                "ID: " + response.getId() + "\n" +
                                "Titulo: " + response.getTitle() + "\n" +
                                "Descrição: " + response.getDescription() + "\n" +
                                "Preço: " + response.getPrice() + "\n" +
                                "Ativo: " + response.getActive(),
                        "Sucesso", JOptionPane.INFORMATION_MESSAGE);

                // Limpar o formulário após o cadastro bem-sucedido
                titleField.setText("");
                descriptionField.setText("");
                priceField.setText("");
            } else {
                // Erro: Mostrar mensagem de erro
                JOptionPane.showMessageDialog(null,
                        "Erro ao cadastrar produto.",
                        "Erro", JOptionPane.ERROR_MESSAGE);
            }
        });

        // Ação para o botão "Limpar"
        clearButton.addActionListener(e -> {
            titleField.setText("");
            descriptionField.setText("");
            priceField.setText("");
        });

        return formPanel;
    }
}
