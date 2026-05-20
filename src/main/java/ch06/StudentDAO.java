package ch06;

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
  // ✅ 기존 하드코딩 제거 → properties에서 로드
  private static final Properties properties = new Properties();

  static {
    try (InputStream is = StudentDAO.class
        .getClassLoader()
        .getResourceAsStream("db.properties")) {

      if (is == null) throw new RuntimeException("db.properties 파일을 찾을 수 없습니다.");
      properties.load(is);

    } catch (IOException e) {
      throw new RuntimeException("db.properties 로드 실패", e);
    }
  }


  // 1) 드라이버로드,   // 2) 데이터베이스 연결
  public void open() {
    try {
      Class.forName(properties.getProperty("db.driver"));
      conn = DriverManager.getConnection(
          properties.getProperty("db.url"),
          properties.getProperty("db.user"),
          properties.getProperty("db.pwd"));
      initFromSqlFile("db.sql");
    } catch (Exception e) {
      System.out.println("드라이버 연결 오류");
      throw new RuntimeException(e);
    }
  }

  private void initFromSqlFile(String fileName) throws Exception {
    InputStream is = getClass().getClassLoader().getResourceAsStream(fileName);

    if (is == null) {
      throw new RuntimeException(fileName + " 파일을 찾을 수 없습니다.");
    }

    String sql = new Scanner(is).useDelimiter("\\A").next();

    try (Statement stmt = conn.createStatement()) {
      for (String s : sql.split(";")) {
        String trimmed = s.trim();
        if (!trimmed.isEmpty()) {
          stmt.execute(trimmed);
        }
      }
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
