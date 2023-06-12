DROP DATABASE IF EXISTS agenda;

CREATE DATABASE IF NOT EXISTS agenda;

USE agenda;

CREATE TABLE IF NOT EXISTS Pessoa (
    `id` int NOT NULL AUTO_INCREMENT,
    `nome` varchar(150) NOT NULL,
    `cpf` char(11) NOT NULL UNIQUE,
	`data_nascimento` date NOT NULL,
    `idade` int NOT NULL,
CONSTRAINT PRIMARY KEY (`id`));


CREATE TABLE IF NOT EXISTS Telefone (
    `id` int NOT NULL AUTO_INCREMENT,
    `tipo` int NOT NULL,
    `codigo_pais` int NOT NULL,
	`codigo_area` int NOT NULL,
    `numero` int NOT NULL,
    `fk_pessoa` int NOT NULL,
CONSTRAINT PRIMARY KEY (`id`),
CONSTRAINT `telefone_pessoa`
    FOREIGN KEY (`fk_pessoa`)
    REFERENCES `Pessoa` (`id`)
    ON UPDATE CASCADE
    ON DELETE CASCADE);
