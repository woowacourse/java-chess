# java-chess

체스 미션 저장소

## 우아한테크코스 코드리뷰

- [온라인 코드 리뷰 과정](https://github.com/woowacourse/woowacourse-docs/blob/master/maincourse/README.md)

## 목표

- 서로 의견 충돌이 있을 때 잠깐 멈추고 다른 방향으로 생각해보기
    - 의견피력시 용어 외쳐보기 “탕슉”
- 헤어질 때 밥 시간이면 술도 곁들이기(주량 안쪽으로 + 집에 갈 수 있게)
- 바닥부터 구현하는 TDD (지금까지 미션 바탕으로)
    - “어떻게” 보다 “무엇을”
- 과설계 하지 않고, 주어진 요구사항에 집중하기
- 최대한 상속 사용하지 않고 조합을 사용하기
    - 단순히 중복 코드를 제거하기 위한 상속 말고, 서로를 대체할 수 있는 좋은 상속관계 만들기
    - 구현은 상속으로 하고, 리팩토링에서 상속 제거 도전해보기
- 시간 관리 잘 하기
    - 작은 단위의 시간 목표로 효율적인 협업
    - 밥 시간 잘 챙기기!
    - 너무 달리지 말고 중간 중간 환기하는 시간 가지기
- 지식 수준을 잘 싱크하면서 토론하기
    - 한 명만 아는 걸로 하지 않고, 서로가 충분히 이해할 수 있도록 다 같이 노력하기
    - 지금까지 피드백 받은 것들을 다 같이 공유해보기
- (시간 되면) 에러 처리하는 것도 도전해보고 싶다
- (1차 PR후에 혼자 리팩터링할때) 도메인 객체를 모두 불변객체로 만들기

---

## 요구사항

- 콘솔 UI에서 체스 게임을 할 수 있는 기능을 구현한다.
- 1단계는 체스 게임을 할 수 있는 체스판을 초기화한다.
- 체스판에서 말의 위치 값은 가로 위치는 왼쪽부터 a ~ h이고, 세로는 아래부터 위로 1 ~ 8로 구현한다.

```
RNBQKBNR  8 (rank 8)
PPPPPPPP  7
........  6
........  5
........  4
........  3
pppppppp  2
rnbbqknr  1 (rank 1)

abcdefgh

```

- 체스판에서 각 진영은 검은색(대문자)과 흰색(소문자) 편으로 구분한다.

### **프로그램 실행 결과**

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

## 기능 목록

- [x] 체스판과 체스 기물이 있다.
- [x] 체스판은 8x8 형식이다.
    - [x] 가로는 왼쪽부터 a~h이고, 세로는 아래부터 위로 1~8이다
- [x] 킹, 룩, 퀸, 비숍과 나이트는 마지막 줄에 위치한다.
    - [x] 양 끝에 룩이 위치한다.
    - [x] 그 다음 양쪽에 나이트가 위치한다.
    - [x] 그 다음에 비숍이 위치한다.
    - [x] 왼쪽 중앙에는 퀸이 위치한다.
    - [x] 오른쪽 중앙에는 킹이 위치한다.
- [x] 앞줄에는 8개의 폰이 한 줄로 위치해있다.
- [x] 반대편에도 동일하게 위치한다.
- [x] 각 진영은 검은색과 흰색으로 구분한다.
- [x] 출력 시 흰색 진영은 소문자, 검은색 진영은 대문자로 출력한다.

### 2단계

- [ ] 각 기물들을 이동할 수 있다.
  - [ ] 나이트를 제외한 모든 기물들은 다른 기물을 뛰어넘을 수 없다.
  - [X] 같은 편의 기물이 있는 자리는 갈 수 없다.
  - [x] 상대 편의 기물이 있는 자리로 가면 상대의 기물은 잡힌다.

##### 각 기물의 기본 이동 규칙
- [ ] 킹
  - [x] 어느 방향이든 한 칸 이동한다.
  - [ ] 스스로 체크 위치로 (잡힐 수 있는 위치) 이동할 수 없다.
- [ ] 퀸
  - [ ] 어느 방향이든 원하는 만큼 이동한다.
- [ ] 룩
  - [ ] 앞, 뒤, 양옆으로 원하는 만큼 이동한다.
- [ ] 비숍
  - [ ] 대각선으로 원하는 만큼 이동한다.
- [ ] 나이트
  - [ ] 앞, 뒤, 양옆으로 2칸 이동한 후 90도로 한 칸 더 이동한다.
- [ ] 폰
  - [ ] 앞으로 한 칸만 이동한다.
  - [ ] 이동이 처음이면 두 칸 이동할 수 있다.
  - [ ] 대각선 앞 한 칸에 상대 기물이 있을 경우, 해당 칸으로 가 상대 기물을 잡을 수 있다.
  - [ ] 바로 앞에 상대 기물이 있을 경우, 잡을 수 없다.

---

## 도메인 설계

사용자 → start를 요청 → 프로그램이 체스판을 초기화 시켜야 함

→ 시스템이 체스판을 초기화하라고 시킴 (시스템이 기물들을 준비시키고, 체스판에 주입시킨다)

→ 체스판이 체스말들을 제자리에 위치시킨다.

→ 체스말들이 메세지를 받고 제 위치로 간다.

### 체스판 Board

체스판 좌표 Position

체스판 가로 Row

체스판 세로 Column

### 체스기물 Piece → Pieces

폰 Pawn

룩 Rook

비숍 Bishop

나이트 Knight

퀸 Queen

킹 King

### 진영 Camp

Black

White

---
### Reference
[체스판을 준비하는 방법](https://www.chesskid.com/ko/learn/articles/how-to-play-chess-ko_KR#board)