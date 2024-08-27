package com.ohgiraffers.section01.insert;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;

import static com.ohgiraffers.common.JDBCTemplate.*;

public class Application01 {
    public static void main(String[] args) {

        Connection con = getConnection();
        PreparedStatement pstmt = null;
        // 성공여부만 담아줄 것이어서 ResultSet 이 필요없음

        // 결과를 담아줄 변수 - 쿼리 반환 값 : 성공 1 / 실패 0
        int result = 0;

        Properties prop = new Properties();

        try {
            prop.loadFromXML(new FileInputStream("src/main/resources/mapper/menu-query.xml"));
            pstmt = con.prepareStatement(prop.getProperty("insertMenu"));

            pstmt.setString(1, "쌀국수");
            pstmt.setInt(2, 11900);
            pstmt.setInt(3, 4);
            pstmt.setString(4, "Y");

            result = pstmt.executeUpdate();

            System.out.println("결과 : " + result);

        } catch (IOException | SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close(con);
            close(pstmt);
        }

    }
}
