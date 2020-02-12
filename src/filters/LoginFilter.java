package filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

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

        System.out.println("aaa");
        String context_path=((HttpServletRequest)request).getContextPath();
        String servlet_path=((HttpServletRequest)request).getServletPath();






        if(!servlet_path.equals("/css.*")){
        ServletContext application=((HttpServletRequest)request).getServletContext();
        Employee login_emp=(Employee)application.getAttribute("login_emp");
        System.out.println("bbb");
        if(login_emp==null){
            System.out.println("ccc");
            ((HttpServletResponse)response).sendRedirect("/daily_report_system/login");
            return;
        }

        System.out.println("eee");
        }
    */
        chain.doFilter(request, response);
        System.out.println("fff");
    }



    /**
     * @see Filter#init(FilterConfig)
     */
    public void init(FilterConfig fConfig) throws ServletException {
        // TODO Auto-generated method stub
    }

}
