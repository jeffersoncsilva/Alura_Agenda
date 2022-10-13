package com.alura.jeffersonapps.agenda.database;

import androidx.annotation.NonNull;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

public class AgendaMigrations {

    private static final Migration MIGRATION_1_2 = new Migration(1, 2) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {
            database.execSQL("alter table aluno add column sobrenome text");
        }
    };

    private static final Migration MIGRATION_2_3 = new Migration(2, 3) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {
            database.execSQL("alter table aluno add column momentoDeCadastro integer");
        }
    };

    private static final Migration MIGRATION_3_4 = new Migration(2, 3) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {
            // Cria uma nova tabela
            database.execSQL("CREATE TABLE IF NOT EXISTS Aluno2 (id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, nome TEXT, telefone TEXT, email TEXT)");
            // Copia os dados da tabela antiga para a nova
            database.execSQL("insert into Aluno2 (id, nome, telefone, email) select id, nome, telefone, email from Aluno");
            // Remove a tabela antiga
            database.execSQL("drop table Aluno");
            // Renomear a tabela nova com o nome da tabela antiga.
            database.execSQL("alter table Aluno2 rename to Aluno");
        }
    };

    public static final Migration[] TODAS_MIGRATIONS = {MIGRATION_1_2, MIGRATION_2_3};
}
