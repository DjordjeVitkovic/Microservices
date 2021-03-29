//package com.djox.os.api;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpRequest;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.client.ClientHttpRequestExecution;
//import org.springframework.http.client.ClientHttpRequestInterceptor;
//import org.springframework.http.client.ClientHttpResponse;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.core.token.Token;
//import org.springframework.security.core.token.TokenService;
//import org.springframework.security.oauth2.core.OAuth2AccessToken;
//import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
//import org.springframework.util.StringUtils;
//
//import java.io.IOException;
//
//
//public class RequestResponseHandlerInterceptor implements ClientHttpRequestInterceptor {
//
//    private static final String AUTHORIZATION = "Authorization";
//
//    /**
//     * This method will intercept every request and response and based on response status code if its 401 then will retry
//     * once
//     */
//
//    @Override
//    public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {
//        ClientHttpResponse response = execution.execute(request, body);
//        JwtAuthenticationToken token = (JwtAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
//        if (HttpStatus.UNAUTHORIZED == response.getStatusCode()) {
//            String accessToken = token.getToken().getTokenValue();
//            if (!StringUtils.isEmpty(accessToken)) {
//                request.getHeaders().remove(AUTHORIZATION);
//                request.getHeaders().add(AUTHORIZATION, "Bearer " + accessToken);
//                //retry
//                response = execution.execute(request, body);
//            }
//        }
//        return response;
//    }
//
//}