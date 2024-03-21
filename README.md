# java-chess

체스 미션 저장소

## 우아한테크코스 코드리뷰

- [온라인 코드 리뷰 과정](https://github.com/woowacourse/woowacourse-docs/blob/master/maincourse/README.md)

<hr>

## 구현 기능 목록

### 체스판

- [x] 체스판의 크기는 `8 x 8`이다.
- [x] 체스판에서 말의 위치 값은 다음과 같이 구현한다.
    - 가로 위치는 왼쪽부터 오른쪽으로 `a ~ h`
    - 세로 위치는 아래부터 위로 `1 ~ 8`
- [x] 체스판에서 각 진영은 검은색(대문자)과 흰색(소문자) 편으로 구분한다.
- [x] 게임 시작 시 검은색 말은 `7 ~ 8` 행에 위치 하고, 흰색 말은 `1 ~ 2` 행에 위치 한다.
- [x] 각 기물의 초기 위치는 아래와 같다.
    ```
    RNBQKBNR  8 (rank 8)
    PPPPPPPP  7
    ........  6
    ........  5
    ........  4
    ........  3
    pppppppp  2
    rnbqkbnr  1 (rank 1)
    
    abcdefgh
    ```
- [x] 체스판 위에 있는 기물을 움직일 수 있다.

### 기물

- [x] 출발지와 목적지를 받아 이동 방식에 따라 움직일 수 있는지 판별한다.
- [x] 목적지로 가는 경로에 다른 기물이 있는지 판별한다.
    - [ ] 단, 나이트는 제외한다.

### 체스 게임

- [ ] 사용자에게 커맨드를 입력 받는다.
    - [x] `start`를 입력 받으면 게임을 시작한다.
        - [x] 게임 시작 시 초기 보드를 출력한다.
    - [ ] `move source위치 target위치`를 입력 받으면 기물은 이동한다.
        - [ ] 출발지와 목적지를 보드에게 전달하여 이동을 요청한다.
    - [ ] `end`를 입력 받으면 게임을 종료한다.
