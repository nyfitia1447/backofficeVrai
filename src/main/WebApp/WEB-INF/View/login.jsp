<%@ page import="tp_s6_back.Base.FonctionBase" %><%--
  Created by IntelliJ IDEA.
  User: Andrianiavo
  Date: 07/05/2023
  Time: 20:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String sql="select *idUtilsateur from utilisateur";
    try {
        out.print(FonctionBase.connect());
    } catch (Exception e) {
        out.print(e.getMessage());
    }
%>
<html>
<head>
    <title>Sing in</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/Asset/css/all.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/Asset/css/style.css">
    <!---->
    <link rel="stylesheet" type="text/css" href="../../../resources/static/Asset/css/all.css">
    <meta http-equiv="Content-Language" content="fr">
    <!---->
</head>
<body>
<div class="container">
    <div class="screen">
        <div class="screen__content">
            <form class="login" action="${pageContext.request.contextPath}/login" method="post" autocomplete="off">
                <div class="login__field">
                    <i class="login__icon fas fa-user"></i>
                    <input type="text" class="login__input" name="user" value="rakoto" placeholder="User name / Email">
                </div>
                <div class="login__field">
                    <i class="login__icon fas fa-lock"></i>
                    <input type="password" class="login__input" name="mdp" value="1234" placeholder="Password">
                </div>
                <button class="button login__submit" type="submit">
                    <span class="button__text">Log In Now</span>
                    <i class="button__icon fas fa-chevron-right"></i>
                </button>
            </form>
            <div class="social-login">
                <h3>log in via</h3>
                <div class="social-icons">
                    <a href="#" class="social-login__icon fab fa-instagram"></a>
                    <a href="#" class="social-login__icon fab fa-facebook"></a>
                    <a href="#" class="social-login__icon fab fa-twitter"></a>
                </div>
            </div>
        </div>
        <div class="screen__background">
            <span class="screen__background__shape screen__background__shape4"></span>
            <span class="screen__background__shape screen__background__shape3"></span>
            <span class="screen__background__shape screen__background__shape2"></span>
            <span class="screen__background__shape screen__background__shape1"></span>
        </div>
    </div>
</div>
</body>
</html>
