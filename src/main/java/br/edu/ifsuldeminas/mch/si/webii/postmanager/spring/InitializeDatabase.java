package br.edu.ifsuldeminas.mch.si.webii.postmanager.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import br.edu.ifsuldeminas.mch.si.webii.postmanager.spring.model.Address;
import br.edu.ifsuldeminas.mch.si.webii.postmanager.spring.model.Cidade;
import br.edu.ifsuldeminas.mch.si.webii.postmanager.spring.model.Funcionario;
import br.edu.ifsuldeminas.mch.si.webii.postmanager.spring.model.User;
import br.edu.ifsuldeminas.mch.si.webii.postmanager.spring.model.repositories.AddressRepository;
import br.edu.ifsuldeminas.mch.si.webii.postmanager.spring.model.repositories.CidadeRepository;
import br.edu.ifsuldeminas.mch.si.webii.postmanager.spring.model.repositories.FuncionarioRepository;
import br.edu.ifsuldeminas.mch.si.webii.postmanager.spring.model.repositories.UserRepository;

@Component
public class InitializeDatabase implements CommandLineRunner{
	
	@Autowired
	private UserRepository uRepo;
	
	@Autowired
	private AddressRepository aRepo;
	
	@Autowired
	private CidadeRepository cRepo;
	
	@Autowired
	private FuncionarioRepository fRepo;
	
	@Override
	public void run(String... args) throws Exception {
		
		Cidade machado = new Cidade();
		machado.setName("Machado");
		machado.setState("MG");
		
		cRepo.saveAndFlush(machado);
		
		Address atiago = new Address();
		atiago.setPlace("rua");
		atiago.setNumber(1);
		atiago.setZipCode("321");
		
		Address aLuiza = new Address();
		aLuiza.setPlace("rua");
		aLuiza.setNumber(1);
		aLuiza.setZipCode("321");
		
		
		atiago.setCidade(machado);
		aLuiza.setCidade(machado);
		
		aRepo.saveAndFlush(atiago);
		aRepo.saveAndFlush(aLuiza);
		
		User tiago = new User();
		tiago.setName("tiago");
		tiago.setEmail("tiago@gmail.com");
		tiago.setGender("M");
		tiago.setAddress(atiago);
		
		User luiza = new User();
		luiza.setName("Luiza carvalho");
		luiza.setEmail("lu@gmail.com");
		luiza.setGender("F");
		luiza.setAddress(aLuiza);
		
		uRepo.saveAndFlush(tiago);
		uRepo.saveAndFlush(luiza);
		
		Funcionario func = new Funcionario();
		func.setName("joãozinho123");
		func.setEmail("hoahda@gmail.com");
		func.setGender("M");
		
		
		fRepo.saveAndFlush(func);

	}

}
