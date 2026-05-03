public class CuentaBancaria {
    private int idEmpresa;
    private String banco;
    private String numeroCuenta;
    private String tipoCuenta;
    private double saldoInicial;
    private String estado;

    // ESTE ES EL CONSTRUCTOR QUE DEBES ACTUALIZAR:
    public CuentaBancaria(int idEmpresa, String banco, String numeroCuenta, String tipoCuenta, double saldoInicial, String estado) {
        this.idEmpresa = idEmpresa;
        this.banco = banco;
        this.numeroCuenta = numeroCuenta;
        this.tipoCuenta = tipoCuenta;
        this.saldoInicial = saldoInicial;
        this.estado = estado;
    }

    // Getters necesarios para que el botón Buscar funcione:
    public String getNumeroCuenta() { return numeroCuenta; }
    public String getBanco() { return banco; }
    public String getTipoCuenta() { return tipoCuenta; }
    public double getSaldoInicial() { return saldoInicial; }
    public String getEstado() { return estado; }
    // Agrega esto junto a los otros getters
public int getIdEmpresa() {
    return idEmpresa;
}

    // Setters necesarios para el botón Editar:
    public void setBanco(String banco) { this.banco = banco; }
    public void setTipoCuenta(String tipoCuenta) { this.tipoCuenta = tipoCuenta; }
    public void setSaldoInicial(double saldoInicial) { this.saldoInicial = saldoInicial; }
    public void setEstado(String estado) { this.estado = estado; }
}