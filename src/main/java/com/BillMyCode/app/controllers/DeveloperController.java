package com.BillMyCode.app.controllers;

import com.BillMyCode.app.entities.Comment;
import com.BillMyCode.app.entities.Company;
import com.BillMyCode.app.entities.User;
import com.BillMyCode.app.exceptions.MiException;
import com.BillMyCode.app.services.DeveloperService;
import com.BillMyCode.app.services.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("")
public class DeveloperController {

    @Autowired
    private DeveloperService developerService;

    @Autowired

    private ImageService imageService;

    @GetMapping("/cuentadeveloper")
    public String  cuentadeveloper() {
        return "crear-cuenta-desarrollador.html";
    }

    @PostMapping("/cuentaDeveloper")
    public String registrarDeveloper(@RequestParam User user,
                                     @RequestParam(required = false)Double salario,
                                     @RequestParam(required = false) String seniority,
                                     @RequestParam(required = false) String especialidad,
                                     @RequestParam(required = false) String descripcion,
                                     @RequestParam(required = false) Comment comment,
                                     ModelMap model)
            throws MiException {

        try {
            developerService.createDeveloper( user, salario, seniority, especialidad, descripcion, comment);
            model.put("exito", "el developer fue creado exitosamente");

        } catch (MiException e) {
            model.put("error", e.getMessage());
            return "crear-cuenta-desarrollo.html";
        }
        return "redirect:/cuentauser";
    }

    /**
     * Método getDevelopersBySeniority(seniority) retorna el o los Developer/s segun el grado de seniority buscado y
     * luego con el String devuelto linkea con el HTML especificado
     * @param seniority
     * @return String "developers.html"
     */
    @GetMapping("/developer/{seniority}")
    public String getDevelopersBySeniority(@PathVariable String seniority) {
                developerService.getDevelopersBySeniority(seniority);
        return "developers"; //vista de la lista de developer donde se cumple la query
    }
}
