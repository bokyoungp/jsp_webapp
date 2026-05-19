package ch06;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentDAO {
  Connection conn;
  PreparedStatement pstmt;
  final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
  final String JDBC_URL = "jdbc:mysql://127.0.0.1/jwbook?serverTimezone=Asia/Seoul";

  // 1) 드라이버로드,   // 2) 데이터베이스 연결
  public void open() {
    try {
      Class.forName(JDBC_DRIVER);
      conn = DriverManager.getConnection(JDBC_URL, "root", "1111");
    } catch (Exception e) {
      System.out.println("드라이버 연결 오류");
      throw new RuntimeException(e);
    }
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
