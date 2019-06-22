CREATE TABLE `command` (
  `id` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
  `origin` VARCHAR(10) NOT NULL,
  `target` VARCHAR(10) NOT NULL,
  `round` BIGINT UNSIGNED NOT NULL,
  `room_id` BIGINT UNSIGNED NOT NULL,
  PRIMARY KEY (`id`));

CREATE TABLE room (
  id BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
  status TINYINT(1) NOT NULL DEFAULT 0,
  winner VARCHAR (30),
  PRIMARY KEY(id));

INSERT INTO room () VALUES ();
INSERT INTO room () VALUES ();

INSERT INTO command(origin, target, round, room_id) VALUES ('a2', 'a4', 1, 1);
INSERT INTO command(origin, target, round, room_id) VALUES ('b7', 'b5', 2, 1);
INSERT INTO command(origin, target, round, room_id) VALUES ('a4', 'b5', 3, 1);
INSERT INTO command(origin, target, round, room_id) VALUES ('g8', 'f6', 4, 1);
INSERT INTO command(origin, target, round, room_id) VALUES ('a1', 'a7', 5, 1);

INSERT INTO command(origin, target, round, room_id) VALUES ('a2', 'a4', 1, 2);
