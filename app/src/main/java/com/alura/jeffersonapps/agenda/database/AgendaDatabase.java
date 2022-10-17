package com.alura.jeffersonapps.agenda.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.alura.jeffersonapps.agenda.database.converter.ConversoCalendar;
import com.alura.jeffersonapps.agenda.database.converter.ConversorTipoTelefone;
import com.alura.jeffersonapps.agenda.database.dao.AlunoDAO;
import com.alura.jeffersonapps.agenda.database.dao.TelefoneDao;
import com.alura.jeffersonapps.agenda.model.Aluno;
import com.alura.jeffersonapps.agenda.model.Telefone;

@Database(entities = {Aluno.class, Telefone.class}, version = 5, exportSchema = false)
@TypeConverters({ConversoCalendar.class, ConversorTipoTelefone.class})
public abstract class AgendaDatabase extends RoomDatabase {
    private static final String NOME_BANCO_DE_DADO = "agenda.db";
    private static AgendaDatabase _database = null;
    public abstract AlunoDAO getAlunoDAO();
    public abstract TelefoneDao getTelefoneDao();

    public static AgendaDatabase getInstance(Context context) {
        if (_database == null)
            _database = Room.databaseBuilder(context, AgendaDatabase.class, NOME_BANCO_DE_DADO)
                    .addMigrations(AgendaMigrations.TODAS_MIGRATIONS)
                    .build();
        return _database;
    }
}