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
 * Servlet implementation class UpdateServlet
 */
@WebServlet("/toppage/update")
public class UpdateServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateServlet() {
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

        Employee login_emp=(Employee) session.getAttribute("login_emp");
        if(login_emp.getAdmin_flag()!=true){
            response.sendRedirect(request.getContextPath()+"/index.html");
        }

        String code=request.getParameter("code");
        String name=request.getParameter("name");
        String mail=request.getParameter("mail");
        String phone=request.getParameter("phone");
        String password=request.getParameter("password");
        Boolean admin_flag=Boolean.valueOf(request.getParameter("admin"));
        Timestamp currentTime=new Timestamp(System.currentTimeMillis());





        //HttpSession session=request.getSession();
        Employee e=(Employee)session.getAttribute("emp");

        EntityManager em=DBUtil.createEntityManager();
        em.getTransaction().begin();

        List<String>errors=new ArrayList<String>();

        Employee emp=em.find(Employee.class,e.getId());



        String original_pass=emp.getPassword();

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
        emp.setUpdated_at(currentTime);




        Boolean code_duplicate_check = true;
        if(e.getCode().equals(code)){
            code_duplicate_check = false;
        }
        Boolean password_check_flag = true;
        if(password == null || password.equals("")) {
            password_check_flag = false;
            emp.setPassword(original_pass);
        } else {
            /*e.setPassword(
                    EncryptUtil.getPasswordEncrypt(
                            password,
                            (String)this.getServletContext().getAttribute("salt")
                            )
                    );*/
        }

        boolean mail_duplicate_check=true;
        if(e.getMail().equals(mail)){
            mail_duplicate_check=false;
        }

        errors=EmployeeValidator.validate(emp,code_duplicate_check,password_check_flag,mail_duplicate_check);

        if(errors.size() > 0) {
            em.close();

            request.setAttribute("_token", request.getSession().getId());
            request.setAttribute("employee", e);
            request.setAttribute("errors", errors);

            RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/topPage/edit.jsp");
            rd.forward(request, response);
        } else {


            if(!(password == null || password.equals(""))){emp.setPassword(
                    EncryptUtil.getPasswordEncrypt(
                            password,
                            (String)this.getServletContext().getAttribute("salt")
                            )
                    );}

        em.persist(emp);
        em.getTransaction().commit();
        em.close();


        if(emp.getCode().equals(login_emp.getCode())){
            session.setAttribute("login_emp", emp);
        }

        session.removeAttribute("emp");
        response.sendRedirect(request.getContextPath()+"/index.html");
        }


    }

}
}