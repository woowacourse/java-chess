# ♟️ 체스 게임 실행 방법

1. `docekr-compose.yml`파일이 있는 `docker` 폴더에서 docker 실행
```zsh
docker-compose -p chess up -d
```

2. 데이터베이스 생성
```mysql
CREATE DATABASE chess DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;
```

3. 테이블 생성
```mysql
USE chess;

CREATE TABLE pieces
(
    id         INT AUTO_INCREMENT PRIMARY KEY,
    board_file INT         NOT NULL,
    board_rank INT         NOT NULL,
    type       VARCHAR(64) NOT NULL
);

CREATE TABLE turns
(
    id    INT AUTO_INCREMENT PRIMARY KEY,
    color varchar(10) NOT NULL
);
```

4. `Application` 실행

---

## 기능 요구 사항

### 각 팀 별 체스 말 개수

- [x] 폰 : 8개
- [x] 룩 : 2개
- [x] 비숍 : 2개
- [x] 나이트 : 2개
- [x] 킹 / 퀸 : 1개

### 시작 조건

- [x] start를 입력하면 게임이 실행된다.

### 게임 진행

- [x] 흰색 턴으로 시작해 한턴씩 번갈아가면서 진행한다.

#### 체스판 초기화

- [x] 체스판에서 말의 위치 값은 가로 위치는 왼쪽부터 a ~ h이고, 세로는 아래부터 위로 1 ~ 8로 구현한다.
- [x] 블랙 : 대문자, 화이트 : 소문자

```markdown
RNBQKBNR 8 (rank 8)
PPPPPPPP 7
........ 6
........ 5
........ 4
........ 3
pppppppp 2
rnbqkbnr 1 (rank 1)

abcdefgh
```

### 체스 말의 이동

- [x] `move source위치 target위치`를 실행해 이동한다.
- [x] 이동 위치에 내 말이 있으면 이동할 수 없다.

| 종류     | 말                                                                                                      |
|--------|--------------------------------------------------------------------------------------------------------|
| 슬라이딩   |- 퀸(Queen) : 상하좌우, 대각선으로 이동 가능하다.<br /> - 룩(Rook) : 상하좌우로 이동 가능하다. <br /> - 비숍(Bishop) : 대각선으로 이동 가능하다. |
| 논 슬라이딩 |- 나이트(Knight) : 상하좌우 한 칸 이동 후 앞방향 대각선으로 이동 가능하다.<br /> - 킹(King) : 상하좌우, 대각선으로 한 칸만 이동 가능하다.|
|폰|- 처음 : 앞으로 2칸까지 갈 수 있다. (슬라이딩)<br />- 기본 : 앞으로 한칸만 갈 수 있다.<br />- 잡기 : 좌우 대각선으로 한 칸만 갈 수 있다.|


### 종료 조건

- [x] end를 입력하면 게임이 종료된다.

---

## 3 단계 기능 요구 사항
- [x] King이 잡혔을 때 게임을 종료한다.
- [x] 현재 남아 있는 말에 대한 점수를 구한다.
  - [x] 한 번에 한 쪽의 점수만을 계산해야 한다.
  - [x] queen은 9점, rook은 5점, bishop은 3점, knight는 2.5점이다.
  - [x] pawn의 기본 점수는 1점이다. 하지만 같은 세로줄에 같은 색의 폰이 있는 경우 1점이 아닌 0.5점이다.
  - [x] king은 잡히는 경우 경기가 끝나기 때문에 점수가 없다.
- [x] "status" 명령을 입력하면 각 진영의 점수를 출력하고 어느 진영이 이겼는지 결과를 볼 수 있어야 한다.

---

## 4 단계 기능 요구 사항
- [x] DB 세팅
- [x] 애플리케이션을 재시작하더라도 이전에 하던 체스 게임을 다시 시작할 수 있어야 한다.
- [x] 저장이 필요한 데이터
  - [x] 말의 위치, 말의 타입
  - [x] 현재 턴 컬러

---

## 리팩터링
- [ ] Controller 커멘드 분기 처리 모으기
- [x] 구현체에 대한 정보가 인터페이스에 많이 드러난다
```java
public abstract boolean isPawn();

public abstract boolean isKing();
```
- [x] README.md 어플리케이션 실행 방법 정리
- [x] ScoreManager ↔️ ChessState 객체끼리 서로 물어보며 점수 계산
- [ ] ScoreManager 상수 사용 or 각 객체에서 자신의 점수 계산
- [x] DBService 네이밍
  - [x] 무엇을 위한 서비스인지 고민
  - [x] 도메인을 서비스에서 만들어서 반환
- [x] DBConnector 네이밍 & 역할
- [x] PieceType : 생성자 사용으로 통일
