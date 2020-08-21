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
    Product selectById(@Param("productId") Long productId);
    int updateInventoryById(@Param("productId") Long productId, @Param("inventory") Long inventory);
}
