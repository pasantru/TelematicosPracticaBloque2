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
@WebServlet(urlPatterns = {"/ServerletLogin"})
public class Login extends HttpServlet {

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {  
  
        response.setContentType("text/html");  
        PrintWriter out = response.getWriter();  

        String n=request.getParameter("user");  
        String p=request.getParameter("passwd");  

        if(LoginDB.validate(n, p)){  
            RequestDispatcher rd = request.getRequestDispatcher("BlogServerlet");  
            rd.forward(request,response);  
        }else{
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

            out.println("<div class=\"side\">");
            out.println("<fieldset style=\"display: inline-block\" align=\"left\">");
            out.println("<div class=\"spacer\">");
            out.println("<form method=\"post\" action=\"ServerletLogin\" id=\"login_login-main\" class=\"login-form login-form-side\">");
            out.println("<input type=\"hidden\" name=\"op\" value=\"login-main\">");
            out.println("Username:<br>");
            out.println("<input name=\"user\" placeholder=\"username\" type=\"text\" maxlength=\"20\" tabindex=\"1\"><br>");
            out.println("Password:<br>");
            out.println("<input name=\"passwd\" placeholder=\"password\" type=\"password\" tabindex=\"1\"><br>");
            //Error message 
            out.println("<div style=\"color:red\" class=\"Error\">Sorry username or password error</div>");
            out.println("<div class=\"submit\">");
            out.println("<input type=\"submit\" value=\"login\"/>");
            out.println("<a href=\"./signup.html\" class=\"registrarse\">Registrarse?</a>");
            out.println("</div>");
            out.println("<div class=\"clear\"></div>");
            out.println("</form>");
            out.println("</div>");
            out.println("</fieldset>");
            out.println("</div>");
            out.println("</body>");
            out.println("</html>");  
        }  

        out.close();  
    } 
}
