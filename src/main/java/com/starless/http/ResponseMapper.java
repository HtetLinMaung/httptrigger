package com.starless.http;

import jakarta.ws.rs.core.Response;
import java.util.Map;

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
}
