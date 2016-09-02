/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
    
    public void sairAplicacao() {
        setUsuario(null);
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    
}
