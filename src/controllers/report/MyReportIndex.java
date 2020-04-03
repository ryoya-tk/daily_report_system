package controllers.report;

import java.io.IOException;
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
import models.Report;
import utils.DBUtil;

/**
 * Servlet implementation class MyReportIndex
 */
@WebServlet("/report/myreport")
public class MyReportIndex extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public MyReportIndex() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session=request.getSession();
        Employee login_emp=(Employee) session.getAttribute("login_emp");

        EntityManager em=DBUtil.createEntityManager();
        em.getTransaction().begin();
        List<Report>reports=em.createNamedQuery("getAllMyReport",Report.class).setParameter("login_emp_code",login_emp.getCode()).getResultList();
        em.close();

        request.setAttribute("reports", reports);


        String _token = (String)session.getAttribute("_token");
        if(_token!=null){
            session.removeAttribute("_token");
        }

        RequestDispatcher rd=request.getRequestDispatcher("/WEB-INF/views/reportPage/indexMyReport.jsp");
        rd.forward(request, response);
    }

}
