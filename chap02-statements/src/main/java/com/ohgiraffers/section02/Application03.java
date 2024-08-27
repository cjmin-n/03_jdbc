package com.ohgiraffers.section02;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import static com.ohgiraffers.common.JDBCTemplate.*;

public class Application03 {
    public static void main(String[] args) {

        // 성씨를 입력 받아 해당 성을 가진 사원 조회
        // SELECT EMP_ID, EMP_NAME FROM EMPLOYEE WHERE EMP_NAME LIKE CONCAT(?, '%');


        Connection con = getConnection();
        PreparedStatement pstmt = null;
        ResultSet rset = null;

        Scanner scr = new Scanner(System.in);
        System.out.println("성씨를 입력해주세요.");
        String lastName = scr.nextLine();

        String query = "SELECT EMP_ID, EMP_NAME FROM EMPLOYEE WHERE EMP_NAME LIKE CONCAT(?, '%')";

        try {

            pstmt = con.prepareStatement(query);
            pstmt.setString(1, lastName); // 미완성인 쿼리에 완성을 시켜주는 구문

            rset = pstmt.executeQuery();

            while(rset.next()) {
                System.out.println(rset.getString(1) + " " + rset.getString(2));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close(con);
            close(pstmt);
            close(rset);
        }


    }
}
