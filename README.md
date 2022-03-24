# java-chess

체스 미션 저장소

## 우아한테크코스 코드리뷰

- [온라인 코드 리뷰 과정](https://github.com/woowacourse/woowacourse-docs/blob/master/maincourse/README.md)

## 기능 요구사항

* 체스판 초기화
    * 기본 위치에 맞게 기물을 생성한다.
    * row는 1~8, column은 a~h로 표현한다.
    * 체스판에서 각 진영은 검은색(대문자)과 흰색(소문자) 편으로 구분한다.

* 기물이 가져야할 기능
    * 팀이 정해져야함(색깔 등)

* 기물의 종류
    * 폰(Pawn)
    * 비숍(Bishop)
    * 나이트(Knight)
    * 룩(Rook)
    * 퀸(Queen)
    * 킹(King)
    

- 게임의 상태에 대해서
    - ready
    - blackTurn
    - whiteTurn
    - blackWin
    - whiteWin

## 기물 이동 기능 구현 목록

- [x] 이동할 위치로 갈 수 있는지를 확인한다.
    - [x] 기물의 이동 규칙을 지키는지 확인한다.
        - [x] PAWN
            - [x] 화이트는 rank가 높아지는 방향으로, 블랙은 rank가 낮아지는 방향으로 이동할 수 있다.
            - [x] 첫 움직임에 한칸 혹은 두칸 앞으로 갈 수 있다.
                - white -> 2 black -> 7 이면 처음이다.
            - [x] 첫 움직임이 아닌 경우 한 칸만 앞으로 갈 수 있다.
            - [x] 대각선 앞에 상대 기물이 있으면 이동하면서 잡을 수 있다.
            - [x] 뒤로 이동할 수 없다.
        - [x] KNIGHT
            - [x] 상하좌우 두칸 앞으로 이동 후 좌우의 위치로 이동한다.
        - [x] ROOK
            - [x] 상하좌우 직선으로 이동한다.
        - [x] BISHOP
            - [x] 대각선 방향으로 직선으로 이동한다.
        - [x] QUEEN
            - [x] 상하좌우 대각선 방향으로 직선으로 이동한다.
        - [x] KING
            - [x] 상하좌우 대각선으로 한 칸 이동한다.
    - [x] 이동할 위치에 아군 기물이 있으면 안된다.
        - [x] PAWN
        - [x] KNIGHT
        - [x] ROOK
        - [x] BISHOP
        - [x] QUEEN
        - [x] KING
- [x] 이동 경로에 다른 기물이 있는지 확인한다.
    - [x] PAWN
    - [x] ROOK
    - [x] BISHOP
    - [x] QUEEN

---

## 승패

각 말들의 점수가 추가되어야함

점수 계산하는 기능 추가

"status"명령을 입력하면 각 진영의 점수를 출력하고 어느 진영이 이겼는지 결과 확인 가능해야함

queen은 9점, rook은 5점, bishop은 3점, knight는 2.5점 pawn 1점(같은 세로줄에 같은 색의 폰이 있는 경우 1점이 아닌 0.5점을 준다.)
