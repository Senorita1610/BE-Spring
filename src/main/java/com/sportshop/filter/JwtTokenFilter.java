package com.sportshop.filter;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.sportshop.config.JwtTokenUtils;
import com.sportshop.security.MyUserDetails;
import com.sportshop.service.impl.MyUserDetailsService;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class JwtTokenFilter extends OncePerRequestFilter {
	@Autowired
	private MyUserDetailsService userDetailsSerive;
	@Autowired
	private JwtTokenUtils jwtTokenUtils;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		final String authHeader = request.getHeader("Authorization");
		if (authHeader != null && authHeader.startsWith("Bearer ")) {
			final String token = authHeader.substring(7);
			final String username = jwtTokenUtils.getUsernameFromToken(token);
			if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
				MyUserDetails userDetails = userDetailsSerive.loadUserByUsername(username);
				if (jwtTokenUtils.validateToken(token, userDetails)) {
					UsernamePasswordAuthenticationToken upat = new UsernamePasswordAuthenticationToken(userDetails,
							null, userDetails.getAuthorities());
					// After setting the Authentication in the context, we specify
					// that the current user is authenticated. So it passes the
					// Spring Security Configurations successfully.
					SecurityContextHolder.getContext().setAuthentication(upat);
				}
			}
		}
		filterChain.doFilter(request, response);
	}
}