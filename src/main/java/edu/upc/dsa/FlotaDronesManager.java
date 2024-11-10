package edu.upc.dsa;

import edu.upc.dsa.models.Dron;
import edu.upc.dsa.models.Piloto;
import edu.upc.dsa.models.Reserva;

import java.util.List;
public interface FlotaDronesManager {
    public Dron addDron(String id, String fabricante, String modelo, double HorasVuelo);
    public Dron addDron(String fabricante, String modelo, double HorasVuelo);
    public Dron addDron(Dron d);
    public List<Dron> listaDronesOrdenadoPorHoras();
    public int almacenarDron(String id);
    public int repararDron();

    public Piloto addPiloto(String id, String nombre, String apellidos, int HorasVuelo);
    public Piloto addPiloto(String nombre, String apellidos, int HorasVuelo);
    public Piloto addPiloto(Piloto p);
    public List<Piloto> listaPilotosOrdenadoPorHoras();


    public Reserva addReserva(Reserva r);
    public List<Reserva> listaReservasAsignadosPiloto(String idPiloto);
    public List<Reserva> listaReservasAsignadosDron(String idDron);

    public void clear();
    public int sizeD();
    public int sizeP();
    public int sizeR();


}
