package com.company.empresaspring.model.entify;

import jakarta.persistence.MappedSuperclass;


@MappedSuperclass
public class Persona {

    protected String nombre;
    protected String dni;
    protected char sexo;

    public Persona() {
    }

    public Persona(String nombre, char sexo, String dni) {
        this.nombre = nombre;
        this.sexo = sexo;
        this.dni = dni;
    }

    public Persona(String nombre, char sexo) {
        this.nombre = nombre;
        this.sexo = sexo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public char getSexo() {
        return sexo;
    }

    public void setSexo(char sexo) {
        this.sexo = sexo;
    }

    @Override
    public String toString(){
        return "Nombre: " + nombre + " dni: " + dni;
    }
}