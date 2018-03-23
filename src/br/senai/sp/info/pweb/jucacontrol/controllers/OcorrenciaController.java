package br.senai.sp.info.pweb.jucacontrol.controllers;

import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.senai.sp.info.pweb.jucacontrol.core.LocalStorage;
import br.senai.sp.info.pweb.jucacontrol.core.PesquisaOcorrencias;
import br.senai.sp.info.pweb.jucacontrol.core.SessionUtils;
import br.senai.sp.info.pweb.jucacontrol.dao.CategoriaOcorrenciaDAO;
import br.senai.sp.info.pweb.jucacontrol.dao.OcorrenciaDAO;
import br.senai.sp.info.pweb.jucacontrol.models.Ocorrencia;
import br.senai.sp.info.pweb.jucacontrol.models.TiposUsuario;
import br.senai.sp.info.pweb.jucacontrol.models.Usuario;

@Controller
@RequestMapping("/app")
public class OcorrenciaController {
	
	@Autowired
	private CategoriaOcorrenciaDAO categoriaDao;
	
	@Autowired
	private OcorrenciaDAO ocorrenciaDao;
	
	@Autowired
	private SessionUtils sessionUtils;
	
	@Autowired
	private LocalStorage storage;
		
	@GetMapping({"", "/"})
	public String abrirListaOcorrencia(@RequestParam(name = "pesquisa", required = false) PesquisaOcorrencias pesquisa, Model model) {
		
		if(pesquisa == null) {
			pesquisa = PesquisaOcorrencias.TODOS;
		}
		
		List<Ocorrencia> ocorrencias = ocorrenciaDao.buscarOcorrencias(pesquisa);
		
		//Aplica a foto de perfil em cada técnico
		for (Ocorrencia ocorrencia : ocorrencias) {
			if(ocorrencia.getTecnico() != null) {
				storage.aplicarCaminhoFotoEmUsuario(ocorrencia.getTecnico());
			}
		}
		model.addAttribute("ocorrencias", ocorrencias);
		model.addAttribute("pesquisas", PesquisaOcorrencias.values());
		
		return "ocorrencia/lista";
	}
	
	@GetMapping({"/ocorrencia/nova"})
	public String abriFormOcorrencia(Model model) {
		model.addAttribute("categorias", categoriaDao.buscarTodos());
		model.addAttribute("ocorrencia", new Ocorrencia());
		
		return "ocorrencia/form";
	}
	
	@GetMapping("/ocorrencia/editar")
	public String abrirEditarOcorrencia(@RequestParam(required = true) Long id, Model model) {
		
		model.addAttribute("ocorrencia", ocorrenciaDao.buscar(id));
		model.addAttribute("categorias", categoriaDao.buscarTodos());
		return "ocorrencia/form";
	}
	
	@GetMapping("/ocorrencia/assumir")
	public String assumirOcorrencia(@RequestParam(required = true) Long id, RedirectAttributes redirectAttributes) {
		
		Ocorrencia ocorrenciaBuscada = ocorrenciaDao.buscar(id);
		if(ocorrenciaBuscada != null) {
			ocorrenciaBuscada.setTecnico(sessionUtils.getUsuarioLogado());
			ocorrenciaDao.alterar(ocorrenciaBuscada);
		}else {
			redirectAttributes.addFlashAttribute("erro", "Ocorrência selecionada para assumir não existe");
		}
		
		return "redirect:/app";
	}
	
	@GetMapping("/ocorrencia/encerrar")
	public String concluirOcorrencia(@RequestParam(required = true) Long id, RedirectAttributes redirectAttributes) {
		Usuario usuarioLogado = sessionUtils.getUsuarioLogado();
		Ocorrencia ocorrenciaBuscada = ocorrenciaDao.buscar(id);
		
		if(ocorrenciaBuscada != null) {
			
			//Encerra a ocorrência sómente se o usuário logado for técnico atual ou administrador
			if(	usuarioLogado.getTipo() == TiposUsuario.ADMINISTRADOR || 
				ocorrenciaBuscada.getTecnico().getId().equals(usuarioLogado.getId())) {
				
				//Aplica a data de conclusão para agora
				ocorrenciaBuscada.setDataConclusao(new Date());
				
				//Altera a ocorrencia no banco de dados
				ocorrenciaDao.alterar(ocorrenciaBuscada);
			}else {
				redirectAttributes.addFlashAttribute("erro", "Você não pode concluir esta ocorrência pois ela não está sob sua responsabilidade");
				System.out.println("Você não pode concluir esta ocorrência pois ela não está sob sua responsabilidade");
			}
		}else {
			redirectAttributes.addFlashAttribute("erro", "Ocorrência selecionada para encerramento não existe");
			System.out.println("Ocorrência selecionada para encerramento não existe");
		}
		
		return "redirect:/app";
	}
	
	@PostMapping("/ocorrencia/salvar")
	public String salvar(@Valid Ocorrencia ocorrencia, BindingResult brOcorrencia, Model model) {		
		//Checa os erros de ocorrência
		if(brOcorrencia.hasFieldErrors("titulo") || brOcorrencia.hasFieldErrors("descricao") || brOcorrencia.hasFieldErrors("categoria")) {
			System.out.println(brOcorrencia);
			model.addAttribute("categorias", categoriaDao.buscarTodos());
			return "ocorrencia/form";
		}
		
		//Atualizando a data de modificação para agora
		ocorrencia.setDataModificacao(new Date());
		
		if(ocorrencia.getId() == null) {
			//Aplicando data de cadastro
			ocorrencia.setDataCadastro(new Date());
			
			ocorrencia.setEmissor(sessionUtils.getUsuarioLogado());
			ocorrenciaDao.inserir(ocorrencia);
		}else {
			Ocorrencia ocorrenciaBuscada = ocorrenciaDao.buscar(ocorrencia.getId());
			ocorrenciaBuscada.setTitulo(ocorrencia.getTitulo());
			ocorrenciaBuscada.setDescricao(ocorrencia.getDescricao());
			ocorrencia.setCategoria(ocorrencia.getCategoria());
			
			ocorrenciaDao.alterar(ocorrenciaBuscada);
		}
		
		return "redirect:/app";
	}
	

}
