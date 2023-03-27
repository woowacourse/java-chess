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
  - 직선: L, R, U, D
  - 대각선: LU, RU, LD, RD 
  - L자: UUL, UUR, DDL, DDR, LLU, LLD, RRU, RRD

- chess pieceType(체스 말): king, queen, rook, knight, bishop, pawn 
  - black(대문자), white(소문자)
  - 각 말이 움직일 수 있는 방향 (Direction) / 연속가능여부 (Continuity)
  - king: 직선, 대각선 / 불연속
  - queen: 직선, 대각선 / 연속 가능
  - rook: 직선 / 연속 가능
  - knight: L자 / 연속 가능
  - bishop: 대각선 / 연속 가능
  - 처음 pawn: 직선 / 연속 가능
  - pawn: 직선, 대각선 / 불연속

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
  
- 점수 계산 규칙
  - 체스 프로그램에서 현재까지 남아 있는 말에 따라 점수를 계산할 수 있어야 한다.
  - 각 말의 점수는 queen은 9점, rook은 5점, bishop은 3점, knight는 2.5점이다.
  - pawn의 기본 점수는 1점이다. 하지만 같은 세로줄에 같은 색의 폰이 있는 경우 1점이 아닌 0.5점을 준다.
  - king은 잡히는 경우 경기가 끝나기 때문에 점수가 없다.
  - 한 번에 한 쪽의 점수만을 계산해야 한다.

## 기능 구현 목록
- [x] 입력
  - [x] 게임 시작 / 종료 명령
  - [x] 체스 말 이동 명령
    - [x] 이동 입력 포맷에 맞는지 확인한다.
  - [x] "status" 명령을 입력

- [x] 출력
  - [x] 게임 규칙 출력
  - [x] 체스 보드 출력
  - [x] 이동 명령에 따른 체스 보드 출력
  - [x] 각 진영의 점수를 출력하고 어느 진영이 이겼는지 결과 출력

- [x] 기능
  - [x] 칸
    - [x] 가로, 세로 범위를 벗어나지 않는지 확인한다.
    - [x] 칸이 비어있는지 확인한다.
  
  - [x] 체스 말
    - [x] 색을 가진다.
    - [x] 이동할 수 있는 칸인지 확인한다.
    - [x] 일부 말은 특수한 규칙을 가진다.
      - [x] knight: 길목에 말이 있어도 이동할 수 있다. 
      - [x] pawn
        - [x] 초기 위치인 경우 2칸을 이동할 수 있다.
        - [x] 상대말이 앞쪽 대각선에 있을 경우 해당 칸으로 이동할 수 있다.
  
  - [x] 보드 
    - [x] 명령에 따라 체스 말의 칸을 변경한다.
    - [x] 명령에 따라 말이 위치한 칸을 이동한다.
    - [x] 이동할 칸에 다른 팀 말이 있으면 공격 및 이동할 수 있다.
    - [x] 이동할 칸에 같은 팀 말이 있으면 이동할 수 없다.

  - [x] 게임
    - [x] 한 팀의 king이 잡히면 게임이 종료 된다.
    - [x] 현재 남아 있는 말에 대한 점수를 구할 수 있다.
    - [x] 게임 방 번호를 이용하여 중단한 게임을 다시 실행할 수 있다.

## 요구 사항 및 기타 컨벤션

- 디미터 법칙 
- getter, setter 사용을 자제한다.
- 메서드는 10라인 이하로 작성한다.
- 메서드 정렬
- 사용하지 않는 메서드, import 확인
- 인덴트가 2뎁스 이상 들어가지 않는다.
- 뷰는 도메인에 의존하지 않는다.
