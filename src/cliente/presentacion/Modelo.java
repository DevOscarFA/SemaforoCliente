package cliente.presentacion;

import java.awt.Graphics;

/**
 * Modelo de la vista de semaforo
 */
public class Modelo implements Runnable {

    private Vista Ventana;
    private Graphics vista;

    public Modelo() {

    }

    /**
     * Obtenemos la el objeto de la ventana
     * 
     * @return 
     */
    public Vista getVentanaJuego() {
        if (Ventana == null) {
            Ventana = new Vista(this);
        }
        return Ventana;
    }

    public void iniciar() {
        getVentanaJuego().setVisible(true);
    }

    @Override
    public void run() {
        Dibujar();
    }

    private void Dibujar() {
        System.out.println("Dobujar");
    }

}
