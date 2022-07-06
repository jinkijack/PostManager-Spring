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
	
	@ModelAttribute("funcionarios")
    public List<Funcionario> getFuncionarios(){
        List<Funcionario> funcionarios = funcRepo.findAll();
        return funcionarios;
    }
	
	@GetMapping("/funcionarios/form")
	public String funcForm(@ModelAttribute("funcionario")Funcionario funcionario) {
		
		return "funcionario_form";
	}
	
	@PostMapping("funcionarios/new")
	public String funcNew(@Valid @ModelAttribute("funcionario")Funcionario funcionario, BindingResult br) {
		
		if(br.hasErrors()) {
			return "funcionario_form";
		}
		
		funcRepo.save(funcionario);

		
		return "redirect:/funcionarios";
	}
	@GetMapping("funcionarios/{id}")
	public String funcForm(@PathVariable("id") Integer id, Model model) {
		
		Optional<Funcionario> funcOpt = funcRepo.findById(id);
		if(funcOpt.isEmpty()) {
			throw new IllegalArgumentException("Usuário inválido!");
		}
		Funcionario funcionario = funcOpt.get();
		model.addAttribute("funcionario", funcionario);
		
		return "funcionario_form";
	}
	@GetMapping("funcionarios/delete/{id}")
	public String funcDelete(@PathVariable("id") Integer id) {
		
		Optional<Funcionario> funcOpt = funcRepo.findById(id);
		
		if(funcOpt.isEmpty())
			throw new IllegalArgumentException("Usuario invalido!");
		
		Funcionario funcionario = funcOpt.get();
		
		
		funcRepo.delete(funcionario);
		
		return"redirect:/funcionarios";
	}
}
