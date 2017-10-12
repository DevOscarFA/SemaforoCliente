package cliente.logica;

import java.awt.Color;

/**
 * Modelo que comtrola las bombillas de los semaforos
 */
public class Bombilla {

    private boolean funcionando;
    private Color colorActivo;
    private boolean activo;
    private int posInicialX;
    private int posInicialY;
    private int posFinalX;
    private int posFinalY;

    //Creamos las bombillas con los valores enviados desde el semaforo
    Bombilla(int posIX, int posIY, int posFX, int posFY, Color color) {
        this.posInicialX = posIX;
        this.posInicialY = posIY;
        this.posFinalX = posFX;
        this.posFinalY = posFY;
        this.colorActivo = color;
        this.activo = true;
    }

    public boolean isFuncionando() {
        return funcionando;
    }

    public void setFuncionando(boolean funcionando) {
        this.funcionando = funcionando;
    }

    public Color getColorActivo() {
        return colorActivo;
    }

    public void setColorActivo(Color colorActivo) {
        this.colorActivo = colorActivo;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public int getPosInicialX() {
        return posInicialX;
    }

    public void setPosInicialX(int posInicialX) {
        this.posInicialX = posInicialX;
    }

    public int getPosInicialY() {
        return posInicialY;
    }

    public void setPosInicialY(int posInicialY) {
        this.posInicialY = posInicialY;
    }

    public int getPosFinalX() {
        return posFinalX;
    }

    public void setPosFinalX(int posFinalX) {
        this.posFinalX = posFinalX;
    }

    public int getPosFinalY() {
        return posFinalY;
    }

    public void setPosFinalY(int posFinalY) {
        this.posFinalY = posFinalY;
    }

}
