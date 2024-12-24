//package com.example.ch4_1_newsfeed.filter;
//
//import jakarta.servlet.*;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import jakarta.servlet.http.HttpSession;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.util.PatternMatchUtils;
//
//import java.io.IOException;
//
//@Slf4j
//public class AuthFilter implements Filter {
//
//    public static final String[] WHITE_LIST = {"/", "/users/login", "/users/signup"};
//
//    @Override
//    public void doFilter(
//            ServletRequest request,
//            ServletResponse response,
//            FilterChain filterChain
//    ) throws IOException, ServletException {
//
//        HttpServletRequest httpRequest = (HttpServletRequest) request;
//        String requestURI = httpRequest.getRequestURI();
//
//        HttpServletResponse httpResponse = (HttpServletResponse) response;
//
//        if (!isWhiteList(requestURI)) {
//
//            HttpSession session = httpRequest.getSession(false);
//
//            if (session != null || session.getAttribute("username") == null) {
//                httpResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED, "unauthorized");
//            }
//        }
//
//        filterChain.doFilter(request, response);
//    }
//
//    public boolean isWhiteList(String requestURI) {
//        return PatternMatchUtils.simpleMatch(WHITE_LIST, requestURI);
//    }
//}
