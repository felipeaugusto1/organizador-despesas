/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.unirn.desktop.modelos;

import java.util.Date;

/**
 *
 * @author felipe
 */
public class Lancamento {
    
    private int id;
    private double valor;
    private Categoria categoria;
    private Date data = new Date();
    private TipoLancamento tipoLancamento;
    private Usuario usuario;

    public Lancamento() {
    }

    public Lancamento(double valor, Categoria categoria, TipoLancamento tipoLancamento, Usuario usuario) {
        this.valor = valor;
        this.categoria = categoria;
        this.tipoLancamento = tipoLancamento;
        this.usuario = usuario;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public int getId() {
        return id;
    }

    public TipoLancamento getTipoLancamento() {
        return tipoLancamento;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public double getValor() {
        return valor;
    }

    public Date getData() {
        return data;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTipoLancamento(TipoLancamento tipoLancamento) {
        this.tipoLancamento = tipoLancamento;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }
    
}
