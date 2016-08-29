package br.edu.unirn.desktop.dao;

import br.edu.unirn.desktop.modelos.Lancamento;

/**
 *
 * @author felipe
 */
public class LancamentoDao extends GenericDao<Lancamento, Long> {

    public LancamentoDao() {
        super(Lancamento.class);
    }   
    
}
