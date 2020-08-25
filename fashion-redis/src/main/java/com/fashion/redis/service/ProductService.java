package com.fashion.redis.service;

/**
 * 订单接口
 *
 * @author Wuxf
 * @date 2020/08/20
 **/
public interface ProductService {
    /**
     * 修改商品库存
     *
     * @param productId 商品ID
     * @param quantity 购买数量
     * @return 结果
     */
    boolean updateProductInventory(String productId, Integer quantity);

    boolean setRedisProductInventory();
}
