package com.starless.http;

import lombok.*;

import java.util.HashMap;
import java.util.Map;
// import java.io.IOException;
// import com.fasterxml.jackson.databind.ObjectMapper;
// import com.fasterxml.jackson.core.type.TypeReference;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class HttpRequest<T> {
    private T body;
    @Builder.Default
    private Map<String, String> headers = new HashMap<>();
    @Builder.Default
    private Map<String, String> queryParams = new HashMap<>();
    @Builder.Default
    private Map<String, String> pathParams = new HashMap<>();
    private String url;
    private String path;
    private String method;

    public String getQueryParamOrDefault(String key, String defaultValue) {
        if (!queryParams.containsKey(key) || queryParams.get(key) == null || queryParams.get(key).isEmpty()) {
            queryParams.put(key, defaultValue);
        }
        return queryParams.get(key);
    }

    // public T getBodyDecoded() {
    // String contentType = this.headers.get("Content-Type");

    // if (contentType != null) {
    // if (contentType.contains("text/plain")) {
    // return (T) this.body;
    // } else if (contentType.contains("application/json")) {
    // ObjectMapper objectMapper = new ObjectMapper();
    // try {
    // return objectMapper.readValue((String) this.body, new TypeReference<T>() {
    // });
    // } catch (IOException e) {
    // throw new RuntimeException("Error parsing JSON", e);
    // }
    // }
    // }
    // throw new IllegalStateException("Content type not supported");
    // }
}
