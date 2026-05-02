**sistema_contable_ues_grup4**  
   
    
   
 **Jos**e Flores:  
   
  **  
   
 **EN LA BASE de  sistema_contable_pruebas agregamos los atributos/columnas de nombre y apellido en al tabla de cuentas_bancarias**  
   
 **USE sistema_contable_pruebas;**  
   
 **ALTER TABLE cuentas_bancarias **  
   
 **ADD COLUMN nombre VARCHAR(100) AFTER numero_cuenta,**  
   
 **ADD COLUMN apellido VARCHAR(100) AFTER nombre;**  
   
    
 **Registre una empresa para realizar mis pruebas.**  
   
 **ESTO EN LA TABLA DE empresa**  
   
 **USE sistema_contable_pruebas;**  
   
 **INSERT INTO empresa (id_empresa, nombre_comercial, razon_social) **  
   
 **VALUES (1, 'Empresa de Prueba', 'Pruebas S.A. de C.V.')**  
   
 **ON DUPLICATE KEY UPDATE nombre_comercial='Empresa de Prueba';**  
    
 **Tabla cuentas_bancarias registros de clientes (IDs 1, 2, 3)**  
   
 **USE sistema_contable_pruebas;**  
   
 **INSERT INTO cuentas_bancarias (id_empresa, banco, numero_cuenta, nombre, apellido, saldo_actual, estado) **  
   
 **VALUES **  
   
 **(1, 'Banco Agricola', '7001234567', 'Josue', 'Perez', 100.00, 'Activo'),**  
   
 **(1, 'Banco Cuscatlan', '7009876543', 'Maria', 'Gomez', 100.00, 'Activo'),**  
   
 **(1, 'Banco Davivienda', '7009872078', 'Luis', 'Peñate', 10.00, 'Activo')**  **;**  
   
    
   
    
   
    
