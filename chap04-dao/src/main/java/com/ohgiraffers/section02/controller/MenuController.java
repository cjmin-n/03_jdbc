package com.ohgiraffers.section02.controller;

import com.ohgiraffers.section02.dao.MenuDAO;
import com.ohgiraffers.section02.dto.MenuDTO;

import java.util.List;
import java.util.Map;
import java.util.Scanner;

import static com.ohgiraffers.common.JDBCTemplate.*;

public class MenuController {
    // 사용자의 명령을 입력받아 DAO에 명령

    private MenuDAO menuDAO = new MenuDAO("src/main/resources/mapper/menu-query.xml");

    public void findMaxCode(){

        int result = menuDAO.selectLastMenuCode(getConnection2());
        System.out.println("최신 메뉴 코드 : " + result);
    }

    public void findCategoryList(){

        List<Map<Integer, String>> categoryList = menuDAO.selectAllCategoryList(getConnection2());
        System.out.print("총 카테고리 리스트 : ");
        for (Map<Integer, String> category : categoryList) {
            System.out.print(category);
        }
        System.out.println();
        System.out.println("총 카테고리 리스트 : " + categoryList);

    }

    public void insertMenu(){

        Scanner scr = new Scanner(System.in);
        MenuDTO menuDTO = new MenuDTO();
        System.out.println("메뉴명을 입력해주세요.");
        menuDTO.menuName(scr.nextLine());
        System.out.println("가격을 입력해주세요.");
        menuDTO.price(scr.nextInt());
        System.out.println("카테고리코드를 입력해주세요.");
        menuDTO.categoryCode(scr.nextInt());
        scr.nextLine();
        System.out.println("판매 여부를 등록해주세요.");
        menuDTO.status(scr.nextLine());

        int result = menuDAO.insertMenu(getConnection2(), menuDTO);

        if(result > 0){
            System.out.println("메뉴 등록 완료");
        }else {
            System.out.println("메뉴 등록 실패");
        }
    }


}
