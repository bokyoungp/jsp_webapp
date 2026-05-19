package ch05;

// /pcontrol?action=list , /pcontrol?action=info

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/pcontrol")
public class ProductController extends HttpServlet {
  ProductService service;

  @Override
  public void init() throws ServletException {
    System.out.println("controller 의 초기화가 진행됩니다.");
    service = new ProductService();
  }

  @Override
  protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    // 1. 사용자의 요청 처리
    if(req.getMethod().equals("POST")) {
      doPost(req, resp);
    } else if(req.getMethod().equals("GET")) {
      doGet(req, resp);
    }
  }

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    // 2. 사용자의 요청을 모델에 넘겨주기, 어떤 화면에 보여줄 지를 받아옴
    String action = req.getParameter("action");
    String view = "/ch05/";
    switch (action) {
      case "list" :
        view += list(req,resp); break;
      case "info" :
        view += info(req,resp);
        break;
    }
    // 3. 화면 보여주기
    getServletContext().getRequestDispatcher(view).forward(req, resp);
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    // 2. 사용자의 요청을 모델에 넘겨주기, 어떤 화면에 보여줄 지를 받아옴
    String action = req.getParameter("action");
    String url = "";
    switch (action) {
      case "insert" :
        url = insert(req,resp); break;
    }
    resp.sendRedirect(url);
  }

  // http://localhost:8080/pcontrol?action=list
  private String list(HttpServletRequest req, HttpServletResponse resp){
    // findAll()
    List<Product> productList = service.findAll();
    req.setAttribute("productList", productList);
    return "productList.jsp";
  }

  // http://localhost:8080/pcontrol?action=info&id=2
  private String info(HttpServletRequest req, HttpServletResponse resp) {
    // findById()
    String id = req.getParameter("id");
    req.setAttribute("product", service.findById(id));
    return "productInfo.jsp";
  }

  // http://localhost:8080/pcontrol?action=insert
  private String insert(HttpServletRequest req, HttpServletResponse resp) {
    // insert()
    Product p = new Product(
        req.getParameter("id"),
        req.getParameter("name"),
        req.getParameter("maker"),
        Integer.parseInt(req.getParameter("price")),
        req.getParameter("date")
    );
    service.insert(p);
    return "/pcontrol?action=list";
  }
}
