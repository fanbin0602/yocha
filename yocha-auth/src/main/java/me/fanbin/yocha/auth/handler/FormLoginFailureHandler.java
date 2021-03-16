package me.fanbin.yocha.auth.handler;

import cn.hutool.core.util.CharsetUtil;
import cn.hutool.http.HttpUtil;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 表单登陆失败处理
 * @author fanbin
 * @date 2021/3/7
 */
@Slf4j
public class FormLoginFailureHandler implements AuthenticationFailureHandler {

    private static final String LOGIN_URL = "/auth/login?error=%s";

    /**
     * 重定向到登录页面，并携带登陆失败信息
     * @param httpServletRequest
     * @param httpServletResponse
     * @param e
     * @throws IOException
     * @throws ServletException
     */
    @Override
    @SneakyThrows
    public void onAuthenticationFailure(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) {
        log.debug("表单登录失败:{}", e.getLocalizedMessage());
        String url = HttpUtil.encodeParams(String.format(LOGIN_URL, e.getMessage()),
                CharsetUtil.CHARSET_UTF_8);
        httpServletResponse.sendRedirect(url);
    }
}
