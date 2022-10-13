package com.alura.jeffersonapps.agenda.dao;

import com.alura.jeffersonapps.agenda.model.Aluno;

import java.util.ArrayList;
import java.util.List;

public class AlunoDao {
    private final static List<Aluno> alunos = new ArrayList<>();
    private static int contadorDeIds = 1;

    public void salva(Aluno aluno) {
        aluno.setId(contadorDeIds++);
        alunos.add(aluno);
    }

    public List<Aluno> todos() {
        return new ArrayList<>(alunos);
    }

    public void edita(Aluno aluno) {
        Aluno encontrado = buscaAluno(aluno);
        if (encontrado != null) {
            int posicao = alunos.indexOf(encontrado);
            alunos.set(posicao, aluno);
        }
    }

    public void removeAluno(Aluno escolhido) {
        Aluno encontrado = buscaAluno(escolhido);
        if(encontrado != null){
            alunos.remove(encontrado);
        }
    }

    private Aluno buscaAluno(Aluno aluno){
        for (Aluno a : alunos) {
            if (a.getId() == aluno.getId()) {
                return  a;
            }
        }
        return  null;
    }
}
