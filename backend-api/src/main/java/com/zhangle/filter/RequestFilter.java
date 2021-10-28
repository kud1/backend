package com.zhangle.filter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class RequestFilter implements Filter {

    private static final String VALID_ERROR = "{\"code\": \"4000\",\"message\": \"TOKEN INVALID\"}";

    @Value("${filter.config.excludeUrls}")
    private String excludeUrls;

    private List<String> excludes;

    @Autowired
    CacheManager cacheManager;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        excludes = Arrays.asList(excludeUrls.split(","));
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
//        String uri = request.getRequestURI();
//        if(!excludes.contains(uri)) {
//            String token = request.getHeader("Authorization");
//            if (token == null || "".equals(token) || cacheManager.getCache("TOKEN").get(token) == null) {
//                servletResponse.getWriter().write(VALID_ERROR);
//                return;
//            }
//        }
        filterChain.doFilter(request, response);
    }
}
