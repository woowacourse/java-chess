CREATE TABLE `user`
(
    `user_id`       int         NOT NULL AUTO_INCREMENT,
    `user_name`     varchar(64) NOT NULL,
    `user_password` varchar(64) NOT NULL,
    PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8