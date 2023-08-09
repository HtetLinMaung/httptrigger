package com.starless.http;

import jakarta.ws.rs.core.Response;
import java.util.Map;

import com.microsoft.azure.functions.HttpRequestMessage;
import com.microsoft.azure.functions.HttpResponseMessage;
import com.microsoft.azure.functions.HttpStatus;

public class ResponseMapper {

    public static Response map(HttpResponse<?> httpResponse) {
        Response.ResponseBuilder responseBuilder = Response
                .status(httpResponse.getStatus())
                .entity(httpResponse.getBody());

        Map<String, String> headers = httpResponse.getHeaders();
        if (headers != null) {
            for (Map.Entry<String, String> header : headers.entrySet()) {
                responseBuilder.header(header.getKey(), header.getValue());
            }
        }

        return responseBuilder.build();
    }

    public static HttpResponseMessage map(HttpResponse<?> customResponse,
            HttpRequestMessage<?> azureRequest) {
        HttpResponseMessage.Builder responseBuilder = azureRequest
                .createResponseBuilder(HttpStatus.valueOf(customResponse.getStatus()));

        // Set headers
        for (Map.Entry<String, String> header : customResponse.getHeaders().entrySet()) {
            responseBuilder.header(header.getKey(), header.getValue());
        }

        // Set body
        responseBuilder.body(customResponse.getBody());

        return responseBuilder.build();
    }

}
