package com.ennodo.resistence.infra.service;

import com.ennodo.resistence.domain.VencedorEnum;
import com.ennodo.resistence.infra.dto.AtualizarPlacarDTO;
import com.ennodo.resistence.infra.dto.PlacarDTO;
import com.ennodo.resistence.infra.entity.GrupoPartidaJpa;
import com.ennodo.resistence.infra.entity.PartidaJpa;
import com.ennodo.resistence.infra.repository.GrupoPartidaRepository;
import com.ennodo.resistence.infra.repository.PartidaRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class PlacarService {
    private final PartidaRepository partidaRepository;
    private final GrupoPartidaRepository grupoPartidaRepository;

    @Transactional
    public void atualizarPlacar(AtualizarPlacarDTO placar) {
        PartidaJpa partida = partidaRepository.findOneById(placar.partida())
                .orElseThrow(RuntimeException::new);

        if (VencedorEnum.ESPIOES.equals(placar.vencedor())) {
            partidaRepository.vencedorEspioes(partida.getId());
        } else {
            partidaRepository.vencedorResistencia(partida.getId());
        }
    }

    public PlacarDTO buscarPlacar() {
        GrupoPartidaJpa grupo = grupoPartidaRepository.findByAtual(true)
                .orElseThrow(() -> new RuntimeException("Sem partida ativa!"));

        List<PartidaJpa> partidas = partidaRepository.findByGrupo_Id(grupo.getId());
        long resitencia = partidas.stream().filter(PartidaJpa::getResistencia).count();
        long espioes = partidas.stream().filter(PartidaJpa::getEspioes).count();

        return new PlacarDTO(resitencia, espioes);
    }
}
