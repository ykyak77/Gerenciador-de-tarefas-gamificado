/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import beans.Personagens;
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
public class PersonagensJpaController implements Serializable {

    public PersonagensJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Personagens personagens) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Usuarios usuarioId = personagens.getUsuarioId();
            if (usuarioId != null) {
                usuarioId = em.getReference(usuarioId.getClass(), usuarioId.getIdUsuario());
                personagens.setUsuarioId(usuarioId);
            }
            em.persist(personagens);
            if (usuarioId != null) {
                usuarioId.getPersonagensCollection().add(personagens);
                usuarioId = em.merge(usuarioId);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Personagens personagens) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Personagens persistentPersonagens = em.find(Personagens.class, personagens.getIdPersonagem());
            Usuarios usuarioIdOld = persistentPersonagens.getUsuarioId();
            Usuarios usuarioIdNew = personagens.getUsuarioId();
            if (usuarioIdNew != null) {
                usuarioIdNew = em.getReference(usuarioIdNew.getClass(), usuarioIdNew.getIdUsuario());
                personagens.setUsuarioId(usuarioIdNew);
            }
            personagens = em.merge(personagens);
            if (usuarioIdOld != null && !usuarioIdOld.equals(usuarioIdNew)) {
                usuarioIdOld.getPersonagensCollection().remove(personagens);
                usuarioIdOld = em.merge(usuarioIdOld);
            }
            if (usuarioIdNew != null && !usuarioIdNew.equals(usuarioIdOld)) {
                usuarioIdNew.getPersonagensCollection().add(personagens);
                usuarioIdNew = em.merge(usuarioIdNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = personagens.getIdPersonagem();
                if (findPersonagens(id) == null) {
                    throw new NonexistentEntityException("The personagens with id " + id + " no longer exists.");
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
            Personagens personagens;
            try {
                personagens = em.getReference(Personagens.class, id);
                personagens.getIdPersonagem();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The personagens with id " + id + " no longer exists.", enfe);
            }
            Usuarios usuarioId = personagens.getUsuarioId();
            if (usuarioId != null) {
                usuarioId.getPersonagensCollection().remove(personagens);
                usuarioId = em.merge(usuarioId);
            }
            em.remove(personagens);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Personagens> findPersonagensEntities() {
        return findPersonagensEntities(true, -1, -1);
    }

    public List<Personagens> findPersonagensEntities(int maxResults, int firstResult) {
        return findPersonagensEntities(false, maxResults, firstResult);
    }

    private List<Personagens> findPersonagensEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Personagens.class));
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

    public Personagens findPersonagens(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Personagens.class, id);
        } finally {
            em.close();
        }
    }

    public int getPersonagensCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Personagens> rt = cq.from(Personagens.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
