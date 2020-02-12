package controllers.report;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.EntityManager;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Employee;
import models.Report;
import utils.DBUtil;

/**
 * Servlet implementation class CreateReportServlet
 */
@WebServlet("/report/create")
public class CreateReportServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreateReportServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        ServletContext application=this.getServletContext();
        Employee login_emp=(Employee) application.getAttribute("login_emp");

        String title=request.getParameter("title");
        String content=request.getParameter("content");
        //タグ
        Timestamp currentTime=new Timestamp(System.currentTimeMillis());

        Timestamp created_at=currentTime;
        Timestamp updated_at=currentTime;
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy/MM/dd");
        String date=request.getParameter("date");
        Date date_at=new Date();
        try {
            date_at = sdf.parse(date);
        } catch (ParseException e) {
            // TODO 自動生成された catch ブロック
            e.printStackTrace();
        }

        EntityManager em=DBUtil.createEntityManager();
        em.getTransaction().begin();

        Report r=new Report();
        r.setEmployee(login_emp);
        r.setTitle(title);
        r.setContent(content);
        r.setCreated_at(created_at);
        r.setUpdated_at(updated_at);
        r.setDate_at(date_at);


        em.persist(r);
        em.close();

        response.sendRedirect(request.getContextPath()+"/report/index");



    }

}
