package edu.upc.dsa;

import edu.upc.dsa.models.Dron;
import edu.upc.dsa.models.Piloto;
import edu.upc.dsa.models.Reserva;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.stream.Collectors;

import edu.upc.dsa.models.Track;
import org.apache.log4j.Logger;
public class FlotaDronesManagerImpl implements FlotaDronesManager{
    private static FlotaDronesManager instance;
    protected List<Dron> drons;
    protected List<Piloto> pilotos;
    protected List<Reserva> reservas;
    protected Queue<Dron> colaAlmacen;
    final static Logger logger = Logger.getLogger(FlotaDronesManagerImpl.class);

    private FlotaDronesManagerImpl() {
        this.drons = new LinkedList<>();
        this.pilotos = new LinkedList<>();
        this.reservas = new LinkedList<>();
        this.colaAlmacen = new LinkedList<>();
    }
    public static FlotaDronesManager getInstance() {
        if (instance==null) instance = new FlotaDronesManagerImpl();
        return instance;
    }

    @Override
    public Dron addDron(Dron d) {
        logger.info("new Dron " + d);
        this.drons.add (d);
        return d;
    }
    @Override
    public Dron addDron(String fabricante, String modelo, double HorasVuelo){
        logger.info("new Dron " + fabricante + " " + modelo + " " + HorasVuelo);
        return this.addDron(null, fabricante, modelo, HorasVuelo);
    }
    @Override
    public Dron addDron(String id, String fabricante, String modelo, double HorasVuelo) {
        logger.info("new Dron " + fabricante + " " + modelo + " " + HorasVuelo);
        return this.addDron(new Dron(id, fabricante, modelo, HorasVuelo));
    }
    @Override
    public List<Dron> listaDronesOrdenadoPorHoras() {
        return this.drons.stream()
                .sorted((d1, d2) -> Double.compare(d2.getHorasVuelo(), d1.getHorasVuelo()))
                .collect(Collectors.toList());
    }
    @Override
    public int almacenarDron(String id) {
        for (Dron d: this.drons) {
            if (d.getId().equals(id)) {
                d.setAlmacenado(true);
                colaAlmacen.add(d);
                logger.info("Dron almacenado con id " + id);
                return 0;
            }
        }
        logger.error("Dron no encontrado con id " + id);
        return -1;
    }
    @Override
    public int repararDron() {
        Dron d = colaAlmacen.poll();
        d.setAlmacenado(false);
        if (d != null) {
            logger.info("Dron reparado con id " + d.getId());
            return 0;
        }
        logger.error("No hay drones para reparar");
        return -1;
    }

    @Override
    public Piloto addPiloto(Piloto p) {
        logger.info("new Piloto " + p);
        this.pilotos.add (p);
        return p;
    }
    @Override
    public Piloto addPiloto(String nombre, String apellidos, int HorasVuelo){
        logger.info("new Piloto " + nombre + " " + apellidos + " " + HorasVuelo);
        return this.addPiloto(null, nombre, apellidos, HorasVuelo);
    }
    @Override
    public Piloto addPiloto(String id, String nombre, String apellidos, int HorasVuelo) {
        logger.info("new Piloto " + nombre + " " + apellidos + " " + HorasVuelo);
        return this.addPiloto(new Piloto(id, nombre, apellidos, HorasVuelo));
    }
    @Override
    public List<Piloto> listaPilotosOrdenadoPorHoras() {
        return this.pilotos.stream()
                .sorted((p1, p2) -> Integer.compare(p2.getHorasVuelo(), p1.getHorasVuelo()))
                .collect(Collectors.toList());
    }
    @Override
    public Reserva addReserva(Reserva r) {
        logger.info("new Reserva " + r.getIdPiloto() + " " + r.getIdDron() + " " + r.getFecha() + " " + r.getTiempoEstimado() + " " + r.getLatitudInicio() + " " + r.getLongitudInicio() + " " + r.getLatitudDestino() + " " + r.getLongitudDestino());
      if(reservas.size() != 0){
        // Check if the dron is in the storage queue (not operational)
          if(colaAlmacen.size() != 0){
          if (colaAlmacen.stream().anyMatch(d -> d.getId().equals(r.getIdDron()))) {
              logger.error("Dron is not operational and in storage for maintenance: " + r.getIdDron());
              return null;
          }
      }
        // Check if the dron or piloto already has a reservation in the given time interval
        for (Reserva reserva : this.reservas) {
            if (reserva.getIdDron().equals(r.getIdDron()) || reserva.getIdPiloto().equals(r.getIdPiloto())) {
                if (reserva.getFecha().equals(r.getFecha())) {
                    logger.error("Dron or Piloto already has a reservation in the given time interval");
                    return null;
                }
            }
        }
    }
        this.reservas.add(r);
        return r;
    }

    @Override
    public List<Reserva> listaReservasAsignadosPiloto(String idPiloto) {
        return this.reservas.stream()
                .filter(r -> r.getIdPiloto().equals(idPiloto))
                .collect(Collectors.toList());
    }
    @Override
    public List<Reserva> listaReservasAsignadosDron(String idDron) {
        return this.reservas.stream()
                .filter(r -> r.getIdDron().equals(idDron))
                .collect(Collectors.toList());
    }

    @Override
    public void clear() {
        this.drons.clear();
        this.pilotos.clear();
        this.reservas.clear();
        logger.info("cleared");
    }


    @Override
    public int sizeD() {
        int ret = this.drons.size();
        logger.info("size " + ret);

        return ret;
    }

    @Override
    public int sizeP() {
        int ret = this.pilotos.size();
        logger.info("size " + ret);

        return ret;
    }

    @Override
    public int sizeR() {
        int ret = this.reservas.size();
        logger.info("size " + ret);

        return ret;
    }

}
