package edu.upc.dsa.models;

import edu.upc.dsa.util.RandomUtils;

public class Piloto {
    String id;
    String nombre;
    String apellidos;
    int HorasVuelo;


    public Piloto() {
        this.setId(RandomUtils.getId());
    }
    public Piloto(String nombre, String apellidos, int HorasVuelo) {
        this();
        this.id=null;
        this.setNombre(nombre);
        this.setApellidos(apellidos);
        this.setHorasVuelo(HorasVuelo);
    }
    public Piloto(String id, String nombre, String apellidos, int HorasVuelo) {
        this();
        if (id != null) this.setId(id);
        this.setNombre(nombre);
        this.setApellidos(apellidos);
        this.setHorasVuelo(HorasVuelo);
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public int getHorasVuelo() {
        return HorasVuelo;
    }

    public void setHorasVuelo(int horasVuelo) {
        HorasVuelo = horasVuelo;
    }
}
