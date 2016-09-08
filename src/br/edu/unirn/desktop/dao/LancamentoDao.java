package br.edu.unirn.desktop.dao;

import static br.edu.unirn.desktop.dao.GenericDao.getEntityManagerFactory;
import br.edu.unirn.desktop.modelos.Lancamento;
import br.edu.unirn.desktop.modelos.TipoLancamento;
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
public class LancamentoDao extends GenericDao<Lancamento, Long> {

    public LancamentoDao() {
        super(Lancamento.class);
    }   
    
    public List<Lancamento> listarLancamentosPorUsuario() {
        try {
            EntityManager em = getEntityManagerFactory().createEntityManager();
                
            CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
            CriteriaQuery<Lancamento> criteria = criteriaBuilder.createQuery(Lancamento.class);
            Root<Lancamento> root = criteria.from(Lancamento.class);
            Predicate p1 = criteriaBuilder.equal(root.get("usuario"), UsuarioSingleton.getInstancia().getUsuario());
            
            Predicate where = criteriaBuilder.and(p1);
            
            criteria.where(where);
        
            return em.createQuery(criteria).getResultList();
        } catch (Exception e) {
            return null;
        }
    }
    
    /**
     * Busca todos os lançamentos do tipo despesa
     * (neste caso, não irá filtrar por mês).
     *
     * @return 
     */
    public List<Lancamento> listarLancamentosDoTipoDespesa() {
        try {
            EntityManager em = getEntityManagerFactory().createEntityManager();
                
            CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
            CriteriaQuery<Lancamento> criteria = criteriaBuilder.createQuery(Lancamento.class);
            Root<Lancamento> root = criteria.from(Lancamento.class);
            Predicate p1 = criteriaBuilder.equal(root.get("usuario"), UsuarioSingleton.getInstancia().getUsuario());
            Predicate p2 = criteriaBuilder.equal(root.get("tipoLancamento"), TipoLancamento.DESPESA);
            
            Predicate where = criteriaBuilder.and(p1, p2);
            
            criteria.where(where);
        
            return em.createQuery(criteria).getResultList();
        } catch (Exception e) {
            return null;
        }
    }
    
}
