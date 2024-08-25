package org.example.options.client;


import org.example.api.ClientApi;
import org.example.api.dto.response.ClientResponseDTO;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class ClientListFormPanel {

    private int currentPage = 0; // Página atual
    private int pageSize = 20; // Tamanho da página
    private final ClientApi clientApi;

    public ClientListFormPanel() {
        clientApi = new ClientApi(); // Inicializar a API
    }

    public JPanel createClientListPanel() {
        JPanel listPanel = new JPanel(new BorderLayout());

        // Tabela para exibir os clientes
        String[] columnNames = {"ID", "Nome", "Sobrenome", "Limite", "Dia Vencimento", "Status"};
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
        List<ClientResponseDTO> clients = clientApi.findAllClientsByActiveIsTrue(currentPage, pageSize);

        // Adicionar os dados na tabela
        for (ClientResponseDTO client : clients) {
            Object[] rowData = {
                    client.getId(),
                    client.getName(),
                    client.getLastName(),
                    client.getCreditLimit(),
                    client.getDueDate(),
                    client.getActive()
            };
            tableModel.addRow(rowData);
        }
    }
}
