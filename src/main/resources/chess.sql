create table chessGameTable
(
    id int auto_increment
        primary key,
    turn varchar(5) not null
);
create table pieceTable
(
    id int auto_increment
        primary key,
    piece varchar(6) not null
);
create table positionTable
(
    id int auto_increment
        primary key,
    position char(2) not null
);
create table teamTable
(
    id int auto_increment
        primary key,
    team varchar(5) not null
);
create table boardTable
(
    positionId int not null,
    pieceId    int not null,
    teamId     int not null,
    gameId     int not null,
    constraint boardTable_ibfk_1
        foreign key (positionId) references positionTable (id),
    constraint boardTable_ibfk_2
        foreign key (pieceId) references pieceTable (id),
    constraint boardTable_ibfk_3
        foreign key (teamId) references teamTable (id),
    constraint boardTable_ibfk_4
        foreign key (gameId) references chessGameTable (id)
);
create index gameId
    on boardTable (gameId);
create index pieceId
    on boardTable (pieceId);
create index positionId
    on boardTable (positionId);
create index teamIdpositionTable
    on boardTable (teamId);

INSERT INTO positionTable values(1,'A1');
INSERT INTO positionTable values(2,'A2');
INSERT INTO positionTable values(3,'A3');
INSERT INTO positionTable values(4,'A4');
INSERT INTO positionTable values(5,'A5');
INSERT INTO positionTable values(6,'A6');
INSERT INTO positionTable values(7,'A7');
INSERT INTO positionTable values(8,'A8');
INSERT INTO positionTable values(9,'B1');
INSERT INTO positionTable values(10,'B2');
INSERT INTO positionTable values(11,'B3');
INSERT INTO positionTable values(12,'B4');
INSERT INTO positionTable values(13,'B5');
INSERT INTO positionTable values(14,'B6');
INSERT INTO positionTable values(15,'B7');
INSERT INTO positionTable values(16,'B8');
INSERT INTO positionTable values(17,'C1');
INSERT INTO positionTable values(18,'C2');
INSERT INTO positionTable values(19,'C3');
INSERT INTO positionTable values(20,'C4');
INSERT INTO positionTable values(21,'C5');
INSERT INTO positionTable values(22,'C6');
INSERT INTO positionTable values(23,'C7');
INSERT INTO positionTable values(24,'C8');
INSERT INTO positionTable values(25,'D1');
INSERT INTO positionTable values(26,'D2');
INSERT INTO positionTable values(27,'D3');
INSERT INTO positionTable values(28,'D4');
INSERT INTO positionTable values(29,'D5');
INSERT INTO positionTable values(30,'D6');
INSERT INTO positionTable values(31,'D7');
INSERT INTO positionTable values(32,'D8');
INSERT INTO positionTable values(33,'E1');
INSERT INTO positionTable values(34,'E2');
INSERT INTO positionTable values(35,'E3');
INSERT INTO positionTable values(36,'E4');
INSERT INTO positionTable values(37,'E5');
INSERT INTO positionTable values(38,'E6');
INSERT INTO positionTable values(39,'E7');
INSERT INTO positionTable values(40,'E8');
INSERT INTO positionTable values(41,'F1');
INSERT INTO positionTable values(42,'F2');
INSERT INTO positionTable values(43,'F3');
INSERT INTO positionTable values(44,'F4');
INSERT INTO positionTable values(45,'F5');
INSERT INTO positionTable values(46,'F6');
INSERT INTO positionTable values(47,'F7');
INSERT INTO positionTable values(48,'F8');
INSERT INTO positionTable values(49,'G1');
INSERT INTO positionTable values(50,'G2');
INSERT INTO positionTable values(51,'G3');
INSERT INTO positionTable values(52,'G4');
INSERT INTO positionTable values(53,'G5');
INSERT INTO positionTable values(54,'G6');
INSERT INTO positionTable values(55,'G7');
INSERT INTO positionTable values(56,'G8');
INSERT INTO positionTable values(57,'H1');
INSERT INTO positionTable values(58,'H2');
INSERT INTO positionTable values(59,'H3');
INSERT INTO positionTable values(60,'H4');
INSERT INTO positionTable values(61,'H5');
INSERT INTO positionTable values(62,'H6');
INSERT INTO positionTable values(63,'H7');
INSERT INTO positionTable values(64,'H8');

INSERT INTO pieceTable values(1,'PAWN');
INSERT INTO pieceTable values(2,'KNIGHT');
INSERT INTO pieceTable values(3,'BISHOP');
INSERT INTO pieceTable values(4,'ROOK');
INSERT INTO pieceTable values(5,'QUEEN');
INSERT INTO pieceTable values(6,'KING');

INSERT INTO teamTable values(1, 'WHITE');
INSERT INTO teamTable values(2, 'BLACK');
