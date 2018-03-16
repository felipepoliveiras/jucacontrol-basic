package br.senai.sp.info.pweb.jucacontrol.dao.jpa;

import org.springframework.stereotype.Repository;

import br.senai.sp.info.pweb.jucacontrol.dao.CategoriaOcorrenciaDAO;
import br.senai.sp.info.pweb.jucacontrol.models.CategoriaOcorrencia;

@Repository("categoriaOcorrenciaDao")
public class CategoriaOcorrenciaJPA extends AbstractJPA<CategoriaOcorrencia> implements CategoriaOcorrenciaDAO {
	@Override
	public String getEntityName() {
		return "CategoriaOcorrencia";
	}	
}
