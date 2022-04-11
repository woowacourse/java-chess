CREATE TABLE `chess`.`state` (
  `name` VARCHAR(20) NOT NULL,
  PRIMARY KEY (`name`));

CREATE TABLE `chess`.`square`
(
  `position` VARCHAR(2) NOT NULL,
  `team` VARCHAR(10) NOT NULL,
  `symbol` VARCHAR(10) NOT NULL,
  PRIMARY KEY (`position`)
);
