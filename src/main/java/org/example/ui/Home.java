package org.example.ui;

import org.example.options.client.ClientDeleteFormPanel;
import org.example.options.client.ClientEditFormPanel;
import org.example.options.client.ClientFormPanel;
import org.example.options.client.ClientOptionsPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Home extends JFrame {

    private final JPanel rightPanel;

    public Home() {
        setTitle("Home");
        setSize(800, 600);
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
    }

    public void showClientOptions() {
        // Limpar o painel à direita
        rightPanel.removeAll();

        // Usar a classe ClientOptionsPanel para criar o painel de opções
        ClientOptionsPanel clientOptionsPanel = new ClientOptionsPanel(this); // Passando a instância do Home
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
}
