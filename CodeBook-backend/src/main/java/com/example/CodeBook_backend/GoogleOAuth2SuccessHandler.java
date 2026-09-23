package com.example.CodeBook_backend;

import com.example.CodeBook_backend.Service.JwtService;
import com.example.CodeBook_backend.Service.UserService;
import com.example.CodeBook_backend.dto.CustomUserDetails;
import com.example.CodeBook_backend.model.Users;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.ResponseCookie;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@Component
public class GoogleOAuth2SuccessHandler
        extends SavedRequestAwareAuthenticationSuccessHandler {

    private final UserService userService;
    private final JwtService jwtService;

    public GoogleOAuth2SuccessHandler(
            UserService userService,
            JwtService jwtService) {

        this.userService = userService;
        this.jwtService = jwtService;
    }

    @Override
    public void onAuthenticationSuccess(
            HttpServletRequest request,
            HttpServletResponse response,
            Authentication authentication)
            throws IOException, ServletException {

        OAuth2User oauthUser =
                (OAuth2User) authentication.getPrincipal();

        String email = oauthUser.getAttribute("email");
        String name = oauthUser.getAttribute("name");

        Users user = userService.findByEmail(email);

        if (user == null) {

            user = new Users();

            user.setEmail(email);
            user.setUsername(name);
            user.setProvider("GOOGLE");

            user = userService.save(user);
        }

        CustomUserDetails userDetails =
                new CustomUserDetails(user);

        String token =
                jwtService.generateToken(userDetails);

        String frontendUrl = System.getenv("FRONTEND_URL");

        String redirectUrl =
                frontendUrl + "/oauth2/success"
                        + "?token=" + URLEncoder.encode(token, StandardCharsets.UTF_8)
                        + "&email=" + URLEncoder.encode(email, StandardCharsets.UTF_8)
                        + "&cbid=" + URLEncoder.encode(
                        String.valueOf(user.getId()),
                        StandardCharsets.UTF_8
                );

        response.sendRedirect(redirectUrl);
    }
}
