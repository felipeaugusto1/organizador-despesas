package br.edu.unirn.desktop.dao;

import br.edu.unirn.desktop.modelos.Usuario;

/**
 *
 * @author felipe
 */
public class UsuarioDao extends GenericDao<Usuario, Long> {

    public UsuarioDao() {
        super(Usuario.class);
    }   
    
}
