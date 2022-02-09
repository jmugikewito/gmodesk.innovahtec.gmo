package gmo.model;

/**
 Created by Asus on 17/05/2018.
 */
public class Fundo {

    public static String[] cols = {
        "iddatabase",
        "idempresa",
        "idfundo",
        "descripcion",
        "escampo",
        "espacking",
        "cantplantas",
        "canthctas",
        "activo",
        "fechacreacion",
        "observaciones"
    };

    private String iddatabase;
    private String idempresa;
    private String idfundo;
    private String descripcion;
    private int escampo;
    private int espacking;
    private int cantplantas;
    private double canthctas;
    private int activo;
    private String fechacreacion;
    private String observaciones;

    public Fundo() {
    }

    public Fundo(String iddatabase, String idempresa, String idfundo, String descripcion, int escampo, int espacking, int cantplantas, double canthctas, int activo, String fechacreacion, String observaciones) {
        this.iddatabase = iddatabase;
        this.idempresa = idempresa;
        this.idfundo = idfundo;
        this.descripcion = descripcion;
        this.escampo = escampo;
        this.espacking = espacking;
        this.cantplantas = cantplantas;
        this.canthctas = canthctas;
        this.activo = activo;
        this.fechacreacion = fechacreacion;
        this.observaciones = observaciones;
    }

    public Fundo(Object[] data) {
        this.iddatabase = data[0].toString();
        this.idempresa = data[1].toString();
        this.idfundo = data[2].toString();
        this.descripcion = data[3].toString();
        this.escampo = Integer.parseInt(data[4].toString());
        this.espacking = Integer.parseInt(data[5].toString());
        this.cantplantas = Integer.parseInt(data[6].toString());
        this.canthctas = Double.parseDouble(data[7].toString());
        this.activo = Integer.parseInt(data[8].toString());
        this.fechacreacion = data[9].toString();
        this.observaciones = data[10].toString();
    }

    public String getIddatabase() {
        return iddatabase;
    }

    public void setIddatabase(String iddatabase) {
        this.iddatabase = iddatabase;
    }

    public String getIdempresa() {
        return idempresa;
    }

    public void setIdempresa(String idempresa) {
        this.idempresa = idempresa;
    }

    public String getIdfundo() {
        return idfundo;
    }

    public void setIdfundo(String idfundo) {
        this.idfundo = idfundo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getEscampo() {
        return escampo;
    }

    public void setEscampo(int escampo) {
        this.escampo = escampo;
    }

    public int getEspacking() {
        return espacking;
    }

    public void setEspacking(int espacking) {
        this.espacking = espacking;
    }

    public int getCantplantas() {
        return cantplantas;
    }

    public void setCantplantas(int cantplantas) {
        this.cantplantas = cantplantas;
    }

    public double getCanthctas() {
        return canthctas;
    }

    public void setCanthctas(double canthctas) {
        this.canthctas = canthctas;
    }

    public int getActivo() {
        return activo;
    }

    public void setActivo(int activo) {
        this.activo = activo;
    }

    public String getFechacreacion() {
        return fechacreacion;
    }

    public void setFechacreacion(String fechacreacion) {
        this.fechacreacion = fechacreacion;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }
}
