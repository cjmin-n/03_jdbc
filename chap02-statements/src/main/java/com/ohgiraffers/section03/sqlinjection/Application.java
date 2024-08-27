package com.ohgiraffers.section03.sqlinjection;

import java.sql.*;

import static com.ohgiraffers.common.JDBCTemplate.*;

public class Application {

    private static String empId = "210";
    private static String empName = "' OR 1=1 AND EMP_ID = '220";
    // Statement는 쿼리문으로 읽기때문에 오류발생되지만 PreparedStatement(미완성 쿼리만 !)는 문자열로 받기 때문에 오류발생하지 않음.

    public static void main(String[] args) {

        Connection con = getConnection();
        Statement stmt = null;
        // PreparedStatement pstmt = null;
        ResultSet rset = null;

        String query = "SELECT * FROM EMPLOYEE WHERE EMP_ID = '" + empId + "' AND EMP_NAME = '" + empName + "'";

        System.out.println(query);

        try {
            stmt = con.createStatement();
            // pstmt = con.prepareStatement(query);
            rset = stmt.executeQuery(query);
            // rset = pstmt.executeQuery();

            if(rset.next() == true) {
                System.out.println(rset.getString("EMP_NAME") + "님 환영합니다.");
            }else {
                System.out.println("회원 정보가 존재하지 않습니다.");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally{
            close(con);
            close(stmt);
            //close(pstmt);
            close(rset);
        }


    }

}
