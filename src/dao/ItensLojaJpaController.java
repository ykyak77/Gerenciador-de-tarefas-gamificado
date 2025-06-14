/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import beans.Inventario;
import beans.ItensLoja;
import dao.exceptions.IllegalOrphanException;
import dao.exceptions.NonexistentEntityException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author ifsp
 */
public class ItensLojaJpaController implements Serializable {

    public ItensLojaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(ItensLoja itensLoja) {
        if (itensLoja.getInventarioCollection() == null) {
            itensLoja.setInventarioCollection(new ArrayList<Inventario>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Collection<Inventario> attachedInventarioCollection = new ArrayList<Inventario>();
            for (Inventario inventarioCollectionInventarioToAttach : itensLoja.getInventarioCollection()) {
                inventarioCollectionInventarioToAttach = em.getReference(inventarioCollectionInventarioToAttach.getClass(), inventarioCollectionInventarioToAttach.getIdInvetario());
                attachedInventarioCollection.add(inventarioCollectionInventarioToAttach);
            }
            itensLoja.setInventarioCollection(attachedInventarioCollection);
            em.persist(itensLoja);
            for (Inventario inventarioCollectionInventario : itensLoja.getInventarioCollection()) {
                ItensLoja oldItemIdOfInventarioCollectionInventario = inventarioCollectionInventario.getItemId();
                inventarioCollectionInventario.setItemId(itensLoja);
                inventarioCollectionInventario = em.merge(inventarioCollectionInventario);
                if (oldItemIdOfInventarioCollectionInventario != null) {
                    oldItemIdOfInventarioCollectionInventario.getInventarioCollection().remove(inventarioCollectionInventario);
                    oldItemIdOfInventarioCollectionInventario = em.merge(oldItemIdOfInventarioCollectionInventario);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(ItensLoja itensLoja) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            ItensLoja persistentItensLoja = em.find(ItensLoja.class, itensLoja.getIdLoja());
            Collection<Inventario> inventarioCollectionOld = persistentItensLoja.getInventarioCollection();
            Collection<Inventario> inventarioCollectionNew = itensLoja.getInventarioCollection();
            List<String> illegalOrphanMessages = null;
            for (Inventario inventarioCollectionOldInventario : inventarioCollectionOld) {
                if (!inventarioCollectionNew.contains(inventarioCollectionOldInventario)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Inventario " + inventarioCollectionOldInventario + " since its itemId field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Collection<Inventario> attachedInventarioCollectionNew = new ArrayList<Inventario>();
            for (Inventario inventarioCollectionNewInventarioToAttach : inventarioCollectionNew) {
                inventarioCollectionNewInventarioToAttach = em.getReference(inventarioCollectionNewInventarioToAttach.getClass(), inventarioCollectionNewInventarioToAttach.getIdInvetario());
                attachedInventarioCollectionNew.add(inventarioCollectionNewInventarioToAttach);
            }
            inventarioCollectionNew = attachedInventarioCollectionNew;
            itensLoja.setInventarioCollection(inventarioCollectionNew);
            itensLoja = em.merge(itensLoja);
            for (Inventario inventarioCollectionNewInventario : inventarioCollectionNew) {
                if (!inventarioCollectionOld.contains(inventarioCollectionNewInventario)) {
                    ItensLoja oldItemIdOfInventarioCollectionNewInventario = inventarioCollectionNewInventario.getItemId();
                    inventarioCollectionNewInventario.setItemId(itensLoja);
                    inventarioCollectionNewInventario = em.merge(inventarioCollectionNewInventario);
                    if (oldItemIdOfInventarioCollectionNewInventario != null && !oldItemIdOfInventarioCollectionNewInventario.equals(itensLoja)) {
                        oldItemIdOfInventarioCollectionNewInventario.getInventarioCollection().remove(inventarioCollectionNewInventario);
                        oldItemIdOfInventarioCollectionNewInventario = em.merge(oldItemIdOfInventarioCollectionNewInventario);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = itensLoja.getIdLoja();
                if (findItensLoja(id) == null) {
                    throw new NonexistentEntityException("The itensLoja with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws IllegalOrphanException, NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            ItensLoja itensLoja;
            try {
                itensLoja = em.getReference(ItensLoja.class, id);
                itensLoja.getIdLoja();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The itensLoja with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<Inventario> inventarioCollectionOrphanCheck = itensLoja.getInventarioCollection();
            for (Inventario inventarioCollectionOrphanCheckInventario : inventarioCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This ItensLoja (" + itensLoja + ") cannot be destroyed since the Inventario " + inventarioCollectionOrphanCheckInventario + " in its inventarioCollection field has a non-nullable itemId field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(itensLoja);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<ItensLoja> findItensLojaEntities() {
        return findItensLojaEntities(true, -1, -1);
    }

    public List<ItensLoja> findItensLojaEntities(int maxResults, int firstResult) {
        return findItensLojaEntities(false, maxResults, firstResult);
    }

    private List<ItensLoja> findItensLojaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(ItensLoja.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public ItensLoja findItensLoja(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(ItensLoja.class, id);
        } finally {
            em.close();
        }
    }

    public int getItensLojaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<ItensLoja> rt = cq.from(ItensLoja.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
