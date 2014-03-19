package utilisateurs.gestionnaire;  
  
import java.util.ArrayList;
import java.util.Collection;  
import javax.ejb.Stateless;  
import javax.persistence.EntityManager;  
import javax.persistence.PersistenceContext;  
import javax.persistence.Query;  
import utilisateurs.modele.Utilisateur;  
  
@Stateless  
public class GestionnaireUtilisateurs {  
    // Ici injection de code : on n'initialise pas. L'entity manager sera créé  
    // à partir du contenu de persistence.xml  
    @PersistenceContext  
    private EntityManager em;  
  
    public void creerUtilisateursDeTest() {  
        creeUtilisateur("jlennon","John", "Lennon");  
        creeUtilisateur( "pmc","Paul", "Mac Cartney");  
        creeUtilisateur( "rstarr","Ringo", "Starr");  
        creeUtilisateur("georgesH","Georges", "Harisson");  
    }  
  
    public Utilisateur creeUtilisateur(String login,String nom, String prenom) {  
     
        if(!loginExist(login)){
              Utilisateur u = new Utilisateur(login, nom, prenom);  
            em.persist(u); 
            return u;
            
        }
        return null; 
    }  
  
    public Collection<Utilisateur> getAllUsers() {  
        // Exécution d'une requête équivalente à un select *  
        Query q = em.createQuery("select u from Utilisateur u");  
        return q.getResultList();  
    }  
    // Add business logic below. (Right-click in editor and choose  
    // "Insert Code > Add Business Method")  
    public Collection<Utilisateur> getUser(String login) {  
        // Exécution d'une requête équivalente à un select *  
        Query q = em.createQuery("select u from Utilisateur u where u.login='"+login+"'");  
        return q.getResultList();  
    }   
   public void deleteUser(String login){
       Collection<Utilisateur> utilisateurs= getUser(login);
        for(Utilisateur u : utilisateurs){
            em.remove(u);
        }
   }
   public boolean loginExist(String login){
        boolean val=false;
        Collection<Utilisateur> utilisateurs= getAllUsers();
        for(Utilisateur u : utilisateurs){
            if(u.getLogin().equals(login)){
                val= true;
            }
        }
        return val;
    }
}