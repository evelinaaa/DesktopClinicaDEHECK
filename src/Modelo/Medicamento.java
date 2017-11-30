package Modelo;

/**
 *
 * @author erick osoy
 */
public class Medicamento {
    private int idMedicamento;
    private String Nombre;
    private String Descripcion;
    private String FechaVencimiento;
    private int Proveedor_idProveedor;
    private int Inventario_idInventario;
    
    public Medicamento(){
        this.idMedicamento=0;
        this.Nombre="";
        this.Descripcion="";
        this.FechaVencimiento="";
        this.Proveedor_idProveedor=0;
        this.Inventario_idInventario=0;
    }

    public int getIdMedicamento() {
        return idMedicamento;
    }

    public void setIdMedicamento(int idMedicamento) {
        this.idMedicamento = idMedicamento;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    public String getDescripcion() {
        return Descripcion;
    }

    public void setDescripcion(String Descripcion) {
        this.Descripcion = Descripcion;
    }

    public String getFechaVencimiento() {
        return FechaVencimiento;
    }

    public void setFechaVencimiento(String FechaVencimiento) {
        this.FechaVencimiento = FechaVencimiento;
    }

    public int getProveedor_idProveedor() {
        return Proveedor_idProveedor;
    }

    public void setProveedor_idProveedor(int Proveedor_idProveedor) {
        this.Proveedor_idProveedor = Proveedor_idProveedor;
    }

    public int getInventario_idInventario() {
        return Inventario_idInventario;
    }

    public void setInventario_idInventario(int Inventario_idInventario) {
        this.Inventario_idInventario = Inventario_idInventario;
    }
    
    
}
