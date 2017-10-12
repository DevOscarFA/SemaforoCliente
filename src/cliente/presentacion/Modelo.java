package cliente.presentacion;

import cliente.logica.Bombilla;
import cliente.logica.Semaforo;
import java.awt.Canvas;
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
    private Graphics canvasGraphics, bufferGraphics;
    private Thread hiloDibujo;
    private BufferedImage dobleBuffer;
    int cantidadSemaforosL1;
    int cantidadSemaforosL2;
    Socket SocketConeccion;
    private ObjectOutputStream OutputStream;
    ArrayList<Semaforo> linea1;
    ArrayList<Semaforo> linea2;

    private boolean pintarSemaforos = false;

    ArrayList<Semaforo> semaforoL1;

    public Modelo() {
        hiloDibujo = new Thread(this, "principal");

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

        crearSemaforo();
        pintarSemaforos = true;
    }

    public void conectar() throws JSONException, IOException, ClassNotFoundException {

        String ip = getVista().getTxtIP().getText();
        int puertoString = Integer.parseInt(getVista().getTxtPuerto().getText());
        try {
            SocketConeccion = new Socket(InetAddress.getByName(ip), puertoString);
            OutputStream = new ObjectOutputStream(SocketConeccion.getOutputStream());

            JSONObject j = new JSONObject();

            j.put("numLineas", getVista().getCantidadSemaforosL1().getValue());
            j.put("numBombRojasFund", getVista().getCantidadSemaforosL1().getValue());
            j.put("numBombAmarillasFund", getVista().getCantidadSemaforosL1().getValue());
            j.put("numBombVerdesFund", getVista().getCantidadSemaforosL1().getValue());

            OutputStream.writeObject(j.toString());
            OutputStream.flush();
            ObjectInputStream ois = null;
            ois = new ObjectInputStream(SocketConeccion.getInputStream());

            System.out.println(ois.readObject().toString());

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    public void crearSemaforo() {
        cantidadSemaforosL1 = (int) getVista().getCantidadSemaforosL1().getValue();
        cantidadSemaforosL2 = (int) getVista().getCantidadSemaforosL2().getValue();
        int varXL1 = 0;
        int varXL2 = 0;
        this.linea1 = new ArrayList<Semaforo>();
        this.linea2 = new ArrayList<Semaforo>();
        for (int i = 0; i < cantidadSemaforosL1; i++) {

            Semaforo sem = new Semaforo(varXL1, 0);
            this.linea1.add(sem);
            varXL1 = varXL1 + 40;
        }

        for (int i = 0; i < cantidadSemaforosL2; i++) {
            this.linea2.add(new Semaforo(varXL2, 110));
            varXL2 = varXL2 + 40;
        }

    }

    @Override
    public void run() {
        while (true) {
            Dibujar();
        }
    }

    private boolean Dibujar() {
        if (pintarSemaforos == true) {
            for (int i = 0; i < linea1.size(); i++) {
                ArrayList<Bombilla> bombillas = linea1.get(i).getListaBombillas();
                for (int j = 0; j < bombillas.size(); j++) {
                    bufferGraphics.setColor(bombillas.get(j).getColorActivo());
                    bufferGraphics.fillOval(bombillas.get(j).getPosInicialX(),
                            bombillas.get(j).getPosInicialY(),
                            bombillas.get(j).getPosFinalX(),
                            bombillas.get(j).getPosFinalY());
                }
            }
            for (int i = 0; i < linea2.size(); i++) {
                ArrayList<Bombilla> bombillas = linea2.get(i).getListaBombillas();
                for (int j = 0; j < bombillas.size(); j++) {
                    bufferGraphics.setColor(bombillas.get(j).getColorActivo());
                    bufferGraphics.fillOval(bombillas.get(j).getPosInicialX(),
                            bombillas.get(j).getPosInicialY(),
                            bombillas.get(j).getPosFinalX(),
                            bombillas.get(j).getPosFinalY());

                }
            }
        }
        canvasGraphics.drawImage(dobleBuffer, 0, 0, getVista().getCanvas());
        return false;
    }

}
