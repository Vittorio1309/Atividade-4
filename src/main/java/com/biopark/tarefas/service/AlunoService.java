package com.biopark.tarefas.service;

import com.biopark.tarefas.model.Aluno;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AlunoService {

    private final List<Aluno> alunos = new ArrayList<>();
    private Long proximoId = 1L;

    public List<Aluno> listarTodos() {
        return alunos;
    }

    public Optional<Aluno> buscarPorId(Long id) {
        return alunos.stream()
                .filter(aluno -> aluno.getId().equals(id))
                .findFirst();
    }

    public void salvar(Aluno aluno) {
        if (aluno.getId() == null) {
            aluno.setId(proximoId++);
            alunos.add(aluno);
        } else {
            Optional<Aluno> alunoExistente = buscarPorId(aluno.getId());
            if (alunoExistente.isPresent()) {
                Aluno existente = alunoExistente.get();
                existente.setNome(aluno.getNome());
                existente.setEmail(aluno.getEmail());
                existente.setMatricula(aluno.getMatricula());
                existente.setCurso(aluno.getCurso());
            }
        }
    }

    public void excluir(Long id) {
        alunos.removeIf(aluno -> aluno.getId().equals(id));
    }
}