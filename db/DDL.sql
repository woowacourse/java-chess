CREATE TABLE user
(

    user_id VARCHAR(12) NOT NULL,

    PRIMARY KEY (user_id)

);

CREATE TABLE room
(

    room_id  INT            NOT NULL AUTO_INCREMENT,

    user_id  VARCHAR(12)    NOT NULL,

    commands VARCHAR(10000) NOT NULL,


    PRIMARY KEY (room_id),

    FOREIGN KEY (user_id) REFERENCES user (user_id)

);
