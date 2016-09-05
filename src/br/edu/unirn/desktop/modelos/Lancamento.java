package br.edu.unirn.desktop.modelos;

import br.edu.unirn.desktop.utils.DateUtils;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
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
    
    @ManyToOne
    private Categoria categoria;
    
    @ManyToOne
    private FormaPagamento formaPagamento;
    
    @Enumerated(EnumType.STRING)
    private TipoLancamento tipoLancamento;
    
    @ManyToOne
    private Usuario usuario;

    public Lancamento() {
    }

    public Lancamento(Long id, double valor, String descricao, Categoria categoria, FormaPagamento formaPagamento, TipoLancamento tipoLancamento, Usuario usuario) {
        this.id = id;
        this.valor = valor;
        this.descricao = descricao;
        this.categoria = categoria;
        this.formaPagamento = formaPagamento;
        this.tipoLancamento = tipoLancamento;
        this.usuario = usuario;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public Long getId() {
        return id;
    }

    public String getTipoLancamento() {
        return tipoLancamento == TipoLancamento.RECEITA ? TipoLancamento.RECEITA.getValor() : TipoLancamento.DESPESA.getValor();
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public double getValor() {
        return valor;
    }

    public String getData() {
        return DateUtils.formatarData(data);
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

    public void setData(Date data) {
        this.data = data;
    }

    public FormaPagamento getFormaPagamento() {
        return formaPagamento;
    }

    public void setFormaPagamento(FormaPagamento formaPagamento) {
        this.formaPagamento = formaPagamento;
    }

}
