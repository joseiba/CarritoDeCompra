/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ComprasOnLine.modelosServicios;

import java.util.ArrayList;
import ComprasOnLine.modelosClases.TransaccionCab;
import ComprasOnLine.modelosClases.TransaccionDet;
import org.json.JSONException;
import ComprasOnLine.transacciones.JdbcTransaccionCabRepository;

/**
 *
 * @author Joseiba
 */
public class ModeloTransaccion {
    public void agregar(TransaccionCab transaccionCab) throws JSONException {

         JdbcTransaccionCabRepository transaccion = new JdbcTransaccionCabRepository();
         transaccion.add(transaccionCab);

    }
    public void agregarDet(ArrayList<TransaccionDet> listaTransaccionDet) throws JSONException {

         JdbcTransaccionCabRepository transaccion = new JdbcTransaccionCabRepository();
         transaccion.addDetalle(listaTransaccionDet);         
   }
}
