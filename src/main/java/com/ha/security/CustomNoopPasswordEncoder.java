package com.ha.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;

public class CustomNoopPasswordEncoder implements PasswordEncoder{
	

	private static final Logger log = LoggerFactory.getLogger(CustomNoopPasswordEncoder.class);

	@Override
	public String encode(CharSequence rawPassword) {
		log.info("encode() rawPassword :"+rawPassword); //원시 패스워드 데이터 
		
		// 비밀번호니까 warning으로 로그 찍어야함
		// 사용자가 입력한 비밀번호를 인코딩(암호화)
		
		return rawPassword.toString();
	}

	@Override
	public boolean matches(CharSequence rawPassword, String encodedPassword) {
		log.info("encode() rawPassword : "+rawPassword);
		log.info("encode() encodedPassword : "+encodedPassword);
		// 원시데이터 vs 인코딩한 데이터 비교
		// 입력한 비밀번호랑 인코딩한 거랑 같은건지 물어봄 -> 같으면 true, 다르면 false
		
		return rawPassword.toString().equals(encodedPassword);
	}
	
	
	
}
