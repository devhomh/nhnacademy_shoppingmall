package com.nhnacademy.shoppingmall.controller.index;

import com.nhnacademy.shoppingmall.common.mvc.annotation.RequestMapping;
import com.nhnacademy.shoppingmall.common.mvc.controller.BaseController;
import com.nhnacademy.shoppingmall.data.domain.User;
import com.nhnacademy.shoppingmall.data.repository.impl.UserRepositoryImpl;
import com.nhnacademy.shoppingmall.data.service.impl.UserServiceImpl;
import com.nhnacademy.shoppingmall.data.service.interfaces.UserService;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RequestMapping(method = RequestMapping.Method.GET, value = "/mypage/index.do")
public class MyPageController implements BaseController {
    UserService userService = new UserServiceImpl(new UserRepositoryImpl());
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        String id = req.getParameter("loginID");
        User loginUser = userService.getUser(id);
        req.setAttribute("user", loginUser);

        return "shop/main/mypage";
    }
}
