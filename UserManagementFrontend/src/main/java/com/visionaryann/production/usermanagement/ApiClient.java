package com.visionaryann.production.usermanagement;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.net.http.*;
import java.net.URI;

public class ApiClient {
    private static final String BASE = "http://localhost:8080/api";
    private static String token;
    private static final HttpClient client = HttpClient.newHttpClient();
    private static final ObjectMapper mapper = new ObjectMapper();

    public static <T> T post(String path, Object body, Class<T> cls) throws Exception {
        var req = HttpRequest.newBuilder()
                .uri(URI.create(BASE + path))
                .header("Content-Type", "application/json")
                .header("Accept", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(mapper.writeValueAsString(body)))
                .build();
        var resp = client.send(req, HttpResponse.BodyHandlers.ofString());
        if (resp.statusCode() >= 400) throw new RuntimeException(resp.body());
        T result = mapper.readValue(resp.body(), cls);
        if (cls == com.visionaryann.production.usermanagement.payload.AuthResponse) {
            token = ((com.visionaryann.production.usermanagement.payload.AuthResponse) result).token;
        }
        return result;
    }

    public static <T> T get(String path, Class<T> cls) throws Exception {
        var builder = HttpRequest.newBuilder()
                .uri(URI.create(BASE + path))
                .header("Accept", "application/json");
        if (token != null) builder.header("Authorization", "Bearer " + token);
        var resp = client.send(builder.GET().build(), HttpResponse.BodyHandlers.ofString());
        if (resp.statusCode() >= 400) throw new RuntimeException(resp.body());
        return mapper.readValue(resp.body(), cls);
    }

    public static <T> T put(String path, Object body, Class<T> cls) throws Exception {
        var builder = HttpRequest.newBuilder()
                .uri(URI.create(BASE + path))
                .header("Content-Type", "application/json")
                .header("Accept", "application/json")
                .PUT(HttpRequest.BodyPublishers.ofString(mapper.writeValueAsString(body)));
        if (token != null) builder.header("Authorization", "Bearer " + token);
        var resp = client.send(builder.build(), HttpResponse.BodyHandlers.ofString());
        if (resp.statusCode() >= 400) throw new RuntimeException(resp.body());
        return mapper.readValue(resp.body(), cls);
    }
}
