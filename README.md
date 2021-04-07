# java-chess
체스 게임 구현을 위한 저장소

## 웹 어플리케이션 기능 구현 목록

- Room
    - 원하는 이름으로 방을 생성한다. `/room/create/{roomName}`
    - 진행 중인 게임 방 목록을 출력하고 해당 방으로 입장한다. `/main`
    - 진행 중인 게임 방의 기록을 삭제한다. `/room/delete/{roomId}`
    
- Game
    - 게임 id에 해당하는 게임 기록을 로드한다. `/game/load/{roomId}`
    - 게임을 생성한다. `/game/create/{roomId}`
    - 게임 기록을 삭제한다. `/game/delete/{roomId}`
    - 기물의 이동 가능 위치를 출력한다. `/game/show/{roomId}`
    - 기물을 이동한다. `/game/move/{roomId}`
    
- 화면
    - 메인 화면에는 생성할 방 이름을 입력하는 입력 박스와 진행 중인 게임 목록이 표시된다.
    - 게임 화면에는 방 이름과 보드, 각 진영의 점수가 출력된다.
    - 기물을 클릭하는 경우 이동 가능한 위치들이 표시되고, 그 중 하나를 클릭하면 기물이 이동된다.
    - 이동 가능한 위치를 표시 후, 그 외 위치를 클릭하면 이동 가능한 위치 표시가 종료되고 다시 대기 상태로 돌아간다.
    - 승리자 출력 화면에는 승자의 이름과 메인 화면으로 돌아가는 링크가 표시된다.
    - 승리자가 없는 경우 무승부를 승자의 이름으로 출력한다.

- 부가 기능
    - 서버가 종료 후 재실행되어도 게임의 현상태를 유지할 수 있다.
    - 여러 pc가 동시에 서로 다른 체스 게임을 진행할 수 있다.
    - 게임이 종료된 이후에는 게임 기록과 방 기록이 삭제된다.
    - 예외 발생 시 예외 종류와 메시지를 담는 예외 페이지가 출력된다.

- 수정 필요
    - db에서 텍스트 형태로 관리되는 보드 내용 (white_pawn, white_knight, black_pawn, , white_king...)
    - http 메소드 별 의미를 생각하지 않고 요청하고, 처리하고 있음

##  실행 환경
port : 4567   
main page : /main   

database : web_chess     
table1 : room_status   
``` java
CREATE TABLE room_status(
id int not null auto_increment primary key,
room_name CHAR(10) not null,
room_id BIGINT
);
```

table2 : game_status   
``` java
CREATE TABLE game_status(
id int not null auto_increment primary key,
room BIGINT,
turn  CHAR(10) not null,
board TEXT
);
```

## 기능 구현 목록
- 체스판 초기화
    - 가로는 a~h 까지, 세로는 1~8까지
    - 진영은 검은색(대문자), 흰색(소문자)로 나뉜다.

- 체스말의 이동 (show source target)
    - 이동 규칙을 구현한다.
        - 체스말의 이동 규칙과 맞지 않는 target 위치
        - source에 체스말이 존재하지 않는 경우
        - 가는 도중 장애물이 있는 경우를 확인한다.
        - source, target으로 체스판을 벗어난 위치가 입력되는 경우 
    - 이동은 진영을 번갈아 가면서 할 수 있도록 한다.

- 이동 가능 위치 표시 (show position)
    - 체스말이 이동 가능한 모든 위치를 표시한다.

- 점수 계산과 출력 (status)
    - king이 잡히면 점수가 없다.
    - Q : 9, R : 5, B : 3, N : 2.5, P : 1
    - 단, 같은 세로 줄에 같은 색의 폰이 있는 경우 폰은 각 0.5점으로 한다.
    
- 입/출력 
    - 체스 게임 시작을 알린다.
    - 게임 명령어를 안내한다. 
        - \> 게임 시작 : start
        - \> 게임 종료 : end
        - \> 게임 이동 : move source 위치 target 위치
    - 유저의 입력을 받는다.
