package controllers.tag;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Original_tag;
import utils.DBUtil;

/**
 * Servlet implementation class ShowTag
 */
@WebServlet("/showTag")
public class ShowTag extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public ShowTag() {
        super();
        // TODO Auto-generated constructor stub
    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        EntityManager em=DBUtil.createEntityManager();
        em.getTransaction().begin();
        List<Original_tag>tags=em.createNamedQuery("getAllOriginalTag",Original_tag.class).getResultList();
        request.setAttribute("tags", tags);
        em.close();
        RequestDispatcher rd=request.getRequestDispatcher("/WEB-INF/views/reportPage/ShowTag.jsp");
        rd.forward(request, response);

    }
    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //タグを全表示
        List<Original_tag>tag=new ArrayList<>();
        EntityManager em=DBUtil.createEntityManager();
        tag=em.createNamedQuery("getAllOriginalTag").getResultList();
        request.setAttribute("tag", tag);
        em.close();

        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/reportPage/ShowTag.jsp");
        rd.forward(request, response);
    }

}
