package cliente.presentacion;

import java.io.ObjectOutputStream;
import java.net.Socket;
import javax.swing.JOptionPane;
import static javax.swing.JOptionPane.ERROR_MESSAGE;

/**
 *
 * @author ce
 */
public class HiloEnviar implements Runnable {

    private ObjectOutputStream OutputStream;
    private String Mensaje;
    private Socket socket;

    public HiloEnviar(Socket SocketConeccion) {
        this.socket = socket;
        if (socket != null) {
            this.Mensaje = "Inicio";
        }
    }

    @Override
    public void run() {
        try {
            OutputStream = new ObjectOutputStream(socket.getOutputStream());
            OutputStream.flush();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());

        }
    }

    public void EnviarMensaje(String Mensaje) {
        try {
            System.out.println("Este Mensaje + " + Mensaje);
            this.Mensaje = Mensaje;
            OutputStream.writeObject(this.Mensaje);
            OutputStream.flush();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

}
