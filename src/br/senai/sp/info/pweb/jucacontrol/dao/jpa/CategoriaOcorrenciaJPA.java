package br.senai.sp.info.pweb.jucacontrol.dao.jpa;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import br.senai.sp.info.pweb.jucacontrol.dao.CategoriaOcorrenciaDAO;
import br.senai.sp.info.pweb.jucacontrol.models.CategoriaOcorrencia;

@Repository("categoriaOcorrenciaDao")
public class CategoriaOcorrenciaJPA implements CategoriaOcorrenciaDAO {
	
	@Autowired
	private SessionFactory session;

	@Override
	public CategoriaOcorrencia buscar(Long id) {
		String hql = "FROM CategoriaOcorrencia c WHERE c.id = :id";
		Query query = session.getCurrentSession().createQuery(hql);
		
		query.setParameter("id", id);
		
		List<CategoriaOcorrencia> resultado = query.list();
		if(resultado.isEmpty()) {
			return null;
		}else {
			return resultado.get(0);
		}
	}

	@Override
	public List<CategoriaOcorrencia> buscarTodos() {
		String hql = "FROM CategoriaOcorrencia c";
		Query query = session.getCurrentSession().createQuery(hql);
		
		return query.list();
	}

	@Override
	public void alterar(CategoriaOcorrencia obj) {
		session.getCurrentSession().update(obj);
	}

	@Override
	public void deletar(CategoriaOcorrencia obj) {
		session.getCurrentSession().delete(obj);
	}

	@Override
	public void inserir(CategoriaOcorrencia obj) {
		session.getCurrentSession().persist(obj);
	}

	@Override
	public CategoriaOcorrencia buscarPorCampo(String campo, Object valor) {
		String hql = "FROM CategoriaOcorrencia c WHERE c."+campo+" = :campo";
		Query query = session.getCurrentSession().createQuery(hql);
		
		query.setParameter("campo", valor);
		
		List<CategoriaOcorrencia> resultado = query.list();
		if(resultado.isEmpty()) {
			return null;
		}else {
			return resultado.get(0);
		}
	}

}
