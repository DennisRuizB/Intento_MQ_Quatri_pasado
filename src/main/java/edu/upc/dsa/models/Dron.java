package edu.upc.dsa.models;

import edu.upc.dsa.util.RandomUtils;

public class Dron {
    String id;
    String fabricante;
    String modelo;
    double HorasVuelo;
    boolean almacenado;


    public Dron() {
        this.setId(RandomUtils.getId());
    }

    public Dron(String fabricante, String modelo, double HorasVuelo) {
        this();
        this.id=null;
        this.setFabricante(fabricante);
        this.setModelo(modelo);
        this.setHorasVuelo(HorasVuelo);
        this.almacenado=false;
    }

    public Dron(String id, String fabricante, String modelo, double HorasVuelo) {
        this();
        if (id != null) this.setId(id);
        this.setFabricante(fabricante);
        this.setModelo(modelo);
        this.setHorasVuelo(HorasVuelo);
        this.almacenado=false;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFabricante() {
        return fabricante;
    }

    public void setFabricante(String fabricante) {
        this.fabricante = fabricante;
    }

    public double getHorasVuelo() {
        return HorasVuelo;
    }

    public void setHorasVuelo(double horasVuelo) {
        HorasVuelo = horasVuelo;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public boolean isAlmacenado() {
        return almacenado;
    }

    public void setAlmacenado(boolean almacenado) {
        this.almacenado = almacenado;
    }


}
