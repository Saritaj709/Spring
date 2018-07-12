package com.bridgelabz.fundoo.jwt;

import java.util.Date;

import javax.xml.bind.DatatypeConverter;

import org.springframework.stereotype.Service;

import com.bridgelabz.fundoo.model.User;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;



@Service
public class JwtToken {
	final static String KEY="Sarita";
	
	public String tokenGenerator(User user) {
		String email=user.getEmail();
		String passkey=user.getPassword();
		//long time=System.currentTimeMillis();
		long nowMillis=System.currentTimeMillis()+(20*60*60*1000);
		Date now=new Date(nowMillis);
		JwtBuilder jwtBuilder=Jwts.builder().setId(passkey).setIssuedAt(now).setSubject(email).signWith(SignatureAlgorithm.HS256,KEY);
		return jwtBuilder.compact();
	}
	public String parseJwtToken(String jwt) {
		Claims claims=Jwts.parser().setSigningKey(DatatypeConverter.parseBase64Binary(KEY)).parseClaimsJws(jwt).getBody();
	    System.out.println("Subject : "+claims.getSubject());
		System.out.println("ID : "+claims.getId());
 return (claims.getId()+" " +claims.getSubject());
	}
}
