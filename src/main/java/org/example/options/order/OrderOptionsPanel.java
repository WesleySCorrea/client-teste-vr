package org.example.options.order;

import javax.swing.*;
import java.awt.*;

public class OrderOptionsPanel {

    public JPanel createOrderOptionsPanel() {
        // Painel para os botões de opções de pedido
        JPanel optionsPanel = new JPanel();
        optionsPanel.setLayout(new BoxLayout(optionsPanel, BoxLayout.Y_AXIS));
        optionsPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Adiciona uma margem ao redor do painel

        // Criar os botões para as opções de pedido
        JButton addOrderButton = new JButton("Iniciar Pedido");
        JButton editOrderButton = new JButton("Editar Pedido");
        JButton deleteOrderButton = new JButton("Excluir Pedido");
        JButton listOrdersButton = new JButton("Listar Pedidos");

        // Define largura fixa para os botões
        Dimension buttonSize = new Dimension(150, 30);
        addOrderButton.setMaximumSize(buttonSize);
        editOrderButton.setMaximumSize(buttonSize);
        deleteOrderButton.setMaximumSize(buttonSize);
        listOrdersButton.setMaximumSize(buttonSize);

        // Alinhamento à esquerda
        addOrderButton.setAlignmentX(Component.LEFT_ALIGNMENT);
        editOrderButton.setAlignmentX(Component.LEFT_ALIGNMENT);
        deleteOrderButton.setAlignmentX(Component.LEFT_ALIGNMENT);
        listOrdersButton.setAlignmentX(Component.LEFT_ALIGNMENT);

        // Adicionar botões ao painel de opções
        optionsPanel.add(addOrderButton);
        optionsPanel.add(Box.createRigidArea(new Dimension(0, 10))); // Espaçamento entre os botões
        optionsPanel.add(editOrderButton);
        optionsPanel.add(Box.createRigidArea(new Dimension(0, 10))); // Espaçamento entre os botões
        optionsPanel.add(deleteOrderButton);
        optionsPanel.add(Box.createRigidArea(new Dimension(0, 10))); // Espaçamento entre os botões
        optionsPanel.add(listOrdersButton);

        return optionsPanel;
    }
}