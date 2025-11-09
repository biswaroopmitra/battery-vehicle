package com.example.BatteryStateOfHealth.Battery.authentication.validation;

import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.function.Predicate;

@Component
public class RouteValidatorForJwt {
    public static final List<String> endpointsWithoutAuth = List.of(
            "/authentication/register",
            "/authentication/login"
    );

    public Predicate<ServerHttpRequest> isSecured =
            request -> endpointsWithoutAuth
                                        .stream()
                                        .noneMatch(uri -> request.getURI().getPath().contains(uri));
}
