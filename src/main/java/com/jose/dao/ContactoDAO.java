/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.jose.dao;

import com.jose.model.Contacto;
import java.util.List;


public interface ContactoDAO {
    //Metodo que devuelve el listado de objetos
    public List<Contacto> findAll();
    // Metodo que inserta un onjeto en la BBDD
    public Contacto insert(Contacto objContacto);
    //Metodo que devuelde un objeto en base a su ID
    public Contacto findById(Integer id);
    //Metodo que modifica un objeto en la BBDD en base a su ID
    public Contacto updateById(Integer id, Contacto objContacto);
    //Metodo que elimina un objeto en la BDD en base a su ID
    public Boolean deleteById(Integer id);
}
