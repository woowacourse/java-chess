# java-chess


### 콘솔에서의 게임 진행

- [x] start를 누를 경우 체스판이 초기화되며 게임이 시작된다.
- [x] end를 누를 경우 게임이 종료된다.


- 게임 진행
  - Ready
    - [x] [ERROR] start가 아닌 명령이 들어올 경우 예외가 발생한다.
    - [x] start명령이 들어오면 체스판을 생성하고 Running 상태를 반환한다.


- Running
  - [x] [ERROR] 허용된 명령어가 아니거나 명령어 형태가 맞지 않으면 예외가 발생한다.
    - 허용된 명령어 목록은 다음과 같다.
      - end (게임 종료)
      - status (점수 출력)
      - move [source] [target] (기물 이동)
    - [x] end명령이 들어오면 End 상태를 반환한다.
    - [x] status명령이 들어오면 현재 체스판의 점수를 출력한다.

- End
  - [x] [ERROR] run을 실행하면 예외가 발생한다.


### DB 연동

- [ ] mysql DB를 통해 게임 진행 상황을 저장하고 관리해야 한다.


### 체스 도메인 규칙

- [x] 각 기물의 시작 갯수는 제한된다
  - King, Queen : 1개
  - Rook, Bishop, Knight : 2개
  - Pawn : 8개


- 기물의 위치 가로는 왼쪽부터 오른쪽으로 a~h이다.
  - [x] [ERROR] 기물의 가로 위치가 a~h 범위가 아닌 경우 예외가 발생한다.


- 기물의 위치 세로는 아래부터 위로 1~8이다.
  - [x] [ERROR] 기물의 세로 위치가 1~8 범위가 아닌 경우 예외가 발생한다.


- [x] 각 기물의 초기위치는 아래와 같다.
```
  RNBQKBNR  8
  PPPPPPPP  7
  ........  6
  ........  5
  ........  4
  ........  3
  pppppppp  2
  rnbqkbnr  1

  abcdefgh
```

```
- 기물을 뜻하는 문자
- King : k
- Queen : q
- Rook : r
- Bishop  b
- Knight : n
- Pawn : p
```


- [x] 각 기물은 일반적인 목적지에 상대 피스가 존재할 경우 해당 위치로 이동할 수 있다.
  - 단 폰의 움직임은 이에 해당하지 않는다.


- [x] 각 기물은 체스판 영역을 벗어날 수 없다.


- [ERROR] 이동할 수 없는 좌표로 이동하려 하는 경우 예외가 발생한다.
  - [x] 각 기물은 목적지가 피스에 의해 경로가 막힐 경우 이동이 제한된다.
  - [x] 각 기물은 자신의 이동 가능 위치에 아군 기물이 있는 경우, 해당 위치로 이동이 제한된다.


- [x] [ERROR] 종료된 게임에서 체스말을 이동하려 할 경우 예외가 발생한다.
- [x] [ERROR] 입력받은 색상과 움직이려는 말의 색상이 다르면 예외가 발생한다.


- 움직일 수 있는 방향은 다음과 같다.
  - 상, 하, 좌, 우
  - 대각선
  - L자 (knight용)


- 각 기물의 움직임 규칙은 아래와 같다.
  - King 0점
    - [x] (상하좌우+대각선)방향으로 1칸 이동할 수 있다.
  - Queen 9점
    - [x] (상하좌우+대각선)방향으로 원하는 만큼 이동할 수 있다.
  - Rook 5점
    - [x] (상하좌우)방향으로 원하는 만큼 이동할 수 있다.
  - Bishop 3점
    - [x] (대각선)방향으로 원하는만큼 이동할 수 있다.
  - Knight 2.5점
    - [x] L자형(상하좌우로 2칸 이동 후 수직으로 1칸)으로 이동할 수 있다.
    - 나이트는 이동할 때 다른 모든 피스를 뛰어넘을 수 있다.

  - Pawn 1점 (같은 세로줄에 아군 폰이 있는 경우 0.5점)
    - 단순 이동
      - [x] 폰은 상대진영 방향으로만 전진할 수 있다.
      - 흰색은 UP, 검은색은 DOWN이다.
      - [x] 일반적으로 1칸 이동할 수 있으나, 최초 이동 시에는 2칸 이동할 수 있다.
      - [x] 목적지와 경로에 기물이 존재할 경우 전진할 수 없다.

    - 상대 기물 attack 후 이동
      - [x] (상대진형 방향 1칸 + 오른쪽 or 왼쪽 1칸)에 상대 기물이 있을 경우 해당 위치로 이동할 수 있다.
      - [x] 상대 보드의 끝으로 가면 퀸, 룩, 비숍, 나이트 중 원하는 기물로 변경할 수 있다. (프로모션)


- 종료된 체스 게임인지 확인할 수 있다.
  - [x] King이 한명이 될 때 게임이 종료된다.


- 현재 유저들의 점수 상태를 반환할 수 있다.
  - [x] 본인이 가지고 있는 기물의 점수 합이 해당 유저의 점수이다.

