package me.fanbin.yocha.auth.config;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.fanbin.yocha.auth.service.YochaClientDetailsService;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;

import javax.sql.DataSource;

/**
 * @author fanbin
 * @date 2021/3/7
 */
@Configuration
@EnableAuthorizationServer
@RequiredArgsConstructor
@Slf4j
public class AuthorizationServerConfigurer extends AuthorizationServerConfigurerAdapter {

    private final DataSource dataSource;
    private final UserDetailsService userDetailsService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    /**
     * 配置 OAuth 客户端逻辑
     * @param clients
     * @throws Exception
     */
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        YochaClientDetailsService clientDetailsService = new YochaClientDetailsService(dataSource);
        clients.withClientDetails(clientDetailsService);
    }

    /**
     *
     * @param security
     * @throws Exception
     */
    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        // client_secret 以表单方式提交
        // security.allowFormAuthenticationForClients();
        // 打开验证 token 的访问权限
        // security.checkTokenAccess("permitAll()");
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        // 配置用户认证服务 @See YochaUserDetailsService
        endpoints.userDetailsService(userDetailsService);
        // 由于密码模式包含认证环节，因此需要配置认证管理器（这里使用的是默认实现）
        // @See WebSecurityConfigurer#authenticationManagerBean()
        endpoints.authenticationManager(authenticationManager);
    }
}
