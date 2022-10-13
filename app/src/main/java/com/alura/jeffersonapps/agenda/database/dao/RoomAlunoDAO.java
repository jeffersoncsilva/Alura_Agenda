package com.alura.jeffersonapps.agenda.database.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.alura.jeffersonapps.agenda.model.Aluno;

import java.util.List;

@Dao
public interface RoomAlunoDAO {
    @Insert
    void salva(Aluno aluno);

    @Query("select * from aluno")
    List<Aluno> todos();

    @Delete
    void remove(Aluno aluno);

    @Update
    void edita(Aluno aluno);
}
