package com.starless.http;

import lombok.*;
import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class HttpResponse<T> {
    @Builder.Default
    private int status = 200;
    private Map<String, String> headers;
    private T body;
}
