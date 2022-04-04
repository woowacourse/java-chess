# 체스 미션

- 변형된 규칙의 체스 게임을 구현하는 미션
- 상속 vs 조합, enum을 활용한 중복 제거, 상태패턴
- Spark(웹), JDBC

## 콘솔 애플리케이션 기능 및 체스 게임 도메인 정보

### 게임 시작

- [x] `start`를 입력받아 새로운 체스 게임을 시작한다.
    - [x] 게임이 시작되지 않은 상태에서 `end`를 입력받으면 즉시 프로그램을 종료한다.
    - [x] `start`와 `end` 이외의 값을 입력하는 경우, 다시 입력을 요구한다.

- [x] 체스 게임이 시작되면 체스판을 초기화한다.
    - 각 행은 아래부터 1~8행으로 구성된다.
    - 각 열은 좌측부터 a~h열로 구성된다.
    - 백색 진영은 아래쪽에, 흑색 진영은 위쪽에 위치한다.
    - 2행과 7행은 각각 8개의 폰(Pawn)들로 구성된다.
    - 1행과 8행은 각각 2개의 룩(Rook), 2개의 나이트(Knight), 2개의 비숍(Bishop), 퀸(Queen)과 킹(King)으로 구성된다.

### 게임 진행

체스 게임이 진행되는 동안 백색과 흑색 진영이 1번씩 순서대로 돌아가며 체스 말을 이동시킨다.

- [x] 백색 진영부터 시작한다.
- [x] 현재 차례인 진영의 색깔이 아닌 체스 말을 이동시키려는 경우 예외가 발생한다.
- [x] 이동/공격 명령은 `move source target`으로 입력받는다.
- [x] 실행가능한 명령 형식에 부합하지 않는 경우 예외가 발생한다.
- [x] 예외가 발생하는 경우 다시 입력을 받는다.

#### 이동

`move source target` 형식으로 입력받았을 때 `target`에 체스 말이 없는 경우 해당 위치로 이동시킨다.

- [x] `source`와 `target`이 존재하지 않는 위치인 경우 예외가 발생한다.
- [x] `source`에 해당되는 체스 말이 없는 경우 예외가 발생한다.
- [x] 체스 말의 이동 가능 범위에 `target`이 포함되어 있지 않은 경우 예외가 발생한다.
    - [x] 기본적으로 해당 체스 말의 이동 규칙에 부합하지 않는 경우 예외가 발생한다.
    - [x] 이동하려는 위치에 도달하기까지 다른 체스 말이 가로막고 있는 경우 예외가 발생한다.

#### 공격

`move source target` 형식으로 입력받았을 때 `target`에 다른 색의 체스 말이 존재하는 경우 해당 체스 말을 공격한다.

- [x] 체스 말의 공격 가능 범위에 상대방 체스 말이 해당되지 않는 경우 예외가 발생한다.
    - [x] 기본적으로 해당 체스 말의 공격 규칙에 부합하지 않는 경우 예외가 발생한다.
    - [x] 공격하려는 위치에 도달하기까지 다른 체스 말이 가로막고 있는 경우 예외가 발생한다.
- [x] `target`에 같은 색의 체스 말이 존재하는 경우 예외가 발생한다.
- [x] 예외가 발생하는 경우 다시 입력을 받는다.

### 게임 종료

- [x] 킹이 잡히는 경우 즉시 게임이 종료된다.
    - [x] 점수와는 별개로 킹을 먼저 잡은 쪽이 승자다.

- [x] 게임 종료 후 `status`를 입력받으면 각 진영의 점수와 승리자 정보를 출력한다.
    - [x] 퀸은 9점, 룩은 5점, 비숍은 3점, 나이트는 2.5점, 폰은 1점으로 계산한다.
    - [x] 같은 색의 폰이 복수로 존재하는 경우, 해당 폰은 전부 0.5점으로 계산한다.
    - [x] 게임이 종료된 상태에서 `end`를 입력받으면 프로그램을 종료한다.
    - [x] `status`와 `end` 이외의 값을 입력하는 경우, 다시 입력을 요구한다.

