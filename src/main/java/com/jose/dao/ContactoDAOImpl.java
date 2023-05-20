/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jose.dao;

import com.jose.config.Conexion;
import com.jose.model.Contacto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author JuaN
 */
public class ContactoDAOImpl implements ContactoDAO {

    private Conexion objConexion;
    private Connection objConnection;

    public ContactoDAOImpl() {
        objConexion = new Conexion();
    }

    @Override
    public List<Contacto> findAll() {
        //Consulta SQL que se tiene que realizar
        String consulta = "SELECT * FROM contactos";
        // Listado de datos que se retorna
        List<Contacto> listado = new LinkedList<>();

        try {
            this.objConexion.conectar();
            this.objConnection = this.objConexion.getJdbcConnection();

            PreparedStatement prest = this.objConnection.prepareStatement(consulta);
            // Ejecuta la consulta
            ResultSet rs = prest.executeQuery(consulta);

            Contacto objContacto;

            while (rs.next()) {
                objContacto = new Contacto();
                objContacto.setId(rs.getInt("id"));
                objContacto.setNombre(rs.getString("nombre"));
                objContacto.setApellido(rs.getString("apellido"));
                objContacto.setEmail(rs.getString("email"));
                objContacto.setDescripcion(rs.getString("descripcion"));

                listado.add(objContacto);
            }

        } catch (Exception e) {
            System.out.println("Error en la consulta");
        }
        return listado;
    }

    @Override
    public Contacto insert(Contacto objContacto) {
        String consulta = "INSERT INTO contactos (nombre, apellido, email, descripcion) VALUES (?,?,?,?)";
        try{
            this.objConexion.conectar();
            this.objConnection = this.objConexion.getJdbcConnection();
            //Inicializacion de la consulta
            PreparedStatement  prest = this.objConnection.prepareStatement(consulta);
            //Agregar los datos dentro de la consulta
            prest.setString(1, objContacto.getNombre());
            prest.setString(2, objContacto.getApellido());
            prest.setString(3, objContacto.getEmail());
            prest.setString(4, objContacto.getDescripcion());
            
            int count = prest.executeUpdate();
            
            ResultSet rs = null;
            rs = prest.executeQuery("SELECT LAST_INSERT_ID()");
            
            int autoKey = -1;
            if(rs.next()){
                autoKey = rs.getInt("id");
                objContacto.setId(autoKey);
                System.out.println("Ultimo ID introducto " + autoKey);
            }else{
                System.out.println("No exite dato de ID");
            }
        }catch(Exception e){
            System.out.println("Error al insertar");
        }
        return objContacto;
    }

    @Override
    public Contacto findById(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Contacto updateById(Integer id, Contacto objContacto) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Boolean deleteById(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
