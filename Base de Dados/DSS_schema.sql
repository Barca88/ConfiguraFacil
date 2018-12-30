-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema ConfiguraFacil
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema ConfiguraFacil
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `ConfiguraFacil` ;
USE `ConfiguraFacil` ;

-- -----------------------------------------------------
-- Table `ConfiguraFacil`.`Utilizador`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ConfiguraFacil`.`Utilizador` (
  `idUtilizador` INT NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(45) NOT NULL,
  `password` VARCHAR(45) NOT NULL,
  `tipo` VARCHAR(1) NOT NULL,
  `email` VARCHAR(45) NOT NULL,
  `telemovel` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`idUtilizador`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `ConfiguraFacil`.`Cliente`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ConfiguraFacil`.`Cliente` (
  `idCliente` INT NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(45) NULL,
  `email` VARCHAR(45) NULL,
  `telemovel` VARCHAR(45) NULL,
  PRIMARY KEY (`idCliente`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `ConfiguraFacil`.`Configuracao`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ConfiguraFacil`.`Configuracao` (
  `idConfiguracao` INT NOT NULL AUTO_INCREMENT,
  `Validade` VARCHAR(1) NOT NULL,
  `Orcamento` FLOAT NULL,
  `Preco` FLOAT NOT NULL, 
  `Utilizador_idUtilizador` INT NOT NULL,
  `Data` VARCHAR(64) NOT NULL,
  `Cliente_idCliente` INT NOT NULL,
  PRIMARY KEY (`idConfiguracao`, `Cliente_idCliente`),
  INDEX `fk_Configuracao_Utilizador_idx` (`Utilizador_idUtilizador` ASC),
  INDEX `fk_Configuracao_Cliente1_idx` (`Cliente_idCliente` ASC),
  CONSTRAINT `fk_Configuracao_Utilizador`
    FOREIGN KEY (`Utilizador_idUtilizador`)
    REFERENCES `ConfiguraFacil`.`Utilizador` (`idUtilizador`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Configuracao_Cliente1`
    FOREIGN KEY (`Cliente_idCliente`)
    REFERENCES `ConfiguraFacil`.`Cliente` (`idCliente`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `ConfiguraFacil`.`Item`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ConfiguraFacil`.`Item` (
  `idItem` INT NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(64) NOT NULL,
  `preco` FLOAT NOT NULL,
  `stock` INT NULL,
  `tipo` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`idItem`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `ConfiguraFacil`.`Configuracao/Item`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ConfiguraFacil`.`Configuracao_Item` (
  `Configuracao_idConfiguracao` INT NOT NULL,
  `Item_idItem` INT NOT NULL,
  PRIMARY KEY (`Configuracao_idConfiguracao`, `Item_idItem`),
  INDEX `fk_Configuracao_has_Item_Item1_idx` (`Item_idItem` ASC),
  INDEX `fk_Configuracao_has_Item_Configuracao1_idx` (`Configuracao_idConfiguracao` ASC),
  CONSTRAINT `fk_Configuracao_has_Item_Configuracao1`
    FOREIGN KEY (`Configuracao_idConfiguracao`)
    REFERENCES `ConfiguraFacil`.`Configuracao` (`idConfiguracao`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Configuracao_has_Item_Item1`
    FOREIGN KEY (`Item_idItem`)
    REFERENCES `ConfiguraFacil`.`Item` (`idItem`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `ConfiguraFacil`.`Incompatibilidade`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ConfiguraFacil`.`Incompatibilidade` (
  `Item_idItem1` INT NOT NULL,
  `Item_idItem2` INT NOT NULL,
  PRIMARY KEY (`Item_idItem1`, `Item_idItem2`),
  INDEX `fk_incomp1` (`Item_idItem1` ASC),
  INDEX `fk_incomp2` (`Item_idItem2` ASC),
  CONSTRAINT `fk_Item_has_table_Item1`
    FOREIGN KEY (`Item_idItem1`)
    REFERENCES `ConfiguraFacil`.`Item` (`idItem`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Item_has_table_Item2`
    FOREIGN KEY (`Item_idItem2`)
    REFERENCES `ConfiguraFacil`.`Item` (`idItem`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `ConfiguraFacil`.`Dependencia`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ConfiguraFacil`.`Dependencia` (
  `Item_idItem1` INT NOT NULL,
  `Item_idItem2` INT NOT NULL,
  PRIMARY KEY (`Item_idItem1`, `Item_idItem2`),
  INDEX `fk_depend1` (`Item_idItem1` ASC),
  INDEX `fk_depend2` (`Item_idItem2` ASC),
  CONSTRAINT `fk_Item_has_table_Item10`
    FOREIGN KEY (`Item_idItem1`)
    REFERENCES `ConfiguraFacil`.`Item` (`idItem`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Item_has_table_Item20`
    FOREIGN KEY (`Item_idItem2`)
    REFERENCES `ConfiguraFacil`.`Item` (`idItem`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `ConfiguraFacil`.`Pacote`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ConfiguraFacil`.`Pacote` (
  `idPacote` INT NOT NULL AUTO_INCREMENT,
  `desconto` FLOAT NOT NULL,
  `nome` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`idPacote`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `ConfiguraFacil`.`Pacote/Itens`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ConfiguraFacil`.`Pacote_Item` (
  `Pacote_idPacote` INT NOT NULL,
  `Item_idItem` INT NOT NULL,
  PRIMARY KEY (`Pacote_idPacote`, `Item_idItem`),
  INDEX `fk_Pacote_has_Item_Item1_idx` (`Item_idItem` ASC),
  INDEX `fk_Pacote_has_Item_Pacote1_idx` (`Pacote_idPacote` ASC),
  CONSTRAINT `fk_Pacote_has_Item_Pacote1`
    FOREIGN KEY (`Pacote_idPacote`)
    REFERENCES `ConfiguraFacil`.`Pacote` (`idPacote`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Pacote_has_Item_Item1`
    FOREIGN KEY (`Item_idItem`)
    REFERENCES `ConfiguraFacil`.`Item` (`idItem`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;