package br.senai.sp.info.pweb.jucacontrol.dao;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import br.senai.sp.info.pweb.jucacontrol.core.PesquisaOcorrencias;
import br.senai.sp.info.pweb.jucacontrol.models.Ocorrencia;

public interface OcorrenciaDAO extends DAO<Ocorrencia> {
	@Transactional
	public List<Ocorrencia> buscarOcorrencias(PesquisaOcorrencias pesquisa);
}
