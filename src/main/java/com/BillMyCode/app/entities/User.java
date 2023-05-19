/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.BillMyCode.app.entities;

import com.BillMyCode.app.enumerations.Rol;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;


/**
 * @author agust
 */
@Getter
@Setter
@MappedSuperclass
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;
    protected String nombre;
    protected String apellido;
    protected String email;
    protected String genero;
    protected String nacionalidad;
    protected String password;
    protected String telefono;
    @Temporal(TemporalType.DATE)
    protected Date fechaNacimiento;
    @OneToOne
    protected Image image;
    @Enumerated(EnumType.STRING)
    protected Rol rol;

}
