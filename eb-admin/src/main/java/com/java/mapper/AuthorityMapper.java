package com.java.mapper;

import com.java.pojo.admin.OneMenu;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

public interface AuthorityMapper {
    /**
     * 查看所有系统所有用户
     *
     * @return
     */
    @Select("SELECT * FROM admin_users")
    List<Map<String, Object>> getSystemUser();

    /**
     * 获取权限
     *
     * @param parentId
     * @return
     */
    @Select("SELECT * FROM admin_menus WHERE parentId=#{arg0} AND flag!='1'")
    List<Map<String, Object>> getAuthority(Long parentId);

    /**
     * 添加系统用户信息
     *
     * @param paramMap
     * @return
     */
    @Options(keyProperty = "userId", useGeneratedKeys = true, keyColumn = "id")
    @Insert("INSERT INTO admin_users VALUES(NULL,#{username},#{pwd},'0',NOW())")
    int addSystemUser(Map<String, Object> paramMap);

    @Insert("insert into admin_user_authority values(#{userId},#{menuId})")
    int addUserAuthority(Long userId, Long menuId);

    /**
     * 获取权限信息，通过关联关系描述
     *
     * @return
     */
    List<OneMenu> getAuthorityByRelation();
}
