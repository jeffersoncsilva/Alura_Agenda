package com.alura.jeffersonapps.agenda.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.alura.jeffersonapps.agenda.R;
import com.alura.jeffersonapps.agenda.dao.AlunoDao;
import com.alura.jeffersonapps.agenda.model.Aluno;

public class FormularioAlunoActivity extends AppCompatActivity {
    private EditText nome, telefone, email;
    private Button salvar;
    private Aluno aluno;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario_aluno);
        setTitle("Novo aluno");
        realizaBuscaDasViews();
        defineAcaoClickSalvarAluno();
        pegaExtrasDeEdicao();
    }

    @Override
    public boolean onCreateOptionsMenu(@NonNull Menu menu) {
        getMenuInflater().inflate(R.menu.act_form_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.act_form_salvar){
            salvarAluno();
        }
        return super.onOptionsItemSelected(item);
    }

    private void salvarAluno(){
        preencheDadosAluno();
        if(aluno.temIdValido())
            new AlunoDao().edita(aluno);
        else
            new AlunoDao().salva(aluno);
        finish();
    }

    private void defineAcaoClickSalvarAluno() {
        salvar.setOnClickListener(view -> salvarAluno());
    }

    private void preencheDadosAluno() {
        String _nome = nome.getText().toString();
        String _telefone = telefone.getText().toString();
        String _email = email.getText().toString();
        if(aluno == null){
            aluno = new Aluno(_nome, _telefone, _email);
        }else {
            aluno.setNome(_nome);
            aluno.setTelefone(_telefone);
            aluno.setEmail(_email);
        }
    }

    private void realizaBuscaDasViews() {
        salvar = findViewById(R.id.btnSalvar);
        nome = findViewById(R.id.edtNome);
        telefone = findViewById(R.id.edtTelefone);
        email = findViewById(R.id.edtEMail);
    }

    private void pegaExtrasDeEdicao() {
        Intent extras = getIntent();
        if (extras.hasExtra(ConstantesActivities.CHAVE_ALUNO)) {
            aluno = (Aluno) extras.getSerializableExtra(ConstantesActivities.CHAVE_ALUNO);
            nome.setText(aluno.getNome());
            telefone.setText(aluno.getTelefone());
            email.setText(aluno.getEmail());
            setTitle("Editando Aluno");
        }
    }
}