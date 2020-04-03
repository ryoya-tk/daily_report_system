package controllers.toppage;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
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
import models.validators.EmployeeValidator;
import utils.DBUtil;
import utils.EncryptUtil;

/**
 * Servlet implementation class CreateServlet
 */
@WebServlet("/toppage/create")
public class CreateServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreateServlet() {
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

        String code=request.getParameter("code");
        String name=request.getParameter("name");
        String mail=request.getParameter("mail");
        String phone=request.getParameter("phone");
        String password=request.getParameter("password");
        Boolean admin_flag=Boolean.valueOf(request.getParameter("admin"));
        Timestamp currentTime=new Timestamp(System.currentTimeMillis());

        EntityManager em=DBUtil.createEntityManager();



        List<String>errors=new ArrayList<String>();


        Employee emp=new Employee();
        emp.setCode(code);
        emp.setName(name);
        emp.setMail(mail);
        emp.setPhone(phone);
        /*emp.setPassword(
                EncryptUtil.getPasswordEncrypt(
                        password,
                        (String)this.getServletContext().getAttribute("salt")
                        )
                );*/
        emp.setPassword(password);
        emp.setAdmin_flag(admin_flag);
        emp.setDelete_flag(0);
        emp.setCreated_at(currentTime);
        emp.setUpdated_at(currentTime);


        errors=EmployeeValidator.validate(emp,true,true,true);
        if(errors.size() > 0){
            em.close();



            request.setAttribute("_token", request.getSession().getId());
            request.setAttribute("emp", emp);
            request.setAttribute("errors", errors);

            RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/topPage/new.jsp");
            rd.forward(request, response);
        }
        else{
            emp.setPassword(
                    EncryptUtil.getPasswordEncrypt(
                            password,
                            (String)this.getServletContext().getAttribute("salt")
                            )
                    );

        em.getTransaction().begin();
        em.persist(emp);
        em.getTransaction().commit();
        em.close();

        response.sendRedirect(request.getContextPath()+"/index.html");
        }


    }

}
}
