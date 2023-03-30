CREATE TABLE chess_game(
                           id bigint PRIMARY KEY auto_increment,
                           source_coordinate_row VARCHAR(255) NOT NULL,
                           source_coordinate_column VARCHAR(255) NOT NULL,
                           destination_coordinate_row VARCHAR(255) NOT NULL,
                           destination_coordinate_column VARCHAR(255) NOT NULL
);