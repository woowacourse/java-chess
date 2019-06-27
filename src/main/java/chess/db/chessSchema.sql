
CREATE TABLE `turn` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `current_team` varchar(10) NOT NULL,
  `round` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `turn_id_uindex` (`id`),
  UNIQUE KEY `turn_round_uindex` (`round`)
);

CREATE TABLE `board` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `piece` varchar(10) NOT NULL,
  `team` varchar(10) NOT NULL,
  `point` varchar(5) NOT NULL,
  `round` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `board_id_uindex` (`id`)
);