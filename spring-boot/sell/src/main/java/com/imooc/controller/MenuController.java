package com.imooc.controller;

import com.imooc.VO.ResultVO;
import com.imooc.dto.MenuDTO;
import com.imooc.service.MenuService;
import com.imooc.utils.ResultVOUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/menu")
@Slf4j
public class MenuController {

    @Autowired
    private MenuService menuService;

    //订单列表
    @GetMapping("/list")
    public ResultVO<List<MenuDTO>> list() {
        return ResultVOUtil.success(menuService.findList(""));
    }

}
