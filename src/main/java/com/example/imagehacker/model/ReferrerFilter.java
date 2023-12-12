//package com.example.imagehacker.model;
//
///**
// * ReferrerFilter.
// *
// * @author Nguyễn Hải
// * Created 11/12/2023
// */
//import jakarta.servlet.FilterChain;
//import jakarta.servlet.ServletException;
//import jakarta.servlet.annotation.WebFilter;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import org.springframework.web.filter.OncePerRequestFilter;
//
//import java.io.IOException;
//
//@WebFilter("/users/code")
//public class ReferrerFilter extends OncePerRequestFilter {
//
//    @Override
//    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
//            throws ServletException, IOException {
//        String referer = request.getHeader("Referer");
//        if (referer != null && referer.contains("bom.so")) {
//            response.sendRedirect("/users/code");
//        } else {
//            response.sendRedirect("/error");
//        }
//    }
//}
//
