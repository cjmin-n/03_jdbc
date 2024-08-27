package com.ohgiraffers.section01.insert;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;
import java.util.Scanner;

import static com.ohgiraffers.common.JDBCTemplate.*;

public class Application02 {
    public static void main(String[] args) {

        /*
        * 사용자가 원하는 메뉴를 등록할 수 있도록 만들어주세요.
        * 등록이 완료되면 성공, 실패하면 실패라고 출력해주세요.
        * */

        Scanner scr = new Scanner(System.in);
        Connection con = getConnection();
        PreparedStatement pstmt = null;

        Properties prop = new Properties();
        int result = 0;

        try {
            prop.loadFromXML(new FileInputStream("src/main/resources/mapper/menu-query.xml"));
            pstmt = con.prepareStatement(prop.getProperty("insertMenu"));

            System.out.println("== 사용자가 원하는 메뉴 등록 ==");
            System.out.println("메뉴명을 입력해주세요.");
            String menuName = scr.nextLine();
            System.out.println("가격을 입력해주세요.");
            int menuPrice = scr.nextInt();
            System.out.println("카테고리코드를 입력해주세요.");
            int categoryCode = scr.nextInt();
            scr.nextLine();
            System.out.println("주문가능 여부를 선택해주세요. (Y/N)");
            String orderableStatus = scr.nextLine();

            pstmt.setString(1, menuName);
            pstmt.setInt(2, menuPrice);
            pstmt.setInt(3, categoryCode);
            pstmt.setString(4, orderableStatus);

            result = pstmt.executeUpdate();


        } catch (IOException | SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close(con);
            close(pstmt);
        }

        if(result == 1){
            System.out.println("등록에 성공했습니다");
        }else {
            System.out.println("등록에 실패했습니다.");
        }
    }
}
