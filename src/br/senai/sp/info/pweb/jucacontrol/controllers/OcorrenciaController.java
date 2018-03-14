package br.senai.sp.info.pweb.jucacontrol.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/app")
public class OcorrenciaController {
	
	@GetMapping({"", "/"})
	public String abrirListaOcorrencia() {
		return "ocorrencia/lista";
	}
	
	@GetMapping({"/ocorrencia/nova"})
	public String abriFormOcorrencia() {
		return "ocorrencia/form";
	}
	

}
