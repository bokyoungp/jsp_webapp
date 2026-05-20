package ch07;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.Properties;
import java.util.Scanner;

@WebListener
public class DBConnetionListener implements ServletContextListener {
  ServletContext sc ;
  Connection conn;

  // db.properties에서 db 설정정보 로드해서 connection 정보를 sc.attribute 에 추가하기
  private static final Properties properties = new Properties();

  static {
    try (InputStream is = DBConnetionListener.class
        .getClassLoader()
        .getResourceAsStream("db.properties")) {

      if (is == null) throw new RuntimeException("db.properties 파일을 찾을 수 없습니다.");
      properties.load(is);

    } catch (IOException e) {
      throw new RuntimeException("db.properties 로드 실패", e);
    }
  }

  @Override
  public void contextDestroyed(ServletContextEvent sce) {
    ServletContextListener.super.contextDestroyed(sce);
  }

  @Override
  public void contextInitialized(ServletContextEvent sce) {
    sc = sce.getServletContext();
    try {
      Class.forName(properties.getProperty("db.driver"));
      conn = DriverManager.getConnection(
          properties.getProperty("db.url"),
          properties.getProperty("db.user"),
          properties.getProperty("db.pwd"));

      sc.setAttribute("conn", conn);
      System.out.println("드라이버 연결 OK");
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
}
