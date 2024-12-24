/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Hibernate.Ej1_Repaso.Modelo.Persistencia;

import Hibernate.Ej1_Repaso.Modelo.Clases.Investigador;
import java.io.Serializable;
import jakarta.persistence.Query;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import Hibernate.Ej1_Repaso.Modelo.Clases.Proyecto;
import Hibernate.Ej1_Repaso.Modelo.Persistencia.exceptions.NonexistentEntityException;
import Hibernate.Ej1_Repaso.Modelo.Persistencia.exceptions.PreexistingEntityException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import java.util.List;

/**
 *
 * @author Fernando
 */
public class InvestigadorJpaController implements Serializable {

    public InvestigadorJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    
    public InvestigadorJpaController(){
        this.emf = Persistence.createEntityManagerFactory("JPUnit");
    }
    
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Investigador investigador) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Proyecto proyecto = investigador.getProyecto();
            if (proyecto != null) {
                proyecto = em.getReference(proyecto.getClass(), proyecto.getNombre());
                investigador.setProyecto(proyecto);
            }
            em.persist(investigador);
            if (proyecto != null) {
                proyecto.getListaInvestigadores().add(investigador);
                proyecto = em.merge(proyecto);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findInvestigador(investigador.getDni()) != null) {
                throw new PreexistingEntityException("Investigador " + investigador + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Investigador investigador) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Investigador persistentInvestigador = em.find(Investigador.class, investigador.getDni());
            Proyecto proyectoOld = persistentInvestigador.getProyecto();
            Proyecto proyectoNew = investigador.getProyecto();
            if (proyectoNew != null) {
                proyectoNew = em.getReference(proyectoNew.getClass(), proyectoNew.getNombre());
                investigador.setProyecto(proyectoNew);
            }
            investigador = em.merge(investigador);
            if (proyectoOld != null && !proyectoOld.equals(proyectoNew)) {
                proyectoOld.getListaInvestigadores().remove(investigador);
                proyectoOld = em.merge(proyectoOld);
            }
            if (proyectoNew != null && !proyectoNew.equals(proyectoOld)) {
                proyectoNew.getListaInvestigadores().add(investigador);
                proyectoNew = em.merge(proyectoNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                String id = investigador.getDni();
                if (findInvestigador(id) == null) {
                    throw new NonexistentEntityException("The investigador with id " + id + " no longer exists.");
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
            Investigador investigador;
            try {
                investigador = em.getReference(Investigador.class, id);
                investigador.getDni();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The investigador with id " + id + " no longer exists.", enfe);
            }
            Proyecto proyecto = investigador.getProyecto();
            if (proyecto != null) {
                proyecto.getListaInvestigadores().remove(investigador);
                proyecto = em.merge(proyecto);
            }
            em.remove(investigador);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Investigador> findInvestigadorEntities() {
        return findInvestigadorEntities(true, -1, -1);
    }

    public List<Investigador> findInvestigadorEntities(int maxResults, int firstResult) {
        return findInvestigadorEntities(false, maxResults, firstResult);
    }

    private List<Investigador> findInvestigadorEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Investigador.class));
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

    public Investigador findInvestigador(String id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Investigador.class, id);
        } finally {
            em.close();
        }
    }

    public int getInvestigadorCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Investigador> rt = cq.from(Investigador.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
