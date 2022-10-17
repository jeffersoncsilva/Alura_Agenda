package com.alura.jeffersonapps.agenda.database;

import androidx.annotation.NonNull;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.alura.jeffersonapps.agenda.model.TipoTelefone;

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

    /*private static final Migration MIGRATION_3_4 = new Migration(2, 3) {
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
    };*/
    private static final Migration MIGRATION_3_4 = new Migration(3, 4) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {
            // Cria uma nova tabela
            database.execSQL("CREATE TABLE IF NOT EXISTS `Aluno2` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `nome` TEXT, `telefoneCelular` TEXT, `email` TEXT, `sobrenome` TEXT, `telefoneFixo` TEXT, `momentoDeCadastro` INTEGER)");
            // Copia os dados da tabela antiga para a nova
            database.execSQL("insert into Aluno2 (id, nome, telefoneCelular, momentoDeCadastro, email) select id, nome, telefone, momentoDeCadastro, email from Aluno");
            // Remove a tabela antiga
            database.execSQL("drop table Aluno");
            // Renomear a tabela nova com o nome da tabela antiga.
            database.execSQL("alter table Aluno2 rename to Aluno");
        }
    };

    private static final Migration MIGRATION_4_5 = new Migration(4, 5) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {
            // Cria uma nova tabela
            database.execSQL("CREATE TABLE IF NOT EXISTS `Aluno2` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `nome` TEXT, `telefoneCelular` TEXT, `email` TEXT, `sobrenome` TEXT, `telefoneFixo` TEXT, `momentoDeCadastro` INTEGER)");
            // Copia os dados da tabela antiga para a nova
            database.execSQL("insert into Aluno2 (id, nome, telefoneCelular, momentoDeCadastro, email) select id, nome, telefone, momentoDeCadastro, email from Aluno");
            // Remove a tabela antiga
            database.execSQL("drop table Aluno");
            // Renomear a tabela nova com o nome da tabela antiga.
            database.execSQL("alter table Aluno2 rename to Aluno");
        }
    };

    private static final Migration MIGRATION_5_6 = new Migration(4, 5) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {
            // Cria uma nova tabela
            database.execSQL("CREATE TABLE IF NOT EXISTS `Aluno2` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `nome` TEXT, `email` TEXT, `sobrenome` TEXT, `momentoDeCadastro` INTEGER)");
            database.execSQL("CREATE TABLE IF NOT EXISTS `Telefone` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `numero` TEXT, `tipo` TEXT, `alunoId` INTEGER NOT NULL, FOREIGN KEY(`alunoId`) REFERENCES `Aluno`(`id`) ON UPDATE CASCADE ON DELETE CASCADE )");

            // Copia os dados da tabela antiga para a nova
            database.execSQL("insert into Aluno2 (id, nome, momentoDeCadastro, email) select id, nome, momentoDeCadastro, email from Aluno");
            database.execSQL("insert into Telefone (alunoId, numero) select id, telefoneFixo from Aluno");
            database.execSQL("update Telefone set tipo = ?", new TipoTelefone[] {TipoTelefone.FIXO});
            // Remove a tabela antiga
            database.execSQL("drop table Aluno");
            // Renomear a tabela nova com o nome da tabela antiga.
            database.execSQL("alter table Aluno2 rename to Aluno");
        }
    };

    public static final Migration[] TODAS_MIGRATIONS = {MIGRATION_1_2, MIGRATION_2_3, MIGRATION_3_4, MIGRATION_5_6};
}
