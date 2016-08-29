package br.edu.unirn.desktop.dao;

import br.edu.unirn.desktop.modelos.Categoria;

/**
 *
 * @author felipe
 */
public class CategoriaDao extends GenericDao<Categoria, Long> {

    public CategoriaDao() {
        super(Categoria.class);
    }   
    
}
