package br.edu.unirn.desktop.modelos;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author felipe
 */
@Entity
public class Lancamento implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    private double valor;
    private String descricao;
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(insertable = true, updatable = false)
    private Date data = new Date();
    
    @OneToOne
    private Categoria categoria;
    
    @Enumerated(EnumType.STRING)
    private TipoLancamento tipoLancamento;
    
    @OneToOne
    private Usuario usuario;

    public Lancamento() {
    }

    public Lancamento(double valor, String descricao, Categoria categoria, TipoLancamento tipoLancamento, Usuario usuario) {
        this.valor = valor;
        this.descricao = descricao;
        this.categoria = categoria;
        this.tipoLancamento = tipoLancamento;
        this.usuario = usuario;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public Long getId() {
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

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public void setId(Long id) {
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

    @Override
    public String toString() {
        return id+" - " +descricao+" - " +valor;
    }
 
    
    
}
