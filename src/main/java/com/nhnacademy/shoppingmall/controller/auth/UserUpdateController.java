package com.nhnacademy.shoppingmall.controller.auth;

import com.nhnacademy.shoppingmall.common.mvc.annotation.RequestMapping;
import com.nhnacademy.shoppingmall.common.mvc.controller.BaseController;
import com.nhnacademy.shoppingmall.data.domain.User;
import com.nhnacademy.shoppingmall.data.repository.impl.UserRepositoryImpl;
import com.nhnacademy.shoppingmall.data.service.impl.UserServiceImpl;
import com.nhnacademy.shoppingmall.data.service.interfaces.UserService;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RequestMapping(method = RequestMapping.Method.POST, value = "/user/updateAction.do")
public class UserUpdateController implements BaseController {
    UserService userService = new UserServiceImpl(new UserRepositoryImpl());


    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        String id = req.getParameter("user_id");
        String password = req.getParameter("user_password");
        String name = req.getParameter("user_name");
        String birth = req.getParameter("user_birth");

        User updated = userService.getUser(id);
        updated.setUserPassword(password);
        updated.setUserName(name);
        updated.setUserBirth(birth);

        userService.updateUser(updated);

        req.getSession().removeAttribute("loginID");
        return "redirect:/index.do";
    }
}
