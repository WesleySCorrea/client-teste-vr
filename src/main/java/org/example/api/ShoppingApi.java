package org.example.api;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.api.dto.request.ShoppingRequestDTO;
import org.example.api.dto.response.OrderResponseDTO;
import org.example.api.dto.response.ShoppingProductResponseDTO;
import org.example.api.dto.response.ShoppingResponseDTO;
import org.example.config.PageConfig;

import javax.swing.*;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

public class ShoppingApi {

    private static final String BASE_URL = "http://localhost:8080/shopping";

    public ShoppingResponseDTO addProductToOrder (ShoppingRequestDTO shoppingRequestDTO) {
        try {
            // Serializar a DTO para JSON
            ObjectMapper objectMapper = new ObjectMapper();
            String requestBody = objectMapper.writeValueAsString(shoppingRequestDTO);

            // Construir a requisição HTTP POST
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(new URI(BASE_URL))
                    .header("Content-Type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                    .build();

            // Enviar a requisição e obter a resposta
            HttpClient client = HttpClient.newHttpClient();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            // Verificar o status da resposta
            if (response.statusCode() == 201) { // HTTP 201 CREATED
                // Mapear a resposta para o ClientResponseDTO
                return objectMapper.readValue(response.body(), ShoppingResponseDTO.class);
            } else {
                // Se a resposta não for CREATED, mostrar a mensagem de erro
                JOptionPane.showMessageDialog(null,
                        "Erro ao incluir o produto no pedido",
                        "Erro", JOptionPane.ERROR_MESSAGE);
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null,
                    "Erro ao conectar com o servidor: " + e.getMessage(),
                    "Erro", JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }
    public List<ShoppingProductResponseDTO> findAllByOrderId(int page, int size, Long orderId) {
        try {
            // Construir a URI com parâmetros de paginação
            URI uri = new URI(BASE_URL + "/order/" + orderId + "/products?page=" + page + "&size=" + size);

            // Construir a requisição HTTP GET
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(uri)
                    .header("Content-Type", "application/json")
                    .GET()
                    .build();

            // Enviar a requisição e obter a resposta
            HttpClient client = HttpClient.newHttpClient();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            // Verificar o status da resposta
            if (response.statusCode() == 200) { // HTTP 200 OK
                // Mapear a resposta para PagedResponse
                ObjectMapper objectMapper = new ObjectMapper();
                PageConfig<ShoppingProductResponseDTO> orderPage = objectMapper.readValue(
                        response.body(),
                        new TypeReference<>() {}
                );
                return orderPage.getContent();
            } else {
                JOptionPane.showMessageDialog(null,
                        "Erro ao buscar os items do pedido",
                        "Erro", JOptionPane.ERROR_MESSAGE);
                return List.of();
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null,
                    "Erro ao conectar com o servidor: " + e.getMessage(),
                    "Erro", JOptionPane.ERROR_MESSAGE);
            return List.of();
        }
    }

//
//    public OrderResponseDTO findOrderById(Long orderId) {
//        try {
//            // Construir a URI para a requisição GET
//            URI uri = new URI(BASE_URL + "/" + orderId);
//
//            // Construir a requisição HTTP GET
//            HttpRequest request = HttpRequest.newBuilder()
//                    .uri(uri)
//                    .header("Content-Type", "application/json")
//                    .GET()
//                    .build();
//
//            // Enviar a requisição e obter a resposta
//            HttpClient client = HttpClient.newHttpClient();
//            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
//
//            // Verificar o status da resposta
//            if (response.statusCode() == 200) { // HTTP 200 OK
//                // Mapear a resposta para o OrderResponseDTO
//                ObjectMapper objectMapper = new ObjectMapper();
//                return objectMapper.readValue(response.body(), OrderResponseDTO.class);
//            } else {
//                // Se a resposta não for OK, mostrar a mensagem de erro
//                JOptionPane.showMessageDialog(null,
//                        "Pedido não encontrado: " + response.body(),
//                        "Erro", JOptionPane.ERROR_MESSAGE);
//                return null;
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//            JOptionPane.showMessageDialog(null,
//                    "Erro ao conectar com o servidor: " + e.getMessage(),
//                    "Erro", JOptionPane.ERROR_MESSAGE);
//            return null;
//        }
//    }
//
//    public ClientResponseDTO updateClient(Long id, ClientRequestDTO clientRequestDTO) {
//        try {
//            // Serializar a DTO para JSON
//            ObjectMapper objectMapper = new ObjectMapper();
//            String requestBody = objectMapper.writeValueAsString(clientRequestDTO);
//
//            // Construir a URI para a requisição PATCH
//            URI uri = new URI(BASE_URL + id);
//
//            // Construir a requisição HTTP PATCH
//            HttpRequest request = HttpRequest.newBuilder()
//                    .uri(uri)
//                    .header("Content-Type", "application/json")
//                    .method("PATCH", HttpRequest.BodyPublishers.ofString(requestBody))
//                    .build();
//
//            // Enviar a requisição e obter a resposta
//            HttpClient client = HttpClient.newHttpClient();
//            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
//
//            // Verificar o status da resposta
//            if (response.statusCode() == 200) { // HTTP 200 OK
//                // Mapear a resposta para o ClientResponseDTO
//                return objectMapper.readValue(response.body(), ClientResponseDTO.class);
//            } else {
//                // Se a resposta não for OK, mostrar a mensagem de erro
//                JOptionPane.showMessageDialog(null,
//                        "Erro ao atualizar cliente: " + response.body(),
//                        "Erro", JOptionPane.ERROR_MESSAGE);
//                return null;
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//            JOptionPane.showMessageDialog(null,
//                    "Erro ao conectar com o servidor: " + e.getMessage(),
//                    "Erro", JOptionPane.ERROR_MESSAGE);
//            return null;
//        }
//    }
//
//    public boolean deleteOrder(Long id) {
//        try {
//            // Construir a URI para a requisição DELETE
//            URI uri = new URI(BASE_URL + "/" + id);
//
//            // Construir a requisição HTTP DELETE
//            HttpRequest request = HttpRequest.newBuilder()
//                    .uri(uri)
//                    .header("Content-Type", "application/json")
//                    .DELETE()
//                    .build();
//
//            // Enviar a requisição e obter a resposta
//            HttpClient client = HttpClient.newHttpClient();
//            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
//
//            // Verificar o status da resposta
//            if (response.statusCode() == 204) { // HTTP 204 NO CONTENT (Deleção bem-sucedida)
//                return true;
//            } else if (response.statusCode() == 403) {
//                // Se a resposta não for NO CONTENT, mostrar a mensagem de erro
//                JOptionPane.showMessageDialog(null,
//                        "Proibido deletar um pedido finalizado.",
//                        "Erro", JOptionPane.ERROR_MESSAGE);
//                return false;
//            }else {
//                // Se a resposta não for NO CONTENT, mostrar a mensagem de erro
//                JOptionPane.showMessageDialog(null,
//                        "Erro ao deletar o pedido: ",
//                        "Erro", JOptionPane.ERROR_MESSAGE);
//                return false;
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//            JOptionPane.showMessageDialog(null,
//                    "Erro ao conectar com o servidor: " + e.getMessage(),
//                    "Erro", JOptionPane.ERROR_MESSAGE);
//            return false;
//        }
//    }
//
//    public List<OrderResponseDTO> findAllOrdersByClientId(int page, int size, Long clientId) {
//        try {
//            // Construir a URI com parâmetros de paginação
//            URI uri = new URI(BASE_URL + "/client/" + clientId + "?page=" + page + "&size=" + size);
//
//            // Construir a requisição HTTP GET
//            HttpRequest request = HttpRequest.newBuilder()
//                    .uri(uri)
//                    .header("Content-Type", "application/json")
//                    .GET()
//                    .build();
//
//            // Enviar a requisição e obter a resposta
//            HttpClient client = HttpClient.newHttpClient();
//            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
//
//            // Verificar o status da resposta
//            if (response.statusCode() == 200) { // HTTP 200 OK
//                // Mapear a resposta para PagedResponse
//                ObjectMapper objectMapper = new ObjectMapper();
//                PageConfig<OrderResponseDTO> orderPage = objectMapper.readValue(
//                        response.body(),
//                        new TypeReference<>() {}
//                );
//                return orderPage.getContent();
//            } else {
//                JOptionPane.showMessageDialog(null,
//                        "Erro ao buscar pedidos",
//                        "Erro", JOptionPane.ERROR_MESSAGE);
//                return List.of();
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//            JOptionPane.showMessageDialog(null,
//                    "Erro ao conectar com o servidor: " + e.getMessage(),
//                    "Erro", JOptionPane.ERROR_MESSAGE);
//            return List.of();
//        }
//
//    }    public List<OrderResponseDTO> findAllOrdersByProductId(int page, int size, Long productId) {
//        try {
//            // Construir a URI com parâmetros de paginação
//            URI uri = new URI(BASE_URL + "/product/" + productId + "?page=" + page + "&size=" + size);
//
//            // Construir a requisição HTTP GET
//            HttpRequest request = HttpRequest.newBuilder()
//                    .uri(uri)
//                    .header("Content-Type", "application/json")
//                    .GET()
//                    .build();
//
//            // Enviar a requisição e obter a resposta
//            HttpClient client = HttpClient.newHttpClient();
//            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
//
//            // Verificar o status da resposta
//            if (response.statusCode() == 200) { // HTTP 200 OK
//                // Mapear a resposta para PagedResponse
//                ObjectMapper objectMapper = new ObjectMapper();
//                PageConfig<OrderResponseDTO> orderPage = objectMapper.readValue(
//                        response.body(),
//                        new TypeReference<>() {}
//                );
//                return orderPage.getContent();
//            } else {
//                JOptionPane.showMessageDialog(null,
//                        "Erro ao buscar pedidos",
//                        "Erro", JOptionPane.ERROR_MESSAGE);
//                return List.of();
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//            JOptionPane.showMessageDialog(null,
//                    "Erro ao conectar com o servidor: " + e.getMessage(),
//                    "Erro", JOptionPane.ERROR_MESSAGE);
//            return List.of();
//        }
//    }
}
