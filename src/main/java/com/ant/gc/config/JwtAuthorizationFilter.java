package com.ant.gc.config;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;

@Configuration
public class JwtAuthorizationFilter extends OncePerRequestFilter {
	@Value("${jwt.signin-key}")
	private String signinKey;
	@Value("${jwt.header}")
	private String header;
	@Value("${jwt.prefix}")
	private String prefix;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		try {
			String headerRequest = request.getHeader(header);

			if (headerRequest == null || !headerRequest.startsWith(prefix)) {
				filterChain.doFilter(request, response);
				return;
			}

			String token = headerRequest.replace(prefix, "");

			Claims claims = Jwts.parser().setSigningKey(signinKey).parseClaimsJws(token).getBody();
			String usernname = claims.getSubject();

			if (usernname != null) {
				List<String> roles = claims.get("roles", List.class);

				List<GrantedAuthority> authorities = new ArrayList<>();

				roles.forEach(role -> {
					authorities.add(new SimpleGrantedAuthority(role));
				});

				UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(usernname, null,
						authorities);
				SecurityContextHolder.getContext().setAuthentication(auth);

			}

			filterChain.doFilter(request, response);
		} catch (ExpiredJwtException | UnsupportedJwtException | MalformedJwtException | SignatureException
				| IllegalArgumentException e) {
			SecurityContextHolder.clearContext();
			filterChain.doFilter(request, response);
			e.printStackTrace();
		}

	}

}
