package br.edu.unirn.desktop.dao;

import static br.edu.unirn.desktop.dao.GenericDao.getEntityManagerFactory;
import br.edu.unirn.desktop.modelos.FormaPagamento;
import br.edu.unirn.desktop.singleton.UsuarioSingleton;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

/**
 *
 * @author felipe
 */
public class FormaPagamentoDao extends GenericDao<FormaPagamento, Long> {

    public FormaPagamentoDao() {
        super(FormaPagamento.class);
    }   
    
    public List<FormaPagamento> buscarFormasPagamentoPorUsuario() {
        try {
            EntityManager em = getEntityManagerFactory().createEntityManager();
                
            CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
            CriteriaQuery<FormaPagamento> criteria = criteriaBuilder.createQuery(FormaPagamento.class);
            Root<FormaPagamento> root = criteria.from(FormaPagamento.class);
            Predicate p1 = criteriaBuilder.equal(root.get("usuario"), UsuarioSingleton.getInstancia().getUsuario());
            
            Predicate where = criteriaBuilder.and(p1);
            
            criteria.where(where);
        
            return em.createQuery(criteria).getResultList();
        } catch (Exception e) {
            return null;
        }
    }
    
}
