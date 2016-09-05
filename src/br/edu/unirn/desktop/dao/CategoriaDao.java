package br.edu.unirn.desktop.dao;

import static br.edu.unirn.desktop.dao.GenericDao.getEntityManagerFactory;
import br.edu.unirn.desktop.modelos.Categoria;
import br.edu.unirn.desktop.modelos.FormaPagamento;
import br.edu.unirn.desktop.modelos.Usuario;
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
public class CategoriaDao extends GenericDao<Categoria, Long> {

    public CategoriaDao() {
        super(Categoria.class);
    }   
    
    public Categoria buscarCategoriaPessoal() {
        String nomeCategoria = "Pessoal";
        
        try {
            EntityManager em = getEntityManagerFactory().createEntityManager();
                
            CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
            CriteriaQuery<Categoria> criteria = criteriaBuilder.createQuery(Categoria.class);
            Root<Categoria> root = criteria.from(Categoria.class);
            Predicate p1 = criteriaBuilder.equal(root.get("usuario"), UsuarioSingleton.getInstancia().getUsuario());
            Predicate p2 = criteriaBuilder.equal(root.get("nome"), nomeCategoria);
            
            Predicate where = criteriaBuilder.and(p1, p2);
            
            criteria.where(where);
        
            return em.createQuery(criteria).getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }
    
    public List<Categoria> buscarCategoriasPorUsuario(Usuario usuario) {
        try {
            EntityManager em = getEntityManagerFactory().createEntityManager();
                
            CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
            CriteriaQuery<Categoria> criteria = criteriaBuilder.createQuery(Categoria.class);
            Root<Categoria> root = criteria.from(Categoria.class);
            Predicate p1 = criteriaBuilder.equal(root.get("usuario"), usuario);
            
            Predicate where = criteriaBuilder.and(p1);
            
            criteria.where(where);
        
            return em.createQuery(criteria).getResultList();
        } catch (Exception e) {
            return null;
        }
    }
    
}
