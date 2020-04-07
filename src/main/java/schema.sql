-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS = @@UNIQUE_CHECKS, UNIQUE_CHECKS = 0;
SET @OLD_FOREIGN_KEY_CHECKS = @@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS = 0;
SET @OLD_SQL_MODE = @@SQL_MODE, SQL_MODE =
        'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema chess
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema chess
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `chess` DEFAULT CHARACTER SET utf8;
USE `chess`;

-- -----------------------------------------------------
-- Table `chess`.`room`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `chess`.`room`
(
    `id`            INT         NOT NULL,
    `black_user_id` INT         NOT NULL,
    `white_user_id` INT         NOT NULL,
    `is_end`        TINYINT     NOT NULL,
    `name`          VARCHAR(21) NOT NULL,
    PRIMARY KEY (`id`)
)
    ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `chess`.`move`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `chess`.`move`
(
    `id`      INT        NOT NULL,
    `room_id` INT        NOT NULL,
    `source`  VARCHAR(2) NOT NULL,
    `target`  VARCHAR(2) NOT NULL,
    PRIMARY KEY (`id`),
    INDEX `room_id_idx` (`room_id` ASC),
    CONSTRAINT `room_id`
        FOREIGN KEY (`room_id`)
            REFERENCES `chess`.`room` (`id`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION
)
    ENGINE = InnoDB;


SET SQL_MODE = @OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS = @OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS = @OLD_UNIQUE_CHECKS;
