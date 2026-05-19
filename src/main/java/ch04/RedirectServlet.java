package ch04;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/redirect")
public class RedirectServlet extends HttpServlet {
  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    Student s = new Student();
    s.name = "홍길동";
    s.score = 90;
    String path="ch04/redirect.jsp";
    req.setAttribute("info", s);
    HttpSession session = req.getSession();
    session.setAttribute("sharedInfo", s);
    resp.sendRedirect(path);
  }
}
