package com.example.BatteryStateOfHealth.Battery.authentication.validation;

import com.example.BatteryStateOfHealth.Battery.authentication.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class JwtValidationFilter extends AbstractGatewayFilterFactory<JwtValidationFilter.Config> {

    @Autowired
    private RouteValidatorForJwt routeValidator;

    @Autowired
    private JwtService jwtService;

    public JwtValidationFilter(){
        super();
    }


    @Override
    public GatewayFilter apply(Config config) {
        return ((exchange, chain) -> {
            if (routeValidator.isSecured.test(exchange.getRequest())){
                //check if the header contains the token
                if(!exchange.getRequest().getHeaders().containsKey(HttpHeaders.AUTHORIZATION)){
                    throw new RuntimeException("No authorisation header found.");
                }

                String authHeader = exchange.getRequest().getHeaders().get(HttpHeaders.AUTHORIZATION).get(0);
                if(authHeader!=null && authHeader.startsWith(AuthConstants.BEARER)){
                    authHeader=authHeader.substring(AuthConstants.BEARER.length());
                }
                try {
                    jwtService.validateJwt(authHeader);
                } catch (Exception e) {
                    throw new RuntimeException("Unauthorised access.");
                }
            }
            return chain.filter(exchange);
        });
    }

    public static class Config{

    }
}
