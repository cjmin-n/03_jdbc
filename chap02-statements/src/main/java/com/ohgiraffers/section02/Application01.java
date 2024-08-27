package com.ohgiraffers.section02;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static com.ohgiraffers.common.JDBCTemplate.*;

public class Application01 {
    public static void main(String[] args) {


        Connection con = getConnection();

        PreparedStatement pstmt = null; // Statement 를 상속받은 클래스 - Statement의 문제점 때문에 주로 사용됨.
        ResultSet rset = null;

        try {
            pstmt = con.prepareStatement("SELECT EMP_ID, EMP_NAME FROM EMPLOYEE");
            rset = pstmt.executeQuery();

            while(rset.next()){
                System.out.println(rset.getString(1) + " " + rset.getString(2)); // getString() 내부에는 "이름" 또는 인덱스번호 가능
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close(con);
            close(rset);
            close(pstmt);
        }


    }
}
