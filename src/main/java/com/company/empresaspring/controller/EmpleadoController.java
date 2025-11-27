package com.company.empresaspring.controller;

import com.company.empresaspring.excepciones.RepositoryException;
import com.company.empresaspring.model.entify.Empleado;
import com.company.empresaspring.model.service.EmpleadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/empresa")
public class EmpleadoController {

    @Autowired
    private EmpleadoService empleadoService;

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/listar")
    public String findAll(Model model) {
        List<Empleado> empleados = empleadoService.findAll();
        model.addAttribute("listaDeEmpleados", empleados);
        return "listarEmpleados";
    }

    @GetMapping({"/mostrarSalarioPorDni", "/buscarSalario"})
    public String mostrarFormularioSalario() {
        return "buscarSalario";
    }

    @GetMapping("/calcularSalario")
    public String calcularSalario(@RequestParam("dni") String dni, Model model) {
        Double salario = empleadoService.mostrarSalarioPorDni(dni);

        if (salario == null) {
            return "buscarSalario";
        }

        model.addAttribute("dniConsultado", dni);
        model.addAttribute("salarioCalculado", salario);
        return "mostrarSalario";
    }

    @GetMapping("/buscarEmpleado")
    public String mostrarBuscadorModificacion() {
        return "buscarEmpleado";
    }

    @GetMapping("/buscarEmpleados")
    public String procesarBusquedaModificacion(@RequestParam("filtro") String filtro, Model model) {
        try {
            List<Empleado> resultados = empleadoService.buscarEmpleados(filtro, null, null, null, null);

            if (resultados.isEmpty()) {
                resultados = empleadoService.buscarEmpleados(null, filtro, null, null, null);
            }

            if (resultados.isEmpty()) {
                return "buscarEmpleado";
            }

            Empleado empleadoEncontrado = resultados.get(0);
            model.addAttribute("empleadoAEditar", empleadoEncontrado);

            return "modificarEmpleado";

        } catch (RepositoryException e) {
            return "error";
        }
    }

    @PostMapping("/guardarCambios")
    public String guardarCambios(@ModelAttribute("empleadoAEditar") Empleado empleado) {
        empleadoService.guardar(empleado);
        return "redirect:/empresa/listar";
    }


    @GetMapping("/buscar")
    @ResponseBody
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
                return new ResponseEntity<>("No se encontraron empleados.", HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(empleados, HttpStatus.OK);
        } catch (RepositoryException e) {
            return new ResponseEntity<>("Error: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/calcular-sueldo")
    @ResponseBody
    public ResponseEntity<Double> calcularSueldo(@RequestBody Empleado empleado) {
        return new ResponseEntity<>(EmpleadoService.sueldo(empleado), HttpStatus.OK);
    }
}