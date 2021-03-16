package me.fanbin.yocha.auth.config;

import me.fanbin.yocha.auth.handler.FormLoginFailureHandler;
import me.fanbin.yocha.auth.handler.SsoLogoutSuccessHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

/**
 *
 * @author fanbin
 * @date 2021/3/6
 */
@Configuration
public class WebSecurityConfigurer extends WebSecurityConfigurerAdapter {

    private static final String LOGIN_URL = "/auth/login";

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.formLogin()
                // 登陆表单
                .loginPage(LOGIN_URL)
                // 登陆提交
                .loginProcessingUrl(LOGIN_URL)
                .failureHandler(authenticationFailureHandler());

        http.logout()
                .logoutSuccessHandler(logoutSuccessHandler())
                .deleteCookies("JSESSIONID")
                .invalidateHttpSession(true);

        http.authorizeRequests()
                .antMatchers("/auth/login", "/token/**", "/actuator/**")
                .permitAll()
                .anyRequest()
                .authenticated();

        http.csrf().disable();
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/css/**");
    }

    @Bean
    public AuthenticationFailureHandler authenticationFailureHandler() {
        return new FormLoginFailureHandler();
    }

    @Bean
    public LogoutSuccessHandler logoutSuccessHandler() {
        return new SsoLogoutSuccessHandler();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * 将 AuthenticationManager 作为一个 Bean 暴露
     * 密码授权模式需要用到
     * @See WebSecurityConfigurer#authenticationManagerBean()
     * @return
     * @throws Exception
     */
    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}
