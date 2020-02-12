package controllers.toppage;

import java.io.IOException;

import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import models.Employee;
import utils.DBUtil;

/**
 * Servlet implementation class UpdateServlet
 */
@WebServlet("/toppage/update")
public class UpdateServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateServlet() {
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

        HttpSession session=request.getSession();
        Employee e=(Employee)session.getAttribute("emp");



        EntityManager em=DBUtil.createEntityManager();
        em.getTransaction().begin();
        Employee emp=em.find(Employee.class,e.getId());


        /*em.merge(emp);*/

        emp.setCode(code);
        emp.setName(name);
        emp.setMail(mail);
        emp.setPhone(phone);


        em.persist(emp);
        em.getTransaction().commit();
        em.close();

        session.removeAttribute("emp");
        response.sendRedirect(request.getContextPath()+"/index.html");



    }

}
