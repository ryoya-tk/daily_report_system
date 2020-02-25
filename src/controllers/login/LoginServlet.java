package controllers.login;

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
 * Servlet implementation class LoginServlet
 */
@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Employee emp=new Employee();

        RequestDispatcher rd=request.getRequestDispatcher("/WEB-INF/views/login/login.jsp");
        rd.forward(request, response);


    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        int id=Integer.valueOf(request.getParameter("id"));
        String password=request.getParameter("password");

        EntityManager em=DBUtil.createEntityManager();
        em.getTransaction().begin();
        System.out.println("aa");

        Employee emp=em.find(Employee.class, id);
        if(emp==null){
            System.out.println("bb");
            RequestDispatcher rd=request.getRequestDispatcher("/WEB-INF/views/login/login.jsp");
            rd.forward(request, response);
        }
        else if(!emp.getPassword().equals(password)){

            System.out.println("cc");
            RequestDispatcher rd=request.getRequestDispatcher("/WEB-INF/views/login/login.jsp");
            rd.forward(request, response);
        }
        else{

            HttpSession session=request.getSession();
            session.setAttribute("login_emp", emp);
            response.sendRedirect(request.getContextPath()+"/index.html");
        }



    }

}
