package com.starless.http;

import java.util.HashMap;
import java.util.Map;

public class HttpUtils {
    public static Map<String, String> getCORSHeaders() {
        Map<String, String> headers = new HashMap<>();
        headers.put("Access-Control-Allow-Origin", "*"); // Or specify a certain domain
        headers.put("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT, OPTIONS, HEAD");
        headers.put("Access-Control-Allow-Headers", "Content-Type, Accept, X-Requested-With");
        return headers;
    }
}
