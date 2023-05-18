package com.BillMyCode.app.controladores;

import com.BillMyCode.app.excepciones.MiException;
import com.BillMyCode.app.servicios.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * Método formDeveloper() retorna el HTML del formulario de carga de Developers
     * @return String formDev.html
     */
    @GetMapping("")
    public String index() {
        return "index";
    }
    @GetMapping("/formUser")
    public String formUser() {
        return "crear-cuenta.html";
    }
    @GetMapping("/cuentauser")
    public String  cuentauser() {
        return "crear-cuenta-user.html";
    }

    @PostMapping("/cuentauser")
    public String registrarDeveloper(@RequestParam MultipartFile archivo,
                                     @RequestParam String nombre,
                                     @RequestParam String apellido,
                                     @RequestParam String email,
                                     @RequestParam String password,
                                     @RequestParam("fechaNac") String fechaNacStr,
                                     @RequestParam Double salario,
                                     @RequestParam String seniority,
                                     @RequestParam String especialidad,
                                     @RequestParam String descripcion,
                                     @RequestParam String genero,
                                     @RequestParam String nacionalidad,
                                     @RequestParam(required = false) String comentario,
                                     @RequestParam(required = false) String empresas,
                                     @RequestParam(required = false) String contador,
                                     ModelMap model)
            throws MiException {

        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date fechaNac = new Date();
        try {
            fechaNac = format.parse(fechaNacStr);
        } catch (ParseException e) {
            model.put("error", "La fecha de nacimiento es inválida.");
            return "redirect:/formDev";
        }

        try {
            userService.createUser(archivo, nombre, apellido, email, genero, nacionalidad , password, fechaNac );
            model.put("exito", "el developer fue creado exitosamente");

        } catch (MiException e) {
            model.put("error", e.getMessage());
            return "crear-cuenta-user.html";
        }
        return "redirect:/cuentauser";
    }

}
