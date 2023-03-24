# java-chess

체스 미션 저장소

## 우아한테크코스 코드리뷰

- [온라인 코드 리뷰 과정](https://github.com/woowacourse/woowacourse-docs/blob/master/maincourse/README.md)

# 기능 요구사항

## 체스판

- [x] 말들의 위치를 관리한다.
    - [x] 위치를 가진다.
    - [x] 말을 가진다.

- [x] 말의 위치를 이동한다.
    - [x] 체스판 위치를 벗어날 수 없다.
    - [x] 아군 말이 있는 곳은 이동할 수 없다.


- [x] 말의 초기 위치를 지정해준다.
- [x] 말의 초기 색을 지정해준다.

### 위치

- [x] 가로 위치(FILE)는 a ~ h로 표현된다.
- [x] 세로 위치(RANK)는 1 ~ 8로 표현된다.

## 말

- [x] 색을 가진다
    - [x] 흑 / 백

- [x] 종류가 있다
    - [x] 폰
    - [x] 나이트
    - [x] 비숍
    - [x] 룩
    - [x] 퀸
    - [x] 킹

- [x] 이동할 수 있다.
    - [x] 각 규칙에 맞게 이동한다.
        - [x] 룩
            - [x] 같은 열, 같은 행으로만 움직일 수 있다.
                - [x] from - to의 File, Rank 중 하나는 무조건 같아야한다.
        - [x] 나이트
            - [x] 직선 2 + 좌우 1 칸으로 움직일 수 있다.
            - [x] 아군 말과 관계없이 이동할 수 있다.
        - [x] 비숍
            -  [x] 대각선으로 움직일 수 있다.
        - [x] 퀸
            -  [x] 상,하,좌,우,대각선으로 움직일 수 있다.
        - [x] 킹
            - [x] 상,하,좌,우,대각선으로 1칸만 움직일 수 있다.
        - [x] 폰
            - [x] 처음에는 앞으로 1칸 혹은 2칸을 움직일 수 있다.
            - [x] 처음 이후의 움직임은 앞으로 1칸만 움직일 수 있다.
            - [x] 적을 공격할 때에는 전진 + 대각선으로만 이동할 수 있다.

## 체스게임

- [x] 게임 시작과 종료를 제어한다.

## 데이터 베이스

- [ ] DB 연결을 한다. - 7분
    - [ ] 테이블 설계를 한다. - 10분
    - [ ] 쿼리를 작성한다. - 20분
    - [ ] CRUD를 수행한다. - 3~40분
        - [ ] CREATE(새로운 게임을 시작 할 때 데이터를 추가한다.) - 10분
        - [ ] READ(기존에 게임이 있는 경우 조회한다.) - 8분
        - [ ] UPDATE(게임을 진행하면서 말이 움직일 때 업데이트 된다.) - 15분

```sql
CREATE TABLE chess_game {
    piece_type VARCHAR
(
    255
) NOT NULL,
    piece_file VARCHAR
(
    255
) NOT NULL,
    piece_rank VARCHAR
(
    255
) NOT NULL,
    team VARCHAR
(
    255
) NOT NULL,
    turn VARCHAR
(
    255
) NOT NULL,
    game_status VARCHAR
(
    255
) NOT NULL
    }
    INSERT INTO chess_game
(
    piece_type,
    piece_file,
    piece_rank,
    team,
    turn,
    game_status
) VALUES
(
    ?,
    ?,
    ?,
    ?,
    ?,
    ?,
    ?
);
DELETE
FROM chess_game;
```

## 시나리오를 작성한다. - 10분

- [ ] 시작을 했을 때 DB에서 저장된 체스 게임의 정보를 읽어온다.
    - [ ] 새로운 게임
    - [ ] 기존 게임이 있는 경우
- [ ] 게임을 진행한다
    - [ ] 각 팀의 턴이 존재한다.
    - [ ] 말이 움직인다.
    - [ ] 점수를 계산한다.
- [ ] 게임을 종료한다
