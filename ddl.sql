
CREATE TABLE pieces (
                        position_file VARCHAR(5) NOT NULL,
                        position_rank VARCHAR(5) NOT NULL,
                        type VARCHAR(10) NOT NULL ,
                        color VARCHAR(5) NOT NULL ,
                        room_id INTEGER NOT NULL ,
                        PRIMARY KEY (position_file, position_rank, room_id)
);

CREATE TABLE gameRooms (
                           room_id INTEGER NOT NULL ,
                           turn_color VARCHAR(5) NOT NULL ,
                           PRIMARY KEY (room_id)
);
