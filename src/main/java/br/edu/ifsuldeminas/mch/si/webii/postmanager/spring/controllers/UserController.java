package br.edu.ifsuldeminas.mch.si.webii.postmanager.spring.controllers;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import br.edu.ifsuldeminas.mch.si.webii.postmanager.spring.model.Cidade;
import br.edu.ifsuldeminas.mch.si.webii.postmanager.spring.model.User;
import br.edu.ifsuldeminas.mch.si.webii.postmanager.spring.model.repositories.AddressRepository;
import br.edu.ifsuldeminas.mch.si.webii.postmanager.spring.model.repositories.CidadeRepository;
import br.edu.ifsuldeminas.mch.si.webii.postmanager.spring.model.repositories.UserRepository;

@Controller
public class UserController {
	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private AddressRepository addressRepo;
	
	@Autowired
	private CidadeRepository cidadeRepo;
	
	@GetMapping("/users")
	public String users(Model model) {
		List<User> users = userRepo.findAll();
		
		model.addAttribute("users", users);
		
		return"listar_usuario";
	}
	
	@ModelAttribute("cidades")
    public List<Cidade> getCidades(){
        List<Cidade> cidades = cidadeRepo.findAll();
        return cidades;
    }
	
	@GetMapping("/users/form")
	public String userForm(@ModelAttribute("user")User user) {
		return "user_form";
	}
	
	//UserNew
	@PostMapping("users/new")
	public String userNew(@Valid @ModelAttribute("user")User user, BindingResult br) {
		
		if(br.hasErrors()) {
			return "user_form";
		}
		
		addressRepo.save(user.getAddress());
		userRepo.save(user);
		
		return "redirect:/users";
	}
	
//	UserForm
	@GetMapping("/users/{id}")
	public String userForm(@PathVariable("id") Integer id, Model model) {
		
		Optional<User> userOpt = userRepo.findById(id);
		
		if(userOpt.isEmpty())
			throw new IllegalArgumentException("Usuario invalido!");
		
		User user = userOpt.get();
		model.addAttribute("user", user);
		
		return "user_form";
		
	}
	
	@GetMapping("/users/delete/{id}")
	public String userDelete(@PathVariable("id") Integer id) {
		
		Optional<User> userOpt = userRepo.findById(id);
		
		if(userOpt.isEmpty())
			throw new IllegalArgumentException("Usuario invalido!");
		
		User user = userOpt.get();
		
		userRepo.delete(user);
		
		return"redirect:/users";
	}
}
