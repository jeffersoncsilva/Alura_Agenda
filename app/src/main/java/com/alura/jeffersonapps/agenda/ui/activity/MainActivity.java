package com.alura.jeffersonapps.agenda.ui.activity;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import com.alura.jeffersonapps.agenda.R;
import com.alura.jeffersonapps.agenda.dao.AlunoDao;
import com.alura.jeffersonapps.agenda.database.AgendaDatabase;
import com.alura.jeffersonapps.agenda.database.dao.RoomAlunoDAO;
import com.alura.jeffersonapps.agenda.model.Aluno;
import com.alura.jeffersonapps.agenda.ui.adapter.ListaAlunosAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;


public class MainActivity extends AppCompatActivity {
    private ListView lv;
    //private final AlunoDao dao = new AlunoDao();
    private RoomAlunoDAO dao;
    private ListaAlunosAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FloatingActionButton btn = findViewById(R.id.floatingActionButton);
        dao = AgendaDatabase.getInstance(this).getRoomAlunoDAO();
        lv = findViewById(R.id.lstAlunos);
        btn.setOnClickListener(view -> startActivity(new Intent(MainActivity.this, FormularioAlunoActivity.class)));
        configuraAdapter();
        configuraClickListView();
        registerForContextMenu(lv);
    }

    @Override
    protected void onResume() {
        super.onResume();
        atualizaListaAlunos();
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
            alertDialogRemoveAluno(aluno);
        } else if (item.getItemId() == R.id.menu_editar) {
            editarAluno(aluno);
        }
        return super.onContextItemSelected(item);
    }

    private void atualizaListaAlunos() {
        adapter.atualizaAlunos(dao.todos());
    }

    private void configuraAdapter() {
        this.adapter = new ListaAlunosAdapter(this);
        lv.setAdapter(adapter);
    }

    private void configuraClickListView() {
        lv.setOnItemClickListener((adapterView, view, posicao, id) -> {
            Aluno aluno = (Aluno) adapterView.getItemAtPosition(posicao);
            editarAluno(aluno);
        });
    }

    private void remove(Aluno aluno) {
        dao.remove(aluno);
        adapter.remove(aluno);
    }

    private void editarAluno(Aluno aluno) {
        Intent in = new Intent(MainActivity.this, FormularioAlunoActivity.class);
        in.putExtra(ConstantesActivities.CHAVE_ALUNO, aluno);
        startActivity(in);
    }

    private void alertDialogRemoveAluno(Aluno aluno){
        new AlertDialog.Builder(this)
                .setTitle("Removendo Aluno")
                .setPositiveButton("Sim", (dialogInterface, i) -> remove(aluno))
                .setNegativeButton("Não", null)
                .show();
    }
}