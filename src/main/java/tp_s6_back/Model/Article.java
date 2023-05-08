package tp_s6_back.Model;


import tp_s6_back.Base.FonctionBase;

import java.sql.Connection;
import java.util.Date;
import java.util.Vector;

public class Article {
    int idArticle ;
    String titre ;
    Date datePublication ;
    String contenu , image ,lien;

    public Article(int idArticle, String titre, Date datePublication, String contenu, String image) {
        this.idArticle = idArticle;
        this.titre = titre;
        this.datePublication = datePublication;
        this.contenu = contenu;
        this.image = image;
    }

    public int getIdArticle() {
        return idArticle;
    }

    public void setIdArticle(int idArticle) {
        this.idArticle = idArticle;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public Date getDatePublication() {
        return datePublication;
    }

    public void setDatePublication(Date datePublication) {
        this.datePublication = datePublication;
    }

    public String getContenu() {
        return contenu;
    }

    public void setContenu(String contenu) {
        this.contenu = contenu;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getLien() {
        return lien;
    }

    public void setLien(String lien) {
        this.lien = lien;
    }

    public static int nombreArticle(Connection connection) throws Exception {
        String sql="select count(idArticle) from article";
        Vector valiny= FonctionBase.all(sql,connection)[0];

        if (valiny.size() == 1){
            int size= ((Long) valiny.elementAt(0)).intValue();

            int no=size/3;
            if (size%3==0 && size!=0){
                return no;
            }
            return no+1;
        }
        return 0;
    }
    public static Vector<Article>all() throws Exception {
        Vector<Article> valiny=new Vector<>();
        String sql="select idArticle,titre,datePublication,contenu,image from article ";
        Vector[]list= FonctionBase.select(sql);
        for (int i = 0; i <list[0].size() ; i++) {
            Article article=new Article((int)list[0].elementAt(i),(String)list[1].elementAt(i),(Date) list[2].elementAt(i),(String) list[3].elementAt(i),(String) list[4].elementAt(i));
            valiny.add(article);
        }
        return valiny;
    }

    public static Vector<Article>paginate(Connection connection,int offset) throws Exception {
        Vector<Article> valiny=new Vector<>();
        String sql="select idArticle,titre,datePublication,contenu,image from article order by idArticle limit 3 offset "+offset;
        Vector[]list= FonctionBase.all(sql,connection);
        for (int i = 0; i <list[0].size() ; i++) {
            Article article=new Article((int)list[0].elementAt(i),(String)list[1].elementAt(i),(Date) list[2].elementAt(i),(String) list[3].elementAt(i),(String) list[4].elementAt(i));
            valiny.add(article);
        }
        return valiny;
    }

    public static Article getById(String id) throws Exception {
        String sql="select idArticle,titre,datePublication,contenu,image from article where idArticle="+id;
        Vector[]list=FonctionBase.select(sql);
        Article article=null;
        for (int i = 0; i <list[0].size() ; i++) {
            article=new Article((int)list[0].elementAt(i),(String)list[1].elementAt(i),(Date) list[2].elementAt(i),(String) list[3].elementAt(i),(String) list[4].elementAt(i));
            break;
        }
        return article;
    }
    public static void delete(String id) throws Exception {
        String sql="delete from article where idArticle="+id;
        FonctionBase.modifBase(sql);
    }
}
