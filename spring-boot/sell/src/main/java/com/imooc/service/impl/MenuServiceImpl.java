package com.imooc.service.impl;

import com.imooc.converter.Menu2MenuDTOConverter;
import com.imooc.dataobject.Menu;
import com.imooc.dto.MenuDTO;
import com.imooc.repository.MenuRepository;
import com.imooc.service.MenuService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class MenuServiceImpl implements MenuService {

    @Autowired
    private MenuRepository menuRepository;


    @Override
    public List<MenuDTO> findList(String menuId) {
        List<Menu> menus = menuRepository.findAll();

        List<MenuDTO> menuDTOList = Menu2MenuDTOConverter.convert(menus);
        return  menuDTOList;
    }

}
