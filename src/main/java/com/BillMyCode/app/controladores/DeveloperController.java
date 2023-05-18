package com.BillMyCode.app.controladores;

import com.BillMyCode.app.entidades.Comentario;
import com.BillMyCode.app.entidades.Empresa;
import com.BillMyCode.app.entidades.User;
import com.BillMyCode.app.excepciones.MiException;
import com.BillMyCode.app.servicios.DeveloperService;
import com.BillMyCode.app.servicios.ImageService;
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

    @PostMapping("/cuentausuario")
    public String registrarDeveloper(@RequestParam User user,
                                     @RequestParam(required = false)Double salario,
                                     @RequestParam(required = false) String seniority,
                                     @RequestParam(required = false) String especialidad,
                                     @RequestParam(required = false) String descripcion,
                                     @RequestParam(required = false) Comentario comentario,
                                     @RequestParam(required = false) List<Empresa> empresas,
                                     ModelMap model)
            throws MiException {

        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date fechaNac = user.getFechaNacimiento();

        try {
            fechaNac = format.parse(fechaNacStr);
            developerService.createDeveloper( user, salario, seniority, especialidad, fechaNac, descripcion, comentario, empresas);
            model.put("exito", "el developer fue creado exitosamente");

        } catch (MiException e) {
            model.put("error", e.getMessage());
            return "crear-cuenta-desarrollo.html";
        } catch (ParseException e) {
            model.put("error", "La fecha de nacimiento es inválida.");
            return "redirect:/cuentauser";

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
