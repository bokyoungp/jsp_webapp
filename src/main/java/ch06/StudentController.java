package ch06;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/student")
public class StudentController extends HttpServlet {
  @Override
  public void init() throws ServletException {
    // 1),2) open()
  }

  @Override
  protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    // 3), 4), 5) CRUD
  }

  @Override
  public void destroy() {
    // 6) close()
  }
}
