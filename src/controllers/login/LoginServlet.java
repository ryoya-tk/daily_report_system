package controllers.login;

import java.io.IOException;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import models.Employee;
import utils.DBUtil;
import utils.EncryptUtil;

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



        RequestDispatcher rd=request.getRequestDispatcher("/WEB-INF/views/login/login.jsp");
        rd.forward(request, response);


    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String code=(request.getParameter("code"));
        String password=request.getParameter("password");

        password=EncryptUtil.getPasswordEncrypt(
                password,
                (String)this.getServletContext().getAttribute("salt")
                );//暗号化

        Employee emp=new Employee();
        long count=0;

        EntityManager em=DBUtil.createEntityManager();
        try{

        em.getTransaction().begin();
        count=(long)(em.createNamedQuery("checkLoginCodeAndPassword",Long.class).setParameter("code", code).setParameter("password",password).getSingleResult());
        }
        catch(NoResultException ex){}
        finally{em.close();}

        if(count!=1){

            System.out.println("apppppppppppppple");
            RequestDispatcher rd=request.getRequestDispatcher("/WEB-INF/views/login/login.jsp");
            rd.forward(request, response);
        }
        /*else if(!emp.getPassword().equals(password)){


            RequestDispatcher rd=request.getRequestDispatcher(request.getContextPath()+"/login");
            rd.forward(request, response);
        }*/
        else{
            EntityManager em2=DBUtil.createEntityManager();
            em2.getTransaction().begin();
            emp=(Employee)(em2.createNamedQuery("getEmployee",Employee.class).setParameter("code", code).getSingleResult());
            em2.close();
            HttpSession session=request.getSession();
            session.setAttribute("login_emp", emp);
            response.sendRedirect(request.getContextPath()+"/index.html");
        }






    }

}
