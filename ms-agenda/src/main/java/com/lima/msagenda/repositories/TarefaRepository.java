package com.lima.msagenda.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lima.msagenda.models.Tarefa;

public interface TarefaRepository extends JpaRepository<Tarefa, Long> {

}
