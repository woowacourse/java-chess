# java-chess

체스 미션 저장소

## 1단계 - 체스판 초기화 ♟

콘솔 UI 에서 체스 게임을 할 수 있는 기능을 구현한다.

### 구현 기능 목록
- [x] 게임 시작 혹은 종료 여부를 입력받는다.
    - [x] [예외처리] `start` 또는 `end` 이외의 문자 입력 시 예외 발생
- [x] 체스판을 생성한다.
    -  가로 위치는 왼쪽부터 a ~ h이고, 세로는 아래부터 위로 1 ~ 8로 구현한다.
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
- [x] 체스판을 출력한다.


### 실행 결과
  ```
  체스 게임을 시작합니다.
  게임 시작은 start, 종료는 end 명령을 입력하세요.
  start
  RNBQKBNR
  PPPPPPPP
  ........
  ........
  ........
  ........
  pppppppp
  rnbqkbnr
  
  end
```