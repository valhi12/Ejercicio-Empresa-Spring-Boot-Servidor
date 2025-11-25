package com.company.empresaspring.model.repository;

import com.company.empresaspring.model.entify.Empleado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmpleadoRepository extends JpaRepository<Empleado, String>  {
    @Query(value = "SELECT n.sueldo FROM nomina n WHERE n.dni = :dni", nativeQuery = true)
    Double findByDni(String dni);

    List<Empleado> findByNombreIgnoreCase(String nombre); //ignoramos mayusculas o minusculas

    Double findByField(String fieldName, String fieldValue);
}
