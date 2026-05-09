package modelo;

public class CuentaBancaria {

    private int idEmpresa;
    private String banco;
    private String numeroCuenta;
    private String tipoCuenta;
    private double saldoInicial;
    private String estado;

    public CuentaBancaria() {
    }

    public CuentaBancaria(
            int idEmpresa,
            String banco,
            String numeroCuenta,
            String tipoCuenta,
            double saldoInicial,
            String estado
    ) {
        this.idEmpresa = idEmpresa;
        this.banco = banco;
        this.numeroCuenta = numeroCuenta;
        this.tipoCuenta = tipoCuenta;
        this.saldoInicial = saldoInicial;
        this.estado = estado;
    }

    public int getIdEmpresa() {
        return idEmpresa;
    }

    public void setIdEmpresa(int idEmpresa) {
        this.idEmpresa = idEmpresa;
    }

    public String getBanco() {
        return banco;
    }

    public void setBanco(String banco) {
        this.banco = banco;
    }

    public String getNumeroCuenta() {
        return numeroCuenta;
    }

    public void setNumeroCuenta(String numeroCuenta) {
        this.numeroCuenta = numeroCuenta;
    }

    public String getTipoCuenta() {
        return tipoCuenta;
    }

    public void setTipoCuenta(String tipoCuenta) {
        this.tipoCuenta = tipoCuenta;
    }

    public double getSaldoInicial() {
        return saldoInicial;
    }

    public void setSaldoInicial(double saldoInicial) {
        this.saldoInicial = saldoInicial;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public boolean estaActiva() {
        return estado != null && estado.equalsIgnoreCase("ACTIVO");
    }

    @Override
    public String toString() {
        return banco + " - " + numeroCuenta + " (" + tipoCuenta + ")";
    }
}