package com.bridgelabz.fundonotes.module.confirmation;

import java.util.Date;

import javax.xml.bind.DatatypeConverter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bridgelabz.fundonotes.module.model.LoginDTO;
import com.bridgelabz.fundonotes.module.model.RegistrationDTO;
import com.bridgelabz.fundonotes.module.model.User;
import com.bridgelabz.fundonotes.module.service.UserRepository;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;



@Service
public class JwtToken {
	final static String KEY="Sarita";
	
	@Autowired
	UserRepository userRepository;
	
	public String tokenGenerator(RegistrationDTO dto) {
		String email=dto.getEmail();
		String passkey=dto.getPassword();
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
	/*public boolean activateJwt(String token) {
		// TODO Auto-generated method stub
		Claims claims=Jwts.parser().setSigningKey(DatatypeConverter.parseBase64Binary("Sarita")).parseClaimsJws(token).getBody();
		Optional<User> user=userRepository.findById(claims.getSubject());
		user.get().setActivate("true");
		userRepository.save(user.get());
		return true;
	}*/
	public String tokenGenerator(LoginDTO dto) {
		String email=dto.getEmail();
		String passkey=dto.getPassword();
		//long time=System.currentTimeMillis();
		long nowMillis=System.currentTimeMillis()+(20*60*60*1000);
		Date now=new Date(nowMillis);
		JwtBuilder jwtBuilder=Jwts.builder().setId(passkey).setIssuedAt(now).setSubject(email).signWith(SignatureAlgorithm.HS256,KEY);
		return jwtBuilder.compact();
	}
	public String tokenGenerator(User dto) {
		String email=dto.getEmail();
		String passkey=dto.getPassword();
		//long time=System.currentTimeMillis();
		long nowMillis=System.currentTimeMillis()+(20*60*60*1000);
		Date now=new Date(nowMillis);
		JwtBuilder jwtBuilder=Jwts.builder().setId(passkey).setIssuedAt(now).setSubject(email).signWith(SignatureAlgorithm.HS256,KEY);
		return jwtBuilder.compact();
	}
	
	public String tokenGenerator(String value) {
		//String email=dto.getEmail();
		//String passkey=dto.getPassword();
		//long time=System.currentTimeMillis();
		long nowMillis=System.currentTimeMillis()+(20*60*60*1000);
		Date now=new Date(nowMillis);
		JwtBuilder jwtBuilder=Jwts.builder().setId(value).setIssuedAt(now).signWith(SignatureAlgorithm.HS256,KEY);
		return jwtBuilder.compact();
	}
}
