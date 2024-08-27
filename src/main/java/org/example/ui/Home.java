package org.example.ui;

import org.example.options.client.*;
import org.example.options.order.OrderDeleteFormPanel;
import org.example.options.order.OrderFormPanel;
import org.example.options.order.OrderListFormPanel;
import org.example.options.order.OrderOptionsPanel;
import org.example.options.product.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Home extends JFrame {

    private final JPanel rightPanel;

    public Home() {
        setTitle("Home");
        setSize(850, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Configuração do layout
        setLayout(new BorderLayout());

        // Painel à esquerda com os botões
        JPanel leftPanel = new JPanel();
        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS)); // Disposição vertical
        leftPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Adiciona espaçamento ao redor do painel
        leftPanel.setPreferredSize(new Dimension(160, getHeight())); // Define largura fixa para o painel

        JButton clientsButton = new JButton("Clientes");
        JButton productsButton = new JButton("Produtos");
        JButton ordersButton = new JButton("Pedidos");
        JButton backButton = new JButton("Voltar");

        // Define largura fixa para os botões
        Dimension buttonSize = new Dimension(150, 30);
        clientsButton.setMaximumSize(buttonSize);
        productsButton.setMaximumSize(buttonSize);
        ordersButton.setMaximumSize(buttonSize);
        backButton.setMaximumSize(buttonSize);

        // Alinhamento à esquerda
        clientsButton.setAlignmentX(Component.LEFT_ALIGNMENT);
        productsButton.setAlignmentX(Component.LEFT_ALIGNMENT);
        ordersButton.setAlignmentX(Component.LEFT_ALIGNMENT);
        backButton.setAlignmentX(Component.LEFT_ALIGNMENT);

        leftPanel.add(clientsButton);
        leftPanel.add(Box.createRigidArea(new Dimension(0, 10))); // Espaçamento entre os botões
        leftPanel.add(productsButton);
        leftPanel.add(Box.createRigidArea(new Dimension(0, 10))); // Espaçamento entre os botões
        leftPanel.add(ordersButton);
        leftPanel.add(Box.createRigidArea(new Dimension(0, 10))); // Espaçamento entre os botões
        leftPanel.add(backButton); // Adicionando o botão "Voltar"

        // Adicionar o painel à esquerda
        add(leftPanel, BorderLayout.WEST);

        // Painel que contém a linha e os botões à direita
        JPanel centerPanel = new JPanel(new BorderLayout());

        // Adicionar uma linha vertical
        JSeparator separator = new JSeparator(SwingConstants.VERTICAL);
        separator.setPreferredSize(new Dimension(1, getHeight())); // Define o tamanho da linha
        centerPanel.add(separator, BorderLayout.WEST);

        // Espaço à direita (inicialmente vazio)
        rightPanel = new JPanel(new BorderLayout());
        centerPanel.add(rightPanel, BorderLayout.CENTER);

        add(centerPanel, BorderLayout.CENTER);

        // Adicionar ações botão clientes
        clientsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showClientOptions();
            }
        });

        productsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showProductOptions();
            }
        });

        ordersButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showOrderOptions();
            }
        });
    }


    //INFORMAÇÕES DE CLIENTES
    public void showClientOptions() {
        // Limpar o painel à direita
        rightPanel.removeAll();

        // Usar a classe ClientOptionsPanel para criar o painel de opções
        ClientOptionsPanel clientOptionsPanel = new ClientOptionsPanel(); // Passando a instância do Home
        JPanel optionsPanel = clientOptionsPanel.createClientOptionsPanel();

        // Adicionar painel de opções ao painel direito
        rightPanel.add(optionsPanel, BorderLayout.WEST);

        // Adicionar uma linha vertical após os botões de opções
        JSeparator rightSeparator = new JSeparator(SwingConstants.VERTICAL);
        rightSeparator.setPreferredSize(new Dimension(1, getHeight()));
        rightPanel.add(rightSeparator, BorderLayout.CENTER);

        // Obtenha as referências dos botões diretamente da classe ClientOptionsPanel
        JButton addClientButton = clientOptionsPanel.getAddClientButton();
        JButton editClientButton = clientOptionsPanel.getEditClientButton();
        JButton deleteClientButton = clientOptionsPanel.getDeleteClientButton();
        JButton listClientsButton = clientOptionsPanel.getListClientsButton();

        // Ação para o botão "Cadastrar Cliente"
        addClientButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                rightPanel.removeAll();
                rightPanel.add(optionsPanel, BorderLayout.WEST);
                rightPanel.add(rightSeparator, BorderLayout.CENTER);
                showClientForm();
            }
        });

        // Ação para o botão "Editar Cliente"
        editClientButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                rightPanel.removeAll();
                rightPanel.add(optionsPanel, BorderLayout.WEST);
                rightPanel.add(rightSeparator, BorderLayout.CENTER);
                showEditClientForm(); // Mostrar o formulário de edição
            }
        });

        // Ação para o botão "Deletar Cliente"
        deleteClientButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                rightPanel.removeAll();
                rightPanel.add(optionsPanel, BorderLayout.WEST);
                rightPanel.add(rightSeparator, BorderLayout.CENTER);
                showDeleteClientForm(); // Mostrar o formulário de deleção
            }
        });

        listClientsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                rightPanel.removeAll();
                rightPanel.add(optionsPanel, BorderLayout.WEST);
                rightPanel.add(rightSeparator, BorderLayout.CENTER);
                showListClientForm(); // Listar Clientes
            }
        });

        // Atualizar o painel
        rightPanel.revalidate();
        rightPanel.repaint();
    }

    public void showClientForm() {
        // Limpar o painel da direita, mas mantendo o painel de opções
        JPanel formContainer = new JPanel(new FlowLayout(FlowLayout.LEFT)); // Mudar de BorderLayout para FlowLayout.LEFT

        // Usar a classe ClientFormPanel para criar o formulário
        ClientFormPanel clientFormPanel = new ClientFormPanel();
        JPanel formPanel = clientFormPanel.createClientFormPanel();

        // Adicionar o formulário à direita dos botões de opções
        formContainer.add(formPanel);

        // Adicionar o formulário ao painel direito sem remover os botões
        rightPanel.add(formContainer, BorderLayout.CENTER);

        // Atualizar o painel
        rightPanel.revalidate();
        rightPanel.repaint();
    }

    public void showEditClientForm() {
        // Limpar o painel da direita, mas mantendo o painel de opções
        JPanel formContainer = new JPanel(new FlowLayout(FlowLayout.LEFT)); // Mudar de BorderLayout para FlowLayout.LEFT

        // Usar a classe ClientEditFormPanel para criar o formulário de edição
        ClientEditFormPanel clientEditFormPanel = new ClientEditFormPanel();
        JPanel formPanel = clientEditFormPanel.createEditClientFormPanel();

        // Adicionar o formulário à direita dos botões de opções
        formContainer.add(formPanel);

        // Adicionar o formulário ao painel direito sem remover os botões
        rightPanel.add(formContainer, BorderLayout.CENTER);

        // Atualizar o painel
        rightPanel.revalidate();
        rightPanel.repaint();
    }

    public void showDeleteClientForm() {
        // Limpar o painel da direita, mas mantendo o painel de opções
        JPanel formContainer = new JPanel(new FlowLayout(FlowLayout.LEFT)); // Mudar de BorderLayout para FlowLayout.LEFT

        // Usar a classe ClientEditFormPanel para criar o formulário de edição
        ClientDeleteFormPanel clientDeleteFormPanel = new ClientDeleteFormPanel();
        JPanel formPanel = clientDeleteFormPanel.deleteClientFormPanel();

        // Adicionar o formulário à direita dos botões de opções
        formContainer.add(formPanel);

        // Adicionar o formulário ao painel direito sem remover os botões
        rightPanel.add(formContainer, BorderLayout.CENTER);

        // Atualizar o painel
        rightPanel.revalidate();
        rightPanel.repaint();
    }

    public void showListClientForm() {
        // Limpar o painel da direita, mas mantendo o painel de opções
        JPanel formContainer = new JPanel(new FlowLayout(FlowLayout.LEFT)); // Mudar de BorderLayout para FlowLayout.LEFT

        // Criar o painel da lista de clientes
        ClientListFormPanel clientListFormPanel = new ClientListFormPanel();
        JPanel listPanel = clientListFormPanel.createClientListPanel();

        // Adicionar o painel da lista ao painel direito
        rightPanel.add(listPanel, BorderLayout.CENTER);

        // Atualizar o painel
        rightPanel.revalidate();
        rightPanel.repaint();
    }

    //INFORMAÇÕES DE PRODUTOS
    public void showProductOptions() {
        // Limpar o painel à direita
        rightPanel.removeAll();

        // Usar a classe ProductOptionsPanel para criar o painel de opções
        ProductOptionsPanel productOptionsPanel = new ProductOptionsPanel();
        JPanel optionsPanel = productOptionsPanel.createProductOptionsPanel();

        // Adicionar painel de opções ao painel direito
        rightPanel.add(optionsPanel, BorderLayout.WEST);

        // Adicionar uma linha vertical após os botões de opções
        JSeparator rightSeparator = new JSeparator(SwingConstants.VERTICAL);
        rightSeparator.setPreferredSize(new Dimension(1, getHeight()));
        rightPanel.add(rightSeparator, BorderLayout.CENTER);

        // Obtenha as referências dos botões diretamente da classe ProductOptionsPanel
        JButton addProductButton = productOptionsPanel.getAddProductButton();
        JButton editProductButton = productOptionsPanel.getEditProductButton();
        JButton deleteProductButton = productOptionsPanel.getDeleteProductButton();
        JButton listProductButton = productOptionsPanel.getListProductsButton();

        // Ação para o botão "Cadastrar Produto"
        addProductButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                rightPanel.removeAll();
                rightPanel.add(optionsPanel, BorderLayout.WEST);
                rightPanel.add(rightSeparator, BorderLayout.CENTER);
                showProductForm();
            }
        });

        // Ação para o botão "Editar Produto"
        editProductButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                rightPanel.removeAll();
                rightPanel.add(optionsPanel, BorderLayout.WEST);
                rightPanel.add(rightSeparator, BorderLayout.CENTER);
                showEditProductForm(); // Mostrar o formulário de edição
            }
        });

        // Ação para o botão "Deletar Produto"
        deleteProductButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                rightPanel.removeAll();
                rightPanel.add(optionsPanel, BorderLayout.WEST);
                rightPanel.add(rightSeparator, BorderLayout.CENTER);
                showDeleteProductForm(); // Mostrar o formulário de deleção
            }
        });

        // Ação para o botão "Listar Produtos"
        listProductButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                rightPanel.removeAll();
                rightPanel.add(optionsPanel, BorderLayout.WEST);
                rightPanel.add(rightSeparator, BorderLayout.CENTER);
                showListProductForm(); // Listar Produtos
            }
        });

        // Atualizar o painel
        rightPanel.revalidate();
        rightPanel.repaint();
    }

    public void showProductForm() {
        // Limpar o painel da direita, mas mantendo o painel de opções
        JPanel formContainer = new JPanel(new FlowLayout(FlowLayout.LEFT)); // Mudar de BorderLayout para FlowLayout.LEFT

        // Usar a classe ProductFormPanel para criar o formulário
        ProductFormPanel productFormPanel = new ProductFormPanel();
        JPanel formPanel = productFormPanel.createProductFormPanel();

        // Adicionar o formulário à direita dos botões de opções
        formContainer.add(formPanel);

        // Adicionar o formulário ao painel direito sem remover os botões
        rightPanel.add(formContainer, BorderLayout.CENTER);

        // Atualizar o painel
        rightPanel.revalidate();
        rightPanel.repaint();
    }

    public void showEditProductForm() {
        // Limpar o painel da direita, mas mantendo o painel de opções
        JPanel formContainer = new JPanel(new FlowLayout(FlowLayout.LEFT)); // Mudar de BorderLayout para FlowLayout.LEFT

        // Usar a classe ProductEditFormPanel para criar o formulário de edição
        ProductEditFormPanel productEditFormPanel = new ProductEditFormPanel();
        JPanel formPanel = productEditFormPanel.createEditProductFormPanel();

        // Adicionar o formulário à direita dos botões de opções
        formContainer.add(formPanel);

        // Adicionar o formulário ao painel direito sem remover os botões
        rightPanel.add(formContainer, BorderLayout.CENTER);

        // Atualizar o painel
        rightPanel.revalidate();
        rightPanel.repaint();
    }

    public void showDeleteProductForm() {
        // Limpar o painel da direita, mas mantendo o painel de opções
        JPanel formContainer = new JPanel(new FlowLayout(FlowLayout.LEFT)); // Mudar de BorderLayout para FlowLayout.LEFT

        // Usar a classe ProductEditFormPanel para criar o formulário de edição
        ProductDeleteFormPanel productDeleteFormPanel = new ProductDeleteFormPanel();
        JPanel formPanel = productDeleteFormPanel.deleteProductFormPanel();

        // Adicionar o formulário à direita dos botões de opções
        formContainer.add(formPanel);

        // Adicionar o formulário ao painel direito sem remover os botões
        rightPanel.add(formContainer, BorderLayout.CENTER);

        // Atualizar o painel
        rightPanel.revalidate();
        rightPanel.repaint();
    }

    public void showListProductForm() {
        // Limpar o painel da direita, mas mantendo o painel de opções
        JPanel formContainer = new JPanel(new FlowLayout(FlowLayout.LEFT)); // Mudar de BorderLayout para FlowLayout.LEFT

        // Criar o painel da lista de produtos
        ProductListFormPanel productListFormPanel = new ProductListFormPanel();
        JPanel listPanel = productListFormPanel.createProductListPanel();

        // Adicionar o painel da lista ao painel direito
        rightPanel.add(listPanel, BorderLayout.CENTER);

        // Atualizar o painel
        rightPanel.revalidate();
        rightPanel.repaint();
    }


    //INFORMAÇÕES DE PEDIDOS
    public void showOrderOptions() {
        // Limpar o painel à direita
        rightPanel.removeAll();

        // Usar a classe OrderOptionsPanel para criar o painel de opções
        OrderOptionsPanel orderOptionsPanel = new OrderOptionsPanel();
        JPanel optionsPanel = orderOptionsPanel.createOrderOptionsPanel();

        // Adicionar painel de opções ao painel direito
        rightPanel.add(optionsPanel, BorderLayout.WEST);

        // Adicionar uma linha vertical após os botões de opções
        JSeparator rightSeparator = new JSeparator(SwingConstants.VERTICAL);
        rightSeparator.setPreferredSize(new Dimension(1, getHeight()));
        rightPanel.add(rightSeparator, BorderLayout.CENTER);

        // Obtenha as referências dos botões diretamente da classe OrderOptionsPanel
        JButton addOrderButton = orderOptionsPanel.getAddOrderButton();
        JButton editOrderButton = orderOptionsPanel.getEditOrderButton();
        JButton deleteOrderButton = orderOptionsPanel.getDeleteOrderButton();
        JButton listOrderButton = orderOptionsPanel.getListOrdersButton();

        // Ação para o botão "Cadastrar Pedido"
        addOrderButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                rightPanel.removeAll();
                rightPanel.add(optionsPanel, BorderLayout.WEST);
                rightPanel.add(rightSeparator, BorderLayout.CENTER);
                showOrderForm();
            }
        });

        // Ação para o botão "Deletar Pedido"
        deleteOrderButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                rightPanel.removeAll();
                rightPanel.add(optionsPanel, BorderLayout.WEST);
                rightPanel.add(rightSeparator, BorderLayout.CENTER);
                showDeleteOrderForm(); // Mostrar o formulário de deleção
            }
        });

        // Ação para o botão "Listar Produtos"
        listOrderButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                rightPanel.removeAll();
                rightPanel.add(optionsPanel, BorderLayout.WEST);
                rightPanel.add(rightSeparator, BorderLayout.CENTER);
                showListOrderForm(); // Listar Pedidos
            }
        });

        // Atualizar o painel
        rightPanel.revalidate();
        rightPanel.repaint();
    }

    public void showOrderForm() {
        // Limpar o painel da direita, mas mantendo o painel de opções
        JPanel formContainer = new JPanel(new FlowLayout(FlowLayout.LEFT)); // Mudar de BorderLayout para FlowLayout.LEFT

        // Usar a classe OrderFormPanel para criar o formulário
        OrderFormPanel orderFormPanel = new OrderFormPanel();
        JPanel formPanel = orderFormPanel.createOrderFormPanel();

        // Adicionar o formulário à direita dos botões de opções
        formContainer.add(formPanel);

        // Adicionar o formulário ao painel direito sem remover os botões
        rightPanel.add(formContainer, BorderLayout.CENTER);

        // Atualizar o painel
        rightPanel.revalidate();
        rightPanel.repaint();
    }

    public void showDeleteOrderForm() {
        // Limpar o painel da direita, mas mantendo o painel de opções
        JPanel formContainer = new JPanel(new FlowLayout(FlowLayout.LEFT)); // Mudar de BorderLayout para FlowLayout.LEFT

        // Usar a classe ProductEditFormPanel para criar o formulário de edição
        OrderDeleteFormPanel orderDeleteFormPanel = new OrderDeleteFormPanel();
        JPanel formPanel = orderDeleteFormPanel.deleteOrderFormPanel();

        // Adicionar o formulário à direita dos botões de opções
        formContainer.add(formPanel);

        // Adicionar o formulário ao painel direito sem remover os botões
        rightPanel.add(formContainer, BorderLayout.CENTER);

        // Atualizar o painel
        rightPanel.revalidate();
        rightPanel.repaint();
    }

    public void showListOrderForm() {
        // Limpar o painel da direita, mas mantendo o painel de opções
        JPanel formContainer = new JPanel(new FlowLayout(FlowLayout.LEFT)); // Mudar de BorderLayout para FlowLayout.LEFT

        // Criar o painel da lista de produtos
        OrderListFormPanel orderListFormPanel = new OrderListFormPanel();
        JPanel listPanel = orderListFormPanel.createOrderListPanel();

        // Adicionar o painel da lista ao painel direito
        rightPanel.add(listPanel, BorderLayout.CENTER);

        // Atualizar o painel
        rightPanel.revalidate();
        rightPanel.repaint();
    }
}