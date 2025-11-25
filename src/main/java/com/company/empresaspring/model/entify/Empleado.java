package com.company.empresaspring.model.entify;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


@Entity
@Table(name = "empleado")
public class Empleado extends Persona {

    @Id
    private Long id;
    private int categoria;
    private int anyos;

    public Empleado() {
        super();
    }


    public Empleado(String nombre, char sexo, String dni, int categoria, int anyos) {
        super(nombre, sexo, dni);
        this.categoria = categoria;
        this.anyos = anyos;
    }


    public int incrAnyo(){
        return this.anyos++;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getCategoria() {
        return categoria;
    }

    public void setCategoria(int categoria) {
        this.categoria = categoria;
    }

    public int getAnyos() {
        return anyos;
    }

    public void setAnyos(int anyos) {
        this.anyos = anyos;
    }

    @Override
    public String toString(){
        return "Empleado: " + "Nombre: " + getNombre() + " dni: " + getDni() + " sexo: " + getSexo() + " categoria: "+ categoria + " años: " + anyos;
    }
}