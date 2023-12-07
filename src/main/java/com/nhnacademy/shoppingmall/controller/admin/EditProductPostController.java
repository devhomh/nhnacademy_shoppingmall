package com.nhnacademy.shoppingmall.controller.admin;

import com.nhnacademy.shoppingmall.common.mvc.annotation.RequestMapping;
import com.nhnacademy.shoppingmall.common.mvc.controller.BaseController;
import com.nhnacademy.shoppingmall.data.domain.Product;
import com.nhnacademy.shoppingmall.data.repository.impl.ProductRepositoryImpl;
import com.nhnacademy.shoppingmall.data.service.impl.ProductServiceImpl;
import com.nhnacademy.shoppingmall.data.service.interfaces.ProductService;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RequestMapping(method = RequestMapping.Method.POST, value = "/admin/product/editAction.do")
public class EditProductPostController implements BaseController {
    ProductService productService = new ProductServiceImpl(new ProductRepositoryImpl());
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {

        int productID = Integer.parseInt(req.getParameter("productID"));
        int categoryID = Integer.parseInt(req.getParameter("categories"));
        String modelNumber = req.getParameter("product_modelnumber");
        String modelName = req.getParameter("product_modelname");
        int quantity = Integer.parseInt(req.getParameter("product_quantity"));
        int price = Integer.parseInt(req.getParameter("product_price"));
        String comment = req.getParameter("product_comment");
        Product updated = new Product(productID,categoryID,modelNumber,modelName,quantity,null,price,comment);

        productService.update(updated);

        return "redirect:/admin/index.do";
    }
}
