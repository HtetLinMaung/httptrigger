package com.starless.http;

import lombok.*;

import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class HttpResponse<T> {
    @Builder.Default
    private int status = 200;
    @Builder.Default
    private Map<String, String> headers = new HashMap<>();
    private T body;
}
