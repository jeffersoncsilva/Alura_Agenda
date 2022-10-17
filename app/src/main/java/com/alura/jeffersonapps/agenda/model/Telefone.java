package com.alura.jeffersonapps.agenda.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(foreignKeys = {@ForeignKey(entity=Aluno.class, parentColumns="id", childColumns = "alunoId", onUpdate=ForeignKey.CASCADE, onDelete = ForeignKey.CASCADE)})
public class Telefone {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String numero;
    private TipoTelefone tipo;

    @ColumnInfo(name="alunoId")
    private int alunoId;

    public Telefone(){ }

    public Telefone(String fixo, TipoTelefone tipo) {
        this.numero = fixo;
        this.tipo = tipo;
    }

    public int getAlunoId() {
        return alunoId;
    }

    public void setAlunoId(int alunoId) {
        this.alunoId = alunoId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public TipoTelefone getTipo() {
        return tipo;
    }

    public void setTipo(TipoTelefone tipo) {
        this.tipo = tipo;
    }
}
