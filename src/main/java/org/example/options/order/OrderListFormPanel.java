package org.example.options.order;


import org.example.api.ClientApi;
import org.example.api.OrderApi;
import org.example.api.ProductApi;
import org.example.api.dto.response.ClientResponseDTO;
import org.example.api.dto.response.OrderResponseDTO;
import org.example.api.dto.response.ProductResponseDTO;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class OrderListFormPanel {

    private int currentPage = 0; // Página atual
    private final int pageSize = 20; // Tamanho da página
    private final OrderApi orderApi;
    private JPanel clientInfoPanel;
    private JPanel productInfoPanel;

    public OrderListFormPanel() {
        orderApi = new OrderApi(); // Inicializar a API
    }

    public JPanel createOrderListPanel() {
        // Criar o painel de informações do cliente
        clientInfoPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
        clientInfoPanel.setVisible(false); // Inicialmente escondido

        // Campo Nome
        JLabel nameLabel = new JLabel("Nome:");
        clientInfoPanel.add(nameLabel);

        JTextField nameField = new JTextField(10);
        nameField.setEditable(false);
        clientInfoPanel.add(nameField);

        // Campo Limite de Crédito
        JLabel creditLimitLabel = new JLabel("Limite de Crédito:");
        clientInfoPanel.add(creditLimitLabel);

        JTextField creditLimitField = new JTextField(10);
        creditLimitField.setEditable(false);
        clientInfoPanel.add(creditLimitField);

        productInfoPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
        productInfoPanel.setVisible(false); // Inicialmente escondido

        // Campo Nome
        JLabel titleLabel = new JLabel("Titulo:");
        productInfoPanel.add(titleLabel);

        JTextField titleField = new JTextField(10);
        titleField.setEditable(false);
        productInfoPanel.add(titleField);

        // Campo Limite de Crédito
        JLabel priceLabel = new JLabel("Preço Unitário R$:");
        productInfoPanel.add(priceLabel);

        JTextField priceField = new JTextField(10);
        priceField.setEditable(false);
        productInfoPanel.add(priceField);

        // Criar o painel de busca com GridBagLayout
        JPanel searchPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbcSearch = new GridBagConstraints();
        gbcSearch.insets = new Insets(5, 5, 5, 5); // Espaçamento entre os componentes
        gbcSearch.fill = GridBagConstraints.HORIZONTAL;
        gbcSearch.anchor = GridBagConstraints.WEST; // Alinhar os componentes à esquerda

        // Primeira linha de campos
        JLabel clientLabel = new JLabel("Cliente ID:");
        gbcSearch.gridx = 0;
        gbcSearch.gridy = 0;
        searchPanel.add(clientLabel, gbcSearch);

        JTextField clientTextField = new JTextField(15);
        gbcSearch.gridx = 1;
        gbcSearch.gridy = 0;
        searchPanel.add(clientTextField, gbcSearch);

        JButton clientButton = new JButton("Buscar por Cliente");
        gbcSearch.gridx = 2;
        gbcSearch.gridy = 0;
        searchPanel.add(clientButton, gbcSearch);

        // Segunda linha de campos
        JLabel productLabel = new JLabel("Produto ID:");
        gbcSearch.gridx = 0;
        gbcSearch.gridy = 1;
        searchPanel.add(productLabel, gbcSearch);

        JTextField productTextField = new JTextField(15);
        gbcSearch.gridx = 1;
        gbcSearch.gridy = 1;
        searchPanel.add(productTextField, gbcSearch);

        JButton productButton = new JButton("Buscar por Produto");
        gbcSearch.gridx = 2;
        gbcSearch.gridy = 1;
        searchPanel.add(productButton, gbcSearch);

        JPanel listPanel = new JPanel(new BorderLayout());

        // Adicionar o painel de busca e o painel de informações do cliente ao topo do listPanel
        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.add(searchPanel, BorderLayout.NORTH);
        topPanel.add(clientInfoPanel, BorderLayout.SOUTH); // Adicionar o painel de informações do cliente
        topPanel.add(productInfoPanel, BorderLayout.WEST); // Adicionar o painel de informações do produto

        listPanel.add(topPanel, BorderLayout.NORTH);

        // Adicionar uma borda entre o painel de informações do cliente e a tabela
        clientInfoPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Borda vazia para espaçamento

        // Adicionar divisória entre a tabela e os botões de navegação
        JPanel dividerPanel = new JPanel();
        dividerPanel.setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0, Color.BLACK)); // Borda preta em cima
        listPanel.add(dividerPanel, BorderLayout.AFTER_LINE_ENDS);

        // Tabela para exibir os pedidos
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

        // Adicionar ação ao botão de busca por cliente
        clientButton.addActionListener(e -> {
            String clientId = clientTextField.getText().trim();
            if (!clientId.isEmpty()) {
                // Buscar informações do cliente e atualizar os campos
                updateTableDataByClientId(tableModel, clientId);
                // Mostrar o painel de informações do cliente
                clientInfoPanel.setVisible(true);
                productInfoPanel.setVisible(false);
                ClientApi clientApi = new ClientApi();
                ClientResponseDTO client = clientApi.findClientById(Long.parseLong(clientId));
                nameField.setText(client.getName());
                creditLimitField.setText(String.valueOf(client.getCreditLimit()));
                productTextField.setText("");
            }
        });

        // Adicionar ação ao botão de busca por produto
        productButton.addActionListener(e -> {
            String productId = productTextField.getText().trim();
            if (!productId.isEmpty()) {
                // Buscar informações do produto e atualizar os campos
                updateTableDataByProductId(tableModel, productId);
                // Mostrar o painel de informações do produto
                clientInfoPanel.setVisible(false);
                productInfoPanel.setVisible(true);
                ProductApi productApi = new ProductApi();
                ProductResponseDTO product = productApi.findProductById(Long.parseLong(productId));
                titleField.setText(product.getTitle());
                priceField.setText(String.valueOf(product.getPrice()));
                clientTextField.setText("");
            }
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

        String status;
        // Adicionar os dados na tabela
        for (OrderResponseDTO order : orders) {
            if (order.getFinished()) {
                status = "Sim";
            } else status = "Não";

            Object[] rowData = {
                    order.getOrderId(),
                    order.getClientId(),
                    order.getTotalValue(),
                    status
            };
            tableModel.addRow(rowData);
        }
    }

    private void updateTableDataByClientId(DefaultTableModel tableModel, String clientId) {
        // Limpar a tabela
        tableModel.setRowCount(0);

        // Buscar a página de pedidos da API
        List<OrderResponseDTO> orders = orderApi.findAllOrdersByClientId(currentPage, pageSize, Long.parseLong(clientId));

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

    private void updateTableDataByProductId(DefaultTableModel tableModel, String productId) {
        // Limpar a tabela
        tableModel.setRowCount(0);

        // Buscar a página de pedidos da API
        List<OrderResponseDTO> orders = orderApi.findAllOrdersByProductId(currentPage, pageSize, Long.parseLong(productId));

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
