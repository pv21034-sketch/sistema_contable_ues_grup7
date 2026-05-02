package modelo;

/**
 *
 * @author ing-jos-flores
 */
public class Transferencia {

    private int idEmpresa;
    private int idCuentaOrigen;
    private int idCuentaDestino;
    private double monto;
    private String fecha;
    private String concepto;

    public Transferencia() {

    }

    public Transferencia(int idEmpresa, int idCuentaOrigen, int idCuentaDestino, double monto, String fecha, String concepto) {
        this.idEmpresa = idEmpresa;
        this.idCuentaOrigen = idCuentaOrigen;
        this.idCuentaDestino = idCuentaDestino;
        this.monto = monto;
        this.fecha = fecha;
        this.concepto = concepto;
    }

    public int getIdEmpresa() {
        return idEmpresa;
    }

    public void setIdEmpresa(int idEmpresa) {
        this.idEmpresa = idEmpresa;
    }

    public int getIdCuentaOrigen() {
        return idCuentaOrigen;
    }

    public void setIdCuentaOrigen(int idCuentaOrigen) {
        this.idCuentaOrigen = idCuentaOrigen;
    }

    public int getIdCuentaDestino() {
        return idCuentaDestino;
    }

    public void setIdCuentaDestino(int idCuentaDestino) {
        this.idCuentaDestino = idCuentaDestino;
    }

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getConcepto() {
        return concepto;
    }

    public void setConcepto(String concepto) {
        this.concepto = concepto;
    }

}
