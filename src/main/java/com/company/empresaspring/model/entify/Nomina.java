package com.company.empresaspring.model.entify;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


@Entity
@Table(name = "nomina")
public class Nomina {

    @Id
    private Integer id;
    private String sueldo;

    public Nomina() {
    }

    public Nomina(Integer id, String sueldo) {
        this.id = id;
        this.sueldo = sueldo;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSueldo() {
        return sueldo;
    }

    public void setSueldo(String sueldo) {
        this.sueldo = sueldo;
    }
}
