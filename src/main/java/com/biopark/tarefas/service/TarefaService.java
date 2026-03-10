package com.biopark.tarefas.service;

import com.biopark.tarefas.model.Tarefa;
import com.biopark.tarefas.repository.TarefaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TarefaService {

    private final TarefaRepository tarefaRepository;

    public TarefaService(TarefaRepository tarefaRepository) {
        this.tarefaRepository = tarefaRepository;
    }

    public Tarefa salvar(Tarefa tarefa) {
        return tarefaRepository.save(tarefa);
    }

    public List<Tarefa> listarTodas() {
        return tarefaRepository.findAll();
    }

    public List<Tarefa> listarPendentes() {
        return tarefaRepository.findAll().stream().filter(tarefa -> !tarefa.getConcluida()).toList();
    }

    public long quantidadeTotal(){
        return tarefaRepository.findAll().stream().count();
    }

    public long quantidadeConcluidas(){
        return tarefaRepository.findAll().stream().filter(tarefa -> tarefa.getConcluida()).count();
    }

    public long quantidadePendentes(){
        return tarefaRepository.findAll().stream().filter(tarefa -> !tarefa.getConcluida()).count();
    }

    public List<Tarefa> listarConcluidos() { return tarefaRepository.findAll().stream().filter(tarefa -> tarefa.getConcluida()).toList();}

    public Optional<Tarefa> buscarPorId(Long id) {
        return tarefaRepository.findById(id);
    }

    public void excluir(Long id) {
        tarefaRepository.deleteById(id);
    }

    public void alterarStatus(Long id) {
        tarefaRepository.findById(id).ifPresent(tarefa -> {
            tarefa.setConcluida(!tarefa.getConcluida());
            tarefaRepository.save(tarefa);
        });
    }
}
