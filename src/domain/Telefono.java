package domain;

import java.io.Serializable;

public class Telefono implements Serializable {
    private static final long serialVersionUID = 1L;
    private int imei;
    private String modelo, marca;

    public Telefono(int imei, String modelo, String marca) {
        this.imei = imei;
        this.modelo = modelo;
        this.marca = marca;
    }

    public int getImei() {
        return imei;
    }

    public void setImei(int imei) {
        this.imei = imei;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    @Override
    public String toString() {
        return "Telefono{" +
                "imei=" + imei +
                ", modelo='" + modelo + '\'' +
                ", marca='" + marca + '\'' +
                '}';
    }
}
