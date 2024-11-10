package edu.upc.dsa;
import edu.upc.dsa.models.Dron;
import edu.upc.dsa.models.Piloto;
import edu.upc.dsa.models.Reserva;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;
public class FlotaDronesManagerTest {
    FlotaDronesManager fdm;
    @Before
    public void setUp() {
        this.fdm = FlotaDronesManagerImpl.getInstance();
        this.fdm.addDron("D1", "DJI", "Mavic Pro", 10);
        this.fdm.addDron("D2", "DJI", "Mavic Air", 5);
        this.fdm.addDron("D3", "DJI", "Mavic Mini", 2);
        this.fdm.addPiloto("P1", "Pepe", "Escobar", 3);
        this.fdm.addPiloto("P2", "Juan", "Perez", 2);
        this.fdm.addPiloto("P3", "Jose", "Garcia", 4);

    }

    @After
    public void tearDown() {
        // És un Singleton
        this.fdm.clear();
    }

    @Test
    public void addDronTest() {
        Dron d = this.fdm.addDron("D4", "DJI", "Mavic Pro", 10);
        Assert.assertNotNull(d);
        Assert.assertEquals("D4", d.getId());
        Assert.assertEquals("DJI", d.getFabricante());
        Assert.assertEquals("Mavic Pro", d.getModelo());
        Assert.assertEquals(10, d.getHorasVuelo(), 0);
    }

    @Test
    public void addPilotoTest() {
        Piloto p = this.fdm.addPiloto("P4", "Pepe", "Escobar", 3);
        Assert.assertNotNull(p);
        Assert.assertEquals("P4", p.getId());
        Assert.assertEquals("Pepe", p.getNombre());
        Assert.assertEquals("Escobar", p.getApellidos());
        Assert.assertEquals(3, p.getHorasVuelo());
    }

    @Test
    public void addReservaTest() {
        Reserva r = this.fdm.addReserva(new Reserva("idDron1", "11/2/2021", 3, 0, 0, 1, 1, "idPiloto1"));
        Assert.assertNotNull(r);
        Assert.assertEquals("idDron1", r.getIdDron());
        Assert.assertEquals("11/2/2021", r.getFecha());
        Assert.assertEquals(3, r.getTiempoEstimado(), 0);
        Assert.assertEquals(0, r.getLatitudInicio());
        Assert.assertEquals(0, r.getLongitudInicio());
        Assert.assertEquals(1, r.getLatitudDestino());
        Assert.assertEquals(1, r.getLongitudDestino());
        Assert.assertEquals("idPiloto1", r.getIdPiloto());
    }

    @Test
    public void listaDronesOrdenadoPorHorasTest() {
        List<Dron> drones = this.fdm.listaDronesOrdenadoPorHoras();
        Assert.assertEquals("D1", drones.get(0).getId());
        Assert.assertEquals("D2", drones.get(1).getId());
        Assert.assertEquals("D3", drones.get(2).getId());
    }

    @Test
    public void listaPilotosOrdenadoPorHorasTest() {
        List<Piloto> pilotos = this.fdm.listaPilotosOrdenadoPorHoras();
        Assert.assertEquals("P3", pilotos.get(0).getId());
        Assert.assertEquals("P1", pilotos.get(1).getId());
        Assert.assertEquals("P2", pilotos.get(2).getId());
    }

    @Test
    public void listaReservasAsignadosPilotoTest() {
        this.fdm.addReserva(new Reserva("idDron1", "11/2/2021", 3, 0, 0, 1, 1, "idPiloto1"));
        this.fdm.addReserva(new Reserva("idDron2", "12/2/2021", 3, 0, 0, 1, 1, "idPiloto1"));
        List<Reserva> reservas = this.fdm.listaReservasAsignadosPiloto("idPiloto1");
        Assert.assertEquals(2, reservas.size());
    }

    @Test
    public void listaReservasAsignadosDronTest() {
        this.fdm.addReserva(new Reserva("idDron1", "11/2/2021", 3, 0, 0, 1, 1, "idPiloto1"));
        this.fdm.addReserva(new Reserva("idDron1", "12/2/2021", 3, 0, 0, 1, 1, "idPiloto2"));
        List<Reserva> reservas = this.fdm.listaReservasAsignadosDron("idDron1");
        Assert.assertEquals(2, reservas.size());
    }


    @Test
    public void almacenarDronTest() {
        Dron d = this.fdm.addDron("D4", "DJI", "Mavic Pro", 10);
        this.fdm.almacenarDron("D4");
        Assert.assertTrue(d.isAlmacenado());
    }


    @Test
    public void repararDronTest() {
        Dron d= this.fdm.addDron("D4", "DJI", "Mavic Pro", 10);
        this.fdm.almacenarDron("D4");
        this.fdm.repararDron();
        Assert.assertFalse(d.isAlmacenado());
    }




}
