package com.java.pojo.admin;

import lombok.Data;

import java.util.List;

@Data
public class OneMenu {
    private Long oneId;
    private String oneText;
    private List<TwoMenu> twoMenuList;
}
