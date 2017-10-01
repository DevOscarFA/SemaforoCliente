package cliente.presentacion;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import javax.swing.JButton;

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
        }
    }

    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }

}
