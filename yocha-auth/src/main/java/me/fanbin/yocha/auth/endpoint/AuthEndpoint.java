package me.fanbin.yocha.auth.endpoint;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author fanbin
 * @date 2021/3/7
 */
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthEndpoint {

    /**
     * 登录页面
     * @param modelAndView
     * @param error
     * @return
     */
    @GetMapping("/login")
    public ModelAndView loginPage(ModelAndView modelAndView,
                                  @RequestParam(value = "error", required = false) String error) {
        modelAndView.setViewName("login");
        modelAndView.addObject("error", error);
        return modelAndView;
    }

}
