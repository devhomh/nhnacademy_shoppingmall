package com.nhnacademy.shoppingmall.controller.index;

import com.nhnacademy.shoppingmall.common.mvc.annotation.RequestMapping;
import com.nhnacademy.shoppingmall.common.mvc.controller.BaseController;
import com.nhnacademy.shoppingmall.data.domain.ShoppingRecord;
import com.nhnacademy.shoppingmall.data.repository.impl.ShoppingCartRepositoryImpl;
import com.nhnacademy.shoppingmall.data.service.impl.ShoppingCartServiceImpl;
import com.nhnacademy.shoppingmall.data.service.interfaces.ShoppingCartService;
import java.time.LocalDateTime;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RequestMapping(method = RequestMapping.Method.POST, value = "/AddCartAction.do")
public class AddShoppingCartController implements BaseController {
    private final ShoppingCartService shoppingCartService = new ShoppingCartServiceImpl(new ShoppingCartRepositoryImpl());

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        String loginID = (String) req.getSession(false).getAttribute("loginID");
        int productID = Integer.parseInt(req.getParameter("productID"));
        ShoppingRecord shoppingRecord = new ShoppingRecord(loginID, 1, productID, LocalDateTime.now());
        shoppingCartService.save(shoppingRecord);
        return "redirect:/index.do";
    }
}
