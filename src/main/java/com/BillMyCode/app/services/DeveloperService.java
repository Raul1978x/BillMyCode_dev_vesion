package com.BillMyCode.app.services;

import com.BillMyCode.app.entities.*;
import com.BillMyCode.app.enumerations.Rol;
import com.BillMyCode.app.exceptions.MiException;
import com.BillMyCode.app.repositories.IDeveloperRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class DeveloperService {

    @Autowired
    private IDeveloperRepository repositorio;

    @Autowired
    private ImageService imageService;

    /**
     * Metodo listDevelopers() devuelve la lista de todos los Developers.
     * @return List<Developers>
     */
    @Transactional(readOnly = true)
    public List<Developer> listDevelopers() {
        return repositorio.findAll();
    }

    /**
     * Metodo searchDeveloperById(id) devuelve el Developer según su id.
     * @param id
     * @return Developer
     */
    @Transactional(readOnly = true)
    public Developer seachDeveloperById(Long id) {
        return repositorio.findById(id).get();
    }

    /**
     * Método deleteDeveloperById(id) borra Developer según su id.
     * @param id
     */
    @Transactional
    public void deleteDeveloperById(Long id) {
        repositorio.deleteById(id);
    }

    /**
     * Método createDeveloper(params) crea un nuevo Developer
     * @param salario
     * @param seniority
     * @param especialidad
     * @param descripcion
     * @param comment
     * @throws MiException
     */
   /* @Transactional
    public void createDeveloper( String nombre,
                                 String apellido,
                                 String sexo,
                                 String nacionalidad,
                                 Date fechaNac,
                                 String email,
                                 String password
    ) throws MiException {
        validate(nombre, apellido, email, password, fechaNac, sexo, nacionalidad);

        Developer developer = new Developer();

        developer.setNombre(nombre);
        developer.setApellido(apellido);
        developer.setSexo(sexo);
        developer.setNacionalidad(nacionalidad);
        developer.setFechaNacimiento(fechaNac);
        developer.setEmail(email);
        developer.setPassword(password);
        developer.setRol(Rol.DEV);

        repositorio.save(developer);
    }*/@Transactional
    public void createDeveloper(User user,
                                Double salario,
                                String seniority,
                                String especialidad,
                                String descripcion,
                                Comment comment) throws MiException {
        validate(descripcion, seniority, especialidad, comment, salario);

        Developer developer = new Developer();

        developer.setNombre(user.getNombre());
        developer.setApellido(user.getApellido());
        developer.setEmail(user.getEmail());
        developer.setPassword(user.getPassword());
        developer.setFechaNacimiento(user.getFechaNacimiento());
        developer.setImage(user.getImage());
        developer.setRol(Rol.DEV);

     /*   List<Empresa> empresas = new ArrayList<>();
        empresas.add(empresa);*/

        developer.setSalario(salario);
        developer.setSeniority(seniority);
        developer.setEspecialidad(especialidad);
        developer.setDescripcion(descripcion);
        developer.setComment(comment);
        /*developer.setEmpresa(empresa);*/

        repositorio.save(developer);
    }

    /**
     * getDeveloperBySeniority(seniority) busca la lista de todos los
     * Developers con el mismo grado de seniority
     * @param seniority
     * @return List<Developer>
     */
    @Transactional(readOnly = true)
    public List<Developer> getDevelopersBySeniority(String seniority) {
        return repositorio.searchBySeniority(seniority);
    }

    /**
     * validate(params) valida que los valores ingresados se cargen conforme a las
     * necesidades de la aplicacion
     * @param salario
     * @param seniority
     * @param especialidad
     * @param descripcion
     * @param comentario
     */
    public void validate(String salario,
                         String seniority,
                         String especialidad,
                         Comment descripcion,
                         Double comentario) {

        if (salario.isEmpty() || salario == "") {
            System.out.println("El salario no puede ser nulo o estar vacio");
        }
        if (seniority.isEmpty() || seniority == "") {
            System.out.println("La seniority no puede ser nula o estar vacia");
        }
        if (especialidad.isEmpty() || especialidad == "") {
            System.out.println("La especialidad no puede ser nula o estar vacia");
        }
        if (descripcion == null) {
            System.out.println("La descripcion no puede ser nula o estar vacia");
        }
        if (comentario == null) {
            System.out.println("El comentario no puede ser nulo");
        }
    }

}
