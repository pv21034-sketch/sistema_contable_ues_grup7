package dao;

import conexion.Conexion;
import modelo.CuentaBancaria;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class CuentaBancariaDAO {

    public boolean guardar(CuentaBancaria cuenta) {

        String sql =
            "INSERT INTO cuentas_bancarias " +
            "(id_empresa, banco, numero_cuenta, tipo_cuenta, saldo_inicial, estado) " +
            "VALUES (?, ?, ?, ?, ?, ?)";

        try (
            Connection con = Conexion.getConexion();
            PreparedStatement ps = con.prepareStatement(sql)
        ) {

            ps.setInt(1, cuenta.getIdEmpresa());

            ps.setString(2, cuenta.getBanco());

            ps.setString(3, cuenta.getNumeroCuenta());

            ps.setString(4, cuenta.getTipoCuenta());

            ps.setDouble(5, cuenta.getSaldoInicial());

            ps.setString(6, cuenta.getEstado());

            return ps.executeUpdate() > 0;

        } catch (Exception e) {

            System.out.println(
                "Error guardando cuenta: " + e.getMessage()
            );

            return false;
        }
    }
}