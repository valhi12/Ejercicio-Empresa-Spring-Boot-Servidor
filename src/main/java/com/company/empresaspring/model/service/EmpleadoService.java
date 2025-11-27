package com.company.empresaspring.model.service;

import com.company.empresaspring.excepciones.RepositoryException;
import com.company.empresaspring.model.entify.Empleado;
import com.company.empresaspring.model.repository.EmpleadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmpleadoService {

    @Autowired
    private EmpleadoRepository empleadoRepository;

    private static final int SUELDO_BASE[] = {50000, 70000, 90000, 110000, 130000, 150000,
            170000, 190000, 210000, 230000};

    public static double sueldo(Empleado e) {
        int categoria = e.getCategoria();
        return (SUELDO_BASE[categoria - 1] + 5000 * e.getAnyos());
    }

    public List<Empleado> findAll() {
        return empleadoRepository.findAll();
    }

    public Optional<Empleado> findByDni(String dni) {
        return empleadoRepository.findById(dni);
    }

    public List<Empleado> buscarEmpleados(String dni, String nombre, Integer categoria, Character sexo, Integer anyos) throws RepositoryException {
        return empleadoRepository.findByParametros(dni, nombre, categoria, sexo, anyos);
    }

    public Double mostrarSalarioPorDni(String dni) {
        return empleadoRepository.findSalarioByID(dni);
    }

    public void guardar(Empleado empleado) {
        empleadoRepository.save(empleado);
    }
}