package com.alura.jeffersonapps.agenda.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.alura.jeffersonapps.agenda.R;
import com.alura.jeffersonapps.agenda.asynctask.BuscaTodosTelefonesDoAlunoTask;
import com.alura.jeffersonapps.agenda.asynctask.EditaAlunoTask;
import com.alura.jeffersonapps.agenda.asynctask.SalvaAlunoTask;
import com.alura.jeffersonapps.agenda.database.AgendaDatabase;
import com.alura.jeffersonapps.agenda.database.dao.AlunoDAO;
import com.alura.jeffersonapps.agenda.database.dao.TelefoneDao;
import com.alura.jeffersonapps.agenda.databinding.ActivityFormularioAlunoBinding;
import com.alura.jeffersonapps.agenda.model.Aluno;
import com.alura.jeffersonapps.agenda.model.Telefone;
import com.alura.jeffersonapps.agenda.model.TipoTelefone;

import java.util.List;

public class FormularioAlunoActivity extends AppCompatActivity {
    public static final int ALUNO_FORA_EDICAO = -1;
    private AlunoDAO alunoDao;
    private TelefoneDao telefoneDao;
    private ActivityFormularioAlunoBinding binding;
    private int idAlunoEmEdicao = ALUNO_FORA_EDICAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityFormularioAlunoBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setTitle(getString(R.string.activity_formulario_aluno_titulo_adiciona));

        alunoDao = AgendaDatabase.getInstance(this).getAlunoDAO();
        telefoneDao = AgendaDatabase.getInstance(this).getTelefoneDao();

        defineAcaoBotaoSalvarAluno();
        carregaAluno();

    }

    @Override
    public boolean onCreateOptionsMenu(@NonNull Menu menu) {
        getMenuInflater().inflate(R.menu.act_form_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(ehOpcaoSalvarAluno(item)){
            salvaAluno();
        }
        return super.onOptionsItemSelected(item);
    }

    private boolean ehOpcaoSalvarAluno(MenuItem item) {
        return item.getItemId() == R.id.act_form_salvar;
    }

    private void carregaAluno() {
        Intent extras = getIntent();
        if (ehParaEditarAluno(extras)) {
            setTitle(getString(R.string.activity_formulario_aluno_titulo_editar));
            Aluno aluno = recuperaAlunoIntent(extras);
            preecheCamposComDadosDoAluno(aluno);
            carregaTelefonesDoAlunoEmEdicao(aluno);
            this.idAlunoEmEdicao = aluno.getId();
        }
    }

    private void carregaTelefonesDoAlunoEmEdicao(Aluno aluno){
        new BuscaTodosTelefonesDoAlunoTask(telefoneDao, aluno.getId(), telefones -> {
            preencheDadosTelefoneAluno(telefones);
        }).execute();
    }

    private void preencheDadosTelefoneAluno(List<Telefone> telefones) {
        for(Telefone telefone: telefones){
            if(ehTelefoneFixo(telefone))
                binding.edtTelefoneFixo.setText(telefone.getNumero());
            else
                binding.edtTelefoneCelular.setText(telefone.getNumero());
        }
    }

    private boolean ehTelefoneFixo(Telefone telefone) {
        return telefone.getTipo() == TipoTelefone.FIXO;
    }

    private void preecheCamposComDadosDoAluno(Aluno aluno) {
        binding.edtNome.setText(aluno.getNome());
        binding.edtSobreNome.setText(aluno.getSobrenome());
        binding.edtEMail.setText(aluno.getEmail());
    }

    private Aluno recuperaAlunoIntent(Intent extras) {
        return (Aluno) extras.getSerializableExtra(ConstantesActivities.CHAVE_ALUNO);
    }

    private boolean ehParaEditarAluno(Intent extras) {
        return extras != null && extras.hasExtra(ConstantesActivities.CHAVE_ALUNO);
    }

    private void salvaAluno(){
        Aluno aluno = pegaDadosAluno();
        Telefone[] telefones = criaTelefones();
        if(idAlunoEmEdicao > ALUNO_FORA_EDICAO) {
            aluno.setId(idAlunoEmEdicao);
            salvaEdicaoAluno(aluno, telefones);
        }
        else {
            salvaNovoAluno(aluno, telefones);
        }
    }

    private void salvaEdicaoAluno(Aluno aluno, Telefone[] telefones) {
        new EditaAlunoTask(alunoDao, aluno, telefones, telefoneDao, this::finish).execute();
    }

    private void salvaNovoAluno(Aluno aluno, Telefone[] telefones) {
        new SalvaAlunoTask(alunoDao, aluno, telefones, telefoneDao, this::finish).execute();
    }

    private Aluno pegaDadosAluno() {
        return new Aluno(binding.edtNome.getText().toString(),
                binding.edtSobreNome.getText().toString(),
                binding.edtEMail.getText().toString());
    }

    private Telefone[] criaTelefones(){
        Telefone[] fones = new Telefone[2];
        fones[0] = new Telefone(binding.edtTelefoneFixo.getText().toString(), TipoTelefone.FIXO);
        fones[1] = new Telefone(binding.edtTelefoneCelular.getText().toString(), TipoTelefone.CELULAR);
        return fones;
    }

    private void defineAcaoBotaoSalvarAluno() {
        binding.btnSalvar.setOnClickListener(view -> salvaAluno());
    }
}