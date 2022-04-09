drop table if exists room;
drop table if exists board;

CREATE TABLE room
(
    id          int          NOT NULL AUTO_INCREMENT,
    name        varchar(255) NOT NULL,
    canJoin     boolean      NOT NULL DEFAULT TRUE,
    currentCamp varchar(5)   not null DEFAULT 'WHITE',
    primary key (id)
);
#     createdAt   datetime     NOT NULL DEFAULT CURRENT_TIMESTAMP

# room 생성 클릭 -> 서버상의 메모리board를 받아와 -> roomId와 함께 저장하여 생성
create table board
(
    roomId   int         not null,
    position VARCHAR(5)  NOT NULL,
    piece    VARCHAR(20) NOT NULL,
    PRIMARY KEY (position, roomId),
    foreign key (roomId) REFERENCES room (id)
);


# insert into board (position, piece)
# values ('a1', 'white_rook'),
#        ('b1', 'white_knight'),
#        ('c1', 'white_bishop'),
#        ('d1', 'white_queen'),
#        ('e1', 'white_king'),
#        ('f1', 'white_bishop'),
#        ('g1', 'white_knight'),
#        ('h1', 'white_rook'),
#
#        ('a2', 'white_pawn'),
#        ('b2', 'white_pawn'),
#        ('c2', 'white_pawn'),
#        ('d2', 'white_pawn'),
#        ('e2', 'white_pawn'),
#        ('f2', 'white_pawn'),
#        ('g2', 'white_pawn'),
#        ('h2', 'white_pawn'),
#
#        ('a3', 'blank'),
#        ('b3', 'blank'),
#        ('c3', 'blank'),
#        ('d3', 'blank'),
#        ('e3', 'blank'),
#        ('f3', 'blank'),
#        ('g3', 'blank'),
#        ('h3', 'blank'),
#
#        ('a4', 'blank'),
#        ('b4', 'blank'),
#        ('c4', 'blank'),
#        ('d4', 'blank'),
#        ('e4', 'blank'),
#        ('f4', 'blank'),
#        ('g4', 'blank'),
#        ('h4', 'blank'),
#
#        ('a5', 'blank'),
#        ('b5', 'blank'),
#        ('c5', 'blank'),
#        ('d5', 'blank'),
#        ('e5', 'blank'),
#        ('f5', 'blank'),
#        ('g5', 'blank'),
#        ('h5', 'blank'),
#
#        ('a6', 'blank'),
#        ('b6', 'blank'),
#        ('c6', 'blank'),
#        ('d6', 'blank'),
#        ('e6', 'blank'),
#        ('f6', 'blank'),
#        ('g6', 'blank'),
#        ('h6', 'blank'),
#
#        ('a7', 'black_pawn'),
#        ('b7', 'black_pawn'),
#        ('c7', 'black_pawn'),
#        ('d7', 'black_pawn'),
#        ('e7', 'black_pawn'),
#        ('f7', 'black_pawn'),
#        ('g7', 'black_pawn'),
#        ('h7', 'black_pawn'),
#
#        ('a8', 'black_rook'),
#        ('b8', 'black_knight'),
#        ('c8', 'black_bishop'),
#        ('d8', 'black_queen'),
#        ('e8', 'black_king'),
#        ('f8', 'black_bishop'),
#        ('g8', 'black_knight'),
#        ('h8', 'black_rook');


# create table commandHistory
# (
#     id     int NOT NULL AUTO_INCREMENT,
#     roomId int not null,
#     camp
#         command
#         before
#         after
#         whiteScore
#         blackScore
#         createAt
#         PRIMARY KEY (position, chess_game_id),
#     FOREIGN KEY (roomId) REFERENCES room (id)
# );

-- fininshed상태에서만 저장되며, 종료시 해당 roomId에 대해 canJoin을 false처리 -> 완료로 출력하도록 해주자.
# create table result
# (
#     id
#         roomId
#         message
#         createdAt
# )
-- 최종 저장할 room 1개만
