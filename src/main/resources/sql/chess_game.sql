create user 'inbi'@'localhost' identified by '1234';

grant all privileges on *.* to 'inbi'@'localhost';

flush privileges;

CREATE DATABASE chess_game DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;

use chess_game;

show tables;

-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema chess_game
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema chess_game
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `chess_game` DEFAULT CHARACTER SET utf8 ;
USE `chess_game` ;

-- -----------------------------------------------------
-- Table `chess_game`.`chess_room`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `chess_game`.`chess_room` (
  `id` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
  `title` VARCHAR(255) NOT NULL,
  `current_turn_team_color` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `current_turn_team_color_UNIQUE` (`current_turn_team_color` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `chess_game`.`piece`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `chess_game`.`piece` (
  `id` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(255) NOT NULL,
  `color` VARCHAR(255) NOT NULL,
  `player_id` BIGINT UNSIGNED NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `name_UNIQUE` (`name` ASC) VISIBLE,
  INDEX `fk_piece_player1_idx` (`player_id` ASC) VISIBLE,
  CONSTRAINT `fk_piece_player1`
    FOREIGN KEY (`player_id`)
    REFERENCES `chess_game`.`player` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `chess_game`.`player`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `chess_game`.`player` (
  `id` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
  `chess_room_id` BIGINT UNSIGNED NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_player_chess_room1_idx` (`chess_room_id` ASC) VISIBLE,
  CONSTRAINT `fk_player_chess_room1`
    FOREIGN KEY (`chess_room_id`)
    REFERENCES `chess_game`.`chess_room` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `chess_game`.`position`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `chess_game`.`position` (
  `id` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
  `file_value` VARCHAR(255) NOT NULL,
  `rank_value` VARCHAR(255) NOT NULL,
  `piece_id` BIGINT UNSIGNED NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `file_value_UNIQUE` (`file_value` ASC) VISIBLE,
  UNIQUE INDEX `rank_value_UNIQUE` (`rank_value` ASC) VISIBLE,
  INDEX `fk_position_piece_idx` (`piece_id` ASC) VISIBLE,
  CONSTRAINT `fk_position_piece`
    FOREIGN KEY (`piece_id`)
    REFERENCES `chess_game`.`piece` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;



