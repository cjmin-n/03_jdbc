package com.ohgiraffers.section01;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import static com.ohgiraffers.common.JDBCTemplate.*;

public class Application {
    public static void main(String[] args) {

        Scanner scr = new Scanner(System.in);
        Connection con = getConnection();
        Properties prop = new Properties();

        PreparedStatement pstmt1 = null;
        PreparedStatement pstmt2 = null;
        PreparedStatement pstmt3 = null;

        ResultSet rset1 = null;
        ResultSet rset2 = null;
        List<Map<Integer, String>> categoryList = null;

        int result = 0;
        int result2 = 0;

        try {
            prop.loadFromXML(new FileInputStream("src/main/resources/mapper/menu-query.xml"));
            String query = prop.getProperty("selectLastMenuCode");
            String query2 = prop.getProperty("selectAllCategoryList");
            String query3 = prop.getProperty("insertMenu");

            pstmt1 = con.prepareStatement(query);
            pstmt2 = con.prepareStatement(query2);
            pstmt3 = con.prepareStatement(query3);

            rset1 = pstmt1.executeQuery();

            if(rset1.next()) { // 결과값이 아예 없을 때부터!
                result = rset1.getInt("MAX(MENU_CODE)");
            }

            System.out.println("최신 메뉴 코드 : " + result);


            rset2 = pstmt2.executeQuery();
            categoryList = new ArrayList<>();

            while(rset2.next()){
                Map<Integer, String> category = new HashMap<>();
                category.put(rset2.getInt("CATEGORY_CODE"), rset2.getString("CATEGORY_NAME"));
                // category.put(rset2.getInt(1), rset2.getString(2));
                categoryList.add(category);
            }
            System.out.println("categoryList = " + categoryList);


            System.out.println("추가할 메뉴명을 입력해주세요.");
            String menuName = scr.nextLine();
            System.out.println("메뉴 가격을 입력해주세요.");
            int menuPrice = scr.nextInt();
            System.out.println("카테고리 코드를 입력해주세요.");
            System.out.println("4.한식 5.중식 6.일식 7.퓨전 8.커피 9.쥬스 10.기타 11.동양 12.서양");
            int categoryCode = scr.nextInt();
            scr.nextLine();
            System.out.println("주문가능한 상태를 입력해주세요.");
            System.out.println("Y / N");
            String orderable = scr.nextLine();

            pstmt3.setString(1, menuName);
            pstmt3.setInt(2, menuPrice);
            pstmt3.setInt(3, categoryCode);
            pstmt3.setString(4, orderable);

            result2 = pstmt3.executeUpdate();

            if(result2 == 1){
                System.out.println("새로운 메뉴가 추가되었습니다.");
            }else {
                System.out.println("메뉴 추가 실패했습니다.");
            }


        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close(con);
            close(pstmt1);
            close(rset1);
            close(pstmt2);
            close(rset2);
            close(pstmt3);
        }

    }
}
