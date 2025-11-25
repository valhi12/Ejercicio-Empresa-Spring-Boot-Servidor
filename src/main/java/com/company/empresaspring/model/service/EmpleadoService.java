package com.company.empresaspring.model.service;

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

    public List<Empleado> findAll() {
        return empleadoRepository.findAll();
    }

    public Double findSalarioDeTablaNomina(String dni) {
        return empleadoRepository.findByDni(dni);
    }

    public List<Empleado> findByField(String fieldName, String fieldValue) {

        if ("dni".equalsIgnoreCase(fieldName)) {
            return empleadoRepository.findById(fieldValue).map(List::of).orElseGet(List::of);
        }

        if ("nombre".equalsIgnoreCase(fieldName)) {
            return empleadoRepository.findByNombreIgnoreCase(fieldValue);
        }

        return List.of();
    }
}