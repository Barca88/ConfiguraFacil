-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `mydb` DEFAULT CHARACTER SET utf8 ;
USE `mydb` ;

-- -----------------------------------------------------
-- Table `mydb`.`Utilizadores`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`Utilizadores` (
  `idUtilizadores` INT NOT NULL AUTO_INCREMENT,
  `tipo` VARCHAR(1) NOT NULL,
  `nome` VARCHAR(45) NOT NULL,
  `email` VARCHAR(45) NOT NULL,
  `telemovel` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`idUtilizadores`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Configuracao`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`Configuracao` (
  `idConfiguracao` INT NOT NULL AUTO_INCREMENT,
  `Validade` VARCHAR(1) NOT NULL,
  `orcamento` FLOAT NULL,
  `Utilizadores_idUtilizadores` INT NOT NULL,
  `NomeCliente` VARCHAR(45) NOT NULL,
  `emailCliente` VARCHAR(45) NOT NULL,
  `telemovelCliente` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`idConfiguracao`),
  INDEX `fk_Configuracao_Utilizadores_idx` (`Utilizadores_idUtilizadores` ASC),
  CONSTRAINT `fk_Configuracao_Utilizadores`
    FOREIGN KEY (`Utilizadores_idUtilizadores`)
    REFERENCES `mydb`.`Utilizadores` (`idUtilizadores`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Items`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`Items` (
  `idItems` INT NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(45) NOT NULL,
  `preco` FLOAT NOT NULL,
  `stock` INT NULL,
  `tipo` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`idItems`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Configuracao/Items`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`Configuracao/Items` (
  `Configuracao_idConfiguracao` INT NOT NULL,
  `Items_idItems` INT NOT NULL,
  PRIMARY KEY (`Configuracao_idConfiguracao`, `Items_idItems`),
  INDEX `fk_Configuracao_has_Items_Items1_idx` (`Items_idItems` ASC),
  INDEX `fk_Configuracao_has_Items_Configuracao1_idx` (`Configuracao_idConfiguracao` ASC),
  CONSTRAINT `fk_Configuracao_has_Items_Configuracao1`
    FOREIGN KEY (`Configuracao_idConfiguracao`)
    REFERENCES `mydb`.`Configuracao` (`idConfiguracao`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Configuracao_has_Items_Items1`
    FOREIGN KEY (`Items_idItems`)
    REFERENCES `mydb`.`Items` (`idItems`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Incompatibilidades`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`Incompatibilidades` (
  `Items_idItems1` INT NOT NULL,
  `Items_idItems2` INT NOT NULL,
  PRIMARY KEY (`Items_idItems1`, `Items_idItems2`),
  CONSTRAINT `fk_Items_has_table_Items1`
    FOREIGN KEY (`Items_idItems1` )
    REFERENCES `mydb`.`Items` (`idItems`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Items_has_table_Items2`
    FOREIGN KEY (`Items_idItems2`)
    REFERENCES `mydb`.`Items` (`idItems`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Dependencias`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`Dependencias` (
  `Items_idItems1` INT NOT NULL,
  `Items_idItems2` INT NOT NULL,
  PRIMARY KEY (`Items_idItems1`, `Items_idItems2`),
  CONSTRAINT `fk_Items_has_table_Items10`
    FOREIGN KEY (`Items_idItems1`)
    REFERENCES `mydb`.`Items` (`idItems`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Items_has_table_Items20`
    FOREIGN KEY (`Items_idItems2`)
    REFERENCES `mydb`.`Items` (`idItems`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Pacote`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`Pacote` (
  `idPacote` INT NOT NULL AUTO_INCREMENT,
  `desconto` FLOAT NOT NULL,
  PRIMARY KEY (`idPacote`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Pacote_has_Items`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`Pacote_has_Items` (
  `Pacote_idPacote` INT NOT NULL,
  `Items_idItems` INT NOT NULL,
  PRIMARY KEY (`Pacote_idPacote`, `Items_idItems`),
  INDEX `fk_Pacote_has_Items_Items1_idx` (`Items_idItems` ASC),
  INDEX `fk_Pacote_has_Items_Pacote1_idx` (`Pacote_idPacote` ASC),
  CONSTRAINT `fk_Pacote_has_Items_Pacote1`
    FOREIGN KEY (`Pacote_idPacote`)
    REFERENCES `mydb`.`Pacote` (`idPacote`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Pacote_has_Items_Items1`
    FOREIGN KEY (`Items_idItems`)
    REFERENCES `mydb`.`Items` (`idItems`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;