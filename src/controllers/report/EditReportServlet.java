package controllers.report;

import java.io.IOException;
import java.text.SimpleDateFormat;

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
 * Servlet implementation class EditReportServlet
 */
@WebServlet("/report/edit")
public class EditReportServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditReportServlet() {
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


        HttpSession session=request.getSession();
        Employee login_emp=(Employee)session.getAttribute("login_emp");
        if(login_emp.getCode().equals(r.getEmployee().getCode())){
        session.setAttribute("report",r);

        SimpleDateFormat sd=new SimpleDateFormat("yyyy/MM/dd");
        String date=sd.format(r.getDate_at());
        request.setAttribute("r_date", date);

        request.setAttribute("_token", request.getSession().getId());
        RequestDispatcher rd=request.getRequestDispatcher("/WEB-INF/views/reportPage/edit.jsp");
        rd.forward(request, response);
        }
        else{
            response.sendRedirect(request.getContextPath()+"/report/index");
        }

        /*login_emp.getCode() boolean equals(){

        }*/

    }

}
