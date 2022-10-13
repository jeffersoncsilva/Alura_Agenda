package com.alura.jeffersonapps.agenda.ui.viewholders;

import android.view.View;
import android.widget.TextView;

import com.alura.jeffersonapps.agenda.R;

public class ListaAlunosViewHolder {
    public final TextView nome;
    public final TextView telefone;

    public ListaAlunosViewHolder(View v){
        nome = (TextView) v.findViewById(R.id.item_aluno_nome);
        telefone = (TextView) v.findViewById(R.id.item_aluno_telefone);
    }
}