## 웹 애플리케이션 기능 목록

### /

- [x] 새로운 게임 시작 버튼을 누르면 신규 게임 시작 후 `/game/:id`라우트로 이동한다.
    1. 버튼을 누르면 `/new-game`으로 POST 요청 전송.
    2. `/new-game`에서는 서버에서는 게임 생성 후 해당 게임에 할당된 `id` 값을 JSON으로 반환.
    3. 해당 게임에 할당된 `id` 값을 라우트에 담아 `/game/:id`로 이동.
- [x] 기존 게임 재개 버튼을 누르면 `/search` 라우트로 이동한다.

### /search

- [x] form에서 `id` 값을 입력받아 그에 대응되는 게임을 찾는다.
  - [x] `id` 값에 대응되는 게임이 존재하는 경우 `/game/:id` 라우트로 이동한다.
  - [x] `id` 값에 대응되는 게임이 존재하지 않는 경우 알람으로 예외 메시지를 보여주며 페이지는 변하지 않는다.

### /game/:id

- [x] `id` 값에 대응되는 게임의 현재 체스말 위치 정보에 따른 체스보드와 게임 상태 정보가 화면에 제시된다.
  - [x] 이동/공격 명령은 체스말 클릭을 통해 입력받는다.
      - [x] 각 턴이 종료될 때마다 해당 게임의 상태와 모든 체스말에 대한 정보는 DB에 저장된다.
      - [x] DB 저장 작업이 종료되었을 때 게임이 진행 중인 경우, 같은 `/game/:id` 화면을 보여준다.
      - 이동과 공격 관련 세부 로직은 콘솔 애플리케이션과 동일하다.
- [x] `id` 값에 대응되는 게임이 종료된 경우 `/result/:id` 라우트로 이동하는 버튼을 제공한다.
  - 게임 결과 조회 이외의 작업은 불가능하도록 팝업으로 화면을 가린다.
- [ ] `id` 값이 숫자이며, 아직 대응되는 게임이 없는 경우 예외로 간주한다.
- [ ] 예외가 발생하는 경우 예외 메시지를 알람으로 보여준다.

### /result/:id

- [x] 종료된 게임에 대한 점수, 승자, 최종적인 보드 정보를 화면에 보여준다.

---

## 부록

### 체스판 초기화

```
♖♘♙♕♔♙♘♖   8 (rank 8)
♗♗♗♗♗♗♗♗   7
........   6
........   5
........   4
........   3
♝♝♝♝♝♝♝♝   2
♜♞♟♛♚♟♞♜   1 (rank 1)

abcdefgh (files)
```

### 체스 말 이동 규칙

1. 기본적으로 각 체스 말은 자신만의 방식으로만 이동/공격할 수 있다.
2. 체스 말은 현재 위치와 이동하려는 위치 사이에 다른 체스 말이 존재하는 경우, 해당 위치로 이동할 수 없다. (나이트 제외)
3. 대부분의 체스 말은 자신이 이동하려는 위치에 다른 색의 체스 말이 있는 경우, 해당 말을 잡을 수 있다. (폰 제외. 킹 부분적 제외)
4. 체크 당한 상태에서는 킹이 공격 범위로부터 벗어나게 하는 행동 외에는 행할 수 없다.

#### 폰

- 이동: 기본적으로 앞으로 한 칸만 전진한다.
    - 다만, 최초 위치에서 단 한 번 2칸 전진할 수 있다.
- 공격: 전진하는 방향의 대각선으로 한 칸 이동하여 공격한다.

#### 나이트

- 상하좌우 1칸을 *건너뛰고* 해당 방향의 대각선으로 한 칸 이동한다.

#### 비숍

- 대각선으로 n칸 이동한다.

#### 룩

- 상하좌우로 n칸 이동한다.

#### 퀸

- 상하좌우 혹은 대각선으로 n칸 이동한다.

#### 킹

- 상하좌우 혹은 대각선으로 1칸만 이동한다.
- 이동하려는 위치가 다른 체스 말의 공격범위인 경우, 해당 위치로 이동할 수 없다.
