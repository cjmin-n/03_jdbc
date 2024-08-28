package com.ohgiraffers.section02.dto;

public class MenuDTO {
    private String menuName;
    private int price;
    private int categoryCode;
    private String status;

    public MenuDTO() {
    }

    public MenuDTO(String menuName, int price, int categoryCode, String status) {
        this.menuName = menuName;
        this.price = price;
        this.categoryCode = categoryCode;
        this.status = status;
    }

    public MenuDTO menuName(String name){
        this.menuName = name;
        return this;
    }

    public MenuDTO price(int price){
        if(price <= 0){
            System.out.println("음수가 입력됨..");
        }else {
            this.price = price;
        }
        return this;
    }

    public MenuDTO categoryCode(int categoryCode){
        this.categoryCode = categoryCode;
        return this;
    }

    public MenuDTO status(String status){
        if(status.equals("예") || status.equals("Y")){
            this.status = "Y";
        }else if (status.equals("아니오") || status.equals("N")){
            this.status = "N";
        }else {
            System.out.println("잘못 입력됨 ..");
        }

        return this;
    }


    public String getMenuName() {
        return menuName;
    }


    public int getPrice() {
        return price;
    }


    public int getCategoryCode() {
        return categoryCode;
    }


    public String getStatus() {
        return status;
    }


    @Override
    public String toString() {
        return "MenuDTO{" +
                "menuName='" + menuName + '\'' +
                ", price=" + price +
                ", categoryCode='" + categoryCode + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
