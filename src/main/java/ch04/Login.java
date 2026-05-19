package ch04;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/login")
public class Login extends HttpServlet {
  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    String id = req.getParameter("id");
    String pwd = req.getParameter("pwd");
    if(id.equals("aaa") && pwd.equals("1111")) {
      HttpSession session = req.getSession(true);
      session.setAttribute("id", id);
      resp.sendRedirect("ch04/loginOK.jsp");
    } else {
      resp.setCharacterEncoding("UTF-8");
      resp.getWriter().append("login failure");
    }

  }
}
