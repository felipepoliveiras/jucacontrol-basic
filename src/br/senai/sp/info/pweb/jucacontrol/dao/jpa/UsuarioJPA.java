package br.senai.sp.info.pweb.jucacontrol.dao.jpa;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import br.senai.sp.info.pweb.jucacontrol.dao.UsuarioDAO;
import br.senai.sp.info.pweb.jucacontrol.models.Usuario;

@Repository("usuarioDao")
public class UsuarioJPA implements UsuarioDAO {
	
	@Autowired
	private SessionFactory session;

	@Override
	public Usuario buscar(Long id) {
		Query query = session.getCurrentSession().createQuery("FROM Usuario u WHERE u.id = :id");
		query.setParameter("id", id);
		
		return null;
	}

	@Override
	public List<Usuario> buscarTodos() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void alterar(Usuario obj) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deletar(Usuario obj) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void inserir(Usuario obj) {
		session.getCurrentSession().persist(obj);
		
	}

	@Override
	public Usuario buscarPorEmailESenha(String email, String senha) {
		String hql = "FROM Usuario u WHERE u.email = :email AND u.senha = :senha";
		Query query = session.getCurrentSession().createQuery(hql);
		
		query.setParameter("email", email);
		query.setParameter("senha", senha);
		
		List<Usuario> result = query.list();
		
		if(result.isEmpty()) {
			return null;
		}else {
			return result.get(0);
		}
	}

	
}
