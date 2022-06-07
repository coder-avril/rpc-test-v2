package com.lding.rbpw.controller;

import com.lding.rbpc.domain.Product;
import com.lding.rbpc.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private IProductService productService;

    @RequestMapping("/save")
    public String save(Product product) throws Exception{
        productService.save(product);
        return "success";
    }

    @RequestMapping("/get")
    public Product get(Long id) throws Exception{
        return productService.get(id);
    }

}
