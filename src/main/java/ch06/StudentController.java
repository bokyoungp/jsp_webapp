package ch06;

import ch05.Product;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/student")
public class StudentController extends HttpServlet {
  StudentDAO service;

  @Override
  public void init() throws ServletException {
    // 1),2) open()
    service = new StudentDAO();
    service.open();
  }

  @Override
  protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    // 3), 4), 5) CRUD
    String action = req.getParameter("action");
    String view = "/ch06/";
    switch (action) {
      case "list" :
        view += list(req,resp);
        getServletContext().getRequestDispatcher(view).forward(req,resp);
        break;
      case "create" :
        if(req.getMethod().equals("GET")) {
          view += "studentForm.jsp";
          getServletContext().getRequestDispatcher(view).forward(req, resp);
        } else if(req.getMethod().equals("POST")) {
          String url = create(req,resp);
          resp.sendRedirect(url);
        }
        break;
    }

  }

  @Override
  public void destroy() {
    // 6) close()
    service.close();
  }

  // http://localhost:8080/student?action=list
  private String list(HttpServletRequest req, HttpServletResponse resp){
    // findAll() , findById(id)
    //Student student = service.findById(1);
    List<Student> studentList = service.findAll();
    req.setAttribute("studentList", studentList);
    return "studentList.jsp";
  }

  private String create(HttpServletRequest req, HttpServletResponse resp){
    return "/student?action=list";
  }
}
