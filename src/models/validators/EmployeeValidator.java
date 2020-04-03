package models.validators;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import models.Employee;
import utils.DBUtil;

public class EmployeeValidator {

    public static List<String> validate(Employee e,boolean code_dupulicate_check_flag,boolean password_check_flag,boolean mail_dupulicate_check_flag){
        List<String>errors=new ArrayList<String>();

        String b=validate_code(e,code_dupulicate_check_flag);
        if(b!=null){
            errors.add(b);
        }

        String a=validate_name(e);
        if(a!=null){
            errors.add(a);
        }

        String c=validate_pass(e,password_check_flag);
        if(c!=null){
            errors.add(c);
        }

        String d=checkMail(e,mail_dupulicate_check_flag);
        if(d!=null){
            errors.add(d);
        }

        String f=checkAdmin(e);
        if(f!=null){
            errors.add(f);
        }

        return errors;
    }




    private static String validate_code(Employee e,boolean code_dupulicate_check_flag){
        if(e.getCode()==null||e.getCode().equals("")){
            return "社員番号を入力してください";
        }
        if(code_dupulicate_check_flag){
            EntityManager em=DBUtil.createEntityManager();
            long q=(long)em.createNamedQuery("checkRegisteredCode",Long.class).setParameter("code",e.getCode()).getSingleResult();
            em.close();
            if(q!=0){
                return "この社員番号はすでに使われています";
            }

        }
        if(!((e.getCode().length()==8)&&(e.getCode().matches("[0-9]*")))){
            return "社員番号は数字8桁です";
        }
        return null;
    }

    private static String validate_name(Employee e){
        if(e.getName()==null||e.getName().equals("")){
            return "なまえを入力してください";
        }
        return null;
    }

    private static String validate_pass(Employee e,boolean password_check_flag){
        if(password_check_flag){
        if(e.getPassword()==null||e.getPassword().equals("")){
            return "パスワードを入力してください";
        }
        if(!((e.getPassword().matches("[a-zA-Z_0-9]{8,}"))&&e.getPassword().matches(".*[0-9].*")&&e.getPassword().matches(".*[]a-zA-Z].*"))){
            return "パスワードは半角英数字８文字以上です";
        }
        }

        return null;

    }

    private static String checkMail(Employee e,boolean mail_dupulicate_check_flag){
        if(e.getMail()==null||e.getMail().equals("")){
            return "メールアドレスを入力してください";
        }
        if(mail_dupulicate_check_flag){
            EntityManager em=DBUtil.createEntityManager();
            long q=(long)em.createNamedQuery("checkRegisteredMail",Long.class).setParameter("mail",e.getMail()).getSingleResult();
            em.close();
            if(q!=0){
                return "このメールアドレスはすでに使われています";
            }

        }
        return null;
    }

    private static String checkAdmin(Employee e){
        if(e.getAdmin_flag()==null||e.getAdmin_flag().equals("")){
            return "権限を選択してください";
        }
        return null;
    }

    /*private static String validate_login(Boolean password_check_flag,Employee e){
        if(password_check_flag){
            EntityManager em=DBUtil.createEntityManager();
            int q=(int)em.createNamedQuery("checkLoginCodeAndPassword").setParameter("code", e.getCode()).setParameter("password", e.getPassword()).getSingleResult();
            em.close();
            if(q!=1){
                return "ログインできません";
            }
            return null;
        }
            return null;
}
    */



}
