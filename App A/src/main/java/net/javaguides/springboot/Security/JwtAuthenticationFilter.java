//package net.javaguides.springboot.Security;
//
//import com.auth0.jwt.JWT;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.fite.carhomework.models.DTOs.LoginDTO;
//import jdk.nashorn.internal.parser.Token;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.AuthenticationException;
//import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
//import org.springframework.web.bind.annotation.CrossOrigin;
//
//import javax.servlet.FilterChain;
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.Date;
//
//import static com.auth0.jwt.algorithms.Algorithm.HMAC512;
//
//
//public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
//    private AuthenticationManager authenticationManager;
//
//    public JwtAuthenticationFilter(AuthenticationManager authenticationManager) {
//        this.authenticationManager = authenticationManager;
//    }
//    /* Trigger when we issue POST request to /login
//    We also need to pass in {"username":"dan", "password":"dan123"} in the request body
//     */
//
//    @Override
//    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
//        // Grab credentials and map them to login viewmodel
//        LoginDTO credentials = null;
//        try {
//            credentials = new ObjectMapper().readValue(request.getInputStream(), LoginDTO.class);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        // Create login token
//        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
//                credentials.getUsername(),
//                credentials.getPassword(),
//                new ArrayList<>());
//
//        // Authenticate user
//        Authentication auth = authenticationManager.authenticate(authenticationToken);
//
//        return auth;
//    }
//
//
//    @Override
//    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
//        // Grab principal
//        UserPrincipal principal = (UserPrincipal) authResult.getPrincipal();
//
//        // Create JWT Token
//        String token = JWT.create()
//                .withSubject(principal.getUsername())
//                .withExpiresAt(new Date(System.currentTimeMillis() + JwtProperties.EXPIRATION_TIME))
//                .sign(HMAC512(JwtProperties.SECRET.getBytes()));
//
//        // Add token in response
//        response.getWriter().print(token);
//    }
//
//
//    @Override
//    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException, ServletException {
//        response.setStatus(401);
//    }
//}