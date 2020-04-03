package controllers.tag;

import java.io.IOException;

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
 * Servlet implementation class NewTag
 */
@WebServlet("/newTag")
public class NewTag extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public NewTag() {
        super();
        // TODO Auto-generated constructor stub
    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Original_tag tag=new Original_tag();
        request.setAttribute("tag", tag);

        RequestDispatcher rd=request.getRequestDispatcher("/WEB-INF/views/reportPage/NewTag.jsp");
        rd.forward(request, response);

    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //バリデーション重複チェックとDB登録

        String title=(String) request.getParameter("title");
        System.out.println(title);
        EntityManager em=DBUtil.createEntityManager();
        try{
        em.getTransaction().begin();
        Original_tag new_tag=new Original_tag();
        long count=(long) em.createNamedQuery("checkRegisteredTitle", Long.class).setParameter("title", title).getSingleResult();
        System.out.println(title+""+count+"件");
        if(count<=0){

            new_tag.setTitle(title);
            em.persist(new_tag);

        }else{
            String error="このタグはすでに登録されています。重複はできません。";
            request.setAttribute("error", error);
        }
        }
        finally{em.close();}

        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/reportPage/NewTag.jsp");
        rd.forward(request, response);
    }

}
