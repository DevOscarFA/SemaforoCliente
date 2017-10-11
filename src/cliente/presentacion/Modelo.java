package cliente.presentacion;

import cliente.logica.Semaforo;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.ArrayList;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Modelo de la vista de semaforo
 */
public class Modelo implements Runnable {

    private Vista Ventana;
    private Graphics vista, canvasGraphics, bufferGraphics;
    private Thread hiloDibujo, conectar;
    private BufferedImage dobleBuffer;
    int cantidadSemaforosL1;
    int cantidadSemaforosL2;
    Socket SocketConeccion;
    private ObjectOutputStream OutputStream;

    ArrayList<Semaforo> semaforoL1;

    public Modelo() {
        hiloDibujo = new Thread(this, "principal");
        semaforoL1 = new ArrayList<Semaforo>();
    }

    /**
     * Obtenemos la el objeto de la ventana
     *
     * @return
     */
    public Vista getVista() {
        if (Ventana == null) {
            Ventana = new Vista(this);
        }
        return Ventana;
    }

    public void iniciar() {
        Canvas canvasVista = getVista().getCanvas();
        dobleBuffer = new BufferedImage(canvasVista.getWidth(), canvasVista.getHeight(), BufferedImage.TYPE_INT_ARGB);
        canvasGraphics = canvasVista.getGraphics();
        bufferGraphics = dobleBuffer.getGraphics();
        getVista().setVisible(true);
        hiloDibujo.start();
    }

    public void pintarSemaforos() {
        cantidadSemaforosL1 = (int) getVista().getCantidadSemaforosL1().getValue();
        cantidadSemaforosL2 = (int) getVista().getCantidadSemaforosL2().getValue();
        System.out.println(cantidadSemaforosL1);
        System.out.println(cantidadSemaforosL2);

    }

    public void conectar() throws JSONException, IOException, ClassNotFoundException {
        String ip = getVista().getTxtIP().getText();
        int puertoString = Integer.parseInt(getVista().getTxtPuerto().getText());
        try {
            SocketConeccion = new Socket(InetAddress.getByName(ip), puertoString);
            OutputStream = new ObjectOutputStream(SocketConeccion.getOutputStream());
            
            JSONObject j = new JSONObject();
            
            j.put("numLineas", getVista().getCantidadSemaforosL1().getValue());
            j.put("numBombRojasFund",  getVista().getCantidadSemaforosL1().getValue());
            j.put("numBombAmarillasFund",  getVista().getCantidadSemaforosL1().getValue());
            j.put("numBombVerdesFund",  getVista().getCantidadSemaforosL1().getValue());
            
            OutputStream.writeObject(j.toString());
            OutputStream.flush();
            ObjectInputStream ois = null;
            ois = new ObjectInputStream(SocketConeccion.getInputStream());
            
            System.out.println(ois.readObject().toString());
            
            
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        
    }

    @Override
    public void run() {
        while (true) {
            Dibujar();
        }
    }

    

    private boolean Dibujar() {
        int varXL1 = 0;
        int varXL2 = 0;

        for (int i = 0; i < cantidadSemaforosL1; i++) {

            bufferGraphics.setColor(new Color(44, 39, 39));
            bufferGraphics.fillRect((varXL1 + 0), 0, 30, 90);

            bufferGraphics.setColor(new Color(225, 80, 80));
            bufferGraphics.fillOval((5 + varXL1), 5, 20, 20);
            bufferGraphics.setColor(new Color(210, 225, 80));
            bufferGraphics.fillOval((5 + varXL1), 35, 20, 20);
            bufferGraphics.setColor(new Color(51, 179, 76));
            bufferGraphics.fillOval((5 + varXL1), 65, 20, 20);

            varXL1 = varXL1 + 40;
        }

        for (int i = 0; i < cantidadSemaforosL2; i++) {

            bufferGraphics.setColor(new Color(44, 39, 39));
            bufferGraphics.fillRect((varXL2 + 0), 100, 30, 190);

            bufferGraphics.setColor(new Color(225, 80, 80));
            bufferGraphics.fillOval((5 + varXL2), 105, 20, 20);
            bufferGraphics.setColor(new Color(210, 225, 80));
            bufferGraphics.fillOval((5 + varXL2), 135, 20, 20);
            bufferGraphics.setColor(new Color(51, 179, 76));
            bufferGraphics.fillOval((5 + varXL2), 165, 20, 20);
            varXL2 = varXL2 + 40;
        }

        canvasGraphics.drawImage(dobleBuffer, 0, 0, getVista().getCanvas());
        return false;
    }

}
