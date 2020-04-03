package controllers.upload;

import java.io.FileNotFoundException;
import java.io.IOException;

import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import org.apache.tomcat.util.http.fileupload.disk.DiskFileItemFactory;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;

import models.Employee;
import models.Picture;
import utils.DBUtil;

/**
 * Servlet implementation class UploadCreate
 */
@WebServlet("/upload/create")
@MultipartConfig(maxFileSize=1048576)
public class UploadCreate extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public UploadCreate() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        response.getWriter().append("Served at: ").append(request.getContextPath());
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session=request.getSession();
        Employee emp=(Employee)session.getAttribute("emp");

        EntityManager em=DBUtil.createEntityManager();
        em.getTransaction().begin();

        DiskFileItemFactory factory = new DiskFileItemFactory();
        ServletFileUpload fileUpload = new ServletFileUpload(factory);

        try{
        String save_directory="C:/pleiades/workspace/daily_report_system/WebContent/WEB-INF/uploaded";//WebContent配下

        Part part = request.getPart("file");
        String name = this.getFileName(part);


        part.write(save_directory+"/"+name);



        Picture pic=new Picture();
        pic=em.find(Picture.class, emp.getCode());//一度pictureを検索

        if(pic!=null){
        pic.setEmployee(emp);
        pic.setEmployee_id(emp.getCode());
        pic.setPass(save_directory+"/"+name);//写真ファイルのパスをDBに格納
        em.persist(pic);
        }
        else{
            Picture new_pic=new Picture();
            new_pic.setEmployee(emp);
            new_pic.setEmployee_id(emp.getCode());
            new_pic.setPass(save_directory+"/"+name);
            em.persist(new_pic);
        }

        em.getTransaction().commit();
        }
        catch(FileNotFoundException ex){
            System.out.println("ファイルがない状態でアップロードが押されました。");
            response.sendRedirect(request.getContextPath()+"/index.html");
        }
        finally{
        System.out.println("例外部分");
        em.close();
        session.removeAttribute("emp");

        }

        response.sendRedirect("/daily_report_system"+"/index.html");

    }

    private String getFileName(Part part) {
        String name = null;
        for (String dispotion : part.getHeader("Content-Disposition").split(";")) {
            if (dispotion.trim().startsWith("filename")) {
                name = dispotion.substring(dispotion.indexOf("=") + 1).replace("\"", "").trim();
                name = name.substring(name.lastIndexOf("\\") + 1);
                break;
            }
        }
        return name;
    }


}
