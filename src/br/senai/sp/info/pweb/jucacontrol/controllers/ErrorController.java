package br.senai.sp.info.pweb.jucacontrol.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ErrorController {
	
	@GetMapping("/erro/proibido")
	public String abrirError403Proibido() {
		return "erro/403";
	}
	
}
