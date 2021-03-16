package me.fanbin.yocha.auth.handler;

import cn.hutool.core.util.StrUtil;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 注销登录成功处理
 * @author fanbin
 * @date 2021/3/7
 */
public class SsoLogoutSuccessHandler implements LogoutSuccessHandler {

    private static final String REDIRECT_URL = "redirect_url";

    /**
     * 成功后跳转指定的地址或 referer 地址
     * @param request
     * @param response
     * @param authentication
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        // 获取请求参数中是否包含 回调地址
        String redirectUrl = request.getParameter(REDIRECT_URL);
        if (StrUtil.isNotBlank(redirectUrl)) {
            response.sendRedirect(redirectUrl);
        }
        else {
            // 默认跳转 referer 地址
            String referer = request.getHeader(HttpHeaders.REFERER);
            response.sendRedirect(referer);
        }
    }
}
