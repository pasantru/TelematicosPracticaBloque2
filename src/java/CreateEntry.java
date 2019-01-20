/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author pasantru
 */
@WebServlet(urlPatterns = {"/CreateEntry"})
public class CreateEntry extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html"); 
        String title =request.getParameter("blog-title");  
        String content =request.getParameter("blog-content");  

        LoginDB.createEntry(title, content, "username"); 
        RequestDispatcher rd = request.getRequestDispatcher("BlogServerlet");  
        rd.forward(request,response);
    }
}
