package models;

import java.util.Date;

public class Movimiento {
    private Date fecha;
    private String descripcion;
    private double monto;
    private int cuentaId;

    public Movimiento(Date fecha, String descripcion, double monto, int cuentaId) {
        this.fecha = fecha;
        this.descripcion = descripcion;
        this.monto = monto;
        this.cuentaId = cuentaId;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }

    public int getCuentaId() {
        return cuentaId;
    }

    public void setCuentaId(int cuentaId) {
        this.cuentaId = cuentaId;
    }
}
