package com.ohgiraffers.section03;

import java.sql.Connection;
import java.sql.SQLException;

import static com.ohgiraffers.common.JDBCTemplate.*;

public class Application {
    public static void main(String[] args) {

        // transaction 처리
        Connection con = getConnection2();
        // 여기서 호출한 이 커넥션 객체만 오토커밋을 끈 것.

        try {
            con.setAutoCommit(false);

            System.out.println("autoCommit : " + con.getAutoCommit());

            con.commit();
            // con.rollback();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
