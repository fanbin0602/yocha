package me.fanbin.yocha.auth.service;

import org.springframework.security.oauth2.provider.client.JdbcClientDetailsService;

import javax.sql.DataSource;

/**
 * JdbcClientDetailsService 扩展
 * @author fanbin
 * @date 2021/3/8
 */
public class YochaClientDetailsService extends JdbcClientDetailsService {

    public YochaClientDetailsService(DataSource dataSource) {
        super(dataSource);
    }

}
