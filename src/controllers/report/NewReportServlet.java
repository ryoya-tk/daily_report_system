package controllers.report;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import models.Report;

/**
 * Servlet implementation class NewReportServlet
 */
@WebServlet("/report/new")
public class NewReportServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public NewReportServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Report report=new Report();
        request.setAttribute("report", report);
        HttpSession session=request.getSession();
        session.setAttribute("_token", request.getSession().getId());

        Date today=new Date();
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy/MM/dd");
        request.setAttribute("date_at", sdf.format(today));

        RequestDispatcher rd=request.getRequestDispatcher("/WEB-INF/views/reportPage/new.jsp");
        rd.forward(request, response);
    }

}
