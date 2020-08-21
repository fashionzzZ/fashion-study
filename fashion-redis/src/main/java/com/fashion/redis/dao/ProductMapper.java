package com.fashion.redis.dao;

import com.fashion.redis.entity.Product;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * dao
 *
 * @author Wuxf
 * @date 2020/08/20
 **/
@Mapper
public interface ProductMapper {
    /**
     * 根据ID获取商品信息
     *
     * @param productId 商品ID
     * @return 商品信息
     */
    Product selectById(@Param("productId") Long productId);

    /**
     * 修改商品数量
     *
     * @param productId 商品ID
     * @param inventory 购买数量
     * @return 结果
     */
    int updateInventoryById(@Param("productId") Long productId, @Param("inventory") Long inventory);
}
