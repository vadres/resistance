package com.ennodo.resistence.infra.dto;

import com.ennodo.resistence.domain.VencedorEnum;

public record AtualizarPlacarDTO(Integer partida, VencedorEnum vencedor) {
}
