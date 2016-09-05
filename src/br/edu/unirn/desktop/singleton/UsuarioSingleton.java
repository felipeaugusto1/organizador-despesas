package br.edu.unirn.desktop.singleton;

import br.edu.unirn.desktop.modelos.Usuario;

/**
 *
 * @author felipe
 */
public class UsuarioSingleton {
    
    private static UsuarioSingleton instancia;
    private Usuario usuario;
    
    private UsuarioSingleton() {}
    
    public static UsuarioSingleton getInstancia() {
        if (instancia == null)
            instancia = new UsuarioSingleton();
        
        return instancia;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    
}
