package org.example.options.product;


import org.example.api.ProductApi;
import org.example.api.dto.response.ProductResponseDTO;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class ProductListFormPanel {

    private int currentPage = 0; // Página atual
    private int pageSize = 20; // Tamanho da página
    private final ProductApi productApi;

    public ProductListFormPanel() {
        productApi = new ProductApi(); // Inicializar a API
    }

    public JPanel createProductListPanel() {
        JPanel listPanel = new JPanel(new BorderLayout());

        // Tabela para exibir os produtos
        String[] columnNames = {"ID", "Titulo", "Descrição", "Preço", "Status"};
        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);
        JTable clientTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(clientTable);
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

        // Buscar a página de clientes da API
        List<ProductResponseDTO> products = productApi.findAllProductsByActiveIsTrue(currentPage, pageSize);

        // Adicionar os dados na tabela
        for (ProductResponseDTO product : products) {
            Object[] rowData = {
                    product.getId(),
                    product.getTitle(),
                    product.getDescription(),
                    product.getPrice(),
                    product.getActive()
            };
            tableModel.addRow(rowData);
        }
    }
}
