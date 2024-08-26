package org.example.options.order;


import org.example.api.OrderApi;
import org.example.api.dto.response.OrderResponseDTO;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class OrderListFormPanel {

    private int currentPage = 0; // Página atual
    private int pageSize = 20; // Tamanho da página
    private final OrderApi orderApi;

    public OrderListFormPanel() {
        orderApi = new OrderApi(); // Inicializar a API
    }

    public JPanel createOrderListPanel() {
        JPanel listPanel = new JPanel(new BorderLayout());

        // Tabela para exibir os clientes
        String[] columnNames = {"Pedido ID", "Cliente ID", "Valor Total", "Finalizada"};
        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);
        JTable orderTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(orderTable);
        listPanel.add(scrollPane, BorderLayout.CENTER);

        // Botões de navegação para paginação
        JPanel navigationPanel = new JPanel();
        JButton prevButton = new JButton("Anterior");
        JButton nextButton = new JButton("Próximo");

        prevButton.addActionListener(e -> {
            if (currentPage > 0) {
                currentPage--;
                updateTableData(tableModel);
            }
        });

        nextButton.addActionListener(e -> {
            currentPage++;
            updateTableData(tableModel);
        });

        navigationPanel.add(prevButton);
        navigationPanel.add(nextButton);
        listPanel.add(navigationPanel, BorderLayout.SOUTH);

        // Carregar os dados da primeira página
        updateTableData(tableModel);

        return listPanel;
    }

    private void updateTableData(DefaultTableModel tableModel) {
        // Limpar a tabela
        tableModel.setRowCount(0);

        // Buscar a página de pedidos da API
        List<OrderResponseDTO> orders = orderApi.findAllOrders(currentPage, pageSize);

        // Adicionar os dados na tabela
        for (OrderResponseDTO order : orders) {
            Object[] rowData = {
                    order.getOrderId(),
                    order.getClientId(),
                    order.getTotalValue(),
                    order.getFinished()
            };
            tableModel.addRow(rowData);
        }
    }
}
