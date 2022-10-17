package com.alura.jeffersonapps.agenda.asynctask;

import com.alura.jeffersonapps.agenda.database.dao.AlunoDAO;
import com.alura.jeffersonapps.agenda.database.dao.TelefoneDao;
import com.alura.jeffersonapps.agenda.model.Aluno;
import com.alura.jeffersonapps.agenda.model.Telefone;

public class EditaAlunoTask extends BaseAlunoComTelefoneTask{
    //EditaAlunoTask(alunoDao, aluno, telefones, this::finish).execute();
    private final AlunoDAO alunoDAO;
    private final Aluno aluno;
    private final Telefone[] telefones;
    private final TelefoneDao telefoneDao;

    public EditaAlunoTask(AlunoDAO alunoDao, Aluno aluno, Telefone[] telefones, TelefoneDao telefoneDao, IFinalizadaListener listener) {
        super(listener);
        this.aluno = aluno;
        this.alunoDAO= alunoDao;
        this.telefones = telefones;
        this.telefoneDao = telefoneDao;
    }

    @Override
    protected Void doInBackground(Void... voids) {
        alunoDAO.edita(aluno);
        vinculaAlunoComTelefone(telefones, aluno.getId());
        telefoneDao.atualiza(telefones);
        return null;
    }

    private void atualizaIdsDosTelefones(Telefone[] telefones, int alunoId){
        for(Telefone t: telefones)
            t.setAlunoId(alunoId);
    }
}
