package models.validators;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import models.Employee;
import utils.DBUtil;

public class EmployeeValidator {
    public static List<String>validate(Employee e,Boolean code_dupulicate_check_flag,Boolean password_check_flag){
        List<String>errors=new ArrayList<String>();

        String a=validate_name(e);
        if(a!=null){
            errors.add(a);
        }

        String b=validate_code(e);
        if(b!=null){
            errors.add(b);
        }

        String c=validate_pass(e);
        if(c!=null){
            errors.add(c);
        }

        String d=validate_login(password_check_flag,e);
        if(d!=null){
            errors.add(d);
        }

        return errors;
    }

    private static String validate_code(Employee e){
        if(e.getCode()==null||e.getCode().equals("")){
            return "社員番号を入力してください";
        }
        return null;
    }

    private static String validate_name(Employee e){
        if(e.getName()==null||e.getName().equals("")){
            return "なまえを入力してください";
        }
        return null;
    }

    private static String validate_pass(Employee e){
        if(e.getPassword()==null||e.getPassword().equals("")){
            return "パスワードを入力してください";
        }
        return null;

    }

    private static String validate_login(Boolean password_check_flag,Employee e){
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

    /*private static String validate_checkCode(Employee e,Boolean code_dupulicate_check_flag){
        if(code_dupulicate_check_flag){
            EntityManager em;
            int q=(int)em.createNamedQuery("checkRegisteredCode").setParameter("code",e.getCode()).getSingleResult();
            if(q!=0){
                return "社員番号の重複は禁止です";
            }
            em.close();
        }
        return null;
    }*/


}
