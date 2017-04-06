package com.br.desafiozup.model;

import com.br.desafiozup.R;

public class MenuDrawerItem {

    int code;
    String imageUser;
    String NameUser;
    String menuName;
    int imageResource;
    boolean header;

    public MenuDrawerItem() {
    }

    public MenuDrawerItem(int code, String imageUser, String nameUser, String menuName, int imageResource, boolean header) {
        this.imageUser = imageUser;
        NameUser = nameUser;
        this.menuName = menuName;
        this.imageResource = imageResource;
        this.code = code;
        this.header = header;
    }

    public MenuDrawerItem(int code, String menuName, int imageResource) {
        this(code, "", "", menuName, imageResource, false);
    }

    public MenuDrawerItem(int code, String nameUser) {
        this(code, "", nameUser, "", R.drawable.logo_zup, true);
    }


    public String getImageUser() {
        return imageUser;
    }

    public void setImageUser(String imageUser) {
        this.imageUser = imageUser;
    }

    public String getNameUser() {
        return NameUser;
    }

    public void setNameUser(String nameUser) {
        NameUser = nameUser;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public int getImageResource() {
        return imageResource;
    }

    public void setImageResource(int imageResource) {
        this.imageResource = imageResource;
    }

    public boolean isHeader() {
        return header;
    }

    public void setHeader(boolean header) {
        this.header = header;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

}
