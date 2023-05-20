/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jose;

import com.jose.dao.ContactoDAO;
import com.jose.dao.ContactoDAOImpl;
import com.jose.model.Contacto;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;


@WebServlet(urlPatterns = "/ContactoServlet")
public class ContactoServlet extends HttpServlet {

    private ContactoDAO contactoDao;

    public ContactoServlet() {
        super();
        contactoDao = new ContactoDAOImpl();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
      this.procesarSolicitud(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.procesarSolicitud(req,resp);
    }

    protected void procesarSolicitud(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        switch (request.getParameter("action")) {
            case "list":
                // this.list(request, response);
                break;
            case "create":
                this.create(request, response);
                break;
            case "read":
                // this.read(request, response);
                break;
            case "update":
                //this.update(request, response);
                break;
            case "delete":
                // this.delete(request, response);
                break;
            case "showRegister":
                this.showRegister(request, response);
                break;
            case "index":
                this.index(request, response);
                break;
            default:
               this.index(request, response);
                break;
        }
        
    }
        private void index(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
            RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
            dispatcher.forward(request, response);
        }
        
        private void showRegister(HttpServletRequest request, HttpServletResponse response)
                throws ServletException, IOException{
            RequestDispatcher dispacther = request.getRequestDispatcher("/view/create.jsp");
            dispacther.forward(request, response);
        
        }
        
        private void create (HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException{
            String name = request.getParameter("nombre");
            String apellido = request.getParameter("apellido");
            String email = request.getParameter("email");
            String descrip = request.getParameter("descripcion");
            // Crear el objeto que se envia al BD
            Contacto objContacto = new Contacto();
            objContacto.setNombre(name);
             objContacto.setApellido(apellido);
              objContacto.setEmail(email);
               objContacto.setDescripcion(descrip);
            
            contactoDao.insert(objContacto);
            
            // Redireccionar al "index"
            
            this.index(request, response);
             
            
        }
}



