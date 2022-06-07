package com.lding.rbpc.service;

import com.lding.rbpc.domain.Product;

/**
 * 定义产品接口的操作规范
 */
public interface IProductService {
    /**
     * 保存产品
     * @param product 产品
     */
    void save(Product product);

    /**
     * 根据产品id删除产品
     * @param productId 产品id
     */
    void deleteById(Long productId);

    /**
     * 修改产品信息
     * @param product 产品
     */
    void update(Product product);

    /**
     * 根据产品id获取到产品信息
     * @param productId 产品id
     * @return 产品
     */
    Product get(Long productId);
}
