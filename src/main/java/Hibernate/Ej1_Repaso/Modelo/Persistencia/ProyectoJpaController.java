/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Hibernate.Ej1_Repaso.Modelo.Persistencia;

import java.io.Serializable;
import jakarta.persistence.Query;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import Hibernate.Ej1_Repaso.Modelo.Clases.Investigador;
import Hibernate.Ej1_Repaso.Modelo.Clases.Proyecto;
import Hibernate.Ej1_Repaso.Modelo.Persistencia.exceptions.NonexistentEntityException;
import Hibernate.Ej1_Repaso.Modelo.Persistencia.exceptions.PreexistingEntityException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Fernando
 */
public class ProyectoJpaController implements Serializable {

    public ProyectoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    
    public ProyectoJpaController(){
        this.emf = Persistence.createEntityManagerFactory("JPUnit");
    }
    
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Proyecto proyecto) throws PreexistingEntityException, Exception {
        if (proyecto.getListaInvestigadores() == null) {
            proyecto.setListaInvestigadores(new ArrayList<Investigador>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Investigador> attachedListaInvestigadores = new ArrayList<Investigador>();
            for (Investigador listaInvestigadoresInvestigadorToAttach : proyecto.getListaInvestigadores()) {
                listaInvestigadoresInvestigadorToAttach = em.getReference(listaInvestigadoresInvestigadorToAttach.getClass(), listaInvestigadoresInvestigadorToAttach.getDni());
                attachedListaInvestigadores.add(listaInvestigadoresInvestigadorToAttach);
            }
            proyecto.setListaInvestigadores(attachedListaInvestigadores);
            em.persist(proyecto);
            for (Investigador listaInvestigadoresInvestigador : proyecto.getListaInvestigadores()) {
                Proyecto oldProyectoOfListaInvestigadoresInvestigador = listaInvestigadoresInvestigador.getProyecto();
                listaInvestigadoresInvestigador.setProyecto(proyecto);
                listaInvestigadoresInvestigador = em.merge(listaInvestigadoresInvestigador);
                if (oldProyectoOfListaInvestigadoresInvestigador != null) {
                    oldProyectoOfListaInvestigadoresInvestigador.getListaInvestigadores().remove(listaInvestigadoresInvestigador);
                    oldProyectoOfListaInvestigadoresInvestigador = em.merge(oldProyectoOfListaInvestigadoresInvestigador);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findProyecto(proyecto.getNombre()) != null) {
                throw new PreexistingEntityException("Proyecto " + proyecto + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Proyecto proyecto) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Proyecto persistentProyecto = em.find(Proyecto.class, proyecto.getNombre());
            List<Investigador> listaInvestigadoresOld = persistentProyecto.getListaInvestigadores();
            List<Investigador> listaInvestigadoresNew = proyecto.getListaInvestigadores();
            List<Investigador> attachedListaInvestigadoresNew = new ArrayList<Investigador>();
            for (Investigador listaInvestigadoresNewInvestigadorToAttach : listaInvestigadoresNew) {
                listaInvestigadoresNewInvestigadorToAttach = em.getReference(listaInvestigadoresNewInvestigadorToAttach.getClass(), listaInvestigadoresNewInvestigadorToAttach.getDni());
                attachedListaInvestigadoresNew.add(listaInvestigadoresNewInvestigadorToAttach);
            }
            listaInvestigadoresNew = attachedListaInvestigadoresNew;
            proyecto.setListaInvestigadores(listaInvestigadoresNew);
            proyecto = em.merge(proyecto);
            for (Investigador listaInvestigadoresOldInvestigador : listaInvestigadoresOld) {
                if (!listaInvestigadoresNew.contains(listaInvestigadoresOldInvestigador)) {
                    listaInvestigadoresOldInvestigador.setProyecto(null);
                    listaInvestigadoresOldInvestigador = em.merge(listaInvestigadoresOldInvestigador);
                }
            }
            for (Investigador listaInvestigadoresNewInvestigador : listaInvestigadoresNew) {
                if (!listaInvestigadoresOld.contains(listaInvestigadoresNewInvestigador)) {
                    Proyecto oldProyectoOfListaInvestigadoresNewInvestigador = listaInvestigadoresNewInvestigador.getProyecto();
                    listaInvestigadoresNewInvestigador.setProyecto(proyecto);
                    listaInvestigadoresNewInvestigador = em.merge(listaInvestigadoresNewInvestigador);
                    if (oldProyectoOfListaInvestigadoresNewInvestigador != null && !oldProyectoOfListaInvestigadoresNewInvestigador.equals(proyecto)) {
                        oldProyectoOfListaInvestigadoresNewInvestigador.getListaInvestigadores().remove(listaInvestigadoresNewInvestigador);
                        oldProyectoOfListaInvestigadoresNewInvestigador = em.merge(oldProyectoOfListaInvestigadoresNewInvestigador);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                String id = proyecto.getNombre();
                if (findProyecto(id) == null) {
                    throw new NonexistentEntityException("The proyecto with id " + id + " no longer exists.");
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
            Proyecto proyecto;
            try {
                proyecto = em.getReference(Proyecto.class, id);
                proyecto.getNombre();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The proyecto with id " + id + " no longer exists.", enfe);
            }
            List<Investigador> listaInvestigadores = proyecto.getListaInvestigadores();
            for (Investigador listaInvestigadoresInvestigador : listaInvestigadores) {
                listaInvestigadoresInvestigador.setProyecto(null);
                listaInvestigadoresInvestigador = em.merge(listaInvestigadoresInvestigador);
            }
            em.remove(proyecto);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Proyecto> findProyectoEntities() {
        return findProyectoEntities(true, -1, -1);
    }

    public List<Proyecto> findProyectoEntities(int maxResults, int firstResult) {
        return findProyectoEntities(false, maxResults, firstResult);
    }

    private List<Proyecto> findProyectoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Proyecto.class));
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

    public Proyecto findProyecto(String id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Proyecto.class, id);
        } finally {
            em.close();
        }
    }

    public int getProyectoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Proyecto> rt = cq.from(Proyecto.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
