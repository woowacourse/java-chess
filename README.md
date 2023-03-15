# java-chess

체스 미션 저장소

## 우아한테크코스 코드리뷰

- [온라인 코드 리뷰 과정](https://github.com/woowacourse/woowacourse-docs/blob/master/maincourse/README.md)

## 체스 게임 기본 구성 및 규칙
- chess board는 8x8 square로 구성되어 있다.
  - square(칸) : rank(가로줄), file(세로줄)으로 구성되어있다.
    - rank: 1 ~ 8
    - file: a ~ h

- movement(방향)
  - L, R, U, D, LU, RU, LD, RD 
  - UUL, UUR, DDL, DDR, LLU, LLD, RRU, RRD
  - UU, DD

- chess pieceType(체스 말): king, queen, rook, knight, bishop, pawn 
  - black(대문자), white(소문자)
  - 각 말이 움직일 수 있는 방향 / 연속여부 
  - king: L, R, U, D, LU, RU, LD, RD / F
  - queen: L, R, U, D, LU, RU, LD, RD / T
  - rook: L, R, U, D / T
  - knight: UUL, UUR, DDL, DDR, LLU, LLD, RRU, RRD / F
  - bishop: LU, RU, LD, RD / T
  - pawn(black): D, DD(초기 위치일 때), LD(상대말 잡을 때), RD(상대말 잡을 때) / F
  - pawn(white): U, UU(초기 위치일 때), LU(상대말 잡을 때), RU(상대말 잡을 때) / F

- 초기 위치
  - black
    - rook: a8, h8
    - knight: b8, g8
    - bishop: c8, f8
    - queen: d8
    - king: e8
    - pawn: a7~h7  
  
  - white
    - rook: a1, h1
    - knight: b1, g1
    - bishop: c1, f1
    - queen: d1
    - king: e1
    - pawn: a2~h2

## 기능 구현 목록
- [ ] 입력
  - [x] 게임 시작 / 종료 명령
  - [ ] 체스 말 이동 명령
    - [ ] 이동 입력 포맷에 맞는지 확인한다.

- [ ] 출력
  - [x] 게임 시작 출력
  - [x] 게임 시작 / 종료 명령 출력
  - [x] 체스 보드 출력
  - [ ] 게임 규칙 출력
  - [ ] 이동 명령에 따른 체스 보드 출력

- [ ] 기능
  - [ ] 칸
    - [ ] 가로, 세로 범위를 벗어나지 않는지 확인한다.
    - [ ] 칸이 비어있는지 확인한다.
  
  - [ ] 체스 말
    - [ ] 자신이 위치한 칸을 가진다.
    - [x] 색을 가진다.
    - [ ] 상태를 가진다.
    - [ ] 명령에 따라 칸을 이동한다. 
    - [ ] 이동할 수 있는 칸인지 확인한다.
    - [ ] 자신의 현재 상태를 변경한다.
    - [ ] 일부 말은 특수한 규칙을 가진다.
      - [ ] knight: 길목에 말이 있어도 이동할 수 있다. 
      - [ ] pawn
        - [ ] 초기 위치인 경우 2칸을 이동할 수 있다.
        - [ ] 상대말이 앞쪽 대각선에 있을 경우 해당 칸으로 이동할 수 있다.
  
  - [ ] 보드 
    - [ ] 명령에 따라 체스 말의 칸을 변경한다.
