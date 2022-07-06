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
		Address aRenan = new Address();
		aRenan.setPlace("bilurilu");
		aRenan.setNumber(1);
		aRenan.setZipCode("321");
		
		Address aLuiza = new Address();
		aLuiza.setPlace("bilurilu");
		aLuiza.setNumber(1);
		aLuiza.setZipCode("321");
		
		Cidade machado = new Cidade();
		machado.setName("Machado");
		machado.setState("MG");
		
		cRepo.save(machado);
		
		aRenan.setCidade(machado);
		aLuiza.setCidade(machado);
		
		aRepo.save(aRenan);
		aRepo.save(aLuiza);
		aRepo.flush();
		
		User renan = new User();
		renan.setName("Renan");
		renan.setEmail("renan@gmail.com");
		renan.setGender("M");
		renan.setAddress(aRenan);
		
		User luiza = new User();
		luiza.setName("Luiza carvalho");
		luiza.setEmail("lu@gmail.com");
		luiza.setGender("F");
		luiza.setAddress(aLuiza);
		
		Funcionario func = new Funcionario();
		func.setName("joãozinho123");
		func.setEmail("hoahda@gmail.com");
		func.setGender("m");
		
		
		fRepo.save(func);
		uRepo.save(renan);
		uRepo.save(luiza);
	}

}
