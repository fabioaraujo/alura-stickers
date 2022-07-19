package br.com.fabioaraujo;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.List;
import java.util.Map;

/**
 * Hello world!
 *
 */
public class App {

    public static void main( String[] args ) throws IOException, InterruptedException{
        var url = "https://raw.githubusercontent.com/alexfelipe/imersao-java/json/top250.json";
        URI endereco = URI.create(url);
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder(endereco).GET().build();
        HttpResponse<String> response = client.send(request, BodyHandlers.ofString());

        String body = response.body();

        var parser = new JsonParser();
        List<Map<String, String>> listaDeFilmes = parser.parse(body);

        for (Map<String,String> filme : listaDeFilmes) {
            //cor magenta de fundo
            System.out.print("\u001b[45m"); 
            System.out.print(filme.get("title"));
            //cor preta de fundo
            System.out.println("\u001b[40m");
            System.out.println(filme.get("image"));
            String imdbRating = filme.get("imDbRating");
            double rating = Double.valueOf(imdbRating);
            System.out.print(imdbRating);
            
            // para visualizar caracteres UTF-8 no terminal executar comando "chcp 65001"
            for(int x = 0; x < rating; x++){
                System.out.print("\u2b50");
            }         
            System.out.println();
        }

    }
}
