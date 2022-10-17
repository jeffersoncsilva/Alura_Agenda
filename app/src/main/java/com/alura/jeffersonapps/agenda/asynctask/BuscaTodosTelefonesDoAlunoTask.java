package com.alura.jeffersonapps.agenda.asynctask;

import android.os.AsyncTask;
import com.alura.jeffersonapps.agenda.database.dao.TelefoneDao;
import com.alura.jeffersonapps.agenda.model.Telefone;

import java.util.List;

public class BuscaTodosTelefonesDoAlunoTask extends AsyncTask<Void, Void, List<Telefone>> {
    private final TelefoneDao dao;
    private final int idAluno;
    private final TelefoneEncontradoListener listener;

    public BuscaTodosTelefonesDoAlunoTask(TelefoneDao dao, int idAluno, TelefoneEncontradoListener listener) {
        this.dao = dao;
        this.idAluno = idAluno;
        this.listener = listener;
    }

    @Override
    protected List<Telefone> doInBackground(Void... voids) {
        List<Telefone> t = dao.buscaTodosTelefonesDoAluno(idAluno);
        return t;
    }

    @Override
    protected void onPostExecute(List<Telefone> telefones) {
        super.onPostExecute(telefones);
        listener.guandoEncontrado(telefones);
    }

    public interface TelefoneEncontradoListener {
        void guandoEncontrado(List<Telefone> telfoneEncontrado);
    }
}
