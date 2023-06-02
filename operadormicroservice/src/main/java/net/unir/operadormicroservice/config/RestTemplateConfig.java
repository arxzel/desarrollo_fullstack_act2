package net.unir.operadormicroservice.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestTemplateConfig {
	
	@Bean
	@LoadBalanced
	public RestTemplate restTemplate() {
		RestTemplate restTemplate = new RestTemplate();
		HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory();
        requestFactory.setConnectTimeout(5000);
        requestFactory.setReadTimeout(5000);

        restTemplate.setRequestFactory(requestFactory);
		return restTemplate;
	}
}
