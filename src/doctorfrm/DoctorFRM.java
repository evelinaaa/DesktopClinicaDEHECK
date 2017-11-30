/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package doctorfrm;

import Controlador.MedicamentoBean;
import Modelo.MedicamentoDao;
import Vista.JFMedicamento;

/**
 *
 * @author javam
 */
public class DoctorFRM {
    

    public static void main(String[] args) {
       JFMedicamento vistaC = new JFMedicamento();   
       MedicamentoDao modeloC = new MedicamentoDao();
       MedicamentoBean controlador = new MedicamentoBean(vistaC, modeloC);
     
        
        vistaC.setVisible(true);
        vistaC.setLocationRelativeTo(null);
        
        
      
        
    }
    
}
