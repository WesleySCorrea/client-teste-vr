package org.example.options.order;

import lombok.Getter;

import javax.swing.*;
import java.awt.*;

@Getter
public class OrderOptionsPanel {

    private JButton addOrderButton;
    private JButton editOrderButton;
    private JButton deleteOrderButton;
    private JButton listOrdersButton;

    public JPanel createOrderOptionsPanel() {
        // Painel para os botões de opções de pedido
        JPanel optionsPanel = new JPanel();
        optionsPanel.setLayout(new BoxLayout(optionsPanel, BoxLayout.Y_AXIS));
        optionsPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Adiciona uma margem ao redor do painel

        // Criar os botões para as opções de pedido
        this.addOrderButton = new JButton("Iniciar Pedido");
        this.editOrderButton = new JButton("Editar Pedido");
        this.deleteOrderButton = new JButton("Excluir Pedido");
        this.listOrdersButton = new JButton("Listar Pedidos");

        // Define largura fixa para os botões
        Dimension buttonSize = new Dimension(150, 30);
        this.addOrderButton.setMaximumSize(buttonSize);
        this.editOrderButton.setMaximumSize(buttonSize);
        this.deleteOrderButton.setMaximumSize(buttonSize);
        this.listOrdersButton.setMaximumSize(buttonSize);

        // Alinhamento à esquerda
        this.addOrderButton.setAlignmentX(Component.LEFT_ALIGNMENT);
        this.editOrderButton.setAlignmentX(Component.LEFT_ALIGNMENT);
        this.deleteOrderButton.setAlignmentX(Component.LEFT_ALIGNMENT);
        this.listOrdersButton.setAlignmentX(Component.LEFT_ALIGNMENT);

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