package com.java.service;

import java.util.List;
import java.util.Map;

/**
 * description：
 * author：丁鹏
 * date：09:50
 */
public interface IndexService {
    List<Map<String, Object>> findWebMenus(String menuType);

    List<Map<String, Object>> findZXMenu();

    void clearZxMenu();

    List<Map<String, Object>> findAllProductDetail();

    List<Map<String, Object>> findUpdatedProductDetail();
}
