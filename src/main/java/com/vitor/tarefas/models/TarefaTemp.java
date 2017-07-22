package com.vitor.tarefas.models;

import java.util.Arrays;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.apache.commons.codec.binary.Base64;

@Entity
@Table(name = "Tarefa")
public class TarefaTemp {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "CodTarefa")
	private int codigo;
	
	@Column(name = "NmTarefa", nullable = false, length = 155)
	private String nome;
	
//	@Temporal(TemporalType.TIMESTAMP)
//	@Column(name = "DtTarefa", nullable = false)
//	private Date data;
//	
//	@Temporal(TemporalType.TIMESTAMP)
//	@Column(name = "DtCriacaoTarefa", nullable = false)
//	private Date dataCriacao;
	
	@Column(name = "StatusTarefa", nullable = false)
	private int status;
	
	@Column(name = "Foto")
	private String foto;
	
	@Column(name = "Localizacao")
	private String localizacao;

//	@ManyToOne(optional = false)
//	@JoinColumn(name = "UsuarioCriou", nullable = false)
//	private Usuario usuarioCriou;
//	
//	@ManyToOne(optional = false)
//	@JoinColumn(name = "UsuarioRealizou", nullable = false)
//	private Usuario usuarioRealizou;
//	
//	@Column(name = "Observacoes", length = 500)
//	private String observacoes;
	
	public final static int STATUS_PENDENTE = 1;
	public final static int STATUS_CONCLUIDA = 2;
	
	public int getCodigo() {
		return codigo;
	}
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
//	public Date getData() {
//		return data;
//	}
//	public void setData(Date data) {
//		this.data = data;
//	}
//	public Date getDataCriacao() {
//		return dataCriacao;
//	}
//	public void setDataCriacao(Date dataCriacao) {
//		this.dataCriacao = dataCriacao;
//	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
//	public Usuario getUsuarioCriou() {
//		return usuarioCriou;
//	}
//	public void setUsuarioCriou(Usuario usuarioCriou) {
//		this.usuarioCriou = usuarioCriou;
//	}
//	public Usuario getUsuarioRealizou() {
//		return usuarioRealizou;
//	}
//	public void setUsuarioRealizou(Usuario usuarioRealizou) {
//		this.usuarioRealizou = usuarioRealizou;
//	}
//	public String getObservacoes() {
//		return observacoes;
//	}
//	public void setObservacoes(String observacoes) {
//		this.observacoes = observacoes;
//	}
	public String getFoto() {
		return foto;
	}
	public void setFoto(String foto) {
		this.foto = foto;
	}
	public String getLocalizacao() {
		return localizacao;
	}
	public void setLocalizacao(String localizacao) {
		this.localizacao = localizacao;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + codigo;
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TarefaTemp other = (TarefaTemp) obj;
		if (codigo != other.codigo)
			return false;
		return true;
	}
//	@Override
//	public String toString() {
//		return "Tarefa [codigo=" + codigo + ", nome=" + nome + ", data=" + data + ", dataCriacao=" + dataCriacao
//				+ ", status=" + status + ", usuarioCriou=" + usuarioCriou + ", usuarioRealizou=" + usuarioRealizou
//				+ ", observacoes=" + observacoes + "]";
//	}
	@Override
	public String toString() {
		return "Tarefa [codigo=" + codigo + ", nome=" + nome + ", status=" + status + ", foto=" + foto
				+ ", localizacao=" + localizacao + "]";
	}
	
	public Tarefa getTarefa() {
		Tarefa t = new Tarefa();
		t.setCodigo(this.codigo);
		t.setLocalizacao(this.localizacao);
		t.setNome(this.nome);
		t.setStatus(this.status);
		t.setFoto(convertToByte(this.foto));
		
		return t;
	}

	public byte[] convertToByte(String str) {
//		byte foto[] = new byte[str.length()];
//		for (int i = 0; i < str.length(); i++) {
//			foto[i] = Byte.parseByte("" + str.charAt(i));
//		}
		
		return Base64.decodeBase64(str);
//		return foto;
	}
}
