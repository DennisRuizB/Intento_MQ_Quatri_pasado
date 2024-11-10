package edu.upc.dsa.services;


import edu.upc.dsa.FlotaDronesManager;
import edu.upc.dsa.FlotaDronesManagerImpl;
import edu.upc.dsa.models.Dron;
import edu.upc.dsa.models.Piloto;
import edu.upc.dsa.models.Reserva;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import javax.ws.rs.*;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

@Api(value = "/flotaDrones", description = "Endpoint to FlotaDrones Service")
@Path("/flotaDrones")
public class FlotaDronesService {

    private FlotaDronesManager fdm;

    public FlotaDronesService() {
        this.fdm = FlotaDronesManagerImpl.getInstance();
        if (fdm.sizeD() == 0) {
            this.fdm.addDron("DJI", "Mavic Mini", 2.0);
            this.fdm.addDron("DJI", "Mavic Air", 1.0);
            this.fdm.addDron("DJI", "Mavic Pro", 0.0);
        }
        if (fdm.sizeP() == 0) {
            this.fdm.addPiloto("pepe","escobar",3);
            this.fdm.addPiloto("juan","perez",2);
            this.fdm.addPiloto("jose","garcia",4);
        }
    }

    @GET
    @ApiOperation(value = "get all Drones ordered by hours", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = Dron.class, responseContainer="List"),
    })
    @Path("/listDrones")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getDrones() {

        List<Dron> drones = this.fdm.listaDronesOrdenadoPorHoras();

        GenericEntity<List<Dron>> entity = new GenericEntity<List<Dron>>(drones) {};
        return Response.status(201).entity(entity).build()  ;

    }

    @POST
    @ApiOperation(value = "add a new Dron", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response= Dron.class),
            @ApiResponse(code = 500, message = "Validation Error")
    })
    @Path("/addDron")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response newDron(Dron d) {

        if (d.getFabricante()==null || d.getModelo()==null)  return Response.status(500).entity(d).build();
        this.fdm.addDron(d.getFabricante(), d.getModelo(), d.getHorasVuelo());
        return Response.status(201).entity(d).build();
    }

    @PUT
    @ApiOperation(value = "store a Dron", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response= Dron.class),
            @ApiResponse(code = 404, message = "Dron not found")
    })
    @Path("/storeDron/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response storeDron(@PathParam("id") String id) {

        int result = this.fdm.almacenarDron(id);

        if (result == 0) return Response.status(201).build();
        else return Response.status(404).build();

    }

    @PUT
    @ApiOperation(value = "repair a Dron", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response= Dron.class),
            @ApiResponse(code = 404, message = "Dron not found")
    })
    @Path("/repairDron")
    @Produces(MediaType.APPLICATION_JSON)
    public Response repairDron() {

        int result = this.fdm.repararDron();

        if (result == 0) return Response.status(201).build();
        else return Response.status(404).build();

    }


    @POST
    @ApiOperation(value = "add a new Piloto", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response= Piloto.class),
            @ApiResponse(code = 500, message = "Validation Error")
    })
    @Path("/addPiloto")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response newPiloto(Piloto p) {

        if (p.getNombre()==null || p.getApellidos()==null)  return Response.status(500).entity(p).build();
        this.fdm.addPiloto(p.getNombre(), p.getApellidos(), p.getHorasVuelo());
        return Response.status(201).entity(p).build();
    }

    @GET
    @ApiOperation(value = "get all Pilotos ordered by hours", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = Piloto.class, responseContainer="List"),
    })
    @Path("/listPilotos")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPilotos() {

        List<Piloto> pilotos = this.fdm.listaPilotosOrdenadoPorHoras();

        GenericEntity<List<Piloto>> entity = new GenericEntity<List<Piloto>>(pilotos) {};
        return Response.status(201).entity(entity).build()  ;

    }

    @POST
    @ApiOperation(value = "add a new Reserva", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response= Reserva.class),
            @ApiResponse(code = 500, message = "Validation Error")
    })
    @Path("/addReserva")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addReserva(Reserva r){
        if(r.getIdDron() == null || r.getIdPiloto()==null) return Response.status(500).entity(r).build();
        this.fdm.addReserva(r);
        return Response.status(201).entity(r).build();
    }

    @GET
    @ApiOperation(value = "get all Reservas assigned to a Piloto", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = Reserva.class, responseContainer="List"),
            @ApiResponse(code = 404, message = "Not found")
    })
    @Path("/listReservasPiloto/{idPiloto}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getReservasPiloto(@PathParam("idPiloto") String idPiloto) {

        List<Reserva> reservas = this.fdm.listaReservasAsignadosPiloto(idPiloto);

        if (reservas == null || reservas.isEmpty()) return Response.status(404).build();

        GenericEntity<List<Reserva>> entity = new GenericEntity<List<Reserva>>(reservas) {};
        return Response.status(201).entity(entity).build()  ;

    }

    @GET
    @ApiOperation(value = "get all Reservas assigned to a Dron", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = Reserva.class, responseContainer="List"),
            @ApiResponse(code = 404, message = "Not found")
    })
    @Path("/listReservasDron/{idDron}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getReservasDron(@PathParam("idDron") String idDron) {

        List<Reserva> reservas = this.fdm.listaReservasAsignadosDron(idDron);

        if (reservas == null || reservas.isEmpty()) return Response.status(404).build();

        GenericEntity<List<Reserva>> entity = new GenericEntity<List<Reserva>>(reservas) {};
        return Response.status(201).entity(entity).build()  ;

    }
}
