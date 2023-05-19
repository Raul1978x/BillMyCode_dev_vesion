package com.BillMyCode.app.controllers;

import com.BillMyCode.app.entities.Developer;
import com.BillMyCode.app.entities.User;
import com.BillMyCode.app.exceptions.MiException;
import com.BillMyCode.app.services.DeveloperService;
import com.BillMyCode.app.services.ImageService;
import com.BillMyCode.app.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api")
public class DeveloperRestControlador {

    @Autowired
    private UserService userService;
    @Autowired
    private DeveloperService developerService;

    @Autowired
    private ImageService imageService;

    @GetMapping("/developers")
    public ResponseEntity<List<Developer>> listaDevelopers() {
        return ResponseEntity.ok(developerService.listDevelopers());
    }

    @GetMapping("/developers/{id}")
    public Developer developerById(@PathVariable Long id) {
        return developerService.seachDeveloperById(id);
    }

    @PostMapping("/developers")
    public void registrarDeveloper(@RequestBody Developer developer) throws MiException {
        MultipartFile archivo = (MultipartFile) developer.getImage();

        User user = userService.createUser(archivo, developer.getNombre(), developer.getApellido(), developer.getEmail(),
                developer.getGenero(), developer.getNacionalidad(), developer.getPassword(), developer.getTelefono(), developer.getFechaNacimiento());
        developerService.createDeveloper(user, developer.getSalario(), developer.getSeniority(), developer.getEspecialidad(),
                developer.getDescripcion(), developer.getComment());

    }

    @DeleteMapping("/developers/{id}")
    public void eliminarDeveloper(@PathVariable Long id) {
        developerService.deleteDeveloperById(id);
    }


   /* public ResponseEntity<Developer> cargarDeveloper(@RequestBody Developer developer, @RequestPart MultipartFile archivo)
            throws MiException {
        developer = servicio.crearDeveloper(archivo, developer);
        imagenServicio.guardar((MultipartFile) archivo);
        return ResponseEntity.ok(developer);
    }*/

    @GetMapping("/developer/{seniority}")
    public List<Developer> getDevelopersBySeniority(@PathVariable String seniority) {
        return developerService.getDevelopersBySeniority(seniority);
    }

}
