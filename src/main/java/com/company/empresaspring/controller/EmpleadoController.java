package com.company.empresaspring.controller;

//tienen que ser dos. otro controller res que maneja la api

import com.company.empresaspring.model.entify.Empleado;
import com.company.empresaspring.model.service.EmpleadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class EmpleadoController {

    @Autowired
    private EmpleadoService empleadoService;

    @GetMapping("/empleados/listado")
    public String findAllEmpleados(Model model) {
        try {
            List<Empleado> list = empleadoService.findAll();
            model.addAttribute("list", list);
            return "findAllEmpleados";
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("Error", "Error al acceder a la lista de empleados.");
            return "error";
        }
    }

    @GetMapping("/empleados/salario")
    public String mostrarSalarioForm() {
        return "mostrarSalarioDni";
    }

    @PostMapping("/empleados/salario")
    public String procesarSalario(@RequestParam("dni") String dni, Model model) {
        if (dni == null || dni.trim().isEmpty()) {
            model.addAttribute("Error", "Debe introducir un DNI.");
            return "mostrarSalarioDni";
        }

        try {
            Double salario = empleadoService.findSalarioByDni(dni);

            if (salario != null) {
                model.addAttribute("salario", salario);
                return "mostrarSalarioDni";
            } else {
                model.addAttribute("Error", "Empleado con DNI " + dni + " no encontrado.");
                return "error";
            }
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("Error", "Error al buscar el salario.");
            return "error";
        }
    }


    @GetMapping("/empleados/modificar")
    public String modificarDatosForm() {
        return "mostrarSalario";
    }

    @PostMapping("/empleados/modificar")
    public String procesarModificacion(
            @RequestParam("campoBusqueda") String campoBusqueda,
            @RequestParam("valorBusqueda") String valorBusqueda,
            Model model) {

        if (campoBusqueda == null || valorBusqueda == null || campoBusqueda.isEmpty() || valorBusqueda.isEmpty()) {
            model.addAttribute("Error", "Los campos de búsqueda no pueden estar vacíos.");
            return "mostrarSalario";
        }

        try {
            Double empleados = empleadoService.findByField(campoBusqueda, valorBusqueda);

            if (!empleados.isNaN()) {
                model.addAttribute("empleadosEncontrados", empleados);
                return "mostrarSalario";
            } else {
                model.addAttribute("Error", "No se encontraron empleados con ese criterio.");
                return "error";
            }
        } catch (Exception re) {
            re.printStackTrace();
            model.addAttribute("Error", "Se ha producido un problema al buscar en el repositorio.");
            return "error";
        }
    }
}