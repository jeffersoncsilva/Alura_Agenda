package com.alura.jeffersonapps.agenda.model;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;

@Entity
public class Aluno implements Serializable {

    @PrimaryKey(autoGenerate = true)
    private int id = 0;
    private String nome;
    private String telefone;
    private String email;
    private String sobrenome;
    private Calendar momentoDeCadastro = Calendar.getInstance();

    public Aluno(){

    }

    @Ignore
    public Aluno(String nome, String telefone, String email, String sobrenome) {
        this.nome = nome;
        this.telefone = telefone;
        this.email = email;
        this.sobrenome = sobrenome;
    }

    public Calendar getMomentoDeCadastro() {
        return momentoDeCadastro;
    }

    public void setMomentoDeCadastro(Calendar momentoDeCadastro) {
        this.momentoDeCadastro = momentoDeCadastro;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }

    public boolean temIdValido(){
        return id > 0;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public String getEmail() {
        return email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNomeCompleto(){
        return nome + " " + sobrenome;
    }

    public String dataFormatada(){
        return new SimpleDateFormat("dd/MM/yyyy").format(momentoDeCadastro.getTime());
    }

    @NonNull
    public String toString(){
        return "Nome: " + nome + " - Email: " + email;
    }
}
