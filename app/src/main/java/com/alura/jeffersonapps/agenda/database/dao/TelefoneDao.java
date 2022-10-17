package com.alura.jeffersonapps.agenda.database.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.alura.jeffersonapps.agenda.model.Telefone;

import java.util.List;

@Dao
public interface TelefoneDao {

    //@Query("select * from Telefone t join Aluno a on t.alunoId = a.id where t.alunoId = :alunoId limit 1")
    @Query("select * from Telefone where alunoId = :alunoId limit 1")
    Telefone buscaPrimeiroTelefoneDoAluno(int alunoId);

    @Insert
    void salva(Telefone... telefones);

    @Query("select * from Telefone where alunoId = :id")
    List<Telefone> buscaTodosTelefonesDoAluno(int id);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void atualiza(Telefone[] telefones);
}
