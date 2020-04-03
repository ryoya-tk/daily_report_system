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
import javax.servlet.http.HttpSession;

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
        EntityManager em=DBUtil.createEntityManager();


        emp=(List<Employee>) em.createNamedQuery("getAllEmployees").getResultList();

        Long count=(Long)em.createNamedQuery("getEmployeesCount").getSingleResult();

        em.close();

        request.setAttribute("empList", emp);
        request.setAttribute("count", count);

        HttpSession session=request.getSession();
        String _token = (String)session.getAttribute("_token");
        if(_token!=null){
            session.removeAttribute("_token");
        }
        //response.sendRedirect(request.getContextPath()+"/login");

        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/topPage/index.jsp");
        rd.forward(request, response);

    }
}
