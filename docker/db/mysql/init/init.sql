CREATE TABLE `chess`.`state`
(
  `name` VARCHAR(10) NOT NULL,
  PRIMARY KEY (`name`)
 )
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

CREATE TABLE `chess`.`square`
(
  `id` INT NOT NULL AUTO_INCREMENT,
  `position` VARCHAR(2) NOT NULL,
  `piece` VARCHAR(10) NOT NULL,
  PRIMARY KEY (`id`)
  );
