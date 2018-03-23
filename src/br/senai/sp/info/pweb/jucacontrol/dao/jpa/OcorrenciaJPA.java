package br.senai.sp.info.pweb.jucacontrol.dao.jpa;

import org.springframework.stereotype.Repository;

import br.senai.sp.info.pweb.jucacontrol.dao.OcorrenciaDAO;
import br.senai.sp.info.pweb.jucacontrol.models.Ocorrencia;

@Repository(value = "ocorrenciaDao")
public class OcorrenciaJPA extends AbstractJPA<Ocorrencia> implements OcorrenciaDAO {

	@Override
	public String getEntityName() {
		return "Ocorrencia";
	}
}
