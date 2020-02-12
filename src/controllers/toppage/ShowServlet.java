package controllers.toppage;

import java.io.IOException;

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
 * Servlet implementation class ShowServlet
 */
@WebServlet("/toppage/show")
public class ShowServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public ShowServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        int id=Integer.valueOf(request.getParameter("id"));
        EntityManager em=DBUtil.createEntityManager();

        Employee emp=em.find(Employee.class, id);
        request.setAttribute("emp",emp);

        em.close();
        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/topPage/show.jsp");
        rd.forward(request, response);
    }

}