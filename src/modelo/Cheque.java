package modelo;
import java.sql.Date;

public class Cheque {
    private int idCheque;
    private int idCuenta;
    private String numeroCheque;
    private String beneficiario;
    private double monto;
    private Date fecha;
    private String concepto;

    public Cheque(int idCheque, int idCuenta, String numeroCheque, String beneficiario, double monto, Date fecha, String concepto) {
        this.idCheque = idCheque;
        this.idCuenta = idCuenta;
        this.numeroCheque = numeroCheque;
        this.beneficiario = beneficiario;
        this.monto = monto;
        this.fecha = fecha;
        this.concepto = concepto;
    }

    public Cheque() {
    }

    public int getIdCheque() {
        return idCheque;
    }

    public void setIdCheque(int idCheque) {
        this.idCheque = idCheque;
    }

    public int getIdCuenta() {
        return idCuenta;
    }

    public void setIdCuenta(int idCuenta) {
        this.idCuenta = idCuenta;
    }

    public String getNumeroCheque() {
        return numeroCheque;
    }

    public void setNumeroCheque(String numeroCheque) {
        this.numeroCheque = numeroCheque;
    }

    public String getBeneficiario() {
        return beneficiario;
    }

    public void setBeneficiario(String beneficiario) {
        this.beneficiario = beneficiario;
    }

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getConcepto() {
        return concepto;
    }

    public void setConcepto(String concepto) {
        this.concepto = concepto;
    }

    
}