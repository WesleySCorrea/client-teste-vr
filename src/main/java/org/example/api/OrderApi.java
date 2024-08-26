package org.example.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.api.dto.request.OrderRequestDTO;
import org.example.api.dto.response.OrderResponseDTO;

import javax.swing.*;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class OrderApi {

    private static final String BASE_URL = "http://localhost:8080/order";

    public OrderResponseDTO sendOrderData(OrderRequestDTO orderRequestDTO) {
        try {
            // Serializar a DTO para JSON
            ObjectMapper objectMapper = new ObjectMapper();
            String requestBody = objectMapper.writeValueAsString(orderRequestDTO);

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
                return objectMapper.readValue(response.body(), OrderResponseDTO.class);
            } else {
                // Se a resposta não for CREATED, mostrar a mensagem de erro
                JOptionPane.showMessageDialog(null,
                        "Erro ao cadastrar o pedido: " + response.body(),
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
}
