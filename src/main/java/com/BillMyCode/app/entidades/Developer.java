/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.BillMyCode.app.entidades;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 *
 * @author agust
 */
@Getter
@Setter
@Entity
public class Developer extends User {


    protected Double salario;
    protected String seniority;
    protected String especialidad;
    @Lob
    protected String descripcion;
    @OneToOne
    protected Comentario comentario;
    @ManyToMany
    protected List<Empresa> empresa;
    @OneToOne
    protected Contador contador;
    
}
