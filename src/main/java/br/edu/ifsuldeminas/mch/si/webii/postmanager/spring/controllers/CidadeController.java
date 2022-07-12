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
import br.edu.ifsuldeminas.mch.si.webii.postmanager.spring.model.repositories.CidadeRepository;

@Controller
public class CidadeController {
	@Autowired
	private CidadeRepository cidadeRepo;
	
	@GetMapping("/cidades")
	public String cidades(Model model) {
		List<Cidade> cidades = cidadeRepo.findAll();
		
		model.addAttribute("cidades", cidades);
		
		return "listar_cidade";
	}
	
	@ModelAttribute("cidades")
    public List<Cidade> getCidades(){
        List<Cidade> cidades = cidadeRepo.findAll();
        return cidades;
    }
	
	@GetMapping("/cidades/form")
	public String cidadeForm(@ModelAttribute("cidade")Cidade cidade) {
		
		return "cidade_form";
	}
	@PostMapping("cidades/new")
	public String cidadeNew(@Valid @ModelAttribute("cidade")Cidade cidade, BindingResult br) {
		
		if(br.hasErrors()) {
			return "cidade_form";
		}
		
		cidadeRepo.saveAndFlush(cidade);

		
		return "redirect:/cidades";
	}
	@GetMapping("/cidades/{id}")
	public String cidadeForm(@PathVariable("id") Integer id, Model model) {
		
		Optional<Cidade> cidadeOpt = cidadeRepo.findById(id);
		if(cidadeOpt.isEmpty()) {
			throw new IllegalArgumentException("Usuário inválido!");
		}
		Cidade cidade = cidadeOpt.get();
		model.addAttribute("cidade", cidade);
		
		return "cidade_form";
	}
	@GetMapping("/cidades/delete/{id}")
	public String cidadeDelete(@PathVariable("id") Integer id) {
		
		Optional<Cidade> cidadeOpt = cidadeRepo.findById(id);
		
		if(cidadeOpt.isEmpty())
			throw new IllegalArgumentException("Usuario invalido!");
		
		Cidade cidade = cidadeOpt.get();
		
		
		cidadeRepo.delete(cidade);
		
		return"redirect:/cidades";
	}
}
