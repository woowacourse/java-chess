# java-chess

체스 미션 저장소

## 우아한테크코스 코드리뷰

- [온라인 코드 리뷰 과정](https://github.com/woowacourse/woowacourse-docs/blob/master/maincourse/README.md)

----

## 기능 목록

### 체스게임
- [x] king이 죽었는지 판별한다.

### 체스판
- [x] 초기 체스판을 생성한다.
- [x] 자기 위치로는 움직일 수 없다.
- [x] 경로 내 장애물이 있는 지 검증한다.

### 기물 
- [x] 체스판을 구성하는 요소이다.
- [x] 움직인다.
    - [x] 빈 공간은 움직일 수 없다.
    - [x] pawn은 올바른 위치로 움직일 수 있다.
      - pawn 처음에만 2칸 전진이 가능하다
      - 처음 이후에는 전진만 가능하다
    - [x] rook은 올바른 위치로 움직일 수 있다.
      - 출발 및 도착 위치에 대하여 행의 차이가 0 이거나 열의 차이가 0인 경우에 이동이 가능하다.
    - [x] knight는 올바른 위치로 움직일 수 있다.
      - 출발 및 도착 위치에 대하여 행의 차와 열의 차의 절댓값이 (2,1), (1,2)에 포함되면 이동이 가능하다.
    - [x] bishop는 올바른 위치로 움직일 수 있다.
      - 출발 및 도착 위치에 대하여 행의 차와 열의 차의 절댓값이 같으면 이동이 가능하다.
    - [x] queen은 올바른 위치로 움직일 수 있다.
      - rook과 bishop의 움지이는 조건이 만족하면 이동이 가능하다.
    - [x] king은 올바른 위치로 움직일 수 있다.
      - 출발 및 도착 위치에 대하여 행의 차와 열의 차의 절댓값이 (0,1), (1,0), (1,1)에 포함되면 이동이 가능하다.

### 위치 
- [x] 유효한 위치인지 검증한다.

### 경로
- [x] 출발 위치와 도착 위치 사이의 경로를 반환해준다.

### 점수
- [x] 현재 남아있는 말을 기준으로 점수를 책정한다.
  - King은 0점
  - Queen은 9점
  - rook은 5점
  - bishop은 3점
  - knight 2.5점
  - pawn의 경우 기본 점수는 1점이나 세로줄의 같은 색의 폰이 있는 경우 0.5점으로 계산한다.

### 입력
- [x] 게임 명령어를 입력 받는다
  - [x] start를 입력하면 초기 체스판이 등장한다.
  - [x] end를 입력하면 게임이 종료된다.
  - [x] move (시작위치) (도착위치)를 입력하면 말 이동을 시도한다.
  - [x] status를 입력하면 현재 판 상태를 기준으로 점수를 계산하고 어느 진영이 이겼는지 나타낸다.

### 출력
- [x] 게임 시작 문구를 출력한다.
- [x] 체스판 현황을 출력한다.

### 데이터베이스
- [x] DB 연결을 한다.
- [ ] 테이블 설계를 한다.
  - [ ] 기보를 저장하는 테이블을 만든다. (source 위치와 target 위치를 저장한다.)
- [ ] CRD를 구현한다.
  - [ ] create - 움직임이 발생했을 때 시작위치와 도착위치를 저장한다.
  - [ ] read - 게임을 불러올 때 기보를 읽어온다.
  - [ ] delete - end 입력시 저장된 정보를 제거한다.

### 리팩토링 목록
- [x] 테스트 코드 가독성 높게 수정하기 (parameterizedTest name 속성 이용하기)
- [x] 빈공간을 의미하는 객체 명칭을 보다 명확하게 하기
- [x] Name 객체 대신에 Team으로 기물들을 나누기
- [x] 파라미터 인자를 객체로 관리하기
- [x] 기물(Piece) 객체가 상태로 방향 객체를 가지게 하기
- [x] 기물에서 이동경로 생성 책임지기
- [x] 폰의 공격 움직임 책임 수정
- [x] 폰의 white와 black 각각으로 관리
- [x] 움직임에 대한 검증 로직을 더 세분화 하기
- [x] piece 타입 만들기