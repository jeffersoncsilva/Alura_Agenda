package com.alura.jeffersonapps.agenda.asynctask;

import android.os.AsyncTask;

import com.alura.jeffersonapps.agenda.database.dao.TelefoneDao;
import com.alura.jeffersonapps.agenda.model.Telefone;

public class BuscaPrimeiroTelefoneDoAluno extends AsyncTask<Void, Void, Telefone> {
    private final TelefoneDao telefoneDao;
    private final int idAluno;
    private final PrimeiroTelefoneEncontradoListener listener;

    public BuscaPrimeiroTelefoneDoAluno(TelefoneDao dao, int alunoId, PrimeiroTelefoneEncontradoListener listener){
        this.telefoneDao = dao;
        this.idAluno = alunoId;
        this.listener = listener;
    }

    @Override
    protected Telefone doInBackground(Void... voids) {
        return telefoneDao.buscaPrimeiroTelefoneDoAluno(idAluno);
    }

    @Override
    protected void onPostExecute(Telefone telefone) {
        super.onPostExecute(telefone);
        listener.telefoneFoiEncontrado(telefone);
    }

    public interface PrimeiroTelefoneEncontradoListener{

        void telefoneFoiEncontrado(Telefone telefone);
    }
}
