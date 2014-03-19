package utilisateurs.gestionnaire;  
  
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
        creeUtilisateur( "jlennon","John", "Lennon");  
        creeUtilisateur("pmc","Paul", "Mac Cartney");  
        creeUtilisateur("rstarr","Ringo", "Starr");  
        creeUtilisateur("georgesH","Georges", "Harisson");  
    }  
  
    public Utilisateur creeUtilisateur(String nom, String prenom, String login) {  
        if(loginExist(login)){
            Utilisateur u = new Utilisateur(nom, prenom, login);  
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