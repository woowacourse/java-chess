# 기능 요구 사항

---

## 체스판 초기화

- [X] 8x8 크기의 보드 생성
- [X] 피스 위치 초기화
    - [X] 검정색 피스 초기화
    - [X] 흰색 피스 초기화

- [X] Piece 경로 계산
    - [X] 퀸
    - [X] 킹
    - [X] 룩
    - [X] 비숍
    - [X] 나이트
    - [X] 폰

- [X] Piece 이동 가능 계산
    - [X] 퀸
    - [X] 킹
    - [X] 룩
    - [X] 비숍
    - [X] 나이트
    - [X] 폰

- [X] Square
    - [X] 인스턴스로 Piece를 가짐
- [X] Squares
    - [X] Square의 일급 컬렉션
- [X] Board
    - [X] Squares의 일급 컬렉션
    - [X] source와 target이 같은 색이면 예외 발생

- [X] 킹이 잡히면 게임이 종료된다.
- [ ] 현재 남아 있는 말에 대한 점수를 구할 수 있다.
  - [X] queen은 9점, rook은 5점, bishop은 3점, knight는 2.5점이다.
  - [ ] pawn의 기본 점수는 1점이다. 하지만 같은 세로줄에 같은 색의 폰이 있는 경우 1점이 아닌 0.5점을 준다.
  - [X] king은 잡히는 경우 경기가 끝나기 때문에 점수가 없다.
  - [ ] 한 번에 한 쪽의 점수만을 계산해야 한다.
- [ ] 점수에 따라 어느 진영이 이겼는지 계산한다.

status를 입력 받는다 -> 킹이 잡히면 게임 종료(Finished) -> 점수 계산, 승패 계산
                    -> 킹이 안잡히면(Running) -> 점수 계산

## 출력

- [X] 게임 시작을 입력받는다
- [X] 게임 종료를 입력받는다
- [X] 보드 출력
    - [X] 검은색 피스는 대문자로 출력
    - [X] 하얀색 피스는 소문자로 출력
