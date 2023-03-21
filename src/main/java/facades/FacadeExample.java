package facades;

import dtos.UserDTO;
import entities.User;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;

//import errorhandling.RenameMeNotFoundException;
import utils.EMF_Creator;

/**
 *
 * Rename Class to a relevant name Add add relevant facade methods
 */
public class FacadeExample {

    private static FacadeExample instance;
    private static EntityManagerFactory emf;
    
    //Private Constructor to ensure Singleton
    private FacadeExample() {}
    
    
    /**
     * 
     * @param _emf
     * @return an instance of this facade class.
     */
    public static FacadeExample getFacadeExample(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new FacadeExample();
        }
        return instance;
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
    
    public UserDTO create(UserDTO rm){
        User rme = new User(rm.getDummyStr1(), rm.getDummyStr2());
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(rme);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
        return new UserDTO(rme);
    }
    public UserDTO getById(long id) { //throws RenameMeNotFoundException {
        EntityManager em = emf.createEntityManager();
        User rm = em.find(User.class, id);
//        if (rm == null)
//            throw new RenameMeNotFoundException("The RenameMe entity with ID: "+id+" Was not found");
        return new UserDTO(rm);
    }
    
    //TODO Remove/Change this before use
    public long getRenameMeCount(){
        EntityManager em = getEntityManager();
        try{
            long renameMeCount = (long)em.createQuery("SELECT COUNT(r) FROM User r").getSingleResult();
            return renameMeCount;
        }finally{  
            em.close();
        }
    }
    
    public List<UserDTO> getAll(){
        EntityManager em = emf.createEntityManager();
        TypedQuery<User> query = em.createQuery("SELECT r FROM User r", User.class);
        List<User> rms = query.getResultList();
        return UserDTO.getDtos(rms);
    }
    
    public static void main(String[] args) {
        emf = EMF_Creator.createEntityManagerFactory();
        FacadeExample fe = getFacadeExample(emf);
        fe.getAll().forEach(dto->System.out.println(dto));
    }

}
