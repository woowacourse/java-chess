# java -chess

체스 기능 구현을 위한 저장소

## 기능 구현

- 입력

    - [x] 명령을 입력한다.

- 출력

    * [x] 게임 시작 시 안내문구를 출력한다.
    * [x] 보드를 출력한다.
    * [x] status 명령에 따른 결과를 출력한다.

- 서비스

    * [x] start 명령 시 8x8의 보드를 생성한다.

    * [x] 특정 Position에 있는 Piece를 찾을 수 있다.

    * [x] 두 Position을 통해 방향을 찾을 수 있다.

    * [x] Piece를 특정 Position으로 움직일 수 있다.

        * [x] 기물 별로 다른 움직임을 가질 수 있다.
        * [x] 도착지점이 King이라면 게임이 종료되어야 한다.

        * [x] Position이 이동가능한 좌표여야 한다.
        * [x] 본인 차례인 기물만 움직일 수 있어야 한다.
        * [x] Pawn이 끝까지 가면 승급해야 한다.
        * [x] 기존 Position은 Piece를 치워야 한다

    * [x] 현재 점수를 계산할 수 있다.

    * [x] end 명령이 들어오면 프로그램을 종료한다.

* 웹
    * [x] '/' 를 받으면 db에서 저장된 게임을 가져와서 체스판에 세팅한다.
    * [x] 재시작 버튼을 누르면 모든 말의 위치를 기본 형태로 초기화한다.
    * [x] 점수 버튼을 누르면 현재 판에 대한 점수가 알림창으로 나오게 된다.
    * [x] 왕을 잡으면 게임이 끝나고 체스판이 초기화된다.
    * [x] 상대 말을 잡으면 체스판에 사라지고 해당 이동에 대한 결과를 db에 저장한다.
    * [x] 턴을 진행하고 나서는 턴을 가진 팀에 대한 정보를 db에 저장한다.

<br/>

> drop table board;
> create table board (
> position varchar(12) not null,
> piece varchar(12) not null,
> primary key (position) );
> insert into board (position, piece) values ('a1', '&#9814;');
> insert into board (position, piece) values ('b1', '&#9816;');
> insert into board (position, piece) values ('c1', '&#9815;');
> insert into board (position, piece) values ('d1', '&#9812;');
> insert into board (position, piece) values ('e1', '&#9813;');
> insert into board (position, piece) values ('f1', '&#9815;');
> insert into board (position, piece) values ('g1', '&#9816;');
> insert into board (position, piece) values ('h1', '&#9814;');
>
>insert into board (position, piece) values ('a8', '&#9820;');
> insert into board (position, piece) values ('b8', '&#9822;');
> insert into board (position, piece) values ('c8', '&#9821;');
> insert into board (position, piece) values ('d8', '&#9818;');
> insert into board (position, piece) values ('e8', '&#9819;');
> insert into board (position, piece) values ('f8', '&#9821;');
> insert into board (position, piece) values ('g8', '&#9822;');
> insert into board (position, piece) values ('h8', '&#9820;');
>
>insert into board (position, piece) values('a7', '&#9823;');
> insert into board (position, piece) values('b7', '&#9823;');
> insert into board (position, piece) values('c7', '&#9823;');
> insert into board (position, piece) values('d7', '&#9823;');
> insert into board (position, piece) values('e7', '&#9823;');
> insert into board (position, piece) values('f7', '&#9823;');
> insert into board (position, piece) values('g7', '&#9823;');
> insert into board (position, piece) values('h7', '&#9823;');
>
>insert into board (position, piece) values ('a2', '&#9817;');
> insert into board (position, piece) values ('b2', '&#9817;');
> insert into board (position, piece) values ('c2', '&#9817;');
> insert into board (position, piece) values ('d2', '&#9817;');
> insert into board (position, piece) values ('e2', '&#9817;');
> insert into board (position, piece) values ('f2', '&#9817;');
> insert into board (position, piece) values ('g2', '&#9817;');
> insert into board (position, piece) values ('h2', '&#9817;');
>
>insert into board (position, piece) values ('a3', '');
> insert into board (position, piece) values ('b3', '');
> insert into board (position, piece) values ('c3', '');
> insert into board (position, piece) values ('d3', '');
> insert into board (position, piece) values ('e3', '');
> insert into board (position, piece) values ('f3', '');
> insert into board (position, piece) values ('g3', '');
> insert into board (position, piece) values ('h3', '');
>
>insert into board (position, piece) values ('a4', '');
> insert into board (position, piece) values ('b4', '');
> insert into board (position, piece) values ('c4', '');
> insert into board (position, piece) values ('d4', '');
> insert into board (position, piece) values ('e4', '');
> insert into board (position, piece) values ('f4', '');
> insert into board (position, piece) values ('g4', '');
> insert into board (position, piece) values ('h4', '');
>
>insert into board (position, piece) values ('a5', '');
> insert into board (position, piece) values ('b5', '');
> insert into board (position, piece) values ('c5', '');
> insert into board (position, piece) values ('d5', '');
> insert into board (position, piece) values ('e5', '');
> insert into board (position, piece) values ('f5', '');
> insert into board (position, piece) values ('g5', '');
> insert into board (position, piece) values ('h5', '');
>
>insert into board (position, piece) values ('a6', '');
> insert into board (position, piece) values ('b6', '');
> insert into board (position, piece) values ('c6', '');
> insert into board (position, piece) values ('d6', '');
> insert into board (position, piece) values ('e6', '');
> insert into board (position, piece) values ('f6', '');
> insert into board (position, piece) values ('g6', '');
> insert into board (position, piece) values ('h6', '');
>
>drop table turn;
> create table turn (
> turn_owner varchar(10) not null,
> primary key (turn_owner));
>
>insert into turn (turn_owner) values ('white');