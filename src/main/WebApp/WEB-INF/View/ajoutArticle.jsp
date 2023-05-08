<%@ page import="tp_s6_back.Model.Article" %>
<%@ page import="java.util.Vector" %><%--
  Created by IntelliJ IDEA.
  User: Andrianiavo
  Date: 06/05/2023
  Time: 07:52
  To change this template use File | Settings | File Templates.

--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%

    Vector<Article> articles= (Vector<Article>) request.getAttribute("listeArticle");
    int offset= (int) request.getAttribute("offset");

%>

<html lang="fr">
<head>
    <title>Intelligence artificielle</title>
    <meta http-equiv="Content-Language" content="fr">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/Asset/css/bootstrap.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/Asset/css/all.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/Asset/css/pagine.css">
    <script src="https://cdn.ckeditor.com/4.5.1/standard/ckeditor.js"></script>
    <!--
    <link rel="stylesheet" type="text/css" href="../../../resources/static/Asset/css/bootstrap.css">
    <link rel="stylesheet" type="text/css" href="../../../resources/static/Asset/css/all.css">
    -->
</head>

<body style="font-family: poppin sans-serif">
<div class="row fixed-top shadow p-3 mb-5 rounded" style="height: 70px; background: #1176a1;">
</div>
<div class="row"
     style="background-image: url('${pageContext.request.contextPath}/Asset/img/fond.jpg');
             height: 400px;width: 100%;margin: 0;  position: absolute;
             background-size: cover;
             background-repeat: no-repeat;
             left: 0;
             opacity: 12;">
</div>
<div class="row " style="width: 100%; position: absolute;top: 300px;margin: 0; background: none;">
    <div class="card col-6 offset-3 shadow-lg p-3 mb-5 bg-body rounded" >
        <form class="row g-3" action="${pageContext.request.contextPath}/save" method="post" enctype="multipart/form-data" autocomplete="off">
            <div class="col-md-6">
                <label for="inputEmail4" class="form-label">Titre</label>
                <input type="text" class="form-control" name="titre" id="inputEmail4">
            </div>
            <div class="col-md-6">
                <label for="inputPassword4" class="form-label">Date</label>
                <input type="date" class="form-control" name="date" id="inputPassword4">
            </div>
            <div class="col-12">
                <label for="inputAddress" class="form-label">image</label>
                <input type="file" class="form-control" name="image" accept="image/*" id="inputAddress" placeholder="Image">
            </div>
            <div class="col-12">
                <label for="inputAddress2" class="form-label">Contenue</label>
                <textarea class="form-control" id="inputAddress2" name="content"></textarea>
            </div>

            <div class="col-12">
                <div class="row">
                    <button type="submit" class="btn btn-primary">Ajouter</button>
                </div>
            </div>
        </form>
    </div>
</div>

<div class="row" style="width: 100%;position: absolute;top: 830px; margin: 0;background: none;">

    <div class="row my-4" style="overflow-x: hidden">
        <div class="row my-1" style="margin-left: 10px">
            <%
                for (Article a : articles){ %>
            <div class="col-4">
                <div class="card shadow-lg bg-body rounded h-100">
                    <img src="data:image/jpg;base64,<%=a.getImage()%>" class="card-img-top" alt="..." style="height: 200px">
                    <div class="card-body">
                        <h5 class="card-title"><%=a.getTitre()%></h5>
                        <p class="card-text" style="max-height: 100px; overflow-y: hidden;"><%=a.getContenu()%></p>
                        <p class="card-text"><a href="${pageContext.request.contextPath}/supprimer?id=<%=a.getLien()%>"><small class="text-muted">Supprimer</small></a></p>
                    </div>
                </div>
            </div>
            <% } %>
        </div>
        <div class="row text-center">
            <div class="pindicator">
                <div class="row">
                    <%
                        for (int i = 0; i <offset ; i++) { %>
                    <a class="col" href="${pageContext.request.contextPath}/page?offset=<%=i%>">
                        <div class="rounded-circle text-center" style="width: 50px;height: 50px; background: #1176a1;">
                            <p style="position: relative; top: 50%;left: 50%;color: white;transform: translate(-50%,-50%);">
                                <%=i+1%>
                            </p>
                        </div>
                    </a>
                    <% } %>
                </div>
            </div>
        </div>
    </div>
</div>


<script src="${pageContext.request.contextPath}/Asset/js/bootstrap.js"></script>
<script>
    CKEDITOR.replace('inputAddress2',{
        format_tags:'p;h1;h2;h3;h4;h5;h6',
        stylesSet: 'customStyles',
        height: 100
    });
</script>
</body>

</html>
