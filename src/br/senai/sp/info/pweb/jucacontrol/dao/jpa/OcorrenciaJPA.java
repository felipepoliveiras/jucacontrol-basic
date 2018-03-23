package br.senai.sp.info.pweb.jucacontrol.dao.jpa;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import br.senai.sp.info.pweb.jucacontrol.core.PesquisaOcorrencias;
import br.senai.sp.info.pweb.jucacontrol.dao.OcorrenciaDAO;
import br.senai.sp.info.pweb.jucacontrol.models.Ocorrencia;

@Repository(value = "ocorrenciaDao")
public class OcorrenciaJPA extends AbstractJPA<Ocorrencia> implements OcorrenciaDAO {

	@Override
	public String getEntityName() {
		return "Ocorrencia";
	}

	@Override
	public List<Ocorrencia> buscarOcorrencias(PesquisaOcorrencias pesquisa) {
		
		String hql = "FROM Ocorrencia o WHERE ";
		
		switch (pesquisa) {
		case ABERTO:
			hql += "o.tecnico = null OR o.dataConclusao = null";
			break;
		case AGUARDANDO:
			hql += "o.tecnico = null";
			break;
		case EM_ATENDIMENTO:
			hql += "o.tecnico <> null AND o.dataConclusao = null";
			break;
		case ENCERRADOS:
			hql += "o.dataConclusao <> null";
			break;
		case TODOS:
			hql = "FROM Ocorrencia o";
			break;
		}
		
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		
		return query.list();
	}
}
