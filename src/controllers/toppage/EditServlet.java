package controllers.toppage;

import java.io.IOException;

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
 * Servlet implementation class EditServlet
 */
@WebServlet("/toppage/edit")
public class EditServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditServlet() {
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

        HttpSession session=request.getSession();
        session.setAttribute("emp", emp);

        em.close();
        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/topPage/edit.jsp");
        rd.forward(request, response);
    }

}
