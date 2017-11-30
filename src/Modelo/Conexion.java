package Modelo;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {
    public Conexion(){
        
    }
    
    
    public Connection getConnection(){
        Connection con = null;
        
        try{
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/CLINICAMEDICA2", "root", "");
            System.out.println("Se a conectado a la base de datos");
        }catch(Exception ex){
            
        }
        return con;
    }
}
