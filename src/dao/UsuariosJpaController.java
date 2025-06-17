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
import beans.Tarefas;
import java.util.ArrayList;
import java.util.Collection;
import beans.Personagens;
import beans.Inventario;
import beans.Usuarios;
import dao.exceptions.IllegalOrphanException;
import dao.exceptions.NonexistentEntityException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author ifsp
 */
public class UsuariosJpaController implements Serializable {

    public UsuariosJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
    
    public boolean emailExiste(String email) {
        EntityManager em = getEntityManager();
        try {
            String jpql = "SELECT COUNT(u) FROM Usuarios u WHERE u.email = :email";
            Long count = em.createQuery(jpql, Long.class)
                           .setParameter("email", email)
                           .getSingleResult();
            return count > 0;
        } finally {
            em.close();
        }
    }

    public boolean usernameExiste(String username) {
        EntityManager em = getEntityManager();
        try {
            String jpql = "SELECT COUNT(u) FROM Usuarios u WHERE u.username = :username";
            Long count = em.createQuery(jpql, Long.class)
                           .setParameter("username", username)
                           .getSingleResult();
            return count > 0;
        } finally {
            em.close();
        }
    }


    public void create(Usuarios usuarios) {
        if (usuarios.getTarefasCollection() == null) {
            usuarios.setTarefasCollection(new ArrayList<Tarefas>());
        }
        if (usuarios.getPersonagensCollection() == null) {
            usuarios.setPersonagensCollection(new ArrayList<Personagens>());
        }
        if (usuarios.getInventarioCollection() == null) {
            usuarios.setInventarioCollection(new ArrayList<Inventario>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Collection<Tarefas> attachedTarefasCollection = new ArrayList<Tarefas>();
            for (Tarefas tarefasCollectionTarefasToAttach : usuarios.getTarefasCollection()) {
                tarefasCollectionTarefasToAttach = em.getReference(tarefasCollectionTarefasToAttach.getClass(), tarefasCollectionTarefasToAttach.getIdTarefas());
                attachedTarefasCollection.add(tarefasCollectionTarefasToAttach);
            }
            usuarios.setTarefasCollection(attachedTarefasCollection);
            Collection<Personagens> attachedPersonagensCollection = new ArrayList<Personagens>();
            for (Personagens personagensCollectionPersonagensToAttach : usuarios.getPersonagensCollection()) {
                personagensCollectionPersonagensToAttach = em.getReference(personagensCollectionPersonagensToAttach.getClass(), personagensCollectionPersonagensToAttach.getIdPersonagem());
                attachedPersonagensCollection.add(personagensCollectionPersonagensToAttach);
            }
            usuarios.setPersonagensCollection(attachedPersonagensCollection);
            Collection<Inventario> attachedInventarioCollection = new ArrayList<Inventario>();
            for (Inventario inventarioCollectionInventarioToAttach : usuarios.getInventarioCollection()) {
                inventarioCollectionInventarioToAttach = em.getReference(inventarioCollectionInventarioToAttach.getClass(), inventarioCollectionInventarioToAttach.getIdInvetario());
                attachedInventarioCollection.add(inventarioCollectionInventarioToAttach);
            }
            usuarios.setInventarioCollection(attachedInventarioCollection);
            em.persist(usuarios);
            for (Tarefas tarefasCollectionTarefas : usuarios.getTarefasCollection()) {
                Usuarios oldUsuarioIdOfTarefasCollectionTarefas = tarefasCollectionTarefas.getUsuarioId();
                tarefasCollectionTarefas.setUsuarioId(usuarios);
                tarefasCollectionTarefas = em.merge(tarefasCollectionTarefas);
                if (oldUsuarioIdOfTarefasCollectionTarefas != null) {
                    oldUsuarioIdOfTarefasCollectionTarefas.getTarefasCollection().remove(tarefasCollectionTarefas);
                    oldUsuarioIdOfTarefasCollectionTarefas = em.merge(oldUsuarioIdOfTarefasCollectionTarefas);
                }
            }
            for (Personagens personagensCollectionPersonagens : usuarios.getPersonagensCollection()) {
                Usuarios oldUsuarioIdOfPersonagensCollectionPersonagens = personagensCollectionPersonagens.getUsuarioId();
                personagensCollectionPersonagens.setUsuarioId(usuarios);
                personagensCollectionPersonagens = em.merge(personagensCollectionPersonagens);
                if (oldUsuarioIdOfPersonagensCollectionPersonagens != null) {
                    oldUsuarioIdOfPersonagensCollectionPersonagens.getPersonagensCollection().remove(personagensCollectionPersonagens);
                    oldUsuarioIdOfPersonagensCollectionPersonagens = em.merge(oldUsuarioIdOfPersonagensCollectionPersonagens);
                }
            }
            for (Inventario inventarioCollectionInventario : usuarios.getInventarioCollection()) {
                Usuarios oldUsuarioIdOfInventarioCollectionInventario = inventarioCollectionInventario.getUsuarioId();
                inventarioCollectionInventario.setUsuarioId(usuarios);
                inventarioCollectionInventario = em.merge(inventarioCollectionInventario);
                if (oldUsuarioIdOfInventarioCollectionInventario != null) {
                    oldUsuarioIdOfInventarioCollectionInventario.getInventarioCollection().remove(inventarioCollectionInventario);
                    oldUsuarioIdOfInventarioCollectionInventario = em.merge(oldUsuarioIdOfInventarioCollectionInventario);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Usuarios usuarios) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Usuarios persistentUsuarios = em.find(Usuarios.class, usuarios.getIdUsuario());
            Collection<Tarefas> tarefasCollectionOld = persistentUsuarios.getTarefasCollection();
            Collection<Tarefas> tarefasCollectionNew = usuarios.getTarefasCollection();
            Collection<Personagens> personagensCollectionOld = persistentUsuarios.getPersonagensCollection();
            Collection<Personagens> personagensCollectionNew = usuarios.getPersonagensCollection();
            Collection<Inventario> inventarioCollectionOld = persistentUsuarios.getInventarioCollection();
            Collection<Inventario> inventarioCollectionNew = usuarios.getInventarioCollection();
            List<String> illegalOrphanMessages = null;
            for (Tarefas tarefasCollectionOldTarefas : tarefasCollectionOld) {
                if (!tarefasCollectionNew.contains(tarefasCollectionOldTarefas)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Tarefas " + tarefasCollectionOldTarefas + " since its usuarioId field is not nullable.");
                }
            }
            for (Personagens personagensCollectionOldPersonagens : personagensCollectionOld) {
                if (!personagensCollectionNew.contains(personagensCollectionOldPersonagens)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Personagens " + personagensCollectionOldPersonagens + " since its usuarioId field is not nullable.");
                }
            }
            for (Inventario inventarioCollectionOldInventario : inventarioCollectionOld) {
                if (!inventarioCollectionNew.contains(inventarioCollectionOldInventario)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Inventario " + inventarioCollectionOldInventario + " since its usuarioId field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Collection<Tarefas> attachedTarefasCollectionNew = new ArrayList<Tarefas>();
            for (Tarefas tarefasCollectionNewTarefasToAttach : tarefasCollectionNew) {
                tarefasCollectionNewTarefasToAttach = em.getReference(tarefasCollectionNewTarefasToAttach.getClass(), tarefasCollectionNewTarefasToAttach.getIdTarefas());
                attachedTarefasCollectionNew.add(tarefasCollectionNewTarefasToAttach);
            }
            tarefasCollectionNew = attachedTarefasCollectionNew;
            usuarios.setTarefasCollection(tarefasCollectionNew);
            Collection<Personagens> attachedPersonagensCollectionNew = new ArrayList<Personagens>();
            for (Personagens personagensCollectionNewPersonagensToAttach : personagensCollectionNew) {
                personagensCollectionNewPersonagensToAttach = em.getReference(personagensCollectionNewPersonagensToAttach.getClass(), personagensCollectionNewPersonagensToAttach.getIdPersonagem());
                attachedPersonagensCollectionNew.add(personagensCollectionNewPersonagensToAttach);
            }
            personagensCollectionNew = attachedPersonagensCollectionNew;
            usuarios.setPersonagensCollection(personagensCollectionNew);
            Collection<Inventario> attachedInventarioCollectionNew = new ArrayList<Inventario>();
            for (Inventario inventarioCollectionNewInventarioToAttach : inventarioCollectionNew) {
                inventarioCollectionNewInventarioToAttach = em.getReference(inventarioCollectionNewInventarioToAttach.getClass(), inventarioCollectionNewInventarioToAttach.getIdInvetario());
                attachedInventarioCollectionNew.add(inventarioCollectionNewInventarioToAttach);
            }
            inventarioCollectionNew = attachedInventarioCollectionNew;
            usuarios.setInventarioCollection(inventarioCollectionNew);
            usuarios = em.merge(usuarios);
            for (Tarefas tarefasCollectionNewTarefas : tarefasCollectionNew) {
                if (!tarefasCollectionOld.contains(tarefasCollectionNewTarefas)) {
                    Usuarios oldUsuarioIdOfTarefasCollectionNewTarefas = tarefasCollectionNewTarefas.getUsuarioId();
                    tarefasCollectionNewTarefas.setUsuarioId(usuarios);
                    tarefasCollectionNewTarefas = em.merge(tarefasCollectionNewTarefas);
                    if (oldUsuarioIdOfTarefasCollectionNewTarefas != null && !oldUsuarioIdOfTarefasCollectionNewTarefas.equals(usuarios)) {
                        oldUsuarioIdOfTarefasCollectionNewTarefas.getTarefasCollection().remove(tarefasCollectionNewTarefas);
                        oldUsuarioIdOfTarefasCollectionNewTarefas = em.merge(oldUsuarioIdOfTarefasCollectionNewTarefas);
                    }
                }
            }
            for (Personagens personagensCollectionNewPersonagens : personagensCollectionNew) {
                if (!personagensCollectionOld.contains(personagensCollectionNewPersonagens)) {
                    Usuarios oldUsuarioIdOfPersonagensCollectionNewPersonagens = personagensCollectionNewPersonagens.getUsuarioId();
                    personagensCollectionNewPersonagens.setUsuarioId(usuarios);
                    personagensCollectionNewPersonagens = em.merge(personagensCollectionNewPersonagens);
                    if (oldUsuarioIdOfPersonagensCollectionNewPersonagens != null && !oldUsuarioIdOfPersonagensCollectionNewPersonagens.equals(usuarios)) {
                        oldUsuarioIdOfPersonagensCollectionNewPersonagens.getPersonagensCollection().remove(personagensCollectionNewPersonagens);
                        oldUsuarioIdOfPersonagensCollectionNewPersonagens = em.merge(oldUsuarioIdOfPersonagensCollectionNewPersonagens);
                    }
                }
            }
            for (Inventario inventarioCollectionNewInventario : inventarioCollectionNew) {
                if (!inventarioCollectionOld.contains(inventarioCollectionNewInventario)) {
                    Usuarios oldUsuarioIdOfInventarioCollectionNewInventario = inventarioCollectionNewInventario.getUsuarioId();
                    inventarioCollectionNewInventario.setUsuarioId(usuarios);
                    inventarioCollectionNewInventario = em.merge(inventarioCollectionNewInventario);
                    if (oldUsuarioIdOfInventarioCollectionNewInventario != null && !oldUsuarioIdOfInventarioCollectionNewInventario.equals(usuarios)) {
                        oldUsuarioIdOfInventarioCollectionNewInventario.getInventarioCollection().remove(inventarioCollectionNewInventario);
                        oldUsuarioIdOfInventarioCollectionNewInventario = em.merge(oldUsuarioIdOfInventarioCollectionNewInventario);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = usuarios.getIdUsuario();
                if (findUsuarios(id) == null) {
                    throw new NonexistentEntityException("The usuarios with id " + id + " no longer exists.");
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
            Usuarios usuarios;
            try {
                usuarios = em.getReference(Usuarios.class, id);
                usuarios.getIdUsuario();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The usuarios with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<Tarefas> tarefasCollectionOrphanCheck = usuarios.getTarefasCollection();
            for (Tarefas tarefasCollectionOrphanCheckTarefas : tarefasCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Usuarios (" + usuarios + ") cannot be destroyed since the Tarefas " + tarefasCollectionOrphanCheckTarefas + " in its tarefasCollection field has a non-nullable usuarioId field.");
            }
            Collection<Personagens> personagensCollectionOrphanCheck = usuarios.getPersonagensCollection();
            for (Personagens personagensCollectionOrphanCheckPersonagens : personagensCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Usuarios (" + usuarios + ") cannot be destroyed since the Personagens " + personagensCollectionOrphanCheckPersonagens + " in its personagensCollection field has a non-nullable usuarioId field.");
            }
            Collection<Inventario> inventarioCollectionOrphanCheck = usuarios.getInventarioCollection();
            for (Inventario inventarioCollectionOrphanCheckInventario : inventarioCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Usuarios (" + usuarios + ") cannot be destroyed since the Inventario " + inventarioCollectionOrphanCheckInventario + " in its inventarioCollection field has a non-nullable usuarioId field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(usuarios);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Usuarios> findUsuariosEntities() {
        return findUsuariosEntities(true, -1, -1);
    }

    public List<Usuarios> findUsuariosEntities(int maxResults, int firstResult) {
        return findUsuariosEntities(false, maxResults, firstResult);
    }

    private List<Usuarios> findUsuariosEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Usuarios.class));
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

    public Usuarios findUsuarios(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Usuarios.class, id);
        } finally {
            em.close();
        }
    }

    public int getUsuariosCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Usuarios> rt = cq.from(Usuarios.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
