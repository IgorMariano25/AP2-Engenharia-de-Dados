-- MySQL Script generated by MySQL Workbench
-- Tue Jun 13 14:41:20 2023
-- Model: New Model    Version: 1.0
-- MySQL Workbench Forward Engineering

DROP DATABASE AP2;

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema AP2
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema AP2
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `AP2` DEFAULT CHARACTER SET utf8 ;
USE `AP2` ;

-- -----------------------------------------------------
-- Table `AP2`.`Autor`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `AP2`.`Autor` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `Nome` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`, `Nome`));

-- -----------------------------------------------------
-- Table `AP2`.`Compositor`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `AP2`.`Compositor` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `Nome` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`, `Nome`));

-- -----------------------------------------------------
-- Table `AP2`.`Categoria`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `AP2`.`Categoria` (
  `Nome` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`Nome`));


-- -----------------------------------------------------
-- Table `AP2`.`Musica`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `AP2`.`Musica` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `Título` VARCHAR(45) NOT NULL,
  `Letra` VARCHAR(500) NOT NULL,
  `Data_Lancamento` DATE NOT NULL,
  `Duracao_segundos` INT NOT NULL,
  `Censura` INT NOT NULL,
  `fk_categoria` VARCHAR(45) NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `musica_categoria`
    FOREIGN KEY (`fk_categoria`)
    REFERENCES `AP2`.`Categoria` (`Nome`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);


-- -----------------------------------------------------
-- Table `AP2`.`Playlist`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `AP2`.`Playlist` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `Data_criacao` DATE NOT NULL,
  `titulo` VARCHAR(45) NOT NULL,
  `visibilidade` TINYINT NOT NULL,
  `fk_categoria` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_categoria`
    FOREIGN KEY (`fk_categoria`)
    REFERENCES `AP2`.`Categoria` (`Nome`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);


-- -----------------------------------------------------
-- Table `AP2`.`Usuario`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `AP2`.`Usuario` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `Nome` VARCHAR(45) NOT NULL,
  `Data_Nascimento` DATE NULL,
  `cpf` CHAR(11) NOT NULL,
  `numero_cartao` INT NOT NULL,
  `fk_playlist` INT NOT NULL,
  PRIMARY KEY (`id`, `cpf`),
  CONSTRAINT `fk_playlist`
    FOREIGN KEY (`fk_playlist`)
    REFERENCES `AP2`.`Playlist` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);


-- -----------------------------------------------------
-- Table `AP2`.`musica_playlist`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `AP2`.`musica_playlist` (
  `fk_musica` INT NOT NULL,
  `fk_playlist` INT NOT NULL,
  PRIMARY KEY (`fk_playlist`, `fk_musica`),
  CONSTRAINT `musica_playlist`
    FOREIGN KEY (`fk_musica`)
    REFERENCES `AP2`.`Musica` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `playlist_musica`
    FOREIGN KEY (`fk_playlist`)
    REFERENCES `AP2`.`Playlist` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);


-- -----------------------------------------------------
-- Table `AP2`.`autor_musica`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `AP2`.`autor_musica` (
  `fk_autor` INT NOT NULL,
  `fk_musica` INT NOT NULL,
  PRIMARY KEY (`fk_autor`, `fk_musica`),
  CONSTRAINT `fk_autor_musica`
    FOREIGN KEY (`fk_autor`)
    REFERENCES `AP2`.`autor` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_musica_autor`
    FOREIGN KEY (`fk_musica`)
    REFERENCES `AP2`.`musica` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE);

-- -----------------------------------------------------
-- Table `AP2`.`compositor_musica`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `AP2`.`compositor_musica` (
  `fk_compositor` INT NOT NULL,
  `fk_musica` INT NOT NULL,
  PRIMARY KEY (`fk_compositor`, `fk_musica`),
  CONSTRAINT `compositor_musica`
    FOREIGN KEY (`fk_compositor`)
    REFERENCES `AP2`.`Compositor` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `muscia_compositor`
    FOREIGN KEY (`fk_musica`)
    REFERENCES `AP2`.`Musica` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE);


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
