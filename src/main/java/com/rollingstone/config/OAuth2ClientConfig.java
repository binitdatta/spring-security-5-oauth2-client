package com.rollingstone.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import  org.springframework.security.oauth2.client.web.OAuth2AuthorizedClientRepository;
import  org.springframework.security.oauth2.client.web.reactive.function.client.ServletOAuth2AuthorizedClientExchangeFilterFunction;

@Configuration
public class OAuth2ClientConfig
{
    @Bean
    public RestTemplate restTemplate() {

        return new RestTemplate();
    }

    @Bean
    public WebClient webClient(ClientRegistrationRepository clientRegistrationRepository,
            OAuth2AuthorizedClientRepository oAuth2AuthorizedClientRepository) {
        ServletOAuth2AuthorizedClientExchangeFilterFunction oauth2 =
                new ServletOAuth2AuthorizedClientExchangeFilterFunction(clientRegistrationRepository,
                        oAuth2AuthorizedClientRepository);

        oauth2.setDefaultOAuth2AuthorizedClient(true);

        return WebClient.builder().apply(oauth2.oauth2Configuration()).build();
    }
}
