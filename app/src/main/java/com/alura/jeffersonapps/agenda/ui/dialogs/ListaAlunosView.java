package com.alura.jeffersonapps.agenda.ui.dialogs;

import android.app.AlertDialog;
import android.content.Context;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ListView;

import com.alura.jeffersonapps.agenda.R;
import com.alura.jeffersonapps.agenda.asynctask.BuscaAlunoTask;
import com.alura.jeffersonapps.agenda.asynctask.RemoveAlunoTask;
import com.alura.jeffersonapps.agenda.database.AgendaDatabase;
import com.alura.jeffersonapps.agenda.database.dao.AlunoDAO;
import com.alura.jeffersonapps.agenda.model.Aluno;
import com.alura.jeffersonapps.agenda.ui.adapter.ListaAlunosAdapter;

public class ListaAlunosView {

    private final ListaAlunosAdapter adapter;
    private final AlunoDAO dao;
    private final Context context;

    public ListaAlunosView(Context context) {
        this.context = context;
        this.adapter = new ListaAlunosAdapter(context);
        dao = AgendaDatabase.getInstance(context).getAlunoDAO();
    }

    public void confirmaRemocao(final MenuItem item) {
        new AlertDialog.Builder(context)
                .setTitle(context.getString(R.string.dialog_remove_aluno_title))
                .setMessage(context.getString(R.string.dialog_remove_aluno_mensagem))
                .setPositiveButton(context.getString(R.string.dialog_positive_option), (dialogInterface, i) -> {
                    removeAluno(item);
                })
                .setNegativeButton(context.getString(R.string.dialog_negative_option), null)
                .show();
    }

    private void removeAluno(final MenuItem item) {
        Aluno alunoEscolhido = pegaAlunoSelecionado(item);
        remove(alunoEscolhido);
    }

    private Aluno pegaAlunoSelecionado(final MenuItem item) {
        AdapterView.AdapterContextMenuInfo menuInfo = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        return adapter.getItem(menuInfo.position);
    }

    private void remove(Aluno aluno) {
        new RemoveAlunoTask(dao, adapter, aluno).execute();
    }

    public void atualizaAlunos() {
        new BuscaAlunoTask(dao, adapter).execute();
    }

    public void configuraAdapter(ListView lstAlunos) {
        lstAlunos.setAdapter(this.adapter);
    }

    public Aluno pegaAlunoNaPosicao(int posicao) {
        return adapter.getItem(posicao);
    }
}
