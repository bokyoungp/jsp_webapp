package ch07;

import javax.servlet.ServletContextAttributeEvent;
import javax.servlet.ServletContextAttributeListener;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

@WebListener
public class TestListener implements ServletContextListener, ServletContextAttributeListener,
    HttpSessionAttributeListener, HttpSessionListener {
  @Override
  public void attributeAdded(ServletContextAttributeEvent event) {
    event.getServletContext().log("ServletContext 속성이 추가되는 이벤트가 발생함 ==> " + event.getName() + ":" + event.getValue());
  }

  @Override
  public void attributeRemoved(ServletContextAttributeEvent event) {
    event.getServletContext().log("ServletContext 속성이 삭제되는 이벤트가 발생함 ==> " + event.getName());
  }

  @Override
  public void attributeReplaced(ServletContextAttributeEvent event) {
    event.getServletContext().log("ServletContext 속성이 변경되는 이벤트가 발생함 ==> " + event.getName() + ":" + event.getValue());
  }

  @Override
  public void contextInitialized(ServletContextEvent sce) {
    sce.getServletContext().log("===================> ServletContext 가 생성됨");
    // 여기에 db.properties 의 정보와 db.sql 의 정보 읽어와서 작업하는 부분 추가하ㅣ
  }

  @Override
  public void contextDestroyed(ServletContextEvent sce) {
    sce.getServletContext().log("ServletContext 가 종료됨");
  }

  @Override
  public void attributeAdded(HttpSessionBindingEvent event) {
    event.getSession().getServletContext().log("세션의 속성이 추가됨" + event.getName() + ":" + event.getValue());
  }

  @Override
  public void attributeRemoved(HttpSessionBindingEvent event) {
    HttpSessionAttributeListener.super.attributeRemoved(event);
  }

  @Override
  public void attributeReplaced(HttpSessionBindingEvent event) {
    HttpSessionAttributeListener.super.attributeReplaced(event);
  }

  @Override
  public void sessionCreated(HttpSessionEvent se) {
    HttpSessionListener.super.sessionCreated(se);
  }

  @Override
  public void sessionDestroyed(HttpSessionEvent se) {
    HttpSessionListener.super.sessionDestroyed(se);
  }
}
