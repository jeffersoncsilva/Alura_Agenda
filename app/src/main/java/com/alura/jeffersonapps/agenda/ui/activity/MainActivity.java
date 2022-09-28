package com.alura.jeffersonapps.agenda.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.alura.jeffersonapps.agenda.R;
import com.alura.jeffersonapps.agenda.dao.AlunoDao;
import com.alura.jeffersonapps.agenda.model.Aluno;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;


public class MainActivity extends AppCompatActivity {

    private ArrayAdapter<Aluno> adapter;
    private ListView lv;
    private AlunoDao dao = new AlunoDao();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FloatingActionButton btn = findViewById(R.id.floatingActionButton);
        lv = findViewById(R.id.lstAlunos);
        btn.setOnClickListener(view -> {
            startActivity(new Intent(MainActivity.this, FormularioAlunoActivity.class));
        });
        configuraAdapter();
        configuraClickListView();
        //configuraLongClickListView();
        registerForContextMenu(lv);
        for (int i = 0; i < 20; i++) {
            dao.salva(new Aluno("Aluno" + i, "123", "email@email.com"));
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        atualizaListaAlunos();
    }

    private void atualizaListaAlunos() {
        adapter.clear();
        adapter.addAll(dao.todos());
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.act_main_menu, menu);

    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        Aluno aluno = adapter.getItem(info.position);
        if (item.getItemId() == R.id.menu_remover) {
            remove(aluno);
        } else if (item.getItemId() == R.id.menu_editar) {
            editarAluno(aluno);
        }
        return super.onContextItemSelected(item);
    }

    private void configuraAdapter() {
        adapter = new ArrayAdapter<Aluno>(this, android.R.layout.simple_list_item_1);
        lv.setAdapter(adapter);
    }

    private void configuraClickListView() {
        lv.setOnItemClickListener((adapterView, view, posicao, id) -> {
            Aluno aluno = (Aluno) adapterView.getItemAtPosition(posicao);
            editarAluno(aluno);
        });
    }

    private void configuraLongClickListView() {
        lv.setOnItemLongClickListener((adapterView, view, posicao, id) -> {
            Aluno escolhido = (Aluno) adapterView.getItemAtPosition(posicao);
            adapter.remove(escolhido);
            return false;
        });
    }

    private void remove(Aluno aluno) {
        adapter.remove(aluno);
        dao.removeAluno(aluno);
    }

    private void editarAluno(Aluno aluno) {
        Intent in = new Intent(MainActivity.this, FormularioAlunoActivity.class);
        in.putExtra(ConstantesActivities.CHAVE_ALUNO, aluno);
        startActivity(in);
    }
}