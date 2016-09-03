package br.edu.unirn.desktop.modelos;

/**
 *
 * @author felipe
 */
public enum TipoLancamento {
    
    RECEITA("Receita"), 
    DESPESA("Despesa");
    
    private final String valor;
    
    TipoLancamento(String valor) {
        this.valor = valor;
    }

    public String getValor() {
        return valor;
    }
    
}
