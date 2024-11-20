package Service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ConsumoApi {


    public String ConsumirApi(String veiculoEscolhido, String endereco){
        String json;

        HttpClient client = HttpClient.newHttpClient();


        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(endereco))
                .GET()
                .build();

        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.
                    ofString());

            return json = response.body();
        } catch (IOException | InterruptedException e) {
            System.err.println("Erro ao consumir a API: " + e.getMessage());
            return "Não foi possível executar! ";
        }
    }

    public String ConsumirModelo(String veiculoEscolhido,String modelo){

        String json;
        String endereco = "https://parallelum.com.br/fipe/api/v1/" + veiculoEscolhido +  "/marcas/" + modelo + "/modelos";

        HttpClient client = HttpClient.newHttpClient();


        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(endereco))
                .GET()
                .build();

        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.
                    ofString());

            return json = response.body();
        } catch (IOException | InterruptedException e) {
            System.err.println("Erro ao consumir a API: " + e.getMessage());
            return "Não foi possível executar! ";
        }


    }
}
