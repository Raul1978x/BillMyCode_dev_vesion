package com.BillMyCode.app.controladores;

import com.BillMyCode.app.entidades.Developer;
import com.BillMyCode.app.excepciones.MiException;
import com.BillMyCode.app.servicios.DeveloperService;
import com.BillMyCode.app.servicios.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api")
public class DeveloperRestControlador {

    @Autowired
    private DeveloperService servicio;

    @Autowired
    private ImageService imageService;

    @GetMapping("/developers")
    public ResponseEntity<List<Developer>> listaDevelopers() {
        return ResponseEntity.ok(servicio.listDevelopers());
    }

    @GetMapping("/developers/{id}")
    public Developer developerById(@PathVariable  Long id) {
        return servicio.seachDeveloperById(id);
    }

    @PostMapping("/developers")
    public void registrarDeveloper(@RequestBody Developer developer) throws MiException {
        MultipartFile archivo = (MultipartFile) developer.getImage();

        servicio.createDeveloper( archivo, developer.getNombre(), developer.getApellido(), developer.getEmail(),
                developer.getPassword(), developer.getFechaNacimiento(), developer.getSalario(), developer.getSeniority(),
                developer.getEspecialidad(), developer.getNacionalidad(), developer.getSexo(), developer.getDescripcion(), developer.getComentario(), developer.getEmpresaNombre(),
                developer.getContadorNombre());

    }

    @DeleteMapping("/developers/{id}")
    public void eliminarDeveloper(@PathVariable Long id){
        servicio.deleteDeveloperById(id);
    }


   /* public ResponseEntity<Developer> cargarDeveloper(@RequestBody Developer developer, @RequestPart MultipartFile archivo)
            throws MiException {
        developer = servicio.crearDeveloper(archivo, developer);
        imagenServicio.guardar((MultipartFile) archivo);
        return ResponseEntity.ok(developer);
    }*/

    @GetMapping("/developer/{seniority}")
    public List<Developer> getDevelopersBySeniority(@PathVariable String seniority) {
        return servicio.getDevelopersBySeniority(seniority);
    }

}
