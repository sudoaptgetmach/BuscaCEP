package org.mach.http;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.mach.records.CEP;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ViaCEPClient {

    private static final HttpClient client = HttpClient.newHttpClient();
    private static final Gson gson = new Gson();

    public CEP requisicao(String input) throws IOException, InterruptedException {

        String endereco = "https://viacep.com.br/ws/" + input + "/json";

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(endereco))
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        String responseBody = response.body();

        JsonObject jsonResponse = JsonParser.parseString(responseBody).getAsJsonObject();
        if (jsonResponse.has("erro") && jsonResponse.get("erro").getAsBoolean()) {
            return new CEP(input, "N/A", "N/A", "N/A", "N/A", "N/A", "N/A", "N/A", "N/A");
        }

        return gson.fromJson(responseBody, CEP.class);
    }
}