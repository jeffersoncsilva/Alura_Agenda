package com.alura.jeffersonapps.agenda.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.alura.jeffersonapps.agenda.R;
import com.alura.jeffersonapps.agenda.databinding.ListaAlunosActivityBinding;
import com.alura.jeffersonapps.agenda.model.Aluno;
import com.alura.jeffersonapps.agenda.ui.dialogs.ListaAlunosView;

public class ListaAlunosActivity extends AppCompatActivity {
    private ListaAlunosActivityBinding binding;
    private ListaAlunosView listaAlunosView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ListaAlunosActivityBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setTitle(getString(R.string.lista_alunos_activity_titulo));
        listaAlunosView = new ListaAlunosView(this);
        configuraFabNovoAluno();
        configuraLista();
    }

    private void configuraFabNovoAluno() {
        binding.floatingActionButton.setOnClickListener(view ->
                startActivity(new Intent(ListaAlunosActivity.this,
                        FormularioAlunoActivity.class)));
    }

    @Override
    protected void onResume() {
        super.onResume();
        listaAlunosView.atualizaAlunos();
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.act_main_menu, menu);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        if (ehOpcaoRemoverAluno(item)) {
            listaAlunosView.confirmaRemocao(item);
        }else if(ehOpcaoEditarAluno(item)){
            Aluno aluno = pegarAlunoNaPosicaoDoMenu(item);
            abreActivityEditarAluno(aluno);
        }
        return super.onContextItemSelected(item);
    }

    private Aluno pegarAlunoNaPosicaoDoMenu(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        return listaAlunosView.pegaAlunoNaPosicao(info.position);
    }

    private boolean ehOpcaoEditarAluno(MenuItem item) {
        return item.getItemId() == R.id.menu_editar;
    }

    private boolean ehOpcaoRemoverAluno(MenuItem item) {
        return item.getItemId() == R.id.menu_remover;
    }

    private void configuraLista() {
        listaAlunosView.configuraAdapter(binding.lstAlunos);
        configuraClickNoItemListView();
        registerForContextMenu(binding.lstAlunos);
    }

    private void configuraClickNoItemListView() {
        binding.lstAlunos.setOnItemClickListener((adapterView, view, posicao, id) -> {
            Aluno aluno = (Aluno) adapterView.getItemAtPosition(posicao);
            abreActivityEditarAluno(aluno);
        });
    }

    private void abreActivityEditarAluno(Aluno aluno) {
        Intent in = new Intent(ListaAlunosActivity.this, FormularioAlunoActivity.class);
        in.putExtra(ConstantesActivities.CHAVE_ALUNO, aluno);
        startActivity(in);
    }
}