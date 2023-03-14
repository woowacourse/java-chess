# java-chess

# 기능 목록

- [ ] 콘솔 UI에서 체스 게임을 할 수 있는 기능을 구현한다.
- [ ] 1단계는 체스 게임을 할 수 있는 체스판을 초기화한다.
- [ ] 체스판에서 말의 위치 값은 가로 위치는 왼쪽부터 a ~ h이고, 세로는 아래부터 위로 1 ~ 8로 구현한다.
- [ ] 체스판에서 각 진영은 검은색(대문자)과 흰색(소문자) 편으로 구분한다.

## 도메인

### 좌표

- [x] 좌표는 file과 rank를 필드로 가진다.
- [x] 좌표는 (A,1) ~ (H,8) 까지 가능하다.

### 피스

- [ ] 자신의 진영을 필드로 가지고 있다.
- [ ] 움직일 수 있는 위치 리스트를 반환한다.

### 체스판

- [ ] 좌표를 입력으로 주었을때, 어떤 피스가 있는지 알 수 있다.
- [ ] 피스를 교체한다.

### 랭크 : 가로줄을 나타내는 enum

### 파일 : 세로줄을 나타내느 enum

### 게임

### 명령

- [ ] 체스판을 초기화 한다.
- [ ] 체스 게임을 종료한다.

## UI

### 입력

- [ ] start를 입력할 수 있다.
- [ ] end를 입력할 수 있다.

### 출력

- [ ] 체스판을 출력한다.