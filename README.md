# java-chess

체스 미션 저장소

## 우아한테크코스 코드리뷰

- [온라인 코드 리뷰 과정](https://github.com/woowacourse/woowacourse-docs/blob/master/maincourse/README.md)

---

## 용어 정리

- rank: 체스판에서의 가로줄(row)을 의미
- file: 체스판에서의 세로줄(column)을 의미

## 기능 요구 사항

### 입력

- [ ] start를 입력하면 게임을 시작한다
- [ ] end를 입력하면 게임이 종료된다
- [ ] move를 입력하면 말이 이동한다
    - [ ] 입력은 move source위치 target위치 의 형태로 입력한다

### 게임 진행

- [x] 체스판을 초기화한다
    - [x] 말의 위치 값은 가로 위치는 왼쪽부터 a ~ h이다
    - [x] 말의 위치 값은 세로는 아래부터 위로 1 ~ 8이다
    - [x] 체스판에서 각 진영은 검은색(대문자)과 흰색(소문자) 편으로 구분한다

### 게임 규칙

- [ ] 나이트를 제외한 다른 말들은 경로 상에 다른 말이 있다면 뛰어넘을 수 없다
- [x] King
    - [x] 상, 하, 좌, 우, 대각선으로 한 칸만 움직일 수 있다
- [x] Queen
    - [x] 상, 하, 좌, 우, 대각선으로 움직일 수 있다
- [x] Rook
    - [x] 상, 하, 좌, 우로 움직일 수 있다
- [ ] Knight
    - [ ] 상, 하 1칸 + 좌, 우 2칸 / 상, 하 2칸 + 좌, 우 1칸으로 움직일 수 있다
    - [ ] 이동 경로에 다른 말이 있어도 뛰어넘어 움직일 수 있다
- [x] Bishop
    - [x] 대각선으로 움직일 수 있다
- [ ] Pawn
    - [ ] 첫 이동은 상대 방향으로 1칸, 혹은 2칸 움직일 수 있다
    - [ ] 첫 이동이 아닌 경우 상대 방향으로 1칸만 움직일 수 있다
    - [ ] 대각선에 상대방 말이 있는 경우 말을 잡고, 그 위치로 이동할 수 있다

### 출력

- [x] 체스판을 출력한다
