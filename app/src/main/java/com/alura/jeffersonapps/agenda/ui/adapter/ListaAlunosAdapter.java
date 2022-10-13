package com.alura.jeffersonapps.agenda.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.alura.jeffersonapps.agenda.R;
import com.alura.jeffersonapps.agenda.model.Aluno;
import com.alura.jeffersonapps.agenda.ui.viewholders.ListaAlunosViewHolder;

import java.util.ArrayList;
import java.util.List;

public class ListaAlunosAdapter extends BaseAdapter {
    private final List<Aluno> alunos = new ArrayList<>();
    private final Context context;
//887.109.121-34
    //17462824
    public ListaAlunosAdapter(Context context) {
        this.context = context;
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
            view = LayoutInflater.from(this.context).inflate(R.layout.item_aluno, viewGroup, false);
            view.setTag(new ListaAlunosViewHolder(view));
        }
        vinculaDadosNaView(view, posicao);
        return view;
    }

    private void vinculaDadosNaView(View v, int posicao){
        ListaAlunosViewHolder holder = (ListaAlunosViewHolder) v.getTag();
        Aluno a = getItem(posicao);
        holder.nome.setText(a.getNomeCompleto());
        holder.telefone.setText(a.dataFormatada());
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
}
