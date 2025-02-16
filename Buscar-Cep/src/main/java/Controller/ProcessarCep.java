/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;



import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import javax.swing.JOptionPane;
import org.json.JSONObject;

/**
 *
 * @author Lucas Victor
 */
public class ProcessarCep {

    public JSONObject carregarEndereco(int cep) throws IOException, InterruptedException {
     //  EnderecoDTo endereco = null;

        //criando o cliente http
        HttpClient cliente = HttpClient.newHttpClient();

        // Criando a requisição HTTP
        HttpRequest requisicao = HttpRequest.newBuilder(URI.create("https://viacep.com.br/ws/" + cep + "/json/?callback=callback_name")).build();

        // Enviando a requisição e recebendo a resposta
        HttpResponse<String> resposta = cliente.send(requisicao, HttpResponse.BodyHandlers.ofString());

        // Obtendo a resposta JSONP e removendo o nome do callback e os parênteses
        String jsonString = resposta.body().replaceAll("^[^{]+|[^}]+$", "");

        // Convertendo a resposta JSON em um objeto JSONObject
        JSONObject json = new JSONObject(jsonString);

        if (json.has("erro")) {
            JOptionPane.showMessageDialog(null, "CEP não encontrado.");
            return null;
        }

        return json;

    }

}
