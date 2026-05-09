-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 08-05-2026 a las 21:19:05
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
-- Base de datos: `sistemas_contables_grup4`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `cheques`
--

CREATE TABLE `cheques` (
  `id_cheque` int(11) NOT NULL,
  `id_empresa` int(11) NOT NULL,
  `id_cuenta` int(11) NOT NULL,
  `numero_cheque` varchar(30) NOT NULL,
  `beneficiario` varchar(150) NOT NULL,
  `monto` decimal(15,2) NOT NULL,
  `fecha` date NOT NULL,
  `concepto` varchar(200) DEFAULT NULL,
  `estado` varchar(20) DEFAULT 'EMITIDO'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `conciliaciones`
--

CREATE TABLE `conciliaciones` (
  `id_conciliacion` int(11) NOT NULL,
  `id_empresa` int(11) NOT NULL,
  `fecha` date NOT NULL,
  `observacion` varchar(200) DEFAULT NULL,
  `estado` varchar(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `cuentas_bancarias`
--

CREATE TABLE `cuentas_bancarias` (
  `id_cuenta` int(11) NOT NULL,
  `id_empresa` int(11) NOT NULL,
  `banco` varchar(100) NOT NULL,
  `numero_cuenta` varchar(50) NOT NULL,
  `nombre` varchar(100) DEFAULT NULL,
  `apellido` varchar(100) DEFAULT NULL,
  `tipo_cuenta` varchar(50) NOT NULL,
  `saldo_inicial` decimal(10,2) NOT NULL,
  `saldo_actual` decimal(15,2) DEFAULT 0.00,
  `estado` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `cuentas_bancarias`
--

INSERT INTO `cuentas_bancarias` (`id_cuenta`, `id_empresa`, `banco`, `numero_cuenta`, `nombre`, `apellido`, `tipo_cuenta`, `saldo_inicial`, `saldo_actual`, `estado`) VALUES
(1, 1, 'Banco Agricola', '7001234567', 'Josue', 'Perez', '', 0.00, 100.00, 'Activo'),
(2, 1, 'Banco Cuscatlan', '7009876543', 'Maria', 'Gomez', '', 0.00, 100.00, 'Activo'),
(3, 1, 'Banco Davivienda', '7009872078', 'Luis', 'Peñate', '', 0.00, 10.00, 'Activo');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `detalle_conciliacion`
--

CREATE TABLE `detalle_conciliacion` (
  `id_detalle` int(11) NOT NULL,
  `id_conciliacion` int(11) NOT NULL,
  `id_movimiento` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `empresa`
--

CREATE TABLE `empresa` (
  `id_empresa` int(11) NOT NULL,
  `nombre_comercial` varchar(100) NOT NULL,
  `razon_social` varchar(150) NOT NULL,
  `nrc` varchar(20) NOT NULL,
  `nit` varchar(20) NOT NULL,
  `giro` varchar(100) NOT NULL,
  `direccion` varchar(200) DEFAULT NULL,
  `telefono` varchar(20) DEFAULT NULL,
  `correo` varchar(100) DEFAULT NULL,
  `estado` varchar(20) DEFAULT 'ACTIVA'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `empresa`
--

INSERT INTO `empresa` (`id_empresa`, `nombre_comercial`, `razon_social`, `nrc`, `nit`, `giro`, `direccion`, `telefono`, `correo`, `estado`) VALUES
(1, 'Empresa de Prueba', 'Empresa A S.A. de C.V.', '12345-6', '0614-010101-101-1', 'Comercio', 'San Salvador', '2222-1111', 'empresaa@mail.com', 'ACTIVA'),
(2, 'Empresa B', 'Empresa B S.A. de C.V.', '98765-4', '0614-020202-202-2', 'Servicios', 'Santa Ana', '2333-2222', 'empresab@mail.com', 'ACTIVA');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `movimientos_bancarios`
--

CREATE TABLE `movimientos_bancarios` (
  `id_movimiento` int(11) NOT NULL,
  `id_empresa` int(11) NOT NULL,
  `id_cuenta` int(11) NOT NULL,
  `fecha` date NOT NULL,
  `tipo` varchar(20) NOT NULL,
  `monto` decimal(15,2) NOT NULL,
  `concepto` varchar(200) DEFAULT NULL,
  `origen` varchar(50) DEFAULT NULL,
  `id_origen` int(11) DEFAULT NULL,
  `estado_conciliacion` varchar(20) DEFAULT 'PENDIENTE'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `movimientos_bancarios`
--

INSERT INTO `movimientos_bancarios` (`id_movimiento`, `id_empresa`, `id_cuenta`, `fecha`, `tipo`, `monto`, `concepto`, `origen`, `id_origen`, `estado_conciliacion`) VALUES
(1, 1, 1, '2026-05-08', 'EGRESO', 50.00, 'PRUEBA CONCILIACION', 'TRANSFERENCIA', 1, 'PENDIENTE');

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
  `id_empresa` int(11) NOT NULL,
  `nombre` varchar(100) NOT NULL,
  `usuario` varchar(50) NOT NULL,
  `clave` varchar(100) NOT NULL,
  `rol` varchar(50) NOT NULL,
  `estado` varchar(20) DEFAULT 'ACTIVO'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `usuarios`
--

INSERT INTO `usuarios` (`id_usuario`, `id_empresa`, `nombre`, `usuario`, `clave`, `rol`, `estado`) VALUES
(1, 1, 'Jonathan', 'admin', '1234', 'ADMIN', 'ACTIVO'),
(2, 2, 'Usuario B', 'adminb', '1234', 'ADMIN', 'ACTIVO');

-- --------------------------------------------------------

--
-- Estructura Stand-in para la vista `vista_disponibilidad_diaria`
-- (Véase abajo para la vista actual)
--
CREATE TABLE `vista_disponibilidad_diaria` (
`id_cuenta` int(11)
,`banco` varchar(100)
,`numero_cuenta` varchar(50)
,`fecha` date
,`saldo_inicial` decimal(10,2)
,`ingresos` decimal(37,2)
,`egresos` decimal(37,2)
,`saldo_disponible` decimal(39,2)
);

-- --------------------------------------------------------

--
-- Estructura para la vista `vista_disponibilidad_diaria`
--
DROP TABLE IF EXISTS `vista_disponibilidad_diaria`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `vista_disponibilidad_diaria`  AS SELECT `c`.`id_cuenta` AS `id_cuenta`, `c`.`banco` AS `banco`, `c`.`numero_cuenta` AS `numero_cuenta`, cast(`m`.`fecha` as date) AS `fecha`, `c`.`saldo_inicial` AS `saldo_inicial`, coalesce(sum(case when `m`.`tipo` = 'INGRESO' then `m`.`monto` else 0 end),0) AS `ingresos`, coalesce(sum(case when `m`.`tipo` = 'EGRESO' then `m`.`monto` else 0 end),0) AS `egresos`, `c`.`saldo_inicial`+ coalesce(sum(case when `m`.`tipo` = 'INGRESO' then `m`.`monto` else 0 end),0) - coalesce(sum(case when `m`.`tipo` = 'EGRESO' then `m`.`monto` else 0 end),0) AS `saldo_disponible` FROM (`cuentas_bancarias` `c` left join `movimientos_bancarios` `m` on(`c`.`id_cuenta` = `m`.`id_cuenta`)) GROUP BY `c`.`id_cuenta`, `c`.`banco`, `c`.`numero_cuenta`, cast(`m`.`fecha` as date), `c`.`saldo_inicial` ;

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `cheques`
--
ALTER TABLE `cheques`
  ADD PRIMARY KEY (`id_cheque`),
  ADD UNIQUE KEY `numero_cheque` (`numero_cheque`);

--
-- Indices de la tabla `conciliaciones`
--
ALTER TABLE `conciliaciones`
  ADD PRIMARY KEY (`id_conciliacion`);

--
-- Indices de la tabla `cuentas_bancarias`
--
ALTER TABLE `cuentas_bancarias`
  ADD PRIMARY KEY (`id_cuenta`),
  ADD UNIQUE KEY `numero_cuenta` (`numero_cuenta`);

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
  ADD PRIMARY KEY (`id_empresa`),
  ADD UNIQUE KEY `nrc` (`nrc`),
  ADD UNIQUE KEY `nit` (`nit`);

--
-- Indices de la tabla `movimientos_bancarios`
--
ALTER TABLE `movimientos_bancarios`
  ADD PRIMARY KEY (`id_movimiento`);

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
  ADD UNIQUE KEY `usuario` (`usuario`),
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
  MODIFY `id_empresa` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT de la tabla `movimientos_bancarios`
--
ALTER TABLE `movimientos_bancarios`
  MODIFY `id_movimiento` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT de la tabla `transferencias`
--
ALTER TABLE `transferencias`
  MODIFY `id_transferencia` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT de la tabla `usuarios`
--
ALTER TABLE `usuarios`
  MODIFY `id_usuario` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `detalle_conciliacion`
--
ALTER TABLE `detalle_conciliacion`
  ADD CONSTRAINT `detalle_conciliacion_ibfk_1` FOREIGN KEY (`id_conciliacion`) REFERENCES `conciliaciones` (`id_conciliacion`),
  ADD CONSTRAINT `detalle_conciliacion_ibfk_2` FOREIGN KEY (`id_movimiento`) REFERENCES `movimientos_bancarios` (`id_movimiento`);

--
-- Filtros para la tabla `usuarios`
--
ALTER TABLE `usuarios`
  ADD CONSTRAINT `usuarios_ibfk_1` FOREIGN KEY (`id_empresa`) REFERENCES `empresa` (`id_empresa`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
