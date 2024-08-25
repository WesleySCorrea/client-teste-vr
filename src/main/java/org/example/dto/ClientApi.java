package org.example.dto;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.dto.request.ClientRequestDTO;
import org.example.dto.response.ClientResponseDTO;

import javax.swing.*;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ClientApi {

    private static final String BASE_URL = "http://localhost:8080";

    public ClientResponseDTO sendClientData(ClientRequestDTO clientRequestDTO) {
        try {
            // Serializar a DTO para JSON
            ObjectMapper objectMapper = new ObjectMapper();
            String requestBody = objectMapper.writeValueAsString(clientRequestDTO);

            // Construir a requisição HTTP POST
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(new URI(BASE_URL + "/client"))
                    .header("Content-Type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                    .build();

            // Enviar a requisição e obter a resposta
            HttpClient client = HttpClient.newHttpClient();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            // Verificar o status da resposta
            if (response.statusCode() == 201) { // HTTP 201 CREATED
                // Mapear a resposta para o ClientResponseDTO
                return objectMapper.readValue(response.body(), ClientResponseDTO.class);
            } else {
                // Se a resposta não for CREATED, mostrar a mensagem de erro
                JOptionPane.showMessageDialog(null,
                        "Erro ao cadastrar cliente: " + response.body(),
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
