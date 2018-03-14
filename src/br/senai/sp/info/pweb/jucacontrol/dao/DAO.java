package br.senai.sp.info.pweb.jucacontrol.dao;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

public interface DAO<T> {
	
	@Transactional
	public T buscar(Long id);
	
	@Transactional
	public List<T> buscarTodos();
	
	@Transactional
	public void alterar(T obj);
	
	@Transactional
	public void deletar(T obj);
	
	@Transactional
	public void inserir(T obj);
	
}
