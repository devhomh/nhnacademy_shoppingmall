package com.nhnacademy.shoppingmall.controller.admin;

import com.nhnacademy.shoppingmall.common.mvc.annotation.RequestMapping;
import com.nhnacademy.shoppingmall.common.mvc.controller.BaseController;
import com.nhnacademy.shoppingmall.data.domain.Category;
import com.nhnacademy.shoppingmall.data.domain.Product;
import com.nhnacademy.shoppingmall.data.repository.impl.CategoryRepositoryImpl;
import com.nhnacademy.shoppingmall.data.repository.impl.ProductRepositoryImpl;
import com.nhnacademy.shoppingmall.data.service.impl.CategoryServiceImpl;
import com.nhnacademy.shoppingmall.data.service.impl.ProductServiceImpl;
import com.nhnacademy.shoppingmall.data.service.interfaces.Service;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RequestMapping(method = RequestMapping.Method.GET, value = "/admin/product/edit.do")
public class EditProductController implements BaseController {
    Service<Product> productService = new ProductServiceImpl(new ProductRepositoryImpl());
    Service<Category> categoryService = new CategoryServiceImpl(new CategoryRepositoryImpl());
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        List<Category> list = categoryService.toList();
        req.setAttribute("categories", list);

        int id = Integer.parseInt(req.getParameter("productID"));
        req.setAttribute("product", productService.get(id));
        return "shop/admin/product_form";
    }
}
