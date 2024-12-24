/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Hibernate.Ej1_Repaso.Modelo.Persistencia;

import Hibernate.Ej1_Repaso.Modelo.Clases.Conferencia;
import Hibernate.Ej1_Repaso.Modelo.Persistencia.exceptions.NonexistentEntityException;
import Hibernate.Ej1_Repaso.Modelo.Persistencia.exceptions.PreexistingEntityException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import java.io.Serializable;
import jakarta.persistence.Query;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.Persistence;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import java.util.List;

/**
 *
 * @author Fernando
 */
public class ConferenciaJpaController implements Serializable {

    public ConferenciaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    
    public ConferenciaJpaController(){
        this.emf = Persistence.createEntityManagerFactory("JPUnit");
    }
    
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Conferencia conferencia) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(conferencia);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findConferencia(conferencia.getNombre()) != null) {
                throw new PreexistingEntityException("Conferencia " + conferencia + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Conferencia conferencia) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            conferencia = em.merge(conferencia);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                String id = conferencia.getNombre();
                if (findConferencia(id) == null) {
                    throw new NonexistentEntityException("The conferencia with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(String id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Conferencia conferencia;
            try {
                conferencia = em.getReference(Conferencia.class, id);
                conferencia.getNombre();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The conferencia with id " + id + " no longer exists.", enfe);
            }
            em.remove(conferencia);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Conferencia> findConferenciaEntities() {
        return findConferenciaEntities(true, -1, -1);
    }

    public List<Conferencia> findConferenciaEntities(int maxResults, int firstResult) {
        return findConferenciaEntities(false, maxResults, firstResult);
    }

    private List<Conferencia> findConferenciaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Conferencia.class));
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

    public Conferencia findConferencia(String id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Conferencia.class, id);
        } finally {
            em.close();
        }
    }

    public int getConferenciaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Conferencia> rt = cq.from(Conferencia.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
