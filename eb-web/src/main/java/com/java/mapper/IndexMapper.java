package com.java.mapper;

import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

/**
 * description：
 * author：丁鹏
 * date：09:46
 */
public interface IndexMapper {

    /**
     * 查询横向或者纵向导航栏
     *
     * @param menuType
     * @return
     */
    @Select("SELECT * FROM web_menu WHERE menuType=#{arg0} ORDER BY updateTime ASC LIMIT 8")
    List<Map<String, Object>> selectWebMenus(String menuType);

    /**
     * 查询所有商品的信息
     *
     * @return
     */
    @Select("SELECT wpd.*,ws.`sortName` FROM `web_product_detail` wpd \n" +
            "INNER JOIN `web_sort` ws ON wpd.`typeId`=ws.id")
    List<Map<String, Object>> getAllProductDetail();

    /**
     * 根据商品id获取商品图片
     *
     * @param productId
     * @return
     */
    @Select("SELECT wpi.`imgUrl` FROM  `web_product_detail` wpd INNER JOIN `web_product_img` wpi \n" +
            "ON wpd.`id`=wpi.`productId` WHERE wpd.id=#{productId}")
    List<String> getProductImgUrls(Long productId);

}
