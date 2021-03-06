package br.edu.unirn.desktop.dao;

import static br.edu.unirn.desktop.dao.GenericDao.getEntityManagerFactory;
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
public class UsuarioDao extends GenericDao<Usuario, Long> {

    public UsuarioDao() {
        super(Usuario.class);
    }   
    
    public Usuario login(String usuario, String senha) {
        try {
            EntityManager em = getEntityManagerFactory().createEntityManager();
                
            CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
            CriteriaQuery<Usuario> criteria = criteriaBuilder.createQuery(Usuario.class);
            Root<Usuario> root = criteria.from(Usuario.class);
            Predicate p1 = criteriaBuilder.equal(root.get("usuario"), usuario);
            Predicate p2 = criteriaBuilder.equal(root.get("senha"), senha);
            
            Predicate where = criteriaBuilder.and(p1, p2);
            
            criteria.where(where);
        
            return em.createQuery(criteria).getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }
    
}
