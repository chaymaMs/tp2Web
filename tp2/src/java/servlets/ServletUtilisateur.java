/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import utilisateurs.gestionnaire.GestionnaireUtilisateurs;
import utilisateurs.modele.Utilisateur;
/**
 *
 * @author michel
 */
@WebServlet(name = "ServletUtilisateurs", urlPatterns = {"/ServletUsers"})
public class ServletUtilisateur extends HttpServlet {
    // ici injection de code ! On n'initialise pas !
    @EJB
    private GestionnaireUtilisateurs gestionnaireUtilisateurs;
    private Iterable<Utilisateur> utilisateurs;
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Pratique pour décider de l'action à faire
        String action = request.getParameter("action");
        String nom=request.getParameter("nom");
        String prenom=request.getParameter("prenom");
        String login=request.getParameter("login");
        String forwardTo = "";
        String message = "";
        HttpSession session = request.getSession(true);
        
        if (action != null) {
            if (action.equals("listerLesUtilisateurs")) {
                Collection<Utilisateur> liste = gestionnaireUtilisateurs.getAllUsers();
                request.setAttribute("listeDesUsers", liste);
                forwardTo = "index.jsp?action=listerLesUtilisateurs";
                message = "Liste des utilisateurs";
            } else if (action.equals("creerUtilisateursDeTest")) {
                gestionnaireUtilisateurs.creerUtilisateursDeTest();
                Collection<Utilisateur> liste = gestionnaireUtilisateurs.getAllUsers();
                request.setAttribute("listeDesUsers", liste);
                forwardTo = "index.jsp?action=listerLesUtilisateurs";
                message = "Liste des utilisateurs";
                
            } 
              else if (action.equals("creerUnUtilisateur")) {
                if((!nom.isEmpty()&& !prenom.isEmpty()&& !login.isEmpty())){
                gestionnaireUtilisateurs.creeUtilisateur(login,nom,prenom);
                Collection<Utilisateur> liste = gestionnaireUtilisateurs.getAllUsers();
                request.setAttribute("listeDesUsers", liste);
                forwardTo = "index.jsp?action=listerLesUtilisateurs";
                message = "Liste des utilisateurs";
                }  else{
                Collection<Utilisateur> liste = gestionnaireUtilisateurs.getAllUsers();
                request.setAttribute("listeDesUsers", liste);
                forwardTo = "index.jsp?action=listerLesUtilisateurs";
                message = "Liste des utilisateurs"; 
                }
              }
              else if (action.equals("chercherParLogin")) {
                Collection<Utilisateur> liste = gestionnaireUtilisateurs.getUser(login);
                request.setAttribute("listeDesUsers", liste);
                forwardTo = "index.jsp?action=listerLesUtilisateurs";
                message = "utilisateur trouve";
               }
             else if (action.equals("updateUtilisateur")) { 
                  
                gestionnaireUtilisateurs.updateUser(login, nom, prenom);   
                Collection<Utilisateur> liste = gestionnaireUtilisateurs.getAllUsers();
                request.setAttribute("listeDesUsers", liste);
                forwardTo = "index.jsp?action=listerLesUtilisateurs";
                message = "Utilisateur modifié";
                       
            } 
              else if (action.equals("deleteUtilisateur")) { 
                     
                gestionnaireUtilisateurs.deleteUser(login);
               Collection<Utilisateur> liste = gestionnaireUtilisateurs.getAllUsers();
                request.setAttribute("listeDesUsers", liste);
                forwardTo = "index.jsp?action=listerLesUtilisateurs";
                message = "Liste des utilisateurs";
                    
            }
              
              else if (action.equals("checkConnexion")) {
		if(request.getParameter("log").equals("toto") && request.getParameter("pass").equals("toto")) {
                    session.setAttribute("login", "toto");           
                    session.setAttribute("mdp", "toto");        
                    session.setAttribute("connecte", true);
                    
                    forwardTo = "index.jsp?action=listerLesUtilisateurs";
                    message = "Vous etes connecte";
                }else {      
                    session.setAttribute("connecte", false);
                    forwardTo = "index.jsp?action=listerLesUtilisateurs";
                    message = "Mot de passe incorrect";
                      }
               }
               else if(action.equals("deconnexion")) { 
                    session.setAttribute("connecte", false);
                    forwardTo = "index.jsp?action=listerLesUtilisateurs";
                    message = "Vous ete deconnecte";
                      }
              
              else {
                forwardTo = "index.jsp?action=todo";
                message = "La fonctionnalité pour le paramètre " + action + " est à implémenter !";
            }
          
        }
        RequestDispatcher dp = request.getRequestDispatcher(forwardTo + "&message=" + message);
        dp.forward(request, response);
        // Après un forward, plus rien ne peut être exécuté après !
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }
  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }
}