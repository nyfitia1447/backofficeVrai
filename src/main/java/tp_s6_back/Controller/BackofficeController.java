package tp_s6_back.Controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;
import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import tp_s6_back.Base.FonctionBase;
import tp_s6_back.Model.Article;
import tp_s6_back.Model.User;

import java.io.InputStream;
import java.sql.Connection;
import java.util.Base64;
import java.util.Vector;

@Controller
@Component
@RequestMapping("/an")
public class BackofficeController {

    @Autowired
    private StandardPBEStringEncryptor encryptor;
    @GetMapping("/")
    public String index(HttpServletRequest request){
        System.out.println(request.getMethod());
        return "login";
    }

    @GetMapping("/page")
    public String page(HttpServletRequest request,Model model){
        HttpSession session=request.getSession();
        if (session.getAttribute("user")==null){
            return "redirect:/";
        }
        try(Connection connection= FonctionBase.connect()) {
            int offset = 0;
            if (request.getParameter("offset")!=null){
                offset=Integer.parseInt(request.getParameter("offset"));
            }
            int nomBreOffset = Article.nombreArticle(connection);
            Vector<Article>list=Article.paginate(connection,offset*3);
            for (Article a : list){
                a.setLien(encryptor.encrypt(String.valueOf(a.getIdArticle())));
                while (a.getLien().contains("+")){
                    a.setLien(encryptor.encrypt(String.valueOf(a.getIdArticle())));
                }
            }
            model.addAttribute("listeArticle",list);
            model.addAttribute("offset",nomBreOffset);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "ajoutArticle";
    }

    @PostMapping("/save")
    public String save(HttpServletRequest request) throws Exception {
        Part image=request.getPart("image");
        InputStream inputStream = image.getInputStream();
        byte[] bytes = inputStream.readAllBytes();

        String base64String = Base64.getEncoder().encodeToString(bytes);
        String titre=request.getParameter("titre");
        String date=request.getParameter("date");
        String contenu=request.getParameter("content");

        if(base64String==null ||base64String.equalsIgnoreCase("") ||titre==null
            || titre.equalsIgnoreCase("")|| date==null||date.equalsIgnoreCase("")
                ||contenu==null || contenu.equalsIgnoreCase("")){
            return "redirect:/page?offset=0";
        }

        contenu=contenu.replaceAll("'","\"");
        titre=titre.replaceAll("'", "&#39;");

        String sql="insert into article(titre, contenu, image,datePublication) VALUES ('%s','%s','%s','%s')";
        sql=String.format(sql,titre,contenu,base64String,date);
        FonctionBase.modifBase(sql);
        return "redirect:/page?offset=0";
    }

    @PostMapping("/login")
    public String showPage(HttpServletRequest request, Model model) throws Exception {
        String user=request.getParameter("user");
        String mdp=request.getParameter("mdp");

        HttpSession session= request.getSession();
        if (user==null){
            return "redirect:/";
        }
        if (User.login(mdp,user)) {
            session.setAttribute("user", "connected!");
            return "redirect:/page?offset=0";
        }
        else
            return "redirect:/";
    }
    @GetMapping("/supprimer")
    public String sup(HttpServletRequest request) throws Exception {
        Article.delete(encryptor.decrypt(request.getParameter("id")));
        return "redirect:/page?offset=0";
    }
}
