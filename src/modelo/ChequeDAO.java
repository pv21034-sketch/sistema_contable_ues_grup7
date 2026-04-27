package modelo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class ChequeDAO {

    Conexion conectar = new Conexion();
    Connection cn;
    PreparedStatement ps;
    ResultSet rs;

    public boolean registrarCheque(Cheque chq) {
        String sqlCheque = "INSERT INTO cheques (id_empresa, id_cuenta, numero_cheque, beneficiario, monto, fecha, concepto, estado) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        String sqlMov = "INSERT INTO movimientos_bancarios (id_empresa, id_cuenta, fecha, tipo, monto, concepto, origen, id_origen, estado_conciliacion) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try {
            cn = conectar.getConexion();
            cn.setAutoCommit(false); 

            ps = cn.prepareStatement(sqlCheque, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, 1); 
            ps.setInt(2, chq.getIdCuenta());
            ps.setString(3, chq.getNumeroCheque());
            ps.setString(4, chq.getBeneficiario());
            ps.setDouble(5, chq.getMonto());
            ps.setDate(6, chq.getFecha());
            ps.setString(7, chq.getConcepto());
            ps.setString(8, "Emitido");
            ps.executeUpdate();

            rs = ps.getGeneratedKeys();
            int idChequeGenerado = 0;
            if (rs.next()) {
                idChequeGenerado = rs.getInt(1);
            }

            ps = cn.prepareStatement(sqlMov);
            ps.setInt(1, 1);                   
            ps.setInt(2, chq.getIdCuenta());   
            ps.setDate(3, chq.getFecha());     
            ps.setString(4, "Salida");         
            ps.setDouble(5, -chq.getMonto());  
            ps.setString(6, "Emisión Cheque N° " + chq.getNumeroCheque()); 
            ps.setString(7, "Cheque");         
            ps.setInt(8, idChequeGenerado);    
            ps.setString(9, "No Conciliado"); 

            ps.executeUpdate();

            cn.commit();
            return true;

        } catch (SQLException e) {
            try {
                cn.rollback();
            } catch (SQLException ex) {
            }
            System.err.println("Error en transacción: " + e);
            return false;
        }
    }

    public void listarCuentas(javax.swing.JComboBox<String> combo) {
        String sql = "SELECT banco, numero_cuenta FROM cuentas_bancarias";

        try {
            cn = conectar.getConexion();
            ps = cn.prepareStatement(sql);
            rs = ps.executeQuery();

            combo.removeAllItems();
            combo.addItem("Seleccione una cuenta...");

            while (rs.next()) {
               
                combo.addItem(rs.getString("banco") + " - " + rs.getString("numero_cuenta"));
            }
        } catch (SQLException e) {
            System.err.println("Error al llenar combo: " + e);
        }
    }


    public List<Cheque> listarCheques() {
        List<Cheque> lista = new ArrayList<>();
        String sql = "SELECT * FROM cheques";
        try {
            cn = conectar.getConexion();
            ps = cn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Cheque c = new Cheque();
                c.setIdCheque(rs.getInt("id_cheque"));
                c.setNumeroCheque(rs.getString("numero_cheque"));
                c.setBeneficiario(rs.getString("beneficiario"));
                c.setMonto(rs.getDouble("monto"));
                c.setFecha(rs.getDate("fecha"));
                c.setConcepto(rs.getString("concepto"));
                c.setIdCuenta(rs.getInt("id_cuenta"));
                lista.add(c);
            }
        } catch (Exception e) {
            System.err.println(e);
        }
        return lista;
    }

    public boolean eliminar(int id) {
        String sql = "DELETE FROM cheques WHERE id_cheque = ?";
        try {
            cn = conectar.getConexion();
            ps = cn.prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean actualizar(Cheque chq) {
        String sql = "UPDATE cheques SET numero_cheque=?, beneficiario=?, monto=?, fecha=?, concepto=? WHERE id_cheque=?";
        try {
            cn = conectar.getConexion();
            ps = cn.prepareStatement(sql);
            ps.setString(1, chq.getNumeroCheque());
            ps.setString(2, chq.getBeneficiario());
            ps.setDouble(3, chq.getMonto());
            ps.setDate(4, chq.getFecha());
            ps.setString(5, chq.getConcepto());
            ps.setInt(6, chq.getIdCheque()); 

            int filas = ps.executeUpdate();
            return filas > 0;
        } catch (SQLException e) {
            System.err.println("Error al actualizar: " + e);
            return false;
        }
    }
    
    public List<Cheque> buscarCheques(String texto) {
    List<Cheque> lista = new ArrayList<>();
    String sql = "SELECT * FROM cheques WHERE beneficiario LIKE ?";
    try {
        cn = conectar.getConexion();
        ps = cn.prepareStatement(sql);
        ps.setString(1, "%" + texto + "%");
        rs = ps.executeQuery();
        while (rs.next()) {
            Cheque c = new Cheque();
            c.setIdCheque(rs.getInt("id_cheque"));
            c.setNumeroCheque(rs.getString("numero_cheque"));
            c.setBeneficiario(rs.getString("beneficiario"));
            c.setMonto(rs.getDouble("monto"));
            c.setFecha(rs.getDate("fecha"));
            c.setConcepto(rs.getString("concepto"));
            lista.add(c);
        }
    } catch (Exception e) {
        System.err.println("Error en búsqueda: " + e);
    }
    return lista;
}
    
}
