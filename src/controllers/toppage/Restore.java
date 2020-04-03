package controllers.toppage;

import java.io.IOException;
import java.sql.Timestamp;

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
 * Servlet implementation class Restore
 */
@WebServlet("/toppage/restore")
public class Restore extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public Restore() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session=request.getSession();
        String _token = (String)session.getAttribute("_token");
        if(_token != null && _token.equals(request.getSession().getId())) {
        int id=Integer.valueOf(request.getParameter("id"));

        Employee login_emp=(Employee)session.getAttribute("login_emp");
        if(login_emp.getAdmin_flag()!=true){
            response.sendRedirect(request.getContextPath()+"/index.html");
        }
        EntityManager em=DBUtil.createEntityManager();
        em.getTransaction().begin();
        Employee emp=em.find(Employee.class,id);

        if(emp.getDelete_flag()!=1){
            response.sendRedirect(request.getContextPath()+"/index.html");
        }
        emp.setDelete_flag(0);
        emp.setUpdated_at(new Timestamp(System.currentTimeMillis()));
        em.getTransaction().commit();
        em.close();


        response.sendRedirect(request.getContextPath()+"/index.html");
        }

}
}