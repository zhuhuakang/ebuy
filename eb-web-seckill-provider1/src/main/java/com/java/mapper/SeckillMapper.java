package com.java.mapper;

import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

public interface SeckillMapper {
    /**
     * 查询开始秒杀的商品
     *
     * @return
     */
    @Select("SELECT * FROM `web_seckill` WHERE `startTime`<=NOW() AND `endTime`>=NOW() AND `status`=0;")
    List<Map<String, Object>> findSeckillProduct();

    /**
     * 设置秒杀的商品的状态
     *
     * @param productId
     */
    @Select("UPDATE `web_seckill` SET `status`=1 WHERE `productId`={productId};")
    void setSeckillStatus(int productId);
}
