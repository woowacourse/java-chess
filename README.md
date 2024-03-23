# java-chess

## 페어와 지킬 컨벤션
1. 클래스 정의 다음 줄은 공백으로 한다.
2. test code에 사용하는 메서드는 `static import`한다.
3. `this`는 같은 클래스의 객체가 파라미터로 넘어왔을 때, 파라미터 변수 명이 필드의 변수 명과 겹칠 때 사용한다.

## 기능 요구 사항

### 체스 말
- [x] 무슨 팀인지 알려준다.

### 움직임
- [x] 이동 가능한지 판단한다.
- [x] 해당 경로를 구한다.

### 팀
- [x] 흰색, 검은색을 구분한다.

### 체스 보드
- [x] 체스 말 위치 초기화를 한다.
- [x] 해당 위치에 어떤 말이 있는지 알려준다.
- [x] 시작 위치의 말을 도착 위치로 옮긴다.
  - [x] 시작 위치에 말이 없을 경우 예외
  - [x] 말의 이동 범위를 넘어갈 경우 예외
  - [x] 이동 경로에 다른 말이 있을 경우 예외
  - [x] 마지막 위치에 적 말이 있을 경우, 잡아먹는다.
  - [x] 흰색부터 번갈아가며 플레이한다.


### 위치
- [x] 가로 위치(왼쪽부터 a~h)를 저장한다.
- [x] 세로 위치(아래부터 1~8)를 저장한다.
- [x] 서로 같은 위치인지 판단한다.
- [x] 다음 동, 서, 남, 북쪽 위치를 알려준다.

### 출력
- [x] 체스판에서 각 진영은 검은색(대문자)과 흰색(소문자) 편으로 구분한다.
