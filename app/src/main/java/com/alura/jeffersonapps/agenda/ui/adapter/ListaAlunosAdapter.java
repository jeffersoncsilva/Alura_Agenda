package com.alura.jeffersonapps.agenda.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.alura.jeffersonapps.agenda.R;
import com.alura.jeffersonapps.agenda.asynctask.BuscaPrimeiroTelefoneDoAluno;
import com.alura.jeffersonapps.agenda.database.AgendaDatabase;
import com.alura.jeffersonapps.agenda.database.dao.TelefoneDao;
import com.alura.jeffersonapps.agenda.databinding.ItemAlunoBinding;
import com.alura.jeffersonapps.agenda.model.Aluno;

import java.util.ArrayList;
import java.util.List;

public class ListaAlunosAdapter extends BaseAdapter {
    private final List<Aluno> alunos = new ArrayList<>();
    private final Context context;
    private final TelefoneDao dao;

    public ListaAlunosAdapter(Context context) {
        this.context = context;
        dao = AgendaDatabase.getInstance(context).getTelefoneDao();
    }

    @Override
    public int getCount() {
        return alunos.size();
    }

    @Override
    public Aluno getItem(int posicao) {
        return alunos.get(posicao);
    }

    @Override
    public long getItemId(int posicao) {
        return getItem(posicao).getId();
    }

    @Override
    public View getView(int posicao, View view, ViewGroup viewGroup) {
        if(view == null) {
            ItemAlunoBinding binding = ItemAlunoBinding.inflate(LayoutInflater.from(context), viewGroup, false);
            view = binding.getRoot();
            view.setTag(new ListaAlunosViewHolder(binding));
        }
        vinculaDadosNaView(view, posicao);
        return view;
    }

    private void vinculaDadosNaView(View v, int posicao){
        ListaAlunosViewHolder holder = (ListaAlunosViewHolder) v.getTag();
        Aluno a = getItem(posicao);
        holder.nome.setText(a.getNomeCompleto());
        new BuscaPrimeiroTelefoneDoAluno(dao, a.getId(), telefoneEncontrado -> holder.telefone.setText(telefoneEncontrado.getNumero())).execute();
    }

    public void atualizaAlunos(List<Aluno> alunos){
        this.alunos.clear();
        this.alunos.addAll(alunos);
        notifyDataSetChanged();
    }

    public void remove(Aluno aluno) {
        this.alunos.remove(aluno);
        notifyDataSetChanged();
    }

    public class ListaAlunosViewHolder {
        public final TextView nome;
        public final TextView telefone;

        public ListaAlunosViewHolder(ItemAlunoBinding binding){
            nome = binding.itemAlunoNome;
            telefone = binding.itemAlunoTelefone;
        }
    }
}