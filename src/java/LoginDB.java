/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSetMetaData;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
/**
 *
 * @author pasantru
 */
public class LoginDB {
    public static boolean validate(String name,String pass){  
        boolean status=false;  
        String dbURL = "jdbc:derby://localhost:1527/Telematiquillos;create=true;user=telematicos;password=telematicos";
        try{  
            Class.forName("org.apache.derby.jdbc.ClientDriver").newInstance();  
            Connection con = DriverManager.getConnection(dbURL);  

            PreparedStatement ps=con.prepareStatement("select * from Telematicos.userreg where name=? and pass=?");  
            ps.setString(1,name);  
            ps.setString(2,pass);  

            ResultSet rs=ps.executeQuery();  
            status=rs.next();  

        }catch(Exception e){System.out.println(e);}  
        return status;  
    } 
    
    public static boolean signup(String name,String pass){
        boolean status=false;  
        String dbURL = "jdbc:derby://localhost:1527/Telematiquillos;create=true;user=telematicos;password=telematicos";
        
        try{  
            Class.forName("org.apache.derby.jdbc.ClientDriver").newInstance();  
            Connection con = DriverManager.getConnection(dbURL); 
            PreparedStatement ps=con.prepareStatement("insert into Telematicos.userreg values (?, ?)");  
            ps.setString(1,name);  
            ps.setString(2,pass); 
            int i=ps.executeUpdate();  
            if(i>0)  status = true;
        }catch(Exception e){System.out.println(e);}  
        
        return status;  
    }
    public static boolean createEntry(String title, String content, String username){
        boolean status=false;  
        String dbURL = "jdbc:derby://localhost:1527/Telematiquillos;create=true;user=telematicos;password=telematicos";

        try{ 
            Class.forName("org.apache.derby.jdbc.ClientDriver").newInstance();  
            Connection con = DriverManager.getConnection(dbURL);
            PreparedStatement ps = con.prepareStatement("INSERT INTO TELEMATICOS.BLOG VALUES (?, ?, ?, ?, ?)"); 
            Statement stmt3 = con.createStatement();
            ResultSet rs3 = stmt3.executeQuery("SELECT COUNT(*) FROM TELEMATICOS.BLOG");
            int j = rs3.getInt(1);
            ps.setInt(1, j+1); 
            ps.setString(2, title);
            ps.setString(3, content);
            ps.setString(4, username);
            ps.setDate(5, new java.sql.Date(System.currentTimeMillis()));
            int i=ps.executeUpdate();  
            if(i>0)  status = true;
        }catch(Exception e){System.out.println(e);}  
        
        return status;
    }
}
