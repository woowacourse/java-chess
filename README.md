# java-chess

체스 미션 저장소
- 체스 게임을 구현한 저장소입니다.

## 애플리케이션 설명
체스 게임을 구현한 애플리케이션입니다.
터미널에서 콘솔UI로 플레이할 수 있고, 웹으로도 플레이할 수 있습니다.

### 환경
- java 11
- junit 5
- spark java

### 웹 UI 기능 요구 사항(WebApplication)

#### API

- 로컬 서버 : `localhost:8080/`
- 체스판 출력 : get : `/chess`
- 기물 위치 이동 : post : `/chess/move` - body : from = 움직일 기물 위치, to = 목표 위치
- 보드판 초기화 : `/chess/reset`

#### 실행 방법

> docekr-compose.yml 파일이 있는 경로에서, docker 명령어로 Server를 실행
- `docker-compose -p chess up -d`
> docker 서버 접속
- `docker exec -it chess_db_1 bash`
> MySQL 접속
- `mysql -u root -proot`
> table 생성
- `docker/db/init/init.sql` 에 있는 쿼리 실행
> WebApplication 실행
- `localhost:8080/chess` 웹 접속 


### 콘솔 UI 기능 요구 사항(Application)
```text
> 체스 게임을 시작합니다.
> 게임 시작 : start
> 게임 종료 : end
> 게임 이동 : move source위치 target위치 - 예. move b2 b3
> 점수 출력 : status
start
RNBQKBNR
PPPPPPPP
........
........
........
........
pppppppp
rnbqkbnr
move b2 b3
RNBQKBNR
PPPPPPPP
........
........
........
.p......
p.pppppp
rnbqkbnr
status
whiteScore : 38.000000 | blackScore : 38.000000
RNBQKBNR
PPPPPPPP
........
........
........
.p......
p.pppppp
rnbqkbnr
end
whiteScore : 38.000000 | blackScore : 38.000000

```

## 기능 목록

- 체스판을 초기화한다.
  - 가로 위치는 왼쪽부터 a ~ h이고, 세로는 아래부터 위로 1 ~ 8로 구분한다.
- 흰팀, 검은팀은 소문자, 대문자로 구분한다.
- [Error] 잘못된 명령을 입력하면 예외를 발생시킨다.
- [Error] 기물이 없는 위치에서 기물을 찾으려하면 예외를 발생시킨다.
- [Error] 다른 팀 기물을 이동하려고 하면 예외를 발생시킨다.

## 움직임 전략

### 공통

- [Error] 현재 위치에서 이동할 수 없는 곳으로 이동하려고 하면 예외를 발생시킨다.
- [Error] 움직이려는 위치에 같은 팀 기물이 있으면 예외를 발생시킨다.
- 이동한다.
  - 이동하려는 위치에 상대 기물이 있으면 상대 기물을 삭제하고 이동한다.

### King

- 상하좌우, 대각선 방향으로 각각 1칸씩만 움직일 수 있다.

### Queen

- 상하좌우, 대각선 방향으로 기물이 없는 칸에 한해서 칸수의 제한 없이 움직일 수 있다.

### Look

- 상하좌우 방향으로 기물이 없는 칸에 한해서 칸수의 제한 없이 움직일 수 있다.

### Bishop

- 대각선 방향으로 기물이 없는 칸에 한해서 칸수의 제한 없이 움직일 수 있다.

### Knight

- 수직 방향으로 한칸 움직인 후 수평 방향으로 두칸 움직이거나 수직 방향으로 두칸 움직인 후 수평 방향으로 한칸 움직인다.
- 다른 기물을 넘어다닐 수 있다.

### Pawn

- 폰은 행마법과 기물을 잡는 법이 다른 유일한 기물이다.
- 바로 앞의 칸이 비어 있다면 앞으로 한 칸 전진할 수 있다.(바로 앞에 상대의 기물이 있어도 잡을 수 없다.)
- 경기중 단 한번도 움직이지 않은 폰은 바로 앞의 두칸이 비어 있을 때 두칸 전진할 수 있다.(한칸만 전진해도 된다.) 폰은 대각선 방향으로 바로 앞에 위치한 기물을 잡을 수 있다.(대각선 방향으로 바로 앞에
  위치한 칸이 비어 있더라도 그곳으로 전진할 수 없다.)
- 폰은 앞쪽으로만 움직이며 절대 뒤쪽으로 행마하지 않는다.

## 팀 점수

- 각 말의 점수는 king은 0점, queen은 9점, rook은 5점, bishop은 3점, knight는 2.5점이다.
- pawn의 기본 점수는 1점이다. 하지만 같은 세로줄에 같은 색의 폰이 있는 경우 1점이 아닌 0.5점을 준다.
