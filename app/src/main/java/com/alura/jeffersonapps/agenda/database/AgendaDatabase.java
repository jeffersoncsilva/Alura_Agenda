package com.alura.jeffersonapps.agenda.database;

import android.content.Context;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;
import com.alura.jeffersonapps.agenda.database.converter.ConversoCalendar;
import com.alura.jeffersonapps.agenda.database.dao.RoomAlunoDAO;
import com.alura.jeffersonapps.agenda.model.Aluno;

@Database(entities = {Aluno.class}, version = 3, exportSchema = false)
@TypeConverters({ConversoCalendar.class})
public abstract class AgendaDatabase extends RoomDatabase {

    private static AgendaDatabase _database = null;

    public abstract RoomAlunoDAO getRoomAlunoDAO();

    public static AgendaDatabase getInstance(Context context){
        if(_database == null)
            _database = Room.databaseBuilder(context, AgendaDatabase.class, "agenda.db")
                    .allowMainThreadQueries()
                    .addMigrations(AgendaMigrations.TODAS_MIGRATIONS)
                    .build();
        return _database;
    }

}