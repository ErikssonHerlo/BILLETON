-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema BILLETON
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema BILLETON
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `BILLETON` DEFAULT CHARACTER SET latin1 ;
USE `BILLETON` ;

-- -----------------------------------------------------
-- Table `BILLETON`.`Usuario`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `BILLETON`.`Usuario` (
  `Codigo` INT NOT NULL AUTO_INCREMENT,
  `Nombre` VARCHAR(100) NULL,
  `DPI` VARCHAR(45) NULL,
  `Direccion` VARCHAR(150) NULL,
  `Sexo` VARCHAR(45) NULL,
  `Password` VARCHAR(100) NULL,
  `Tipo_Usuario` INT NULL,
  PRIMARY KEY (`Codigo`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `BILLETON`.`Gerente`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `BILLETON`.`Gerente` (
  `Usuario_Codigo` INT NOT NULL,
  `Nombre` VARCHAR(100) NULL,
  `Turno` VARCHAR(45) NULL,
  `Hora_Entrada` TIME NULL,
  `Hora_Salida` TIME NULL,
  `Estado` TINYINT NULL,
  PRIMARY KEY (`Usuario_Codigo`),
  CONSTRAINT `fk_Gerente_Usuario`
    FOREIGN KEY (`Usuario_Codigo`)
    REFERENCES `BILLETON`.`Usuario` (`Codigo`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `BILLETON`.`Cajero`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `BILLETON`.`Cajero` (
  `Usuario_Codigo` INT NOT NULL,
  `Nombre` VARCHAR(100) NULL,
  `Turno` VARCHAR(45) NULL,
  `Hora_Entrada` TIME NULL,
  `Hora_Salida` TIME NULL,
  `Estado` TINYINT NULL,
  PRIMARY KEY (`Usuario_Codigo`),
  CONSTRAINT `fk_Cajero_Usuario1`
    FOREIGN KEY (`Usuario_Codigo`)
    REFERENCES `BILLETON`.`Usuario` (`Codigo`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `BILLETON`.`Cliente`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `BILLETON`.`Cliente` (
  `Usuario_Codigo` INT NOT NULL,
  `Nombre` VARCHAR(100) NULL,
  `Nacimiento` DATE NULL,
  `DPI_Escaneado` MEDIUMBLOB NULL,
  `Estado` TINYINT NULL,
  PRIMARY KEY (`Usuario_Codigo`),
  CONSTRAINT `fk_Cliente_Usuario1`
    FOREIGN KEY (`Usuario_Codigo`)
    REFERENCES `BILLETON`.`Usuario` (`Codigo`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `BILLETON`.`Historial_Gerente`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `BILLETON`.`Historial_Gerente` (
  `idHistorial_Gerente` INT NOT NULL AUTO_INCREMENT,
  `Usuario_Codigo` INT NOT NULL,
  `Nombre` VARCHAR(100) NULL,
  `DPI` VARCHAR(45) NULL,
  `Direccion` VARCHAR(150) NULL,
  `Sexo` VARCHAR(45) NULL,
  `Password` VARCHAR(100) NULL,
  `Turno` VARCHAR(45) NULL,
  `Hora_Entrada` TIME NULL,
  `Hora_Salida` TIME NULL,
  `Tipo` VARCHAR(45) NULL,
  `Fecha_Cambio` DATE NULL,
  PRIMARY KEY (`idHistorial_Gerente`),
  INDEX `fk_Historial_Gerente_Usuario1_idx` (`Usuario_Codigo` ASC) VISIBLE,
  CONSTRAINT `fk_Historial_Gerente_Usuario1`
    FOREIGN KEY (`Usuario_Codigo`)
    REFERENCES `BILLETON`.`Usuario` (`Codigo`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `BILLETON`.`Historial_Cajero`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `BILLETON`.`Historial_Cajero` (
  `idHistorial_Cajero` INT NOT NULL AUTO_INCREMENT,
  `Usuario_Codigo` INT NOT NULL,
  `Nombre` VARCHAR(100) NULL,
  `DPI` VARCHAR(45) NULL,
  `Direccion` VARCHAR(150) NULL,
  `Sexo` VARCHAR(45) NULL,
  `Password` VARCHAR(100) NULL,
  `Turno` VARCHAR(45) NULL,
  `Hora_Entrada` TIME NULL,
  `Hora_Salida` TIME NULL,
  `Tipo` VARCHAR(45) NULL,
  `Fecha_Cambio` DATE NULL,
  PRIMARY KEY (`idHistorial_Cajero`),
  INDEX `fk_Historial_Cajero_Usuario1_idx` (`Usuario_Codigo` ASC) VISIBLE,
  CONSTRAINT `fk_Historial_Cajero_Usuario1`
    FOREIGN KEY (`Usuario_Codigo`)
    REFERENCES `BILLETON`.`Usuario` (`Codigo`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `BILLETON`.`Historial_Cliente`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `BILLETON`.`Historial_Cliente` (
  `idHistorial_Cliente` INT NOT NULL AUTO_INCREMENT,
  `Usuario_Codigo` INT NOT NULL,
  `Nombre` VARCHAR(100) NULL,
  `DPI` VARCHAR(45) NULL,
  `Direccion` VARCHAR(150) NULL,
  `Sexo` VARCHAR(45) NULL,
  `Password` VARCHAR(100) NULL,
  `Nacimiento` DATE NULL,
  `DPI_Escaneado` MEDIUMBLOB NULL,
  `Tipo` VARCHAR(45) NULL,
  `Fecha_Cambio` DATE NULL,
  PRIMARY KEY (`idHistorial_Cliente`),
  INDEX `fk_Historial_Cliente_Usuario1_idx` (`Usuario_Codigo` ASC) VISIBLE,
  CONSTRAINT `fk_Historial_Cliente_Usuario1`
    FOREIGN KEY (`Usuario_Codigo`)
    REFERENCES `BILLETON`.`Usuario` (`Codigo`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `BILLETON`.`Cuenta`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `BILLETON`.`Cuenta` (
  `No_Cuenta` INT NOT NULL AUTO_INCREMENT,
  `Fecha_Creacion` DATE NULL,
  `Saldo_Cuenta` DOUBLE NULL,
  `Cliente_Usuario_Codigo` INT NOT NULL,
  PRIMARY KEY (`No_Cuenta`),
  INDEX `fk_Cuenta_Cliente1_idx` (`Cliente_Usuario_Codigo` ASC) VISIBLE,
  CONSTRAINT `fk_Cuenta_Cliente1`
    FOREIGN KEY (`Cliente_Usuario_Codigo`)
    REFERENCES `BILLETON`.`Cliente` (`Usuario_Codigo`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `BILLETON`.`Transaccion`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `BILLETON`.`Transaccion` (
  `Codigo` INT NOT NULL AUTO_INCREMENT,
  `Cuenta_No_Cuenta` INT NOT NULL,
  `Fecha` DATE NULL,
  `Hora` TIME NULL,
  `Tipo` VARCHAR(45) NULL,
  `Monto` DOUBLE NULL,
  `Saldo_Cuenta_Anterior` DOUBLE NULL,
  `Saldo_Cuenta_Actual` DOUBLE NULL,
  `Cajero_Usuario_Codigo` INT NOT NULL,
  PRIMARY KEY (`Codigo`),
  INDEX `fk_Transaccion_Cuenta1_idx` (`Cuenta_No_Cuenta` ASC) VISIBLE,
  INDEX `fk_Transaccion_Cajero1_idx` (`Cajero_Usuario_Codigo` ASC) VISIBLE,
  CONSTRAINT `fk_Transaccion_Cuenta1`
    FOREIGN KEY (`Cuenta_No_Cuenta`)
    REFERENCES `BILLETON`.`Cuenta` (`No_Cuenta`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Transaccion_Cajero1`
    FOREIGN KEY (`Cajero_Usuario_Codigo`)
    REFERENCES `BILLETON`.`Cajero` (`Usuario_Codigo`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `BILLETON`.`Asociacion_Cuenta`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `BILLETON`.`Asociacion_Cuenta` (
  `idAsociacion_Cuenta` INT NOT NULL AUTO_INCREMENT,
  `Codigo_ClienteA` INT NOT NULL,
  `CuentaA` INT NOT NULL,
  `ClienteA` VARCHAR(100) NULL,
  `DPI_ClienteA` VARCHAR(45) NULL,
  `Codigo_ClienteB` INT NOT NULL,
  `CuentaB` INT NOT NULL,
  `ClienteB` VARCHAR(100) NULL,
  `DPI_ClienteB` VARCHAR(45) NULL,
  PRIMARY KEY (`idAsociacion_Cuenta`),
  INDEX `fk_Asociacion_Cuenta_Cuenta1_idx` (`CuentaA` ASC) VISIBLE,
  INDEX `fk_Asociacion_Cuenta_Cuenta2_idx` (`CuentaB` ASC) VISIBLE,
  INDEX `fk_Asociacion_Cuenta_Cliente1_idx` (`Codigo_ClienteA` ASC) VISIBLE,
  INDEX `fk_Asociacion_Cuenta_Cliente2_idx` (`Codigo_ClienteB` ASC) VISIBLE,
  CONSTRAINT `fk_Asociacion_Cuenta_Cuenta1`
    FOREIGN KEY (`CuentaA`)
    REFERENCES `BILLETON`.`Cuenta` (`No_Cuenta`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Asociacion_Cuenta_Cuenta2`
    FOREIGN KEY (`CuentaB`)
    REFERENCES `BILLETON`.`Cuenta` (`No_Cuenta`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Asociacion_Cuenta_Cliente1`
    FOREIGN KEY (`Codigo_ClienteA`)
    REFERENCES `BILLETON`.`Cliente` (`Usuario_Codigo`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Asociacion_Cuenta_Cliente2`
    FOREIGN KEY (`Codigo_ClienteB`)
    REFERENCES `BILLETON`.`Cliente` (`Usuario_Codigo`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `BILLETON`.`Historial_Asociacion`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `BILLETON`.`Historial_Asociacion` (
  `idHistorial_Asociacion` INT NOT NULL AUTO_INCREMENT,
  `Codigo_ClienteA` INT NOT NULL,
  `CuentaA` INT NOT NULL,
  `ClienteA` VARCHAR(100) NULL,
  `DPI_ClienteA` VARCHAR(45) NULL,
  `Codigo_ClienteB` INT NOT NULL,
  `CuentaB` INT NOT NULL,
  `ClienteB` VARCHAR(100) NULL,
  `DPI_ClienteB` VARCHAR(45) NULL,
  `Fecha_Solicitud` DATE NULL,
  `No_Intento` INT NULL,
  `Estado` VARCHAR(45) NULL,
  PRIMARY KEY (`idHistorial_Asociacion`),
  INDEX `fk_Historial_Asociacion_Cuenta1_idx` (`CuentaA` ASC) VISIBLE,
  INDEX `fk_Historial_Asociacion_Cuenta2_idx` (`CuentaB` ASC) VISIBLE,
  INDEX `fk_Historial_Asociacion_Cliente1_idx` (`Codigo_ClienteA` ASC) VISIBLE,
  INDEX `fk_Historial_Asociacion_Cliente2_idx` (`Codigo_ClienteB` ASC) VISIBLE,
  CONSTRAINT `fk_Historial_Asociacion_Cuenta1`
    FOREIGN KEY (`CuentaA`)
    REFERENCES `BILLETON`.`Cuenta` (`No_Cuenta`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Historial_Asociacion_Cuenta2`
    FOREIGN KEY (`CuentaB`)
    REFERENCES `BILLETON`.`Cuenta` (`No_Cuenta`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Historial_Asociacion_Cliente1`
    FOREIGN KEY (`Codigo_ClienteA`)
    REFERENCES `BILLETON`.`Cliente` (`Usuario_Codigo`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Historial_Asociacion_Cliente2`
    FOREIGN KEY (`Codigo_ClienteB`)
    REFERENCES `BILLETON`.`Cliente` (`Usuario_Codigo`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `BILLETON`.`Configuracion_Reportes`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `BILLETON`.`Configuracion_Reportes` (
  `idConfiguracion_Reportes` INT NOT NULL AUTO_INCREMENT,
  `Limite_Reporte2` DOUBLE NULL,
  `Limite_Reporte3` DOUBLE NULL,
  `Gerente_Usuario_Codigo` INT NOT NULL,
  PRIMARY KEY (`idConfiguracion_Reportes`),
  INDEX `fk_Configuracion_Reportes_Gerente1_idx` (`Gerente_Usuario_Codigo` ASC) VISIBLE,
  CONSTRAINT `fk_Configuracion_Reportes_Gerente1`
    FOREIGN KEY (`Gerente_Usuario_Codigo`)
    REFERENCES `BILLETON`.`Gerente` (`Usuario_Codigo`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
