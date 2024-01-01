package com.nhnacademy.shoppingmall.controller.index;

import com.nhnacademy.shoppingmall.common.mvc.annotation.RequestMapping;
import com.nhnacademy.shoppingmall.common.mvc.controller.BaseController;
import com.nhnacademy.shoppingmall.data.domain.Product;
import com.nhnacademy.shoppingmall.data.domain.ShoppingRecord;
import com.nhnacademy.shoppingmall.data.repository.impl.ProductRepositoryImpl;
import com.nhnacademy.shoppingmall.data.repository.impl.ShoppingCartRepositoryImpl;
import com.nhnacademy.shoppingmall.data.repository.impl.UserRepositoryImpl;
import com.nhnacademy.shoppingmall.data.service.impl.ProductServiceImpl;
import com.nhnacademy.shoppingmall.data.service.impl.ShoppingCartServiceImpl;
import com.nhnacademy.shoppingmall.data.service.impl.UserServiceImpl;
import com.nhnacademy.shoppingmall.data.service.interfaces.Service;
import com.nhnacademy.shoppingmall.data.service.interfaces.ShoppingCartService;
import com.nhnacademy.shoppingmall.data.service.interfaces.UserService;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RequestMapping(method = RequestMapping.Method.GET, value = "/mypage/cart.do")
public class ShoppingCartController implements BaseController {
    private final UserService userService = new UserServiceImpl(new UserRepositoryImpl());
    private final ShoppingCartService shoppingCartService = new ShoppingCartServiceImpl(new ShoppingCartRepositoryImpl());
    private final Service<Product> productService = new ProductServiceImpl(new ProductRepositoryImpl());

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        String loginID = (String) req.getSession(false).getAttribute("loginID");
        List<ShoppingRecord> cartList = shoppingCartService.filterById(loginID);

        Map<Product, Integer> map = new HashMap<>();

        cartList.forEach(item -> {
                    int productID = item.getProductID();
                    Product product = productService.get(productID);
                    map.put(product, item.getQuantity());
                });

        req.setAttribute("map", map);
        req.setAttribute("user", userService.getUser(loginID));
        return "shop/main/shoppingcart";
    }
}
