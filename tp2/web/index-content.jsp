
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<!-- Ne pas oublier cette ligne sinon tous les tags de la JSTL seront ignorés ! -->
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:if test="${!connecte}"> 
    <h1>Veuillez d'abord vous identifier pour accèder au site</h1>
</c:if>
<c:if test="${connecte}"> 


    <div class="row">
        <div class="col-md-6">
            <!-- Message qui s'affiche lorsque la page est appelé avec un paramètre http message -->
            <c:if test="${!empty param['message']}">
                <h2>Reçu message : ${param.message}</h2>
            </c:if>



            <h2>Menu de gestion des utilisateurs</h2>
            <ul>
                <li><a href="ServletUsers?action=listerLesUtilisateurs">Afficher/raffraichir la liste de tous les utilisateurs</a></li>
                <p>
            </ul>

            <ol>
                <li>Combien voulez-vous créer d'utilisateurs </li>
                <form action="ServletUsers" method="get">
                    Nombre : <input type="texte" name="utilisateur"/><br>
                    <!-- Astuce pour passer des paramètres à une servlet depuis un formulaire JSP !-->
                    <input type="hidden" name="action" value="creerDesUtilisateurs"/>
                    <input type="submit" value="Créer des utilisateurs" name="submit"/>
                </form>
                
                <li><a href="ServletUsers?action=creerUtilisateursDeTest">Créer 4 utilisateurs de test</a></li>

                <li>Créer un utilisateur</li>
                <form action="ServletUsers" method="get">
                    Nom : <input type="text" name="nom"/><br>
                    Prénom : <input type="text" name="prenom"/><br>
                    Login : <input type="text" name="login"/><br>
                    <!-- Astuce pour passer des paramètres à une servlet depuis un formulaire JSP !-->
                    <input type="hidden" name="action" value="creerUnUtilisateur"/>
                    <input type="submit" value="Créer l'utilisateur" name="submit"/>
                </form>

                <li>Afficher les détails d'un utilisateur</li>
                <form action="ServletUsers" method="get">
                    login : <input type="text" name="login"/><br>
                    <input type="hidden" name="action" value="chercherParLogin"/>
                    <input type="submit" value="Chercher" name="submit"/>
                </form>


                <li>Modifier les détails d'un utilisateur :</li>
                <form action="ServletUsers" method="get">
                    Login : <input type="text" name="login"/><br>
                    Nom : <input type="text" name="nom"/><br>
                    Prénom : <input type="text" name="prenom"/><br>
                    <input type="hidden" name="action" value="updateUtilisateur"/>
                    <input type="submit" value="Mettre à jour" name="submit"/>
                </form>
                <li>Supprimer un utilisateur :</li>  
                <form action="ServletUsers" method="get">  
                    Login : <input type="text" name="login"/><br>   
                    <input type="hidden" name="action" value="deleteUtilisateur"/>  
                    <input type="submit" value="Supprimer" name="submit"/>  
                </form>
            </ol>
        </div>
        <!-- Fin du menu -->

        <!-- Zone qui affiche les utilisateurs si le paramètre action vaut listerComptes -->
        <div class="col-md-6">
            <c:if test="${param['action'] == 'listerLesUtilisateurs'}" >
                <h2>Liste des utilisateurs</h2>

                <table class="table table-bordered">
                    <!-- La ligne de titre du tableau des comptes -->
                    <tr>
                        <td><b>Login</b></td>
                        <td><b>Nom</b></td>
                        <td><b>Prénom</b></td>
                    </tr>

                    <!-- Ici on affiche les lignes, une par utilisateur -->
                    <!-- cette variable montre comment on peut utiliser JSTL et EL pour calculer -->
                    <c:set var="total" value="0"/>

                    <c:forEach var="u" items="${requestScope['listeDesUsers']}">
                        <tr>

                            <td>${u.login}</td>
                            <td>${u.firstname}</td>
                            <td>${u.lastname}</td>


                            <!-- On compte le nombre de users -->
                            <c:set var="total" value="${total+1}"/>
                        </tr>
                    </c:forEach>

                    <!-- Affichage du solde total dans la dernière ligne du tableau -->
                    <tr><td><b>TOTAL</b></td>
                        <td></td>
                        <td><b>${total}</b></td>
                        </tr>
                </table>

            </c:if>
        </div>
    </div>
    </c:if>