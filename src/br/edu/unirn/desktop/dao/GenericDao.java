package br.edu.unirn.desktop.dao;

import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

/**
 *
 * @author felipe
 * @param <T>
 * @param <ID>
 */
public abstract class GenericDao <T, ID extends Serializable> {

    private static final EntityManagerFactory emf;
    private Class<T> classe;

    static {
        emf = Persistence.createEntityManagerFactory("OrganizadorDespesasPU");
    }
    
    public GenericDao(Class<T> classe) {
        this.classe = classe;
    }
    
    public void salvar(T entidade) {
        EntityManager em = getEntityManagerFactory().createEntityManager();
        
        EntityTransaction t = em.getTransaction();
        t.begin();
        
        em.persist(entidade);
        
        t.commit();
    }

    public T atualizar(T entidade) {
        EntityManager em = getEntityManagerFactory().createEntityManager();
        return em.merge(entidade);
    }

    public void remover(T entidade) {
        EntityManager em = getEntityManagerFactory().createEntityManager();
        em.remove(entidade);
    }
    
    public List<T> listarTodos() {
        EntityManager em = getEntityManagerFactory().createEntityManager();
        
        CriteriaBuilder builder = getEntityManagerFactory().getCriteriaBuilder();
        CriteriaQuery<T> query = builder.createQuery(classe);
        query.from(classe);
        return em.createQuery(query).getResultList();
    }	
    
    public T buscarPorId(int id) {
        EntityManager em = getEntityManagerFactory().createEntityManager();
        return em.find(classe, id);
    }
    
    public static EntityManagerFactory getEntityManagerFactory() {
        return emf;
    }
    
}
