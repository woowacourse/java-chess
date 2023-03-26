# java-chess

체스 미션 저장소

## 우아한테크코스 코드리뷰

- [온라인 코드 리뷰 과정](https://github.com/woowacourse/woowacourse-docs/blob/master/maincourse/README.md)

## 기능 목록

### 입력

- [x] 입장하길 원하는 게임방 id를 입력받는다.
  - [x] 입력값이 숫자인지 검증한다.
- [x] 게임 시작 또는 종료 명령을 입력받는다(start, end)
- [x] 체스 말의 이동 정보(move source, target)를 입력받는다.
    - [x] 체스판의 위치 값은 가로 위치는 왼쪽부터 a ~ h이고, 세로는 아래부터 위로 1 ~ 8임을 검증한다.
- [x] 점수와 진영 승패 결과를 보기 위한 status 명령을 입력받는다.

### 출력

- [x] 현재 존재하는 게임방 id들을 출력한다.
- [x] 게임 안내 문구를 출력한다.
- [x] 체스판을 현 상황을 출력한다.
    - [x] 검은색 말은 대문자, 흰색 말은 소문자로 구분한다.
- [x] 각 진영의 점수를 출력한다.
- [x] 어느 진영이 이겼는지 결과를 출력한다.

### 데이터베이스 연결

- [x] 데이터베이스를 연결한다.
- [x] 이전에 하던 체스 게임을 다시 시작할 수 있어야 한다. (필수)
- [x] 체스 게임방을 만들고 체스 게임방에 입장할 수 있는 기능을 추가한다. (선택)
- [ ] 사용자별로 체스 게임 기록을 관리한다. (선택)

```
  CREATE TABLE chess_game(
  id INT NOT NULL AUTO_INCREMENT,
  turn VARCHAR(8) NOT NULL,
  
  PRIMARY KEY(id)
  );

  CREATE TABLE board(
  chess_game_id INT NOT NULL,
  position_column INT NOT NULL,
  position_row INT NOT NULL,
  piece_type VARCHAR(8) NOT NULL,
  piece_team VARCHAR(8) NOT NULL,
  
  PRIMARY KEY(chess_game_id, position_column, position_row),
  CONSTRAINT `fk_chess_game_id` FOREIGN KEY (`chess_game_id`) REFERENCES `chess_game` (`id`) ON DELETE CASCADE
  );

  INSERT INTO chess_game(turn) VALUES (?);
  INSERT INTO board(chess_game_id, position_column, position_row, piece_type, piece_team) VALUES(?,?,?,?,?);
  SELECT * FROM chess_game WHERE id = ?;
  SELECT * FROM board WHERE chess_game_id = ?;
  DELETE FROM chess_game WHERE id = ?;
  DELETE FROM board WHERE chess_game_id = ?;
  UPDATE chess_game SET turn = ? WHERE id = ?;
```

### ChessGame

- [x] 어느 진영이 게임할 순서인지 확인
- [x] 한쪽의 King 이 잡혔을 때 게임을 종료
- [x] 남아있는 체스말들의 점수를 계산
    - [x] 한 번에 한 쪽의 점수만을 계산해야 한다.
    - [x] pawn 에 대한 마이너스 점수를 뺀다.
- [x] 이긴 진영을 판단

### Board

- [x] 초기화 기능
    - [x] 체스말 놓기
- [x] 말을 움직이는 기능
    - [x] 움직이려는 자리에 말이 있는지 확인
    - [x] 이동경로의 방해물을 확인
    - [x] 정상적인 이동경로일 경우 말 이동
- [x] 정렬된 보드를 반환
- [x] 남아있는 체스말들의 점수들을 취합하여 반환
- [x] 킹이 죽었는지 확인
- [x] 같은 팀의 Pawn 이 같은 세로줄에 두개 이상 있으면 `0.5점 * 해당되는 폰의 개수`를 반환

### Piece

- [X] 말은 Pawn, Bishop, Knight, Rook, Queen, King 으로 구성
- [X] 말은 흑 또는 백
- [X] 상대위치와 타겟을 입력받아서 올바르게 움직일 수 있는지 확인
    - [X] 자신과 타겟이 같은 팀일경우 예외처리
    - [X] 올바르지 않은 방향으로 이동할 경우 예외처리
- [x] 각 말은 자신의 점수를 앎
    - [x] Queen 은 9점, Rook 은 5점, Bishop 은 3점, Knight 는 2.5점, Pawn 은 1점
    - [x] King 은 잡히는 경우 경기가 끝나기 때문에 점수가 존재하지 않음
- [ ] Pawn 의 특수한 룰을 구현
    - [ ] 승진
    - [ ] 앙파상

### ObstacleStrategy

- [x] 이동 경로에 장애물이 있는지 확인이 필요한 위치들의 리스트를 반환

### Movement

- [X] 상대 위치까지의 이동이 가능한지 판별

### RelativePosition

- [x] 상대 위치를 단위 상대 위치로 변환하는 기능

### Direction

- [x] 말의 최소 이동 단위 제공

### Position

- [x] 입력된 위치가 유효한지 검증


