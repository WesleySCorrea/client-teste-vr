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
            } else if (response.statusCode() == 403) {
                JOptionPane.showMessageDialog(null,
                        "Credito insuficiente para incluir o produto no pedido",
                        "Erro", JOptionPane.ERROR_MESSAGE);
                return null;
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
}
