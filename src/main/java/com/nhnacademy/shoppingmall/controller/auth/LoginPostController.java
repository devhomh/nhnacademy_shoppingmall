package com.nhnacademy.shoppingmall.controller.auth;

import com.nhnacademy.shoppingmall.common.mvc.annotation.RequestMapping;
import com.nhnacademy.shoppingmall.common.mvc.controller.BaseController;
import com.nhnacademy.shoppingmall.data.domain.User;
import com.nhnacademy.shoppingmall.data.exception.UserNotFoundException;
import com.nhnacademy.shoppingmall.data.repository.impl.UserRepositoryImpl;
import com.nhnacademy.shoppingmall.data.service.interfaces.UserService;
import com.nhnacademy.shoppingmall.data.service.impl.UserServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequestMapping(method = RequestMapping.Method.POST,value = "/loginAction.do")
public class LoginPostController implements BaseController {

    private final UserService userService = new UserServiceImpl(new UserRepositoryImpl());

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        //todo#13-2 로그인 구현, session은 60분동안 유지됩니다.
        String id = req.getParameter("user_id");
        String password = req.getParameter("user_password");
        HttpSession session = req.getSession();
        try {
            User loginUser = userService.doLogin(id, password);

            session.setAttribute("loginID", loginUser.getUserId());
            session.setMaxInactiveInterval(3600);

            return loginUser.getUserAuth() == User.Auth.ROLE_ADMIN ? "redirect:/admin/index.do" : "redirect:/index.do";
        } catch(UserNotFoundException e){
            req.setAttribute("errMsg", "아이디와 비밀번호 확인 바랍니다.");
            return "/shop/login/login_form";
        }

    }
}
