package com.ha.security;

import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.inject.Inject;
import javax.sql.DataSource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(
	{
	"file:src/main/webapp/WEB-INF/spring/security-context.xml",
	"file:src/main/webapp/WEB-INF/spring/root-context.xml"
	}
)
public class SpringSecurityTest {
	// junit 통해서 테스트 돌릴 예정
	// security-context, root-context.xml 을 사용
	
	private static final Logger log = LoggerFactory.getLogger(SpringSecurityTest.class);
	
	@Inject
	private DataSource ds;
	
	@Inject 
	private PasswordEncoder pwEncoder; // 업캐스팅해서 주입
	
	//@Test
	public void 암호화테스트() throws Exception {
		log.info("ψ(｀∇´)ψ 암호화테스트  호출됨");
		log.info("ψ(｀∇´)ψ 암호화테스트  12345: " + pwEncoder.encode("12345"));
	} // 12345 = $2a$10$njorBDdxgviDbxRc2L4fxuABfdnB/jOE5EPx1rY6bULf0PiXr6sNS
	
	//@Test
	public void 사용자생성() throws Exception {
		log.info("ψ(｀∇´)ψ 사용자생성  호출됨");
		
		Connection con = null;
		PreparedStatement pstmt = null;
		
		for(int i = 0; i<10; i++) {
			con = ds.getConnection();
			String sql = "insert into tbl_member(userid, userpw, username, useremail) "
					+ "values(?, ?, ?, ?)";
			pstmt = con.prepareStatement(sql);
			
			pstmt.setString(2, pwEncoder.encode("pw"+i));
			
			if(i<8) {
				pstmt.setString(1, "user"+i);
				pstmt.setString(3, "사용자"+i);
				pstmt.setString(4, "user"+i+"@itwill.com");
			} else if(i<9) {
				pstmt.setString(1, "manager"+i);
				pstmt.setString(3, "매니저"+i);
				pstmt.setString(4, "manager"+i+"@itwill.com");
			} else {
				pstmt.setString(1, "admin"+i);
				pstmt.setString(3, "관리자"+i);
				pstmt.setString(4, "admin"+i+"@itwill.com");
			}
			
			pstmt.executeUpdate();
			
		}
	}
	
	@Test
	public void 인증정보테이블() throws Exception {
		log.info("ψ(｀∇´)ψ 인증정보테이블  호출됨");
		
		Connection con = null;
		PreparedStatement pstmt = null;
		
		for(int i =0; i <10; i++) {
			con = ds.getConnection();
			String sql =  "insert into tbl_member_auth(userid,auth) values(?,?)";
			pstmt = con.prepareStatement(sql);
			// 아이디 user1 / 권한 ROLE_USER, ROLE_MANAGER, ROLE_ADMIN
			
			if(i<8) {
				pstmt.setString(1, "user"+i);
				pstmt.setString(2, "ROLE_USER");
			} else if (i<9) {
				pstmt.setString(1, "manager"+i);
				pstmt.setString(2, "ROLE_MANAGER");
			} else {
				pstmt.setString(1, "admin"+i);
				pstmt.setString(2, "ROLE_ADMIN");
			}
			pstmt.executeUpdate();
		}
	}
	
}
