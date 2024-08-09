package com.moviesplus.catalogapp.security;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SpringSecurityController {

    @GetMapping("/csrf-token")
    public Object retriveCsrfToken(HttpServletRequest req) {
        return req.getAttribute("_csrf");
    }
}
