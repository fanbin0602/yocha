package me.fanbin.yocha.auth;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @author fanbin
 * @date 2021/3/8
 */
@SpringBootTest
public class PasswordEncoderTest {

    @Test
    public void encodeTest() {
        PasswordEncoder encoder = new BCryptPasswordEncoder();
        System.out.println(encoder.encode("123456"));
    }

}
