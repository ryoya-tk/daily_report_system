package controllers.toppage;

import java.io.IOException;
import java.sql.Timestamp;

import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Employee;
import utils.DBUtil;

/**
 * Servlet implementation class CreateServlet
 */
@WebServlet("/toppage/create")
public class CreateServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreateServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String code=request.getParameter("code");
        String name=request.getParameter("name");
        String mail=request.getParameter("mail");
        String phone=request.getParameter("phone");
        String password=request.getParameter("password");
        Boolean admin_flag=Boolean.valueOf(request.getParameter("admin"));
        Timestamp currentTime=new Timestamp(System.currentTimeMillis());

        EntityManager em=DBUtil.createEntityManager();
        em.getTransaction().begin();

        Employee emp=new Employee();
        emp.setCode(code);
        emp.setName(name);
        emp.setMail(mail);
        emp.setPhone(phone);
        emp.setPassword(password);
        emp.setAdmin_flag(admin_flag);
        emp.setDelete_flag(0);
        emp.setCreated_at(currentTime);
        emp.setUpdated_at(currentTime);

        em.persist(emp);
        em.getTransaction().commit();
        em.close();

        response.sendRedirect(request.getContextPath()+"/index.html");



    }

}
