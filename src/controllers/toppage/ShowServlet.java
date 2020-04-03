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
import models.Picture;
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


        EntityManager em=DBUtil.createEntityManager();

        try{
            em.getTransaction().begin();
            int id=Integer.valueOf(request.getParameter("id"));

            Employee emp=em.find(Employee.class,id);
            request.setAttribute("emp",emp);
            String code=emp.getCode();

            HttpSession session=request.getSession();
            session.setAttribute("_token", request.getSession().getId());

            Employee login_emp=(Employee)session.getAttribute("login_emp");
            boolean admin=login_emp.getAdmin_flag();
            request.setAttribute("admin", admin);
            int delete=emp.getDelete_flag();
            request.setAttribute("delete", delete);
            Picture pic=em.find(Picture.class, code);
            if(pic!=null){
            request.setAttribute("pic", pic);
            }else{
            pic=null;
            request.setAttribute("pic", pic);
            }
            }

            finally{
                em.close();
            }



        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/topPage/show.jsp");
        rd.forward(request, response);
    }

}
