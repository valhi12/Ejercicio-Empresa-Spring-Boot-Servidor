package com.company.empresaspring.model.repository;

import com.company.empresaspring.model.entify.Empleado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmpleadoRepository extends JpaRepository<Empleado, String> {

    @Query("SELECT e FROM Empleado e WHERE e.dni = ?1 OR e.nombre = ?2 OR e.categoria = ?3 OR e.sexo = ?4 OR e.anyos = ?5")
    List<Empleado> findByParametros(
            String dni,
            String nombre,
            Integer categoria,
            Character sexo,
            Integer anyos
    );


    @Query(value = "SELECT sueldo FROM nomina WHERE Dni = ?1", nativeQuery = true)
    Double findSalarioByID(String dni);
}