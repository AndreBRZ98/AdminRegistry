package com.nelr.adminregistry.jwt;



import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import javax.crypto.SecretKey;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;


@Service
public class JwtService {
	
	//private static final String SECRET_KEY="A345345435F3456346F56SQFG64564564523H66542344567HJ76KJ7";
	private static final SecretKey SECRET_KEY =  Jwts.SIG.HS256.key().build(); //Genera la firma

	public String getToken(UserDetails login) { //Genera el token sin extra claims
		return getToken(new HashMap<>(), login);
	}
	
	private String getToken(Map<String, Object> extraClaims, UserDetails login) { //Genera el token con extra claims y la informacion del usuario
		
		String token = Jwts
				.builder()
				.claims(extraClaims)
				.subject(login.getUsername())
				.issuedAt(new Date(System.currentTimeMillis()))
				.expiration(new Date(System.currentTimeMillis()+1000*60*24))
				//.signWith(Jwts.SIG.HS256.key().build())
				.signWith(SECRET_KEY)
				.compact();
		
		return token;
	}

	public String getUsernameFromToken(String token) { // Extrae el subject correo / usuario del token
		return getClaim(token, Claims::getSubject);
	}

	public boolean isTokenValid(String token, UserDetails userDetails) { //Valida si el token le pertenece al usuario
		final String username=getUsernameFromToken(token);
		return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
	}			
		
	private Claims getAllClaims(String token) { //extrae todos los claims del token
		return Jwts
				.parser()
				.verifyWith(SECRET_KEY)
				.build()
				.parseSignedClaims(token)
				.getPayload();
	}
	
	public <T> T getClaim(String token, Function<Claims,T> claimsResolver) { //extrae cada claim individualmente
		
		final Claims claims = getAllClaims(token);
		return claimsResolver.apply(claims);

	}
	
	private Date getExpiration(String token) { //Extrae la expiracion del token
		return (Date) getClaim(token, Claims::getExpiration);
	}
	
	private boolean isTokenExpired(String token) { //Verifica la expiracion del token
		return getExpiration(token).before(new Date());
	}
}
