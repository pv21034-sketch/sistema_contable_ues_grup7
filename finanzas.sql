-- phpMyAdmin SQL Dump
-- version 4.9.2
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 03-05-2026 a las 01:21:20
-- Versión del servidor: 10.4.11-MariaDB
-- Versión de PHP: 7.2.26

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `finanzas`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `cuentas`
--

CREATE TABLE `cuentas` (
  `id` int(11) NOT NULL,
  `nombre` varchar(100) NOT NULL,
  `saldo_inicial` decimal(10,2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `cuentas`
--

INSERT INTO `cuentas` (`id`, `nombre`, `saldo_inicial`) VALUES
(1, 'Banco Atlántida', '5000.00'),
(2, 'BAC', '3000.00'),
(3, 'Ficohsa', '8000.00'),
(4, 'Davivienda', '10000.00');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `movimientos`
--

CREATE TABLE `movimientos` (
  `id` int(11) NOT NULL,
  `fecha` date NOT NULL,
  `tipo` enum('INGRESO','EGRESO') NOT NULL,
  `monto` decimal(10,2) NOT NULL,
  `cuenta_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `movimientos`
--

INSERT INTO `movimientos` (`id`, `fecha`, `tipo`, `monto`, `cuenta_id`) VALUES
(1, '2026-05-01', 'INGRESO', '1000.00', 1),
(2, '2026-05-01', 'EGRESO', '500.00', 1),
(3, '2026-05-02', 'INGRESO', '200.00', 2),
(4, '2026-05-02', 'EGRESO', '100.00', 2),
(5, '2026-05-02', 'INGRESO', '300.00', 1),
(6, '2026-05-01', 'INGRESO', '1500.00', 1),
(7, '2026-05-01', 'EGRESO', '300.00', 1),
(8, '2026-05-01', 'EGRESO', '200.00', 1),
(9, '2026-05-02', 'INGRESO', '300.00', 1),
(10, '2026-05-02', 'EGRESO', '100.00', 1),
(11, '2026-05-02', 'INGRESO', '500.00', 1),
(12, '2026-05-03', 'INGRESO', '2000.00', 1),
(13, '2026-05-03', 'EGRESO', '400.00', 1),
(14, '2026-05-04', 'INGRESO', '700.00', 1),
(15, '2026-05-04', 'EGRESO', '150.00', 1),
(16, '2026-05-05', 'INGRESO', '1200.00', 1),
(17, '2026-05-05', 'EGRESO', '600.00', 1),
(18, '2026-05-01', 'INGRESO', '500.00', 2),
(19, '2026-05-01', 'EGRESO', '100.00', 2),
(20, '2026-05-02', 'INGRESO', '900.00', 2),
(21, '2026-05-02', 'EGRESO', '300.00', 2),
(22, '2026-05-03', 'INGRESO', '400.00', 2),
(23, '2026-05-03', 'EGRESO', '200.00', 2),
(24, '2026-05-04', 'INGRESO', '1000.00', 2),
(25, '2026-05-04', 'EGRESO', '250.00', 2),
(26, '2026-05-05', 'INGRESO', '800.00', 2),
(27, '2026-05-05', 'EGRESO', '400.00', 2),
(28, '2026-05-01', 'INGRESO', '1000.00', 3),
(29, '2026-05-02', 'EGRESO', '200.00', 3),
(30, '2026-05-03', 'INGRESO', '500.00', 3),
(31, '2026-05-01', 'INGRESO', '2000.00', 4),
(32, '2026-05-02', 'EGRESO', '300.00', 4),
(33, '2026-05-03', 'INGRESO', '800.00', 4);

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `cuentas`
--
ALTER TABLE `cuentas`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `movimientos`
--
ALTER TABLE `movimientos`
  ADD PRIMARY KEY (`id`),
  ADD KEY `cuenta_id` (`cuenta_id`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `cuentas`
--
ALTER TABLE `cuentas`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT de la tabla `movimientos`
--
ALTER TABLE `movimientos`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=34;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `movimientos`
--
ALTER TABLE `movimientos`
  ADD CONSTRAINT `movimientos_ibfk_1` FOREIGN KEY (`cuenta_id`) REFERENCES `cuentas` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
