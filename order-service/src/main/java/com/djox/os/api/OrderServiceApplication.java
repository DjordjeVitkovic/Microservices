package com.djox.os.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;

@SpringBootApplication
@EnableEurekaClient
public class OrderServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(OrderServiceApplication.class, args);
    }

    @Bean
    @LoadBalanced
    public RestTemplate restTemplate() {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.setInterceptors(Collections.singletonList(new RequestResponseHandlerInterceptor()));
        return restTemplate;
        //return new RestTemplate();
    }

    @Bean("ribbon-template")
    @LoadBalanced
    public RestTemplate restTemplateWithRibbon() {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.setInterceptors(Collections.singletonList(new RequestResponseHandlerInterceptor()));
        return restTemplate;
    }

//    @KeycloakConfiguration
//    class SecConfig extends KeycloakWebSecurityConfigurerAdapter{
//
//        @Autowired
//        public void configureGlobal(AuthenticationManagerBuilder auth){
//            KeycloakAuthenticationProvider keycloakAuthenticationProvider = keycloakAuthenticationProvider();
//            keycloakAuthenticationProvider.setGrantedAuthoritiesMapper(new SimpleAuthorityMapper());
//            auth.authenticationProvider(keycloakAuthenticationProvider);
//        }
//
//        @Bean
//        @Override
//        protected SessionAuthenticationStrategy sessionAuthenticationStrategy() {
//            return new RegisterSessionAuthenticationStrategy(new SessionRegistryImpl());
//        }
//        @Bean
//        public KeycloakConfigResolver keycloakConfigResolver(){
//            return new KeycloakSpringBootConfigResolver();
//        }
//
//        @Override
//        protected void configure(HttpSecurity http) throws Exception {
//            super.configure(http);
//            http.
//                    authorizeRequests()
//                    .antMatchers("/order*").hasRole("user")
//                    .anyRequest().permitAll();
//        }
//    }

}
