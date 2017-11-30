package Controlador;

import Modelo.MedicamentoDao;
import Vista.JFMedicamento;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author erick osoy
 */
public class MedicamentoBean implements ActionListener, KeyListener {

    JFMedicamento vistaCrud = new JFMedicamento();
    MedicamentoDao modeloCrud = new MedicamentoDao();

    public MedicamentoBean(JFMedicamento vistaCrud, MedicamentoDao modeloCrud) {
        this.modeloCrud = modeloCrud;
        this.vistaCrud = vistaCrud;
        this.vistaCrud.btnRegistrar.addActionListener(this);
        this.vistaCrud.btnListar.addActionListener(this);
        this.vistaCrud.btnEditar.addActionListener(this);
        this.vistaCrud.btnEdit.addActionListener(this);
        this.vistaCrud.btnEliminar.addActionListener(this);
        this.vistaCrud.txtCodigo.addKeyListener(this);
        this.vistaCrud.txtNombre.addKeyListener(this);
        this.vistaCrud.txtDescripcion.addKeyListener(this);
        this.vistaCrud.txtFecha.addKeyListener(this);
        this.vistaCrud.txtProveedor.addKeyListener(this);
        this.vistaCrud.txtInventario.addKeyListener(this);
        this.vistaCrud.txtBusqueda.addKeyListener(this);

    }

    public void InicializarCrud() {

    }

    public void LlenarTabla(JTable tablaD) {
        DefaultTableModel modeloT = new DefaultTableModel();
        tablaD.setModel(modeloT);

        modeloT.addColumn("idMedicamento");
        modeloT.addColumn("Nombre");
        modeloT.addColumn("Descripcion");
        modeloT.addColumn("FechaVencimiento");
        modeloT.addColumn("Proveedor");
        modeloT.addColumn("Inventario");

        Object[] columna = new Object[6];

        int numRegistros = modeloCrud.listMedicamento().size();

        for (int i = 0; i < numRegistros; i++) {
            columna[0] = modeloCrud.listMedicamento().get(i).getIdMedicamento();
            columna[1] = modeloCrud.listMedicamento().get(i).getNombre();
            columna[2] = modeloCrud.listMedicamento().get(i).getDescripcion();
            columna[3] = modeloCrud.listMedicamento().get(i).getFechaVencimiento();
            columna[4] = modeloCrud.listMedicamento().get(i).getProveedor_idProveedor();
            columna[5] = modeloCrud.listMedicamento().get(i).getInventario_idInventario();
            modeloT.addRow(columna);
        }
    }

    public void Limpiar() {
        vistaCrud.txtCodigo.setText("");
        vistaCrud.txtCodigo.setEditable(true);
        vistaCrud.txtNombre.setText("");
        vistaCrud.txtDescripcion.setText("");
        vistaCrud.txtFecha.setText("");
        vistaCrud.txtProveedor.setText("");
        vistaCrud.txtInventario.setText("");
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == vistaCrud.btnRegistrar) {
            int codigo = Integer.parseInt(vistaCrud.txtCodigo.getText());
            String Nombre = vistaCrud.txtNombre.getText();
            String Descripcion = vistaCrud.txtDescripcion.getText();
            String Fecha = vistaCrud.txtFecha.getText();
            int Proveedor = Integer.parseInt(vistaCrud.txtProveedor.getText());
            int Inventario = Integer.parseInt(vistaCrud.txtInventario.getText());

            String rptaRegistro = modeloCrud.insertarMedicamento(codigo, Nombre, Descripcion, Fecha, Proveedor, Inventario);

            if (rptaRegistro != null) {
                JOptionPane.showMessageDialog(null, rptaRegistro);
            } else {
                JOptionPane.showMessageDialog(null, "ERROOOOOOOOR");
            }
            Limpiar();
            LlenarTabla(vistaCrud.jtDatos);
        }

        if (e.getSource() == vistaCrud.btnListar) {
            LlenarTabla(vistaCrud.jtDatos);
        }
        
        

