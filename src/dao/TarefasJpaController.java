/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import beans.Tarefas;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import beans.Usuarios;
import dao.exceptions.NonexistentEntityException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author ifsp
 */
public class TarefasJpaController implements Serializable {

    public TarefasJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Tarefas tarefas) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Usuarios usuarioId = tarefas.getUsuarioId();
            if (usuarioId != null) {
                usuarioId = em.getReference(usuarioId.getClass(), usuarioId.getIdUsuario());
                tarefas.setUsuarioId(usuarioId);
            }
            em.persist(tarefas);
            if (usuarioId != null) {
                usuarioId.getTarefasCollection().add(tarefas);
                usuarioId = em.merge(usuarioId);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Tarefas tarefas) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Tarefas persistentTarefas = em.find(Tarefas.class, tarefas.getIdTarefas());
            Usuarios usuarioIdOld = persistentTarefas.getUsuarioId();
            Usuarios usuarioIdNew = tarefas.getUsuarioId();
            if (usuarioIdNew != null) {
                usuarioIdNew = em.getReference(usuarioIdNew.getClass(), usuarioIdNew.getIdUsuario());
                tarefas.setUsuarioId(usuarioIdNew);
            }
            tarefas = em.merge(tarefas);
            if (usuarioIdOld != null && !usuarioIdOld.equals(usuarioIdNew)) {
                usuarioIdOld.getTarefasCollection().remove(tarefas);
                usuarioIdOld = em.merge(usuarioIdOld);
            }
            if (usuarioIdNew != null && !usuarioIdNew.equals(usuarioIdOld)) {
                usuarioIdNew.getTarefasCollection().add(tarefas);
                usuarioIdNew = em.merge(usuarioIdNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = tarefas.getIdTarefas();
                if (findTarefas(id) == null) {
                    throw new NonexistentEntityException("The tarefas with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Tarefas tarefas;
            try {
                tarefas = em.getReference(Tarefas.class, id);
                tarefas.getIdTarefas();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The tarefas with id " + id + " no longer exists.", enfe);
            }
            Usuarios usuarioId = tarefas.getUsuarioId();
            if (usuarioId != null) {
                usuarioId.getTarefasCollection().remove(tarefas);
                usuarioId = em.merge(usuarioId);
            }
            em.remove(tarefas);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Tarefas> findTarefasEntities() {
        return findTarefasEntities(true, -1, -1);
    }

    public List<Tarefas> findTarefasEntities(int maxResults, int firstResult) {
        return findTarefasEntities(false, maxResults, firstResult);
    }

    private List<Tarefas> findTarefasEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Tarefas.class));
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

    public Tarefas findTarefas(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Tarefas.class, id);
        } finally {
            em.close();
        }
    }

    public int getTarefasCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Tarefas> rt = cq.from(Tarefas.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
