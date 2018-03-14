package br.senai.sp.info.pweb.jucacontrol.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import br.senai.sp.info.pweb.jucacontrol.core.SessionUtils;
import br.senai.sp.info.pweb.jucacontrol.dao.UsuarioDAO;
import br.senai.sp.info.pweb.jucacontrol.models.Usuario;

@Controller
public class UsuarioController {
	
	@Autowired
	private UsuarioDAO usuarioDao;
	
	@Autowired
	private SessionUtils sessionUtils;
	
	@GetMapping("/")
	public String abrirLogin(Model model) {
		model.addAttribute("usuario", new Usuario());
		return "index";
	}
	
	@GetMapping({"/usuario/novo"})
	public String abrirFormCadastro() {
		return "usuario/form";
	}
	
	@PostMapping({"/usuario/autenticar"})
	public String autenticar(@Valid Usuario usuario, BindingResult brUsuario) {
		
		//Se houver erros no usuario, reabre o index
		if(brUsuario.hasFieldErrors("email") || brUsuario.hasFieldErrors("senha")) {
			return "index";
		}
		
		//hasheia a senha inserida para buscar no banco de dados
		usuario.hashearSenha();
		Usuario usuarioBuscado = usuarioDao.buscarPorEmailESenha(usuario.getEmail(), usuario.getSenha());
		if(usuarioBuscado == null) {
			brUsuario.addError(new FieldError("usuario", "email", "Email ou senha inválidos"));
			return "index";
		}
		
		//Aplica o usuário na sessão e abre a página inicial
		sessionUtils.setUsuarioLogado(usuarioBuscado);
		
		return "redirect:/app/";
	}
	
	@GetMapping({"/app/usuario/sair"})
	public String logout() {
		sessionUtils.invalidarSessao();
		
		return "redirect:/";
	}

}