        if (e.getSource() == vistaCrud.btnEditar) {
            int filaEditar = vistaCrud.jtDatos.getSelectedRow();
            int numFS = vistaCrud.jtDatos.getSelectedRowCount();

            if (filaEditar >= 0 && numFS == 1) {
                vistaCrud.txtCodigo.setText(String.valueOf(vistaCrud.jtDatos.getValueAt(filaEditar, 0)));
                vistaCrud.txtNombre.setText(String.valueOf(vistaCrud.jtDatos.getValueAt(filaEditar, 1)));
                vistaCrud.txtDescripcion.setText(String.valueOf(vistaCrud.jtDatos.getValueAt(filaEditar, 2)));
                vistaCrud.txtFecha.setText(String.valueOf(vistaCrud.jtDatos.getValueAt(filaEditar, 3)));
                vistaCrud.txtProveedor.setText(String.valueOf(vistaCrud.jtDatos.getValueAt(filaEditar, 4)));
                vistaCrud.txtInventario.setText(String.valueOf(vistaCrud.jtDatos.getValueAt(filaEditar, 5)));
                
                vistaCrud.txtCodigo.setEditable(false);
                vistaCrud.btnRegistrar.setEnabled(false);
                vistaCrud.btnEditar.setEnabled(false);
                vistaCrud.btnEliminar.setEnabled(false);
                vistaCrud.txtBusqueda.setEnabled(false);
            } else {
                JOptionPane.showMessageDialog(null, "Seleccione solo una fila");
            }

        }
        
        
        if (e.getSource() == vistaCrud.btnEdit) {
            int codigo = Integer.parseInt(vistaCrud.txtCodigo.getText());
            String Nombre = vistaCrud.txtNombre.getText();
            String Descripcion = vistaCrud.txtDescripcion.getText();
            String Fecha = vistaCrud.txtFecha.getText();
            int Proveedor = Integer.parseInt(vistaCrud.txtProveedor.getText());
            int Inventario = Integer.parseInt(vistaCrud.txtInventario.getText());

            int rptaEdit = modeloCrud.editarMedicamento(codigo, Nombre, Descripcion, Fecha, Proveedor, Inventario);

            if (rptaEdit > 0) {
                JOptionPane.showMessageDialog(null, "Editado con Exito");
            } else {
                JOptionPane.showMessageDialog(null, "ERROOR EN EDITAR BEAN");
            }
            Limpiar();
            LlenarTabla(vistaCrud.jtDatos);
            vistaCrud.btnRegistrar.setEnabled(true);
            vistaCrud.btnEditar.setEnabled(true);
            vistaCrud.btnEliminar.setEnabled(true);
            vistaCrud.txtBusqueda.setEnabled(true);
            vistaCrud.btnEdit.setEnabled(false);
        }
        
        

        if (e.getSource() == vistaCrud.btnEliminar) {
            int filaIncio = vistaCrud.jtDatos.getSelectedRow();
            int numFS = vistaCrud.jtDatos.getSelectedRowCount();
            ArrayList<String> listaCod = new ArrayList();
            String cod = "";
            if (filaIncio > 0) {
                for (int i = 0; i < numFS; i++) {
                    cod = String.valueOf(vistaCrud.jtDatos.getValueAt(i + filaIncio, 0));
                    listaCod.add(cod);
                }
                for (int i = 0; i < numFS; i++) {
                    int rptaUsuario = JOptionPane.showConfirmDialog(null, "Quiere eliminar el registro" + cod + "?");
                    if (rptaUsuario == 0) {
                        modeloCrud.eliminarMedicamento(Integer.parseInt(cod));
                    }
                }
                LlenarTabla(vistaCrud.jtDatos);
            } else {
                JOptionPane.showMessageDialog(null, "Seleccione una fila para eliminar");
            }
        }

    }
    
    

    @Override
    public void keyTyped(KeyEvent e) {
        if(e.getSource() == vistaCrud.txtCodigo || e.getSource() == vistaCrud.txtProveedor || e.getSource() == vistaCrud.txtInventario){
            char c = e.getKeyChar();
            if(c<'0' || c>'9'){
                e.consume();
            }
        }
        
        if(e.getSource() == vistaCrud.txtNombre || e.getSource() == vistaCrud.txtDescripcion){
            char c = e.getKeyChar();
            if((c<'a' || c>'z') && (c<'A' || c>'Z') && (c != (char)KeyEvent.VK_SPACE)){
                e.consume();
            }
        }
        
    }

    @Override
    public void keyPressed(KeyEvent e) {
        
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getSource() == vistaCrud.txtBusqueda) {
            int codigo = Integer.parseInt(vistaCrud.txtBusqueda.getText());
            DefaultTableModel modeloT = new DefaultTableModel();
            vistaCrud.jtDatos.setModel(modeloT);

            modeloT.addColumn("idMedicamento");
            modeloT.addColumn("Nombre");
            modeloT.addColumn("Descripcion");
            modeloT.addColumn("FechaVencimiento");
            modeloT.addColumn("Proveedor");
            modeloT.addColumn("Inventario");

            Object[] columna = new Object[6];

            int numRegistros = modeloCrud.buscarMedicamento(codigo).size();

            for (int i = 0; i < numRegistros; i++) {
                columna[0] = modeloCrud.buscarMedicamento(codigo).get(i).getIdMedicamento();
                columna[1] = modeloCrud.buscarMedicamento(codigo).get(i).getNombre();
                columna[2] = modeloCrud.buscarMedicamento(codigo).get(i).getDescripcion();
                columna[3] = modeloCrud.buscarMedicamento(codigo).get(i).getFechaVencimiento();
                columna[4] = modeloCrud.buscarMedicamento(codigo).get(i).getProveedor_idProveedor();
                columna[5] = modeloCrud.buscarMedicamento(codigo).get(i).getInventario_idInventario();
                modeloT.addRow(columna);
            }
        }
    }

}
