package com.fashion.redis.entity;

/**
 * 商品实体类
 *
 * @author Wuxf
 * @date 2020/08/20
 **/
public class Product {

    /**
     * 商品ID
     */
    Long id;

    /**
     * 商品库存
     */
    Long inventory;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getInventory() {
        return inventory;
    }

    public void setInventory(Long inventory) {
        this.inventory = inventory;
    }
}
