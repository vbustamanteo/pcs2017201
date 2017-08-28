/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.fisi;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author vhbo
 */
public class inicio extends HttpServlet {

    int cuenta;
    FileDao dao;

    public inicio() {
        super();
    }
    
    @Override
    public void init() throws ServletException {
        dao = new FileDao();
        try {
            cuenta = dao.getCount();
        } catch (Exception e) {
            System.out.println("Error en el contador de visitas " + e.getMessage());
            throw new ServletException("Error en el contador de visitas " + e.getMessage());
        }
    }

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Set a cookie for the user, so that the counter does not increate 
        // everytime the user press refresh 
        HttpSession session = request.getSession(true);
        // Set the session valid for 5 secs 
        session.setMaxInactiveInterval(5);
        response.setContentType("text/plain;charset=UTF-8");
        response.setContentType("text/plain");
        PrintWriter out = response.getWriter();
        if (session.isNew()) {
            cuenta++;
        }
        out.println("Este sitio ha sido visitado  " + cuenta + " veces.");
    }

    public void destroy() {
        super.destroy();
        try {
            dao.save(cuenta);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
