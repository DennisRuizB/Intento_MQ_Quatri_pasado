package edu.upc.dsa.models;
//Para la creación de una
//reserva de plan de vuelo se indica el identificador del dron, una fecha
//(dia + hora), una estimación de tiempo de la reserva (en horas), una
//posición (lat/long) de inicio, una posición de destino y un piloto. En caso
//que el dron o el piloto ya tenga un plan de vuelo asignado dentro de ese
//intervalo de tiempo se indicará el error. En caso que el dron no esté
//operativo y en el almacen para realizar tareas de mantenimiento, se
//deberá informar del error
public class Reserva {
    String idDron;
    String fecha;
    double tiempoEstimado;
    int latitudInicio;
    int longitudInicio;
    int latitudDestino;
    int longitudDestino;
    String idPiloto;

    public Reserva() {
    }

    public Reserva(String idDron, String fecha, double tiempoEstimado, int latitudInicio, int longitudInicio, int latitudDestino, int longitudDestino, String idPiloto) {
        this.setIdDron(idDron);
        this.setFecha(fecha);
        this.setTiempoEstimado(tiempoEstimado);
        this.setLatitudInicio(latitudInicio);
        this.setLongitudInicio(longitudInicio);
        this.setLatitudDestino(latitudDestino);
        this.setLongitudDestino(longitudDestino);
        this.setIdPiloto(idPiloto);
    }

    public String getIdDron() {
        return idDron;
    }

    public void setIdDron(String idDron) {
        this.idDron = idDron;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public double getTiempoEstimado() {
        return tiempoEstimado;
    }

    public void setTiempoEstimado(double tiempoEstimado) {
        this.tiempoEstimado = tiempoEstimado;
    }

    public int getLatitudInicio() {
        return latitudInicio;
    }

    public void setLatitudInicio(int latitudInicio) {
        this.latitudInicio = latitudInicio;
    }

    public int getLongitudInicio() {
        return longitudInicio;
    }

    public void setLongitudInicio(int longitudInicio) {
        this.longitudInicio = longitudInicio;
    }

    public int getLatitudDestino() {
        return latitudDestino;
    }

    public void setLatitudDestino(int latitudDestino) {
        this.latitudDestino = latitudDestino;
    }

    public int getLongitudDestino() {
        return longitudDestino;
    }

    public void setLongitudDestino(int longitudDestino) {
        this.longitudDestino = longitudDestino;
    }

    public String getIdPiloto() {
        return idPiloto;
    }

    public void setIdPiloto(String idPiloto) {
        this.idPiloto = idPiloto;
    }
}
