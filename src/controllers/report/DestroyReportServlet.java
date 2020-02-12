package controllers.report;

import java.io.IOException;

import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import models.Report;
import utils.DBUtil;

/**
 * Servlet implementation class DestroyReportServlet
 */
@WebServlet("/report/destroy")
public class DestroyReportServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public DestroyReportServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session=request.getSession();
        Report r=(Report)session.getAttribute("report");

        EntityManager em=DBUtil.createEntityManager();
        em.getTransaction().begin();
        Report report=em.find(Report.class, r.getId());
        em.remove(report);

        session.removeAttribute("report");
        em.close();

        response.sendRedirect(request.getContextPath()+"/report/index");
    }

}
