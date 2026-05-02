-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Servidor: localhost
-- Tiempo de generación: 02-05-2026 a las 18:43:04
-- Versión del servidor: 10.4.32-MariaDB
-- Versión de PHP: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `sistema_contable_pruebas`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `cheques`
--

CREATE TABLE `cheques` (
  `id_cheque` int(11) NOT NULL,
  `id_empresa` int(11) DEFAULT NULL,
  `id_cuenta` int(11) DEFAULT NULL,
  `numero_cheque` varchar(30) DEFAULT NULL,
  `beneficiario` varchar(150) DEFAULT NULL,
  `monto` decimal(15,2) DEFAULT NULL,
  `fecha` date DEFAULT NULL,
  `concepto` varchar(200) DEFAULT NULL,
  `estado` varchar(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `conciliaciones`
--

CREATE TABLE `conciliaciones` (
  `id_conciliacion` int(11) NOT NULL,
  `id_empresa` int(11) DEFAULT NULL,
  `id_cuenta` int(11) DEFAULT NULL,
  `periodo` varchar(20) DEFAULT NULL,
  `fecha_creacion` date DEFAULT NULL,
  `saldo_banco` decimal(15,2) DEFAULT NULL,
  `saldo_sistema` decimal(15,2) DEFAULT NULL,
  `diferencia` decimal(15,2) DEFAULT NULL,
  `estado` varchar(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `cuentas_bancarias`
--

CREATE TABLE `cuentas_bancarias` (
  `id_cuenta` int(11) NOT NULL,
  `id_empresa` int(11) DEFAULT NULL,
  `banco` varchar(100) DEFAULT NULL,
  `numero_cuenta` varchar(50) DEFAULT NULL,
  `nombre` varchar(100) DEFAULT NULL,
  `apellido` varchar(100) DEFAULT NULL,
  `tipo_cuenta` varchar(30) DEFAULT NULL,
  `saldo_inicial` decimal(15,2) DEFAULT NULL,
  `saldo_actual` decimal(15,2) DEFAULT NULL,
  `estado` varchar(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `cuentas_bancarias`
--

INSERT INTO `cuentas_bancarias` (`id_cuenta`, `id_empresa`, `banco`, `numero_cuenta`, `nombre`, `apellido`, `tipo_cuenta`, `saldo_inicial`, `saldo_actual`, `estado`) VALUES
(1, 1, 'Banco Agricola', '7001234567', 'Josue', 'Perez', NULL, NULL, 0.00, 'Activo'),
(2, 1, 'Banco Cuscatlan', '7009876543', 'Maria', 'Gomez', NULL, NULL, 200.00, 'Activo'),
(3, 1, 'Banco Davivienda', '7009872078', 'Luis', 'Peñate', NULL, NULL, 10.00, 'Activo');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `detalle_conciliacion`
--

CREATE TABLE `detalle_conciliacion` (
  `id_detalle` int(11) NOT NULL,
  `id_conciliacion` int(11) DEFAULT NULL,
  `id_movimiento` int(11) DEFAULT NULL,
  `descripcion` varchar(200) DEFAULT NULL,
  `monto` decimal(15,2) DEFAULT NULL,
  `tipo` varchar(20) DEFAULT NULL,
  `origen` varchar(30) DEFAULT NULL,
  `estado` varchar(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `empresa`
--

CREATE TABLE `empresa` (
  `id_empresa` int(11) NOT NULL,
  `nombre_comercial` varchar(100) DEFAULT NULL,
  `razon_social` varchar(100) DEFAULT NULL,
  `NRC` varchar(20) DEFAULT NULL,
  `NIT` varchar(20) DEFAULT NULL,
  `giro` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `empresa`
--

INSERT INTO `empresa` (`id_empresa`, `nombre_comercial`, `razon_social`, `NRC`, `NIT`, `giro`) VALUES
(1, 'Empresa de Prueba', 'Pruebas S.A. de C.V.', NULL, NULL, NULL);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `movimientos_bancarios`
--

CREATE TABLE `movimientos_bancarios` (
  `id_movimiento` int(11) NOT NULL,
  `id_empresa` int(11) DEFAULT NULL,
  `id_cuenta` int(11) DEFAULT NULL,
  `fecha` date DEFAULT NULL,
  `tipo` varchar(20) DEFAULT NULL,
  `monto` decimal(15,2) DEFAULT NULL,
  `concepto` varchar(200) DEFAULT NULL,
  `origen` varchar(50) DEFAULT NULL,
  `id_origen` int(11) DEFAULT NULL,
  `estado_conciliacion` varchar(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `transferencias`
--

CREATE TABLE `transferencias` (
  `id_transferencia` int(11) NOT NULL,
  `id_empresa` int(11) DEFAULT NULL,
  `id_cuenta_origen` int(11) DEFAULT NULL,
  `id_cuenta_destino` int(11) DEFAULT NULL,
  `monto` decimal(15,2) DEFAULT NULL,
  `fecha` date DEFAULT NULL,
  `concepto` varchar(200) DEFAULT NULL,
  `estado` varchar(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `transferencias`
--

INSERT INTO `transferencias` (`id_transferencia`, `id_empresa`, `id_cuenta_origen`, `id_cuenta_destino`, `monto`, `fecha`, `concepto`, `estado`) VALUES
(1, 1, 1, 1, 10.00, '2026-05-02', 'aver', 'Activo'),
(2, 1, 1, 3, 90.00, '2026-05-02', 'PROBANDO', 'Activo'),
(3, 1, 3, 1, 90.00, '2026-05-02', 'PROBANDO', 'Activo'),
(4, 1, 1, 2, 100.00, '2026-05-02', 'PROBANDOOO', 'Activo'),
(5, 1, 1, 3, 10.00, '2026-05-02', 'AVER', 'Activo'),
(6, 1, 3, 1, 10.00, '2026-05-02', 'AVR', 'Activo'),
(7, 1, 1, 3, 12.00, '2026-05-02', 'frr', 'Activo'),
(8, 1, 3, 1, 12.00, '2026-05-02', 'cvcrevrv', 'Activo');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuarios`
--

CREATE TABLE `usuarios` (
  `id_usuario` int(11) NOT NULL,
  `id_empresa` int(11) DEFAULT NULL,
  `nombre` varchar(100) DEFAULT NULL,
  `usuario` varchar(50) DEFAULT NULL,
  `clave` varchar(100) DEFAULT NULL,
  `rol` varchar(50) DEFAULT NULL,
  `estado` varchar(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `cheques`
--
ALTER TABLE `cheques`
  ADD PRIMARY KEY (`id_cheque`),
  ADD KEY `id_empresa` (`id_empresa`),
  ADD KEY `id_cuenta` (`id_cuenta`);

--
-- Indices de la tabla `conciliaciones`
--
ALTER TABLE `conciliaciones`
  ADD PRIMARY KEY (`id_conciliacion`),
  ADD KEY `id_empresa` (`id_empresa`),
  ADD KEY `id_cuenta` (`id_cuenta`);

--
-- Indices de la tabla `cuentas_bancarias`
--
ALTER TABLE `cuentas_bancarias`
  ADD PRIMARY KEY (`id_cuenta`),
  ADD KEY `id_empresa` (`id_empresa`);

--
-- Indices de la tabla `detalle_conciliacion`
--
ALTER TABLE `detalle_conciliacion`
  ADD PRIMARY KEY (`id_detalle`),
  ADD KEY `id_conciliacion` (`id_conciliacion`),
  ADD KEY `id_movimiento` (`id_movimiento`);

--
-- Indices de la tabla `empresa`
--
ALTER TABLE `empresa`
  ADD PRIMARY KEY (`id_empresa`);

--
-- Indices de la tabla `movimientos_bancarios`
--
ALTER TABLE `movimientos_bancarios`
  ADD PRIMARY KEY (`id_movimiento`),
  ADD KEY `id_empresa` (`id_empresa`),
  ADD KEY `id_cuenta` (`id_cuenta`);

--
-- Indices de la tabla `transferencias`
--
ALTER TABLE `transferencias`
  ADD PRIMARY KEY (`id_transferencia`),
  ADD KEY `id_empresa` (`id_empresa`),
  ADD KEY `id_cuenta_origen` (`id_cuenta_origen`),
  ADD KEY `id_cuenta_destino` (`id_cuenta_destino`);

--
-- Indices de la tabla `usuarios`
--
ALTER TABLE `usuarios`
  ADD PRIMARY KEY (`id_usuario`),
  ADD KEY `id_empresa` (`id_empresa`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `cheques`
--
ALTER TABLE `cheques`
  MODIFY `id_cheque` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `conciliaciones`
--
ALTER TABLE `conciliaciones`
  MODIFY `id_conciliacion` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `cuentas_bancarias`
--
ALTER TABLE `cuentas_bancarias`
  MODIFY `id_cuenta` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT de la tabla `detalle_conciliacion`
--
ALTER TABLE `detalle_conciliacion`
  MODIFY `id_detalle` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `empresa`
--
ALTER TABLE `empresa`
  MODIFY `id_empresa` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT de la tabla `movimientos_bancarios`
--
ALTER TABLE `movimientos_bancarios`
  MODIFY `id_movimiento` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `transferencias`
--
ALTER TABLE `transferencias`
  MODIFY `id_transferencia` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT de la tabla `usuarios`
--
ALTER TABLE `usuarios`
  MODIFY `id_usuario` int(11) NOT NULL AUTO_INCREMENT;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `cheques`
--
ALTER TABLE `cheques`
  ADD CONSTRAINT `cheques_ibfk_1` FOREIGN KEY (`id_empresa`) REFERENCES `empresa` (`id_empresa`),
  ADD CONSTRAINT `cheques_ibfk_2` FOREIGN KEY (`id_cuenta`) REFERENCES `cuentas_bancarias` (`id_cuenta`);

--
-- Filtros para la tabla `conciliaciones`
--
ALTER TABLE `conciliaciones`
  ADD CONSTRAINT `conciliaciones_ibfk_1` FOREIGN KEY (`id_empresa`) REFERENCES `empresa` (`id_empresa`),
  ADD CONSTRAINT `conciliaciones_ibfk_2` FOREIGN KEY (`id_cuenta`) REFERENCES `cuentas_bancarias` (`id_cuenta`);

--
-- Filtros para la tabla `cuentas_bancarias`
--
ALTER TABLE `cuentas_bancarias`
  ADD CONSTRAINT `cuentas_bancarias_ibfk_1` FOREIGN KEY (`id_empresa`) REFERENCES `empresa` (`id_empresa`);

--
-- Filtros para la tabla `detalle_conciliacion`
--
ALTER TABLE `detalle_conciliacion`
  ADD CONSTRAINT `detalle_conciliacion_ibfk_1` FOREIGN KEY (`id_conciliacion`) REFERENCES `conciliaciones` (`id_conciliacion`),
  ADD CONSTRAINT `detalle_conciliacion_ibfk_2` FOREIGN KEY (`id_movimiento`) REFERENCES `movimientos_bancarios` (`id_movimiento`);

--
-- Filtros para la tabla `movimientos_bancarios`
--
ALTER TABLE `movimientos_bancarios`
  ADD CONSTRAINT `movimientos_bancarios_ibfk_1` FOREIGN KEY (`id_empresa`) REFERENCES `empresa` (`id_empresa`),
  ADD CONSTRAINT `movimientos_bancarios_ibfk_2` FOREIGN KEY (`id_cuenta`) REFERENCES `cuentas_bancarias` (`id_cuenta`);

--
-- Filtros para la tabla `transferencias`
--
ALTER TABLE `transferencias`
  ADD CONSTRAINT `transferencias_ibfk_1` FOREIGN KEY (`id_empresa`) REFERENCES `empresa` (`id_empresa`),
  ADD CONSTRAINT `transferencias_ibfk_2` FOREIGN KEY (`id_cuenta_origen`) REFERENCES `cuentas_bancarias` (`id_cuenta`),
  ADD CONSTRAINT `transferencias_ibfk_3` FOREIGN KEY (`id_cuenta_destino`) REFERENCES `cuentas_bancarias` (`id_cuenta`);

--
-- Filtros para la tabla `usuarios`
--
ALTER TABLE `usuarios`
  ADD CONSTRAINT `usuarios_ibfk_1` FOREIGN KEY (`id_empresa`) REFERENCES `empresa` (`id_empresa`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
