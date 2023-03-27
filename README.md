# java-chess

체스 미션 저장소

## 우아한테크코스 코드리뷰

- [온라인 코드 리뷰 과정](https://github.com/woowacourse/woowacourse-docs/blob/master/maincourse/README.md)

# 기능 목록
## 체스 보드
  - [X] 8 x 8칸의 정사각형 이다.
    - [X] 각 칸은 좌표가 있다. x좌표(a-h), y좌표(1-8)
  - [X] 기물을 초기위치에 배치시킨다.
  - [X] 기물들이 기물의 규칙에 따라 이동할 수 있다.
    - [X] 모든 기물은 도착 지점에 위치한 다른편의 기물을 잡을 수 있다.
    - [X] 나이트를 제외한 모든 기물은 다른 기물을 넘을 수 없다.
    - [X] 폰 앞에 기물이 존재하면 전진할 수 없다.
    - [X] 폰의 대각선 전방 한 칸에 적 기물이 존재하면 해당 위치로 이동 할 수 있다.

## 기물
  - [X] 진영이 존재한다.(흑, 백)
    - [X] 계급이 존재한다.

### 비숍(Bishop)
- [X] 대각선 방향으로만 이동할 수 있다.
### 룩(Rook)
- [X] 가로 또는 세로 방향으로만 이동할 수 있다.
### 나이트(Knight)
- [X] L자 모양으로 이동 할 수 있다.
### 퀸(Queen)
- [X] 대각선, 가로, 세로 방향으로 이동할 수 있다.
### 킹(King)
- [X] 대각선, 가로, 세로 방향으로 한 칸만 이동할 수 있다.
### 폰(Pawn)
- [X] 한 칸 전진만 가능 하다.
- [X] 처음 이동하는 경우 두 칸 전진할 수 있다.

## 체스 보드 출력
  - [X] 기물은 진영에 따라 다르게 출력한다.
    - [X] 흑은 대문자로 출력한다.
    - [X] 백은 소문자로 출력한다.
  - [X] 비어있는 칸은 "."으로 출력한다.

## 체스 결과
- [X] 상대방의 킹을 잡으면 게임이 종료된다.
- [X] 진영의 점수를 계산하여 결과를 확인할 수 있다.
  - [X] 진영 점수는 현재 남아있는 기물들의 각 점수의 합이다.
  - [X] 이때, 폰의 점수는 동일한 진영의 폰이 동일한 파일에 존재하는 경우 절반 점수로 반영된다. 
  - [X] 점수가 높은 진영이 승리한다.

## 체스 DB
```mysql
CREATE TABLE board (
	board_id BIGINT NOT NULL UNIQUE AUTO_INCREMENT,
	turn VARCHAR(8) NOT NULL,
	PRIMARY KEY(board_id)
);

CREATE TABLE piece (
	piece_id BIGINT NOT NULL UNIQUE AUTO_INCREMENT,
	camp VARCHAR(8) NOT NULL,
	position VARCHAR(4) NOT NULL,
	type VARCHAR(10) NOT NULL,
	board_id BIGINT NOT NULL,
	PRIMARY KEY (piece_id)
);
```
- [X] 새로운 게임이 시작되면 초기 기물 상태를 저장 후 반환
- [X] 이동시 정보 저장
- [X] 존재하는 게임 선택 시, 저장된 진행 정보 반환
- [X] 게임 일시 정지 시, 현재 게임 상태를 저장
