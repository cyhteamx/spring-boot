package com.imooc.service;

import com.imooc.dto.MenuDTO;

import java.util.List;

public interface MenuService {

    /** 查询订单列表. */
    List<MenuDTO> findList(String id);

}
