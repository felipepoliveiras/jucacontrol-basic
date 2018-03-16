package br.senai.sp.info.pweb.jucacontrol.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import br.senai.sp.info.pweb.jucacontrol.dao.CategoriaOcorrenciaDAO;
import br.senai.sp.info.pweb.jucacontrol.models.CategoriaOcorrencia;

@Controller
@RequestMapping("/app/adm")
public class CategoriaOcorrenciaController {
	
	@Autowired
	private CategoriaOcorrenciaDAO categoriaOcorrenciaDao;
	
	@GetMapping("/categoria")
	public String abrirMenuCategorias(@RequestParam(name = "id", required = false) Long id,
										Model model) {
		
		//Pega a categoria que está sendo alterada
		if(id != null) {
			model.addAttribute("categoria", categoriaOcorrenciaDao.buscar(id));
		}else {
			model.addAttribute("categoria", new CategoriaOcorrencia());
		}
		
		//Pega todas as categorias para exibir na lista
		model.addAttribute("categorias", categoriaOcorrenciaDao.buscarTodos());
		
		return "categoria/menu";
	}
	
	
	@PostMapping("/categoria/salvar")
	public String salvar(@Valid CategoriaOcorrencia categoriaOcorrencia, BindingResult brCategoriaOcorrencia,
						Model model) {
		//Verifica se existem erros no modelo
		if(brCategoriaOcorrencia.hasErrors()) {
			model.addAttribute("categoria", categoriaOcorrencia);
			return "categoria/menu";
		}
		
		//Verifica se o nome já existe
		if(categoriaOcorrenciaDao.buscarPorCampo("nome", categoriaOcorrencia).getNome() != null) {
			brCategoriaOcorrencia.addError(new FieldError("categoriaOcorrencia", "nome", "Este nome já esta sendo utilizado no sistema"));
			
			model.addAttribute("categoria", categoriaOcorrencia);
			return "categoria/menu";
		}
		
		//Persiste no banco de dados
		categoriaOcorrenciaDao.inserir(categoriaOcorrencia);
		
		//Redireciona para pagina de ocorrencia
		return "redirect:/app/adm/ocorrencia";
	}

}
