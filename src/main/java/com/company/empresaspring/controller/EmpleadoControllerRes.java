package com.company.empresaspring.controller;

import com.company.empresaspring.excepciones.RepositoryException;
import com.company.empresaspring.model.entify.Empleado;
import com.company.empresaspring.model.service.EmpleadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/empresa")
public class EmpleadoControllerRes {

    @Autowired
    private EmpleadoService empleadoService;

    @GetMapping
    public ResponseEntity<List<Empleado>> findAll() {
        List<Empleado> empleados = empleadoService.findAll();

        if (empleados.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return new ResponseEntity<>(empleados, HttpStatus.OK);
    }

    @GetMapping("/listar")
    public List<Empleado> listar() {
        return empleadoService.findAll();
    }

    @GetMapping("/buscar")
    public ResponseEntity<?> buscarEmpleados(
            @RequestParam(required = false) String dni,
            @RequestParam(required = false) String nombre,
            @RequestParam(required = false) Integer categoria,
            @RequestParam(required = false) Character sexo,
            @RequestParam(required = false) Integer anyos
    ) {
        try {
            List<Empleado> empleados = empleadoService.buscarEmpleados(dni, nombre, categoria, sexo, anyos);

            if (empleados.isEmpty()) {
                return ResponseEntity.notFound().build();
            }

            return new ResponseEntity<>(empleados, HttpStatus.OK);

        } catch (RepositoryException e) {
            return new ResponseEntity<>("Error interno al buscar empleados: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @GetMapping("/salario/{dni}")
    public ResponseEntity<Double> obtenerSalario(@PathVariable String dni) {
        Optional<Empleado> opt = empleadoService.findByDni(dni);
        if (opt.isPresent()) {
            return ResponseEntity.ok(empleadoService.sueldo(opt.get()));
        }
        return ResponseEntity.notFound().build();
    }


    @PostMapping("/calcular-sueldo")
    public ResponseEntity<Double> calcularSueldo(@RequestBody Empleado empleado) {
        double sueldoCalculado = EmpleadoService.sueldo(empleado);
        return new ResponseEntity<>(sueldoCalculado, HttpStatus.OK);
    }
}