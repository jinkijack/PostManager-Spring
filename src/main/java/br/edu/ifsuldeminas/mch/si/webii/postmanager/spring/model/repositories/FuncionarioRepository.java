package br.edu.ifsuldeminas.mch.si.webii.postmanager.spring.model.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import br.edu.ifsuldeminas.mch.si.webii.postmanager.spring.model.Funcionario;

public interface FuncionarioRepository extends JpaRepository<Funcionario, Integer>{}
