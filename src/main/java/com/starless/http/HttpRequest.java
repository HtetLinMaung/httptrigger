package com.starless.http;

import lombok.*;
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
    private Map<String, String> headers;
    private Map<String, String> queryParams;
    private Map<String, String> pathParams;
    private String url;
    private String path;
    private String method;

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
