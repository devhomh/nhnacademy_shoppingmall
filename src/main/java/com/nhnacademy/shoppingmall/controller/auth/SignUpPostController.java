package com.nhnacademy.shoppingmall.controller.auth;

import com.nhnacademy.shoppingmall.common.mvc.annotation.RequestMapping;
import com.nhnacademy.shoppingmall.common.mvc.controller.BaseController;
import com.nhnacademy.shoppingmall.data.domain.User;
import com.nhnacademy.shoppingmall.data.repository.impl.UserRepositoryImpl;
import com.nhnacademy.shoppingmall.data.service.impl.UserServiceImpl;
import com.nhnacademy.shoppingmall.data.service.interfaces.UserService;
import java.time.LocalDateTime;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RequestMapping(method = RequestMapping.Method.POST, value = "/signupAction.do")
public class SignUpPostController implements BaseController {
    UserService userService = new UserServiceImpl(new UserRepositoryImpl());

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        String id = req.getParameter("user_id");
        String password = req.getParameter("user_password");
        String name = req.getParameter("user_name");
        String birth = req.getParameter("user_birth");

        User user = new User(id, name, password, birth, User.Auth.ROLE_USER, 1_000_000, LocalDateTime.now(), null);

        userService.saveUser(user);

        return "redirect:/index.do";
    }
}
