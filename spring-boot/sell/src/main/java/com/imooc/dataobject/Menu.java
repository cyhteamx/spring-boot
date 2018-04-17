package com.imooc.dataobject;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Data
@Table(name="SELL_MENU")
public class Menu {

    /** 菜单ID. */
    @Id
    @Column(name="ID")
    private Integer menuId;

    /** 菜单名称. */
    private String name;

    /** 菜单URL. */
    private String url;

    /** 序号. */
    private String number;

    /** 是否叶子节点:0父节点,1叶节点. */
    private Integer isleaf;

    /** 层级:一级菜单,二级菜单,三级菜单. */
    private Integer level;

    /** 父节点:一级菜单为0. */
    @Column(name="PARENT_ID")
    private Integer parentId;

    /** 菜单icon. */
    private String icon;

    /** 创建时间. */
    @Column(name="CREATE_TIME")
    private Date createTime;

    /** 更新时间. */
    @Column(name="UPDATE_TIME")
    private Date updateTime;

}
