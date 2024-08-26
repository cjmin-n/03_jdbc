package com.ohgiraffers.section01.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Application01 {
    public static void main(String[] args) {

        // DB 접속을 위한 Connection instance 생성
        Connection con = null;
        // DB 와의 연결을 위해서 접속 정보를 관리하는 객체

        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); // 이제 자동으로 해줘서 생략가능

            // DriverManager 를 이용해 Connection 생성 주소, 아이디, 비밀번호 전달
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/employee", "gangnam", "gangnam");

            System.out.println(con);

        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {

            try {
                con.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

        }



    }
}
