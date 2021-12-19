package com.example.myapplication.model;

public class MenuModel {

    public String menuName;
    public Boolean hasChildren,isGroup;

    public MenuModel(String menuName, Boolean hasChildren, Boolean isGroup) {
        this.menuName = menuName;
        this.hasChildren = hasChildren;
        this.isGroup = isGroup;
    }
}
