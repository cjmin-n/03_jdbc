package com.ohgiraffers.understand;

import com.ohgiraffers.dto.EmployeeDTO;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import static com.ohgiraffers.common.JDBCTemplate.*;

public class Application {
    public static void main(String[] args) {

        Connection con = getConnection();
        PreparedStatement pstmt = null;
        ResultSet rset = null;

        Properties prop = new Properties();
        EmployeeDTO row = null;
        List<EmployeeDTO> empList = null;

        try {
            prop.loadFromXML(new FileInputStream("src/main/java/com/ohgiraffers/understand/employee-query.xml"));
            pstmt = con.prepareStatement(prop.getProperty("empInfo"));

            // System.out.println(pstmt);
            rset = pstmt.executeQuery();
            // System.out.println(rset);

            empList = new ArrayList<EmployeeDTO>();

            while(rset.next()) {
                row = new EmployeeDTO();

                row.setEmpId(rset.getString("EMP_ID"));
                row.setEmpName(rset.getString("EMP_NAME"));
                row.setEmpNo(rset.getString("EMP_NO"));
                row.setEmail(rset.getString("EMAIL"));
                row.setPhone(rset.getString("PHONE"));
                row.setDeptCode(rset.getString("DEPT_CODE"));
                row.setJobCode(rset.getString("JOB_CODE"));
                row.setSalLevel(rset.getString("SAL_LEVEL"));
                row.setSalary(rset.getInt("SALARY"));
                row.setBonus(rset.getDouble("BONUS"));
                row.setManagerId(rset.getString("MANAGER_ID"));
                row.setHireDate(rset.getDate("HIRE_DATE"));
                row.setEntDate(rset.getDate("ENT_DATE"));
                row.setEntYn(rset.getString("ENT_YN"));

                empList.add(row);
            }

            pstmt = con.prepareStatement(prop.getProperty("empJob"));
            rset = pstmt.executeQuery();

            while(rset.next()){
                System.out.println("2번 : " + rset.getString(1) + " " + rset.getString(2) + " " + rset.getString(3) + " " + rset.getString(4) + " " + rset.getString(5));
            }


        } catch (IOException | SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close(con);
            close(pstmt);
            close(rset);
        }

        for (EmployeeDTO emp : empList) {
            // System.out.println(emp);
            System.out.println("1번 : " + emp.getEmpId() + " " + emp.getEmpName() + " " + emp.getEmpNo() + " " + emp.getEmail() + " " + emp.getPhone() + " " + emp.getDeptCode() + " " + emp.getJobCode() + " " + emp.getSalLevel() + " " + emp.getSalary() + " " + emp.getBonus() + " " + emp.getManagerId() + " " + emp.getHireDate() + " " + emp.getEntDate() + " " + emp.getEntYn());
        }
    }
}
