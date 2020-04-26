package com.example.demo;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;
import static com.example.demo.SecurityConstants.HEADER_STRING;
import static com.example.demo.SecurityConstants.TOKEN_PREFIX;
import org.springframework.util.StringUtils;
import java.util.Collections;



public class JwtAuthenticationFilter extends OncePerRequestFilter {
	
	@Autowired
	private JwtTokenProvider jwtTokenProvider;
	@Autowired
	private CustomUserDetailsService customUserDetailsService;

	@Override
	protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			String jwt = getJWTFromRequest(httpServletRequest);

            if(StringUtils.hasText(jwt)&& jwtTokenProvider.validateToken(jwt)){
                Long userId = jwtTokenProvider.getUserIdFromJWT(jwt);
                User userDetails = customUserDetailsService.loadUserById(userId);

                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                        userDetails, null, Collections.emptyList());
                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(httpServletRequest));
                SecurityContextHolder.getContext().setAuthentication(authentication); 
			
		}}catch(Exception e) {
            logger.error("Could not set user authentication in security context", e);

		}
		filterChain.doFilter(httpServletRequest, httpServletResponse);
		
	}

	private String getJWTFromRequest(HttpServletRequest request){
        String bearerToken = request.getHeader(HEADER_STRING);
        
        if(StringUtils.hasText(bearerToken)&&bearerToken.startsWith(TOKEN_PREFIX)){
            return bearerToken.substring(7, bearerToken.length());
        }
   
	
	return null;
	}

}
