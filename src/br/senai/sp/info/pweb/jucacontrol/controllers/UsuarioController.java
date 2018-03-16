package br.senai.sp.info.pweb.jucacontrol.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import br.senai.sp.info.pweb.jucacontrol.core.SessionUtils;
import br.senai.sp.info.pweb.jucacontrol.dao.UsuarioDAO;
import br.senai.sp.info.pweb.jucacontrol.models.TiposUsuario;
import br.senai.sp.info.pweb.jucacontrol.models.Usuario;

@Controller
public class UsuarioController {
	
	@Autowired
	private UsuarioDAO usuarioDao;
	
	@Autowired
	private SessionUtils sessionUtils;
	
	@GetMapping(value = {"/", ""})
	public String abrirLogin(Model model) {
		
		if(!sessionUtils.isUsuarioLogado()) {
			model.addAttribute("usuario", new Usuario());
			return "index";
		}else {
			return "redirect:/app";
		}
	}
	
	@GetMapping("/app/adm/usuario")
	public String abrirLista(Model model) {
		
		model.addAttribute("usuarios", usuarioDao.buscarTodos());
		
		return "usuario/lista";
	}
	
	@GetMapping("/app/adm/usuario/novo")
	public String abrirFormNovoUsuario(Model model) {
		model.addAttribute("usuario", new Usuario());
		
		return "usuario/form";
	}
	
	@GetMapping("/app/adm/usuario/editar")
	public String abriFormEditarUsuario(@RequestParam(name = "id", required = true) Long id, Model model) {
		model.addAttribute("usuario", usuarioDao.buscar(id));
		
		return "usuario/form";
	}
	
	@GetMapping("/app/perfil")
	public String abrirFormEditarUsuarioLogado(Model model) {
		model.addAttribute("usuario", sessionUtils.getUsuarioLogado());
		
		return "usuario/form";
	}
	
	@GetMapping("/app/alterarSenha")
	public String abrirFormAlterarSenha(Model model) {
		model.addAttribute("usuario", sessionUtils.getUsuarioLogado());
		
		return "usuario/alterarSenha";
	}
	
	@PostMapping({"/app/adm/usuario/salvar"})
	public String salvar(@Valid Usuario usuario, BindingResult brUsuario, 
						@RequestParam(name = "isAdministrador", required = false) Boolean administrador) {
		
		if(brUsuario.hasFieldErrors()) {
			return "usuario/form";
		}
		
		//Verifica se o usuário é administardor
		if(administrador != null && administrador) {
			usuario.setTipo(TiposUsuario.ADMINISTRADOR);
		}
		
		//Hasheia a senha do usuário
		usuario.hashearSenha();
		
		if(usuario.getId() == null) {
			usuarioDao.inserir(usuario);
		}else {
			
			usuarioDao.alterar(usuario);
		}
		
		return "redirect:/app/adm/usuario";
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
	
	@GetMapping({"/sair"})
	public String logout() {
		sessionUtils.invalidarSessao();
		
		return "redirect:/";
	}

}
