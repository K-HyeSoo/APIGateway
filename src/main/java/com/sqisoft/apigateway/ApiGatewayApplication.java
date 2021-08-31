package com.sqisoft.apigateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;
import org.springframework.http.codec.ServerCodecConfigurer;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;


@SpringBootApplication
@EnableZuulProxy
public class ApiGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(ApiGatewayApplication.class, args);
        
        // jenkins 자동 빌드 테스트 1
    }

    @Bean
    public ServerCodecConfigurer serverCodecConfigurer(){
        return ServerCodecConfigurer.create();
    }

    @Bean
    public com.sqisoft.apigateway.config.filter.GatewayPreFilter preFilter(){
        return new com.sqisoft.apigateway.config.filter.GatewayPreFilter();
    }

    @Bean
    public com.sqisoft.apigateway.config.filter.GatewayPostFilter postFilter() {
        return new com.sqisoft.apigateway.config.filter.GatewayPostFilter();
    }
    @Bean
    public com.sqisoft.apigateway.config.filter.GatewayRouteFilter routeFilter() {
        return new com.sqisoft.apigateway.config.filter.GatewayRouteFilter();
    }
    @Bean
    public com.sqisoft.apigateway.config.filter.GatewayErrorFilter errorFilter() {
        return new com.sqisoft.apigateway.config.filter.GatewayErrorFilter();
    }

/*
  @Bean
  public CustomErrorFilter customFilter() {
    return new CustomErrorFilter();
  }
*/


    @Bean
    public CorsFilter corsFilter(){
        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        final CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
        config.addAllowedOrigin("*");
        config.addAllowedHeader("*");
        config.addAllowedMethod("OPTIONS");
        config.addAllowedMethod("HEAD");
        config.addAllowedMethod("GET");
        config.addAllowedMethod("PUT");
        config.addAllowedMethod("POST");
        config.addAllowedMethod("DELETE");
        config.addAllowedMethod("PATCH");
        source.registerCorsConfiguration("/**", config);
        return new CorsFilter(source);
    }

}