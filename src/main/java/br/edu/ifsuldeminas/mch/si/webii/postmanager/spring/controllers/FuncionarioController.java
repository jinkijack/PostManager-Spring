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

import br.edu.ifsuldeminas.mch.si.webii.postmanager.spring.model.Funcionario;
import br.edu.ifsuldeminas.mch.si.webii.postmanager.spring.model.repositories.FuncionarioRepository;

@Controller
public class FuncionarioController {
	@Autowired
	private FuncionarioRepository funcRepo;
	
	@GetMapping("/funcionarios")
	public String funcionarios(Model model) {
		
		List<Funcionario> funcionarios = funcRepo.findAll();
		
		model.addAttribute("funcionarios", funcionarios);
		
		return "listar_funcionario";
	}
	
	@GetMapping("/funcionarios/form")
	public String funcionarioForm(@ModelAttribute("funcionario")Funcionario funcionario) {
				
		return "funcionario_form";
	}
	
	@PostMapping("funcionarios/new")
	public String newFuncionario(@Valid @ModelAttribute("funcionario")Funcionario funcionario, BindingResult br) {
		
		if(br.hasErrors()) {
			return "user_form";
		}
		
		funcRepo.save(funcionario);
		
		return "redirect:/funcionarios";
	}
	
	@GetMapping("/funcionarios/{id}")
	public String funcionarioForm(@PathVariable("id") Integer id, Model model) {
		
		Optional<Funcionario> funcionarioOpt = funcRepo.findById(id);
		
		if(funcionarioOpt.isEmpty())
			throw new IllegalArgumentException("Funcionario Invalido!");
		
		Funcionario funcionario = funcionarioOpt.get();
		model.addAttribute("funcionario", funcionario);
			
		return "funcionario_form";
	}
	
	public String funcionarioDelete(@PathVariable("id") Integer id){
		
		Optional<Funcionario> funcionarioOpt = funcRepo.findById(id);
		
		if(funcionarioOpt.isEmpty())
			throw new IllegalArgumentException("Funcionario Invalido");
		
		Funcionario funcionario = funcionarioOpt.get();
		
		funcRepo.delete(funcionario);
		
		return "redirect:/funcionarios";
	}
}
