package com.nhnacademy.shoppingmall.controller.admin;

import com.nhnacademy.shoppingmall.common.mvc.annotation.RequestMapping;
import com.nhnacademy.shoppingmall.common.mvc.controller.BaseController;
import com.nhnacademy.shoppingmall.data.domain.Category;
import com.nhnacademy.shoppingmall.data.repository.impl.CategoryRepositoryImpl;
import com.nhnacademy.shoppingmall.data.service.impl.CategoryServiceImpl;
import com.nhnacademy.shoppingmall.data.service.interfaces.CategoryService;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RequestMapping(method = RequestMapping.Method.GET, value = "/admin/product/add.do")
public class AddProductController implements BaseController {
    CategoryService categoryService = new CategoryServiceImpl(new CategoryRepositoryImpl());
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        List<Category> list = categoryService.toList();
        req.setAttribute("categories", list);
        return "shop/admin/product_form";
    }
}
