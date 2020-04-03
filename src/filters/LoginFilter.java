package filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import models.Employee;

/**
 * Servlet Filter implementation class LoginFilter
 */
@WebFilter("/*")
public class LoginFilter implements Filter {

    /**
     * Default constructor.
     */
    public LoginFilter() {
        // TODO Auto-generated constructor stub
    }

    /**
     * @see Filter#destroy()
     */
    public void destroy() {
        // TODO Auto-generated method stub
    }

    /**
     * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
     */
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

/*
        Employee e1=new Employee();

        String password="qwerty123";

        password=EncryptUtil.getPasswordEncrypt(
                password,
                (String)((HttpServletRequest)request).getServletContext().getAttribute("salt")
                );//暗号化


        e1.setAdmin_flag(true);
        e1.setCode("20209999");
        e1.setName("管理太郎");
        e1.setMail("kanritaro@gmail.com");
        e1.setPhone("09012345678");
        e1.setPassword(password);
        e1.setDelete_flag(0);
        Timestamp currentTime=new Timestamp(System.currentTimeMillis());
        e1.setCreated_at(currentTime);
        e1.setUpdated_at(currentTime);

        int id=1;



        EntityManager em=DBUtil.createEntityManager();



        try{

        em.getTransaction().begin();
        Employee e=em.find(Employee.class,id);
        if(e==null){
        em.persist(e1);
        em.getTransaction().commit();
        }
        }
        finally{
        em.close();
        }
*/

        String context_path=((HttpServletRequest)request).getContextPath();
        String servlet_path=((HttpServletRequest)request).getServletPath();


        if(!servlet_path.equals("/css.*")){
        HttpSession session=((HttpServletRequest)request).getSession();
        Employee login_emp=(Employee)session.getAttribute("login_emp");


        if(!servlet_path.equals("/login")){
            if(login_emp==null){
            ((HttpServletResponse)response).sendRedirect(context_path+"/login");
            return;
            }
        }
        else if(servlet_path.equals("/login")){
            if(login_emp!=null){
                ((HttpServletResponse)response).sendRedirect(context_path+"/index.html");
                return;
            }
        }


        }

        chain.doFilter(request, response);
    }



    /**
     * @see Filter#init(FilterConfig)
     */
    public void init(FilterConfig fConfig) throws ServletException {
        // TODO Auto-generated method stub
    }

}
