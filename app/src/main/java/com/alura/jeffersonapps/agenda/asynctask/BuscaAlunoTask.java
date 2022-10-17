package com.alura.jeffersonapps.agenda.asynctask;

import android.os.AsyncTask;

import com.alura.jeffersonapps.agenda.database.dao.AlunoDAO;
import com.alura.jeffersonapps.agenda.model.Aluno;
import com.alura.jeffersonapps.agenda.ui.adapter.ListaAlunosAdapter;
import java.util.List;

public class BuscaAlunoTask extends AsyncTask<Void, Void, List<Aluno>> {
    private final AlunoDAO dao;
    private final ListaAlunosAdapter adapter;

    public BuscaAlunoTask(AlunoDAO dao, ListaAlunosAdapter adapter){
        this.dao = dao;
        this.adapter = adapter;
    }

    @Override
    protected List<Aluno> doInBackground(Void... voids) {
        return dao.todos();
    }

    @Override
    protected void onPostExecute(List<Aluno> lst) {
        super.onPostExecute(lst);
        adapter.atualizaAlunos(lst);
    }
}
