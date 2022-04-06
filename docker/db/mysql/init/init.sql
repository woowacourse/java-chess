CREATE TABLE `chess`.`state`
(
  `name` VARCHAR(10) NOT NULL,
  PRIMARY KEY (`name`)
 )
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

CREATE TABLE `chess`.`square`
(
  `position` VARCHAR(2) NOT NULL,
  `team` VARCHAR(10) NOT NULL,
  `symbol` VARCHAR(10) NOT NULL,
  PRIMARY KEY (`position`)
);
