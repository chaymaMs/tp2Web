<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<!-- Ne pas oublier cette ligne sinon tous les tags de la JSTL seront ignorés ! -->
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:if test="${!connecte}"> 
    <form action="ServletUsers" method="get" id="formconnexion"> 
        Connexion : 
        <input type="text" name="log"><br/>
        <input type="text" name="pass"><br/> 
        <input type="hidden" name="action" value="checkConnexion">
        <input type="submit" name="submit" value="Connexion">
    </form>
    </c:if>
<c:if test="${connecte}">
    <a href="ServletUsers?action=deconnexion">Déconnexion</a>
    </c:if>