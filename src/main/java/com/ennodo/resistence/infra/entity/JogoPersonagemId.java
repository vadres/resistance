package com.ennodo.resistence.infra.entity;

import org.hibernate.Hibernate;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class JogoPersonagemId implements Serializable {
	private static final long serialVersionUID = 730224600482548169L;
	@Column(name = "id_personagem", nullable = false)
	private Integer idPersonagem;

	@Column(name = "id_jogo", nullable = false)
	private Integer idJogo;

	public Integer getIdPersonagem() {
		return idPersonagem;
	}

	public void setIdPersonagem(Integer idPersonagem) {
		this.idPersonagem = idPersonagem;
	}

	public Integer getIdJogo() {
		return idJogo;
	}

	public void setIdJogo(Integer idJogo) {
		this.idJogo = idJogo;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
		JogoPersonagemId entity = (JogoPersonagemId) o;
		return Objects.equals(this.idPersonagem, entity.idPersonagem) &&
				Objects.equals(this.idJogo, entity.idJogo);
	}

	@Override
	public int hashCode() {
		return Objects.hash(idPersonagem, idJogo);
	}

}