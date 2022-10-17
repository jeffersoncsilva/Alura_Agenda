package com.alura.jeffersonapps.agenda.asynctask;

import com.alura.jeffersonapps.agenda.database.dao.AlunoDAO;
import com.alura.jeffersonapps.agenda.database.dao.TelefoneDao;
import com.alura.jeffersonapps.agenda.model.Aluno;
import com.alura.jeffersonapps.agenda.model.Telefone;

public class SalvaAlunoTask extends BaseAlunoComTelefoneTask {

    private final AlunoDAO dao;
    private final TelefoneDao telefoneDao;
    private final Aluno aluno;
    private final Telefone[] telefones;
    private final IFinalizadaListener listener;

    public SalvaAlunoTask(AlunoDAO dao, Aluno aluno, Telefone[] telefones, TelefoneDao telefoneDao, IFinalizadaListener listener) {
        super(listener);
        this.dao = dao;
        this.aluno = aluno;
        this.telefones = telefones;
        this.telefoneDao = telefoneDao;
        this.listener = listener;
    }

    @Override
    protected Void doInBackground(Void... voids) {
        int alunoId = dao.salva(aluno).intValue();
        vinculaAlunoComTelefone(telefones, alunoId);
        telefoneDao.salva(telefones);
        return null;
    }

}
