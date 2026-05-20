package ch06;

import javax.servlet.ServletContext;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Scanner;

public class StudentDAO {
  Connection conn;
  PreparedStatement pstmt;

  // 1) 드라이버로드,   // 2) 데이터베이스 연결
  public void open(ServletContext sc) {
    conn = (Connection) sc.getAttribute("conn");
    if(conn == null) throw new RuntimeException("db connection 없음");
  }

  // 3) Statement 생성,   // 4) SQL 문 전송 ,   // 5) 결과 받아와서 처리
  public Student findById(int id) {
    Student s = null;
    try {
      pstmt = conn.prepareStatement("select * from student where id = ?");
      pstmt.setInt(1, id);
      ResultSet resultSet = pstmt.executeQuery();
      resultSet.next();
      // java.util.Date 가 java.sql.Date 의 부모 타입이라 업 캐스팅 가능
      s = new Student(
          resultSet.getInt("id"),
          resultSet.getString("name"),
          resultSet.getString("univ"),
          resultSet.getDate("birth"),
          resultSet.getString("email")
          );

    } catch (SQLException e) {
      System.out.println("학생정보 조회 오류");
      throw new RuntimeException(e);
    }
    System.out.println(s.getName());
    return s;
  }

  // 3) Statement 생성,   // 4) SQL 문 전송 ,   // 5) 결과 받아와서 처리
  public List<Student> findAll() {
    ArrayList<Student> studentList = new ArrayList<>();
    try {
      pstmt = conn.prepareStatement("select * from student");

      ResultSet resultSet = pstmt.executeQuery();
      while(resultSet.next()) {
        studentList.add(new Student(
            resultSet.getInt("id"),
            resultSet.getString("name"),
            resultSet.getString("univ"),
            resultSet.getDate("birth"),
            resultSet.getString("email")
        ));
      }
    } catch (SQLException e) {
      System.out.println("학생정보 조회 오류");
      throw new RuntimeException(e);
    }
    return studentList;
  }

  // 6) 연결 해제
  public void close() {
    try {
      pstmt.close();
      conn.close();
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }
}
