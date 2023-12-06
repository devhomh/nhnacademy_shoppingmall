package com.nhnacademy.shoppingmall.controller.admin;

import com.nhnacademy.shoppingmall.common.mvc.annotation.RequestMapping;
import com.nhnacademy.shoppingmall.common.mvc.controller.BaseController;
import com.nhnacademy.shoppingmall.data.domain.Product;
import com.nhnacademy.shoppingmall.data.repository.impl.ProductRepositoryImpl;
import com.nhnacademy.shoppingmall.data.service.impl.ProductServiceImpl;
import com.nhnacademy.shoppingmall.data.service.interfaces.ProductService;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RequestMapping(method = RequestMapping.Method.POST, value = "/admin/addProductAction.do")
public class AddProductController implements BaseController {
    ProductService productService = new ProductServiceImpl(new ProductRepositoryImpl());

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        int categoryID = Integer.parseInt(req.getParameter("categories"));
        String modelNumber = req.getParameter("product_modelnumber");
        String modelName = req.getParameter("product_modelname");
        int quantity = Integer.parseInt(req.getParameter("product_quantity"));
        int price = Integer.parseInt(req.getParameter("product_price"));
        String comment = req.getParameter("product_comment");

        Product product = new Product(categoryID, modelNumber, modelName, quantity, null, price, comment);
        productService.save(product);

        return "shop/admin/index";
    }
}