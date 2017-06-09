package com.zz;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class WebBehandApplicationTests {

	@Test
	public void contextLoads() {
		BCryptPasswordEncoder bc = new BCryptPasswordEncoder();
		String password = bc.encode("admin123");
		System.out.println(password);
	}

}
