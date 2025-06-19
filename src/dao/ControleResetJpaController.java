/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import emf.Emf;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Kayky Henrique
 */
public class ControleResetJpaController implements Serializable {
    
    public ControleResetJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
    
    public void ResetarTarefasDiarias() {
        EntityManager em = emf.createEntityManager();

        try {
            Date hoje = new Date();
            java.sql.Date dataAtual = new java.sql.Date(hoje.getTime());

            Date ultimaExecucao = em.createQuery("SELECT c.ultimaExecucao FROM ControleReset c WHERE c.id = 1", Date.class)
                                    .getSingleResult();
            
            LocalDate dataAtualLocal = LocalDate.now();
            LocalDate ultimaExecucaoLocal = ultimaExecucao.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

            if (!dataAtualLocal.equals(ultimaExecucaoLocal)) {
                
                em.getTransaction().begin();

                em.createQuery("UPDATE Tarefas t SET t.concluido = false").executeUpdate();

                em.createQuery("UPDATE ControleReset c SET c.ultimaExecucao = :hoje WHERE c.id = 1")
                  .setParameter("hoje", dataAtual)
                  .executeUpdate();

                em.getTransaction().commit();
            }
            
            else{
                return;
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            em.close();
        }
    }

    
}
