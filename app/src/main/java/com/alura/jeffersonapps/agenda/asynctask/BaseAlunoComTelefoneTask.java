package com.alura.jeffersonapps.agenda.asynctask;

import android.os.AsyncTask;

import com.alura.jeffersonapps.agenda.model.Telefone;

public abstract class BaseAlunoComTelefoneTask extends AsyncTask<Void, Void, Void> {

    private final IFinalizadaListener listener;

    BaseAlunoComTelefoneTask(IFinalizadaListener listener) {
        this.listener = listener;
    }

    @Override
    protected void onPostExecute(Void unused) {
        super.onPostExecute(unused);
        listener.tarefaFinalizada();
    }

    protected void vinculaAlunoComTelefone(Telefone[] telefones, int alunoId) {
        for(Telefone t : telefones)
            t.setAlunoId(alunoId);
    }

}
