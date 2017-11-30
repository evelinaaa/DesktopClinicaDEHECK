package Modelo;
import java.sql.*;
import java.util.ArrayList;

/**
 *
 * @author erick osoy
 */
public class MedicamentoDao {
    
    Conexion conexion;
    
    public MedicamentoDao(){
        conexion = new Conexion();
    }
    
    
    public String insertarMedicamento(int codigo, String Nombre, String Descripcion, String Fecha, int Proveedor, int Inventario){
        String rptaRegistro = null;
        try{
            Connection acceso = conexion.getConnection();
            PreparedStatement psa = acceso.prepareCall("{call sp_insertMedicamento(?,?,?,?,?,?)}");
            psa.setInt(1, codigo);
            psa.setString(2, Nombre);
            psa.setString(3, Descripcion);
            psa.setString(4, Fecha);
            psa.setInt(5, Proveedor);
            psa.setInt(6, Inventario);
            int numAfectadas = psa.executeUpdate();
            
            if(numAfectadas>0){
                rptaRegistro ="Registro Almacenado";
            }
        }catch(Exception ex){
            System.out.println("ERRRROR INSERTAR MEDICAMENTO" +ex);
        }
        return rptaRegistro;
    }
    
    
    
    
    
    
    
    public ArrayList<Medicamento> listMedicamento(){
        ArrayList listaMedicamento = new ArrayList();
        Medicamento med;
        try{
            Connection acceDB = conexion.getConnection();
            PreparedStatement psa = acceDB.prepareStatement("select * from Medicamento");
            ResultSet rs = psa.executeQuery();
            
            while(rs.next()){
                med = new Medicamento();
                med.setIdMedicamento(rs.getInt("idMedicamento"));
                med.setNombre(rs.getString("Nombre"));
                med.setDescripcion(rs.getString("Descripcion"));
                med.setFechaVencimiento(rs.getString("FechaVencimiento"));
                med.setProveedor_idProveedor(rs.getInt("Proveedor_idProveedor"));
                med.setInventario_idInventario(rs.getInt("Inventario_idInventario"));
                listaMedicamento.add(med);
            }
            
            
        }catch(Exception ex){
            
        }
        return listaMedicamento;
    }
    
    
    public int editarMedicamento (int codigo, String Nombre, String Descripcion, String Fecha, int Proveedor, int Inventario){
        int numFA = 0;
        
        try{
            Connection accedeDB = conexion.getConnection();
            CallableStatement cs = accedeDB.prepareCall("{call sp_modificarMedicamento(?,?,?,?,?,?)}");
            cs.setInt(1, codigo);
            cs.setString(2, Nombre);
            cs.setString(3, Descripcion);
            cs.setString(4, Fecha);
            cs.setInt(5, Proveedor);
            cs.setInt(6, Inventario);
            
            numFA = cs.executeUpdate();
        }catch(Exception ex){
            System.out.println("ERRRROR INSERTAR MEDICAMENTO" +ex);
        }
        return numFA;
    }
    
    
    
    
    public int eliminarMedicamento(int codigo){
        int numFA = 0;
        
        try{
            Connection accedeDB = conexion.getConnection();
            CallableStatement cs = accedeDB.prepareCall("{call sp_eliminarMedicamento(?)}");
            cs.setInt(1, codigo);
            
            numFA = cs.executeUpdate();
        }catch(Exception ex){
            
        }
        return numFA;
    }
    
    
    
    public ArrayList<Medicamento> buscarMedicamento(int codigo){
        ArrayList<Medicamento> listaMedicamento = new ArrayList();
        Medicamento med;
        
        try{
            Connection accedeDB = conexion.getConnection();
            CallableStatement cs = accedeDB.prepareCall("{call sp_buscarMedicamento(?)}");
            cs.setInt(1, codigo);
            ResultSet rs = cs.executeQuery();
            
            while(rs.next()){
                med = new Medicamento();
                med.setIdMedicamento(rs.getInt("idMedicamento"));
                med.setNombre(rs.getString("Nombre"));
                med.setDescripcion(rs.getString("Descripcion"));
                med.setFechaVencimiento(rs.getString("FechaVencimiento"));
                med.setProveedor_idProveedor(rs.getInt("Proveedor_idProveedor"));
                med.setInventario_idInventario(rs.getInt("Inventario_idInventario"));
                listaMedicamento.add(med);
            }
            
            
        }catch(Exception ex){
            
        }
        return listaMedicamento;
    }
    
    
}
