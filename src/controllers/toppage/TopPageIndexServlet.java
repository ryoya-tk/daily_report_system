package controllers.toppage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Employee;
import utils.DBUtil;

/**
 * Servlet implementation class TopPageIndexServlet
 */
@WebServlet("/index.html")
public class TopPageIndexServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public TopPageIndexServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Employee> emp=new ArrayList<>();

/*      Employee e1=new Employee();
        e1.setAdmin_flag(true);
        e1.setCode("24678");
        e1.setName("Mochizuki");
        e1.setMail("sabakan@yahoo.com");
        e1.setPhone("090-1234-5678");
        e1.setPassword("kalp5485");
        e1.setDelete_flag(0);
        Timestamp currentTime=new Timestamp(System.currentTimeMillis());
        e1.setCreated_at(currentTime);
        e1.setUpdated_at(currentTime);
*/
        EntityManager em=DBUtil.createEntityManager();
/*        em.getTransaction().begin();
        em.persist(e1);
        em.getTransaction().commit();
*/
        emp=(List<Employee>) em.createNamedQuery("getAllEmployees").getResultList();

        Long count=(Long)em.createNamedQuery("getEmployeesCount").getSingleResult();

        em.close();

        request.setAttribute("empList", emp);
        request.setAttribute("count", count);

        //response.sendRedirect(request.getContextPath()+"/login");

        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/topPage/index.jsp");
        rd.forward(request, response);

    }
}
