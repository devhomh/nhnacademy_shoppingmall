package com.nhnacademy.shoppingmall.controller.index;

import com.nhnacademy.shoppingmall.common.mvc.annotation.RequestMapping;
import com.nhnacademy.shoppingmall.common.mvc.controller.BaseController;
import com.nhnacademy.shoppingmall.data.domain.Product;
import com.nhnacademy.shoppingmall.data.repository.impl.ProductRepositoryImpl;
import com.nhnacademy.shoppingmall.data.service.impl.ProductServiceImpl;
import com.nhnacademy.shoppingmall.data.service.interfaces.Service;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RequestMapping(method = RequestMapping.Method.GET, value = "/product/view.do")
public class ViewProductController implements BaseController {
    Service<Product> productService = new ProductServiceImpl(new ProductRepositoryImpl());

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        int productID = Integer.parseInt(req.getParameter("productID"));
        Product product = productService.get(productID);
        req.setAttribute("product", product);

        return "shop/main/product_view";
    }
}
