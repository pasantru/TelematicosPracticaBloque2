/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author pasantru
 */
@WebServlet(urlPatterns = {"/BlogServerlet"})
public class BlogServerlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String dbURL = "jdbc:derby://localhost:1527/Telematiquillos;create=true;user=telematicos;password=telematicos";
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        
        out.println("<html>");
        out.println("<head>");
        out.println("<title>Blog telematiquillos - login</title>");
        out.println("<html xmlns=\"http://www.w3.org/1999/xhtml\"></html>");
        out.println("<meta charset=\"UTF-8\">");
        out.println("<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">");
        out.println("</head>");
        out.println("<body>");
        out.println("<ul class=\"tabmenu \">");
        out.println("<li class=\"selected\"><a href=\"./index.html\" class=\"choice\">Inicio</a></li>");
        out.println("<li><a href=\"./blogs.html\" class=\"choice\">Blogs</a></li>");
        out.println("<li><a href=\"./perfil.html\" class=\"choice\">Perfil</a></li>");
        out.println("</ul>");
        out.println("<table border=\"1\" width=\"80%\" align=\"center\">");
        out.println("<tbody>");
        
        try{  
            Class.forName("org.apache.derby.jdbc.ClientDriver").newInstance();  
            Connection con = DriverManager.getConnection(dbURL); 
            Statement stmt = con.createStatement();
            String query = "SELECT * FROM TELEMATICOS.BLOG ORDER BY DATE DESC";
            ResultSet rs = stmt.executeQuery(query);
            
            while (rs.next()) {
                int index = rs.getInt("INDEX");
                String title = rs.getString("TITLE");
                String content = rs.getString("CONTENT");
                String username = rs.getString("USERNAME");
                Date d = rs.getDate("DATE");
                out.println("<tr>");
                out.println("<td>");
                out.println("<a class=\"table-row\" href=\"./index.html\">");
                out.println("<div class=\"table-element\">");
                out.println("<h2>" + title + "</h2>");
                out.println("<p>" + content + "</p>");
                out.println("<div class=\"bottom-blog\">");
                out.println("<div class=\"right-blog\">");
                out.println("submitted on " + d + " by " + username);
                out.println("</div>");
                out.println("</div>");
                out.println("</div>");
                out.println("</a>");
                out.println("</td>");
                out.println("</tr>");
                
            }
            out.println("<tr>");
            out.println("<td>");
            out.println("<a href=\"./createEntry.html\"><input type=\"submit\" value=\"CreateEntry\"/></a>");
            out.println("</td>");
            out.println("</tr>");
            out.println("</tbody>");
            out.println("</table>");
            out.println("</body>");
            out.println("</html>");

            
        }catch(Exception e){System.out.println(e);} 
        
        out.close();
    }
}
