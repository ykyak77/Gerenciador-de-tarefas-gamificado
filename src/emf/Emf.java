package emf;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author ifsp
 */
public class Emf {

    public static EntityManagerFactory getEmf() {
        return Persistence.createEntityManagerFactory("thelastoftaskPU");
    }

}
