package com.ohgiraffers.section03.delete;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;
import java.util.Scanner;

import static com.ohgiraffers.common.JDBCTemplate.*;

public class Application {
    public static void main(String[] args) {

        Scanner scr = new Scanner(System.in);
        Connection con = getConnection();
        PreparedStatement pstmt = null;
        Properties prop = new Properties();
        int result = 0;

        try {
            prop.loadFromXML(new FileInputStream("src/main/resources/mapper/menu-query.xml"));
            pstmt = con.prepareStatement(prop.getProperty("deleteMenu"));

            System.out.println("삭제할 메뉴명을 입력 해주세요.");
            String menuName = scr.nextLine();

            pstmt.setString(1, menuName);
            result = pstmt.executeUpdate();

            if(result == 1){
                //System.out.println(result);
                System.out.println("성공적으로 삭제되었습니다.");
            } else{
                //System.out.println(result);
                System.out.println("삭제에 실패했습니다.");
            }

        } catch (IOException | SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close(con);
            close(pstmt);
        }

    }
}
