package br.senai.sp.info.pweb.jucacontrol.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.senai.sp.info.pweb.jucacontrol.dao.CategoriaOcorrenciaDAO;
import br.senai.sp.info.pweb.jucacontrol.models.Ocorrencia;

@Controller
@RequestMapping("/app")
public class OcorrenciaController {
	
	@Autowired
	private CategoriaOcorrenciaDAO categoriaDao;
	
	@Autowired
	
	@GetMapping({"", "/"})
	public String abrirListaOcorrencia() {
		return "ocorrencia/lista";
	}
	
	@GetMapping({"/ocorrencia/nova"})
	public String abriFormOcorrencia(Model model) {
		model.addAttribute("categorias", categoriaDao.buscarTodos());
		model.addAttribute("ocorrencia", new Ocorrencia());
		
		return "ocorrencia/form";
	}
	

}
