package org.example.options.product;

import lombok.Getter;

import javax.swing.*;
import java.awt.*;

@Getter
public class ProductOptionsPanel {

    private JButton addProductButton;
    private JButton editProductButton;
    private JButton deleteProductButton;
    private JButton listProductsButton;

    public JPanel createProductOptionsPanel() {
        // Painel para os botões de opções
        JPanel optionsPanel = new JPanel();
        optionsPanel.setLayout(new BoxLayout(optionsPanel, BoxLayout.Y_AXIS));
        optionsPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Criar os botões para as opções de produtos
        this.addProductButton = new JButton("Cadastrar Produto");
        this.editProductButton = new JButton("Editar Produto");
        this.deleteProductButton = new JButton("Excluir Produto");
        this.listProductsButton = new JButton("Listar Produtos");

        // Define largura fixa para os botões
        Dimension buttonSize = new Dimension(150, 30);
        this.addProductButton.setMaximumSize(buttonSize);
        this.editProductButton.setMaximumSize(buttonSize);
        this.deleteProductButton.setMaximumSize(buttonSize);
        this.listProductsButton.setMaximumSize(buttonSize);

        // Alinhamento à esquerda
        this.addProductButton.setAlignmentX(Component.LEFT_ALIGNMENT);
        this.editProductButton.setAlignmentX(Component.LEFT_ALIGNMENT);
        this.deleteProductButton.setAlignmentX(Component.LEFT_ALIGNMENT);
        this.listProductsButton.setAlignmentX(Component.LEFT_ALIGNMENT);

        // Adicionar botões ao painel de opções
        optionsPanel.add(addProductButton);
        optionsPanel.add(Box.createRigidArea(new Dimension(0, 10))); // Espaçamento entre os botões
        optionsPanel.add(editProductButton);
        optionsPanel.add(Box.createRigidArea(new Dimension(0, 10))); // Espaçamento entre os botões
        optionsPanel.add(deleteProductButton);
        optionsPanel.add(Box.createRigidArea(new Dimension(0, 10))); // Espaçamento entre os botões
        optionsPanel.add(listProductsButton);

        return optionsPanel;
    }
}