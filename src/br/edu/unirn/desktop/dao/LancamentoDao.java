package br.edu.unirn.desktop.dao;

import static br.edu.unirn.desktop.dao.GenericDao.getEntityManagerFactory;
import br.edu.unirn.desktop.modelos.Lancamento;
import br.edu.unirn.desktop.modelos.Usuario;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

/**
 *
 * @author felipe
 */
public class LancamentoDao extends GenericDao<Lancamento, Long> {

    public LancamentoDao() {
        super(Lancamento.class);
    }   
    
    public Lancamento listarLancamentosPorUsuario(Usuario usuario) {
        try {
            EntityManager em = getEntityManagerFactory().createEntityManager();
                
            CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
            CriteriaQuery<Lancamento> criteria = criteriaBuilder.createQuery(Lancamento.class);
            Root<Lancamento> root = criteria.from(Lancamento.class);
            Predicate p1 = criteriaBuilder.equal(root.get("usuario"), usuario);
            
            Predicate where = criteriaBuilder.and(p1);
            
            criteria.where(where);
        
            return em.createQuery(criteria).getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }
    
}
