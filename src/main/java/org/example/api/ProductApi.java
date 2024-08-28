package org.example.api;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.api.dto.request.ProductRequestDTO;
import org.example.api.dto.response.ProductResponseDTO;
import org.example.config.PageConfig;

import javax.swing.*;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

public class ProductApi {

    private static final String BASE_URL = "http://localhost:8080/products";

    public ProductResponseDTO sendProductData(ProductRequestDTO productRequestDTO) {
        try {
            // Serializar a DTO para JSON
            ObjectMapper objectMapper = new ObjectMapper();
            String requestBody = objectMapper.writeValueAsString(productRequestDTO);

            // Construir a requisição HTTP POST
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(new URI(BASE_URL))
                    .header("Content-Type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                    .build();

            // Enviar a requisição e obter a resposta
            HttpClient product = HttpClient.newHttpClient();
            HttpResponse<String> response = product.send(request, HttpResponse.BodyHandlers.ofString());

            // Verificar o status da resposta
            if (response.statusCode() == 201) { // HTTP 201 CREATED
                // Mapear a resposta para o ProductResponseDTO
                return objectMapper.readValue(response.body(), ProductResponseDTO.class);
            } else {
                // Se a resposta não for CREATED, mostrar a mensagem de erro
                JOptionPane.showMessageDialog(null,
                        "Erro ao cadastrar produto: " + response.body(),
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

    public ProductResponseDTO findProductById(Long productId) {
        try {
            // Construir a URI para a requisição GET
            URI uri = new URI(BASE_URL + "/" + productId);

            // Construir a requisição HTTP GET
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(uri)
                    .header("Content-Type", "application/json")
                    .GET()
                    .build();

            // Enviar a requisição e obter a resposta
            HttpClient product = HttpClient.newHttpClient();
            HttpResponse<String> response = product.send(request, HttpResponse.BodyHandlers.ofString());

            // Verificar o status da resposta
            if (response.statusCode() == 200) { // HTTP 200 OK
                // Mapear a resposta para o ProductResponseDTO
                ObjectMapper objectMapper = new ObjectMapper();
                return objectMapper.readValue(response.body(), ProductResponseDTO.class);
            } else {
                // Se a resposta não for OK, mostrar a mensagem de erro
                JOptionPane.showMessageDialog(null,
                        "Produto de id " + productId + " não encontrado",
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

    public ProductResponseDTO updateProduct(Long id, ProductRequestDTO productRequestDTO) {
        try {
            // Serializar a DTO para JSON
            ObjectMapper objectMapper = new ObjectMapper();
            String requestBody = objectMapper.writeValueAsString(productRequestDTO);

            // Construir a URI para a requisição PATCH
            URI uri = new URI(BASE_URL + "/" + id);

            // Construir a requisição HTTP PATCH
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(uri)
                    .header("Content-Type", "application/json")
                    .method("PATCH", HttpRequest.BodyPublishers.ofString(requestBody))
                    .build();

            // Enviar a requisição e obter a resposta
            HttpClient product = HttpClient.newHttpClient();
            HttpResponse<String> response = product.send(request, HttpResponse.BodyHandlers.ofString());

            // Verificar o status da resposta
            if (response.statusCode() == 200) { // HTTP 200 OK
                // Mapear a resposta para o ProductResponseDTO
                return objectMapper.readValue(response.body(), ProductResponseDTO.class);
            } else {
                // Se a resposta não for OK, mostrar a mensagem de erro
                JOptionPane.showMessageDialog(null,
                        "Erro ao atualizar producto: " + response.body(),
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

    public boolean deleteProduct(Long id) {
        try {
            // Construir a URI para a requisição DELETE
            URI uri = new URI(BASE_URL + "/" + id);

            // Construir a requisição HTTP DELETE
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(uri)
                    .header("Content-Type", "application/json")
                    .DELETE()
                    .build();

            // Enviar a requisição e obter a resposta
            HttpClient product = HttpClient.newHttpClient();
            HttpResponse<String> response = product.send(request, HttpResponse.BodyHandlers.ofString());

            // Verificar o status da resposta
            if (response.statusCode() == 204) { // HTTP 204 NO CONTENT (Deleção bem-sucedida)
                return true;
            } else {
                // Se a resposta não for NO CONTENT, mostrar a mensagem de erro
                JOptionPane.showMessageDialog(null,
                        "Erro ao deletar producte de id " + id,
                        "Erro", JOptionPane.ERROR_MESSAGE);
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null,
                    "Erro ao conectar com o servidor: " + e.getMessage(),
                    "Erro", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }

    public List<ProductResponseDTO> findAllProductsByActiveIsTrue(int page, int size) {
        try {
            // Construir a URI com parâmetros de paginação
            URI uri = new URI(BASE_URL + "?page=" + page + "&size=" + size);

            // Construir a requisição HTTP GET
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(uri)
                    .header("Content-Type", "application/json")
                    .GET()
                    .build();

            // Enviar a requisição e obter a resposta
            HttpClient product = HttpClient.newHttpClient();
            HttpResponse<String> response = product.send(request, HttpResponse.BodyHandlers.ofString());

            // Verificar o status da resposta
            if (response.statusCode() == 200) { // HTTP 200 OK
                // Mapear a resposta para PagedResponse
                ObjectMapper objectMapper = new ObjectMapper();
                PageConfig<ProductResponseDTO> productPage = objectMapper.readValue(
                        response.body(),
                        new TypeReference<>() {}
                );
                return productPage.getContent();
            } else {
                JOptionPane.showMessageDialog(null,
                        "Erro ao buscar lista de produtos",
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
