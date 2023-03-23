# 🪜 체스 게임 구현 미션

## Pair: 져니 [⛄️](http://github.com/cl8d), 제나 [❤️](https://github.com/yenawee)

## 1단계 피드백 사항 및 추가 수정 사항
- [ ] 출력 시 View 와 Domain 의존성 제거 - DTO 도입
- [x] 매직 넘버 상수화
- [x] 클래스 및 메소드 네이밍 의미있는 표현으로 변경
- [ ] Pawn 의 이동 규칙 관리 ChessGame 에서 적절한 객체로 책임 위임
- [x] Piece 추상화 구조 및 이동 규칙 관리(move) 설계 수정
- [x] 일급 컬렉션 추가(미션 요구사항)
- [ ] 게임 커맨드 관리 상태 패턴 -> 커맨드 패턴 적용해보기 (학습 목적)
- [ ] Readme 에 네이밍 사전, 클래스 다이어그램, 프로그래밍 흐름도 정리하기
- [ ] 예외 발생 시 재입력


## ✔ 기능 요구사항

### ChessBoard

- [x] 체스판을 초기화한다.
    - [x] 체스 말의 위치에 대한 정보를 관리한다.
- [x] 해당 위치에 체스말이 존재하는지 확인한다.
- [x] 특정 위치에 존재하는 체스말을 반환한다.
- [x] 특정 위치에 존재하는 체스말을 제거한다.
- [x] 특정 위치에 체스말을 둔다.
- [x] 특정 말의 종류에 따라서 시작 위치에서 종료 위치로 이동 가능한지 판단한다.

### ChessGame

- [x] 체스 게임을 진행한다.

### CampType

- [x] 사용자의 진영을 관리한다.
    - [x] 입력받은 위치의 열이 소문자면 WHITE, 대문자면 BLACK 진영으로 나눈다.
- [x] 플레이할 진영을 번갈아가며 반환한다.

### Piece

- [x] 체스말이 어떤 진영에 속하는지 관리한다.
- [x] 두 개의 체스말이 동일한 진영에 속하는지 판단한다.
- [x] 체스말이 폰인지 확인한다.
- [x] 체스말이 나이트인지 확인한다.
- [x] 체스말이 입력받은 진영에 속하는지 판단한다.
- [x] 체스말이 시작 위치에서 도착 위치로 이동 가능한지 판단한다.

### Queen

- [x] 입력받은 위치에 대해서 도착 위치까지 퀸이 이동 가능한지 판단한다.

### Rook

- [x] 입력받은 위치에 대해서 도착 위치까지 룩이 이동 가능한지 판단한다.

### Bishop

- [x] 입력받은 위치에 대해서 도착 위치까지 비숍이 이동 가능한지 판단한다.

### King

- [x] 입력받은 위치에 대해서 도착 위치까지 킹이 이동 가능한지 판단한다.

### Knight

- [x] 입력받은 위치에 대해서 도착 위치까지 나이트가 이동 가능한지 판단한다.

### Pawn

- [x] 입력받은 위치에 대해서 도착 위치까지 폰이 이동 가능한지 판단한다.

### PieceType

- [x] 체스말의 종류를 관리한다.
- [x] 특정 체스말이 시작 위치에서 종료 위치까지 이동 가능한지 판단한다.

### Position

- [x] 체스판 위의 위치 정보를 저장한다.
- [x] 목표 위치를 반환한다.
- [x] 위치가 입력받은 제한값을 넘어가는지 판단한다.
- [x] 목표 위치로 이동하기 위한 단위 벡터를 계산한다.
- [x] 입력받은 위치를 복사한 새로운 위치를 반환한다.
- [x] 현재 위치의 rank가 입력받은 rank보다 큰지 판단한다.

### Direction

- [x] 체스말이 이동하는 방향으로 관리한다.
- [x] 모든 방향에 대해서 반환한다.
- [x] 상하좌우에 대해서 반환한다.
- [x] 상하좌우의 대각선에 대해서 반환한다.
- [x] L자 모양으로 이동하는 방향에 대해서 반환한다.

### Movable

- [x] 체스말의 움직임을 관리한다.

### Move

- [x] 입력받은 위치로 이동시킨다.
- [x] 체스말이 이동 가능한 위치를 전부 반환한다.

### QueenMove

- [x] 입력받은 위치에 대해서 퀸이 이동 가능한지 판단한다.

### RookMove

- [x] 입력받은 위치에 대해서 룩이 이동 가능한지 판단한다.

### BishopMove

- [x] 입력받은 위치에 대해서 비숍이 이동 가능한지 판단한다.

### KingMove

- [x] 입력받은 위치에 대해서 킹이 이동 가능한지 판단한다.

### KnightMove

- [x] 입력받은 위치에 대해서 나이트가 이동 가능한지 판단한다.

### PawnMove

- [x] 입력받은 출발 위치의 행보다 도착 위치의 행이 더 크면 UP 방향, 아니면 DOWN 방향으로 이동할 수 있는지 판단한다.

### PositionConverter

- [x] 입력받은 source, target 위치를 가로, 세로 값으로 분리한다.
    - [x] 가로값은 왼쪽부터 0~7 세로값은 아래부터 0~7으로 변환한다.
- [x] 사용자가 입력한 위치를 검증한다.
    - [x] 입력받은 위치 명령어의 길이가 2인지 확인한다.
    - [x] 첫 번째 글자가 a~h, 두 번째 글자가 1~8인지 확인한다.

### InputView

- [x] 명령어를 입력받는다.

### Command

- [x] 사용자가 입력한 명령어에 대해서 관리한다.
- [x] 사용자가 입력한 명령어가 start, move, end인지 검증한다.
- [x] 사용자가 입력한 명령어가 start인지 확인한다.
- [x] 사용자가 입력한 명령어가 end인지 확인한다.
- [x] 사용자가 입력한 명령어가 move일 때 올바른 명령어 길이로 들어오는지 확인한다.

### CommandType

- [x] 명령어의 종류에 대해서 관리한다. (start, move, end)

### Status

- [x] 사용자가 입력한 명령어에 따라 게임의 상태를 확인한다.
- [x] 현재 게임이 실행 중인지 판단한다.

#### end

- [x] 사용자가 입력한 명령어가 end가 아니라면 예외를 발생시킨다.
- [x] 현재 게임이 실행 중인지 판단한다.

#### move

- [x] 사용자가 입력한 명령어가 start라면 예외를 발생시키고, end라면 종료한다.
- [x] 사용자가 입력한 체스말을 시작 지점에서 끝 지점으로 이동시키고, 차례를 변경한다.
- [x] 현재 게임이 실행 중인지 판단한다.

#### start

- [x] 사용자가 입력한 명령어가 move라면 예외를 발생시키고, end라면 종료한다.
- [x] 현재 게임이 실행 중인지 판단한다.

### PieceName

- [x] 체스말의 종류에 따라 체스말의 이름으로 변환한다.

### OutputView

- [x] 게임 안내 메시지를 출력한다.
- [x] 체스판에 있는 체스말을 출력하고, 비어있는 곳은 .으로 출력한다.
- [x] source에 있는 말이 target으로 이동된 체스판을 출력한다.
