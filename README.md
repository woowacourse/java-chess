# java-chess

체스 미션 저장소

## 우아한테크코스 코드리뷰

- [온라인 코드 리뷰 과정](https://github.com/woowacourse/woowacourse-docs/blob/master/maincourse/README.md)


## 1단계 체스판 초기화

1. [x] 체스판은 8x8 사이즈이다.
2. [x] 체스의 말 종류는 P, R, N, B, Q, K 이렇게 6가지다.
3. [x] 진영은 대문자(검은색, 위쪽)와 소문자(흰색, 아래쪽)로 구분한다.
4. [x] `start`를 입력하면 체스판을 초기화한다.
5. [x] `end`를 입력하면 종료된다.


## 2단계 체스 말 이동
1. [x] 룩은 직선으로 움직인다.
2. [x] 비숍은 대각선으로 움직인다.
3. [x] 퀸은 직선 + 대각선으로 움직인다.
4. [x] 킹은 직선 + 대각선인데 이동 범위가 한칸으로 제한된다.
5. [x] 나이트는 직선으로 한칸 이동하고 그 방향의 대각선으로 한칸 이동한다.
6. [x] 폰은 처음 움직일 경우에는 직선 방향으로 한칸 또는 두칸 이동한다. 공격할 경우는 이동하는 방향의 대각선으로 한칸만 가능하다.
7. [x] 이동 경로에 다른 기물이 있으면 이동할 수 없다.
8. [x] 이동할 지점에 같은 편의 기물이 있으면 이동할 수 없다.
9. [x] 이동할 지점에 상대 편의 기물이 있으면 상대편 기물을 제거하고 그 자리에 이동한 기물이 있게된다.

## 3단계 승패 및 점수
1. [x] King이 잡히는 순간 게임을 종료한다.
2. [ ] status 명령을 입력하면 각 진영의 점수 출력하고 승패 여부를 출력한다.
3. [ ] 킹 = 0 / 퀸 = 9 / 룩 = 5 / 비숍 = 3 / 나이트 = 2.5 / 폰 = 1(세로줄에 같은 팀 폰있으면 0.5)

### Board
Map<Location, Piece>
해당 위치에 어떤 말이 있는지 알려줌

### Location
행(a,b,c ...) -> File
열(1,2,3 ...) -> Rank 

### Piece
해당 말의 종류와 진영

## 4단계
- [X] 체스 초기상태 웹뷰에 띄우기
- [x] input form 으로 move 명령어 적용하기 (뷰하고 백앤드하고 통합)
    - [x] move 명령 시 예외 메시지 뷰로 던져주기
- [x] 클릭으로 move 하는 기능 (Rest API)
    - [x] move 명령 시 예외 메시지 뷰로 던져주기
    - [x] response 에 게임 종료 여부 추가
    - [x] 게임 종료 시 게임이 종료됐다는 alert 띄우기
- [x] status 버튼 만들기
- [x] 체스판, 말 이미지 적용
- [x] 종료버튼 만들기

## 5단계
- [x] 모든 Position 은 DB에 저장되어야 한다. (64개)
- [x] if movable, update query and delete origin record
- [x] if status, get all positions and pieces
- [x] if end, destroy the room
- [x] create a room and create members
