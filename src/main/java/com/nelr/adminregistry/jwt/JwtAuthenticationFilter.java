package com.nelr.adminregistry.jwt;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.nelr.adminregistry.auth.UserDetailsServiceImpl;

import org.springframework.util.StringUtils;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter{ // Filtro que por el cual el Token se analiza
	
	@Autowired
	private JwtService jwtService;
	@Autowired
	private UserDetailsServiceImpl userDetailsService;
	

	

	@Override
	protected void doFilterInternal(HttpServletRequest request,
									HttpServletResponse response,
									FilterChain filterChain)
			throws ServletException, IOException {
		
		final String token = getTokenFromRequest(request); //extrae el token del Header del Request
		final String username;
		
		if(token == null) { //Finaliza el filtro si el token es null
			filterChain.doFilter(request, response);
			return;
		}
		
		username = jwtService.getUsernameFromToken(token); //Extrae el username / correo del token

		//revisa que el nombre / correo este vacio y que el usuario no este autenticado (Consulata en el Securit Context Holder)
		if(username!=null && SecurityContextHolder.getContext().getAuthentication()==null) {

			//revisa si existe el usuario en la base de datos
			//Mi entidad Login implementa UserDetails
			//User DetailsService lo implemente, en caso contrario se deberia de hacer un Bean en Application Config userDetailsService()
			UserDetails userDetails = userDetailsService.loadUserByUsername(username);
			
			if(jwtService.isTokenValid(token, userDetails)) {

				//Actualiza el security context holder
				UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
						userDetails,
						null,
						userDetails.getAuthorities());
				
				authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				SecurityContextHolder.getContext().setAuthentication(authToken);
			}
		}

		//pasa el request al los demas filtros
		filterChain.doFilter(request, response);
	}

	private String getTokenFromRequest(HttpServletRequest request) {
		final String authHeader=request.getHeader(HttpHeaders.AUTHORIZATION);
		if(StringUtils.hasText(authHeader) && authHeader.startsWith("Bearer ")) {
			return authHeader.substring(7);
		}
		return null;
	}

}
