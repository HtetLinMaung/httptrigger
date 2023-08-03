package com.starless.http;

import jakarta.ws.rs.core.MultivaluedMap;
import jakarta.ws.rs.core.UriInfo;
import jakarta.ws.rs.container.ContainerRequestContext;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;

public class RequestMapper {

    public static <T> HttpRequest<T> map(ContainerRequestContext ctx, Class<T> type) {
        UriInfo uriInfo = ctx.getUriInfo();
        MultivaluedMap<String, String> headers = ctx.getHeaders();
        String method = ctx.getMethod();

        Map<String, String> queryParams = new HashMap<>();
        for (Map.Entry<String, List<String>> entry : uriInfo.getQueryParameters().entrySet()) {
            queryParams.put(entry.getKey(), entry.getValue().get(0));
        }

        Map<String, String> headerMap = new HashMap<>();
        for (Map.Entry<String, List<String>> entry : headers.entrySet()) {
            headerMap.put(entry.getKey(), entry.getValue().get(0));
        }

        // Extracting path parameters
        Map<String, String> pathParams = new HashMap<>();
        for (Map.Entry<String, List<String>> entry : uriInfo.getPathParameters().entrySet()) {
            pathParams.put(entry.getKey(), entry.getValue().get(0));
        }

        T body = parseBody(ctx.getEntityStream(), type); // Parse the body to the expected type

        return HttpRequest.<T>builder()
                .body(body)
                .headers(headerMap)
                .url(uriInfo.getRequestUri().toString())
                .path(uriInfo.getPath())
                .queryParams(queryParams)
                .pathParams(pathParams)
                .method(method)
                .build();
    }

    private static <T> T parseBody(InputStream inputStream, Class<T> type) {
        // Use an ObjectMapper to convert the InputStream to the expected type
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.readValue(inputStream, type);
        } catch (IOException e) {
            throw new RuntimeException("Failed to parse body from request", e);
        }
    }
}
