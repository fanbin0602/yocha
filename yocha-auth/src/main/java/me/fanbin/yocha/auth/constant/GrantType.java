package me.fanbin.yocha.auth.constant;

/**
 * OAuth2 授权模式
 * @author fanbin
 * @date 2021/3/8
 */
public class GrantType {

    /**
     * 密码模式
     */
    public static final String PASSWORD = "password";

    /**
     * 授权码模式
     */
    public static final String AUTHORIZATION_CODE = "authorization_code";

    /**
     * 客户端模式
     */
    public static final String CLIENT_CREDENTIALS = "client_credentials";

    /**
     * 刷新模式
     */
    public static final String REFRESH_TOKEN = "refresh_token";

    /**
     * 简化模式
     */
    public static final String IMPLICIT = "implicit";

}
