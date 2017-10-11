package cliente.presentacion;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import org.json.JSONException;

/**
 * Controlador de la vista de semaforos
 */
public class Controlador implements ActionListener, MouseMotionListener {

    private final Vista vista;

    //Iniclaizamos la vista
    public Controlador(Vista miVista) {
        vista = miVista;

    }

    /**
     * Escuchamos los eventos de click sobre los botones
     * 
     * @param e 
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        JButton boton = (JButton) e.getSource();
        if (boton.equals(vista.getCrearSemaforos())) {
            System.out.println("crear semaforo");
            vista.getModelo().pintarSemaforos();
        }
        if (boton.equals(vista.getBtnConectar())) {
            System.out.println("conectar");
            try {
                vista.getModelo().conectar();
            } catch (JSONException ex) {
                Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }

}
