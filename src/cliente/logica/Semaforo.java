package cliente.logica;

import java.awt.Color;
import java.util.ArrayList;

/**
 *
 * @author ce
 */
public class Semaforo {

    ArrayList<Bombilla> listaBombillas;
    private int semsforoX;
    private int semsforoY;
    private boolean intermitente;

    public Semaforo(int posX, int posY) {
        int valIncremet = 0;

        listaBombillas = new ArrayList<Bombilla>();
        listaBombillas.add(new Bombilla(posX, posY, 20, 20, Color.RED));
        listaBombillas.add(new Bombilla(posX, (posY + 30), 20, 20, Color.YELLOW));
        listaBombillas.add(new Bombilla(posX, (posY + 60), 20, 20, Color.GREEN));

    }

    public ArrayList<Bombilla> getListaBombillas() {
        return listaBombillas;
    }

    public void setListaBombillas(ArrayList<Bombilla> listaBombillas) {
        this.listaBombillas = listaBombillas;
    }

    public int getSemsforoX() {
        return semsforoX;
    }

    public void setSemsforoX(int semsforoX) {
        this.semsforoX = semsforoX;
    }

    public int getSemsforoY() {
        return semsforoY;
    }

    public void setSemsforoY(int semsforoY) {
        this.semsforoY = semsforoY;
    }

    public boolean isIntermitente() {
        return intermitente;
    }

    public void setIntermitente(boolean intermitente) {
        this.intermitente = intermitente;
    }

}
