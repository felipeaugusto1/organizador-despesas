package br.edu.unirn.desktop.modelos;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Lancamento.class)
public abstract class Lancamento_ {

	public static volatile SingularAttribute<Lancamento, FormaPagamento> formaPagamento;
	public static volatile SingularAttribute<Lancamento, Date> data;
	public static volatile SingularAttribute<Lancamento, TipoLancamento> tipoLancamento;
	public static volatile SingularAttribute<Lancamento, Categoria> categoria;
	public static volatile SingularAttribute<Lancamento, Double> valor;
	public static volatile SingularAttribute<Lancamento, Usuario> usuario;
	public static volatile SingularAttribute<Lancamento, Long> id;
	public static volatile SingularAttribute<Lancamento, String> descricao;

}

