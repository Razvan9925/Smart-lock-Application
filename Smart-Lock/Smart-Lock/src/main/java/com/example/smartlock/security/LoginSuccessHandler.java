package com.example.smartlock.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class LoginSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
        SecurityUsers securityUsers = (SecurityUsers) authentication.getPrincipal();
        if (securityUsers.hasRole("ROLE_ADMIN") || securityUsers.hasRole("ROLE_EMPLOYEE")) {
            response.sendRedirect("/dashboard");
        }
        if (securityUsers.hasRole("ROLE_USER")) {

            response.sendRedirect("/category/1/page/1");
        }


    }
}
