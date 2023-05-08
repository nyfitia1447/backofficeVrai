package tp_s6_back.Model;

import tp_s6_back.Base.FonctionBase;

import java.util.Vector;

public class User {
    String mdp;
    String user;
    public static boolean login(String mdp,String user) throws Exception {
        String sql="select idUtilisateur from utilisateur where username='%s' and mdp='%s'";
        sql=String.format(sql,user,mdp);
        Vector[]all= FonctionBase.select(sql);
        return all[0].size() == 1;
    }
}
