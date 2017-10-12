/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cliente.logica;

import java.awt.Color;

/**
 *
 * @author ce
 */
public class Bombilla {

    private boolean funcionando;
    private Color colorActivo;
    private Color colorInactivo;
    private int posInicialX;
    private int posInicialY;
    private int posFinalX;
    private int posFinalY;

    Bombilla(int posIX, int posIY, int posFX, int posFY, Color color) {
        this.posInicialX = posIX;
        this.posInicialY = posIY;
        this.posFinalX = posFX;
        this.posFinalY = posFY;
        this.colorActivo = color;
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

    public Color getColorInactivo() {
        return colorInactivo;
    }

    public void setColorInactivo(Color colorInactivo) {
        this.colorInactivo = colorInactivo;
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
