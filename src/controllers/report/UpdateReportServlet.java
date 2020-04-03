package controllers.report;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

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
 * Servlet implementation class UpdateReportServlet
 */
@WebServlet("/report/update")
public class UpdateReportServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateReportServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session=request.getSession();
        String _token = (String)session.getAttribute("_token");
        if(_token != null && _token.equals(request.getSession().getId())) {
        //HttpSession session=request.getSession();
        Report r=(Report) session.getAttribute("report");

        String title=request.getParameter("title");
        String content=request.getParameter("content");
        Timestamp currentTime=new Timestamp(System.currentTimeMillis());
        String strDate=request.getParameter("date");
        SimpleDateFormat sd=new SimpleDateFormat("yyyy/MM/dd");
        Date date=new Date();
        try {
            date=sd.parse(strDate);
        } catch (ParseException e) {
            // TODO 自動生成された catch ブロック
            e.printStackTrace();
            //response.sendRedirect(request.getContextPath()+"/report/show");
        }finally{
            /*int id=r.getId();
            request.setAttribute("id", id);
            response.sendRedirect(request.getContextPath()+"/report/show");
            RequestDispatcher rd = request.getRequestDispatcher(request.getContextPath()+"/report/show");
            rd.forward(request, response);*/
        }

        EntityManager em=DBUtil.createEntityManager();
        em.getTransaction().begin();
        Report report=em.find(Report.class, r.getId());
        report.setTitle(title);
        report.setContent(content);
        report.setCreated_at(currentTime);
        report.setDate_at(date);

        session.removeAttribute("report");
        em.merge(report);
        em.getTransaction().commit();
        em.close();

        response.sendRedirect(request.getContextPath()+"/report/index");


    }

}
}