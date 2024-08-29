package com.ohgiraffers.section02.dao;

import com.ohgiraffers.section02.dto.MenuDTO;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.*;

import static com.ohgiraffers.common.JDBCTemplate.*;

public class MenuDAO {
    // 데이터 액세스 오브젝트 - 데이터베이스와 상호작용을 할 클래스 (쿼리문 모아놓을 클래스)
    // 단일책임의 원칙

    private Properties prop = new Properties();


    public MenuDAO(String url) {
        // MenuDAO가 생성될 때 url 을 꼭 입력받아야 한다.
        // 객체로 만들어질 때 prop 에 url 을 받아서 준비가 된다.
        try {
            prop.loadFromXML(new FileInputStream(url));

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public int selectLastMenuCode(Connection con){

        Statement stmt = null;
        ResultSet rset = null;
        int maxCode = 0;

        String query = prop.getProperty("selectLastMenuCode");

        try {
            stmt = con.createStatement();
            rset = stmt.executeQuery(query);

            if(rset.next()){
                maxCode = rset.getInt("MAX(MENU_CODE)");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close(con);
            close(stmt);
            close(rset);
        }

        return maxCode;
    }

    public List<Map<Integer, String>> selectAllCategoryList(Connection con){

        List<Map<Integer, String>> categoryList;
        Map<Integer, String> category;

        PreparedStatement pstmt = null;
        ResultSet rset = null;

        try {
            pstmt = con.prepareStatement(prop.getProperty("selectAllCategoryList"));
            rset = pstmt.executeQuery();

            categoryList = new ArrayList<>();

            while(rset.next()){
                category = new HashMap<>();

                category.put(rset.getInt(1), rset.getString(2));
                categoryList.add(category);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close(con);
            close(pstmt);
            close(rset);
        }

        return categoryList;
    }

    public int insertMenu(Connection con, MenuDTO menuDTO) {
        PreparedStatement pstmt = null;

        int result = 0;

        String query = prop.getProperty("insertMenu");

        try {
            pstmt = con.prepareStatement(query);
            pstmt.setString(1, menuDTO.getMenuName());
            pstmt.setInt(2, menuDTO.getPrice());
            pstmt.setInt(3, menuDTO.getCategoryCode());
            pstmt.setString(4, menuDTO.getStatus());

            result = pstmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println("잘못된 값이 입력됨...");
        } finally {
            close(con);
            close(pstmt);

        }

        return result;
    }

    public int updateMenu(Connection con, MenuDTO menuDTO, String name) {
        PreparedStatement pstmt = null;
        int result = 0;

        try {
            pstmt = con.prepareStatement(prop.getProperty("updateMenu"));

            pstmt.setString(1, menuDTO.getMenuName());
            pstmt.setInt(2, menuDTO.getPrice());
            pstmt.setInt(3, menuDTO.getCategoryCode());
            pstmt.setString(4, menuDTO.getStatus());
            pstmt.setString(5, name);

            result = pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return result;
    }

    public int deleteMenu(Connection con, String name){
        PreparedStatement pstmt = null;
        int result = 0;
        String query = prop.getProperty("deleteMenu");

        try {
            pstmt = con.prepareStatement(query);
            pstmt.setString(1, name);

            result = pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return result;
    }

}
