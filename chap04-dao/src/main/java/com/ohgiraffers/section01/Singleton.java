package com.ohgiraffers.section01;

import java.sql.Connection;

import static com.ohgiraffers.common.JDBCTemplate.*;

public class Singleton {

    public static void main(String[] args) {

        // 싱글톤 객체 확인 - 같은 주소값!

        Connection con = getConnection();
        Connection con2 = getConnection();

        System.out.println(con); // com.mysql.cj.jdbc.ConnectionImpl@7c6908d7
        System.out.println(con2); // com.mysql.cj.jdbc.ConnectionImpl@7c6908d7

        System.out.println("--------------------------------------------------");

        // 싱글톤이 아닐 때는 getConnection2() 호출할 때마다 새로 Connection 생성
        Connection con3 = getConnection2();
        Connection con4 = getConnection2();

        System.out.println(con3); // com.mysql.cj.jdbc.ConnectionImpl@bcec361
        System.out.println(con4); // com.mysql.cj.jdbc.ConnectionImpl@26794848
    }
}
