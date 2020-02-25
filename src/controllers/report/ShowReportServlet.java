package controllers.report;

import java.io.IOException;

import javax.persistence.EntityManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Report;
import utils.DBUtil;

/**
 * Servlet implementation class ShowReportServlet
 */
@WebServlet("/report/show")
public class ShowReportServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public ShowReportServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        int id=Integer.parseInt(request.getParameter("id"));
        EntityManager em=DBUtil.createEntityManager();
        em.getTransaction().begin();
        Report r=em.find(Report.class, id);

        em.close();
        request.setAttribute("report", r);
        RequestDispatcher rd=request.getRequestDispatcher("/WEB-INF/views/reportPage/show.jsp");
        rd.forward(request, response);

    }

}
