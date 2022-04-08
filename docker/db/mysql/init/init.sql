CREATE TABLE `game` (
  `id` int unsigned NOT NULL AUTO_INCREMENT,
  `black_user_name` varchar(15) NOT NULL,
  `white_user_name` varchar(15) NOT NULL,
  `turn` varchar(15) NOT NULL,
  PRIMARY KEY (`id`)
);

CREATE TABLE `board` (
  `id` int(15) unsigned zerofill NOT NULL AUTO_INCREMENT,
  `game_id` int unsigned NOT NULL,
  `piece` varchar(10) NOT NULL,
  `position` varchar(15) NOT NULL,
  `color` varchar(10) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `game_id` (`game_id`),
  CONSTRAINT `game_id` FOREIGN KEY (`game_id`) REFERENCES `game` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
);
