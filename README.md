# 🪜 체스 게임 구현 미션

## Pair: 져니 [⛄️](http://github.com/cl8d), 제나 [❤️](https://github.com/yenawee)

## ✔ 기능 요구사항

### ChessBoard

- [x] 체스판을 초기화한다.
    - [x] 체스 말의 위치에 대한 정보를 관리한다.
- [x] 해당 위치에 체스말이 존재하는지 확인한다.
- [x] 특정 위치에 존재하는 체스말을 반환한다.

### Camp

- [x] 사용자가 입력한 위치를 검증한다.
    - [x] 입력받은 위치 명령어의 길이가 2인지 확인한다.
    - [x] 첫 번째 글자가 a~h, 두 번째 글자가 1~8인지 확인한다.
- [x] 첫 번째 글자의 대소문자를 확인하여 영역을 확인한다.

### CampType

- 사용자의 진영을 관리한다.
    - [x] 입력받은 위치의 열이 소문자면 WHITE, 대문자면 BLACK 진영으로 나눈다.

### Piece

- [x] 체스말이 어떤 진영에 속하는지 관리한다.
- 원래 위치에서 target 위치로 이동한다.

### PieceType

- [x] 체스말의 종류를 관리한다.

### Position

- [x] 체스판 위의 위치 정보를 저장한다.
- [x] 목표 위치를 반환한다.

### MoveRule (interface)

- 체스말이 이동하는 행위를 관리한다.
    - 위로 이동한다.
    - 아래로 이동한다.
    - 왼쪽으로 이동한다.
    - 오른쪽으로 이동한다.
    - 우상향으로 이동한다.
    - 좌상향으로 이동한다.
    - 우하향으로 이동한다.
    - 좌하향으로 이동한다.
- **KingMoveRule**
    - 위로 1칸씩 이동한다.
    - 아래로 1칸씩 이동한다.
    - 왼쪽으로 1칸씩 이동한다.
    - 오른쪽으로 1칸씩 이동한다.
    - 우상향으로 1칸씩 이동한다.
    - 좌상향으로 1칸씩 이동한다.
    - 우하향으로 1칸씩 이동한다.
    - 좌하향으로 1칸씩 이동한다.
- **QueenMoveRule**
    - 위로 가능한 만큼 이동한다.
    - 아래로 가능한 만큼 이동한다.
    - 왼쪽으로 가능한 만큼 이동한다.
    - 오른쪽으로 가능한 만큼 이동한다.
    - 우상향으로 가능한 만큼 이동한다.
    - 좌상향으로 가능한 만큼 이동한다.
    - 우하향으로 가능한 만큼 이동한다.
    - 좌하향으로 가능한 만큼 이동한다.
- **RookMoveRule**
    - 위로 가능한 만큼 이동한다.
    - 아래로 가능한 만큼 이동한다.
    - 왼쪽으로 가능한 만큼 이동한다.
    - 오른쪽으로 가능한 만큼 이동한다.
- **KnightMoveRule**
    - 위로 2칸씩, 왼쪽으로 1칸씩 이동한다.
    - 위로 2칸씩, 오른쪽으로 1칸씩 이동한다.
    - 아래로 2칸씩, 왼쪽으로 1칸씩 이동한다.
    - 아래로 2칸씩, 오른쪽으로 1칸씩 이동한다.
- **PawnMoveRule**
    - 첫 시도에는 위로 2칸 이동할 수 있다.
    - 첫 시도 이후에는, 위로 1칸 이동한다.
    - 좌상향에 상대 체스말이 존재하면, 이동할 수 있다.
    - 우상향에 상대 체스말이 존재하면, 이동할 수 있다.
- **BishopMoveRule**
    - 우상향으로 가능한 만큼 이동한다.
    - 좌상향으로 가능한 만큼 이동한다.
    - 우하향으로 가능한 만큼 이동한다.
    - 좌하향으로 가능한 만큼 이동한다.

### PositionValidator (interface)

- **SourcePositionValidator**
    - 입력받은 위치에 같은 캠프의 체스 말이 존재하는지 확인하고, 없으면 예외를 발생시킨다.
- **TargetPositionValidator**
    - 입력받은 위치에 체스 말이 존재하는지 확인하고, 있으면 true, 없으면 false를 반환한다.

### PositionConverter

- [x] 입력받은 source, target 위치를 가로, 세로 값으로 분리한다.
- [x] 가로값은 왼쪽부터 0~7 세로값은 아래부터 0~7으로 변환한다.

### InputView

- [x] 게임 안내 메시지를 출력한다.
- [x] start를 입력하면 게임이 시작된다.
- end를 입력하면 게임이 종료된다.
- move와 source, target 위치를 분리한다.

### Command

- [x] 사용자가 입력한 명령어가 start, end, move인지 검증한다.

### PieceName

- [x] 체스말의 종류에 따라 체스말의 이름으로 변환한다.

### OutputView

- [x] 체스판에 있는 체스말을 출력하고, 비어있는 곳은 .으로 출력한다.
- source에 있는 말이 target으로 이동된 체스판을 출력한다.

---

## ✔ 프로그래밍 요구사항

- 자바 코드 컨벤션을 지키면서 프로그래밍한다.
    - 기본적으로 Java Style Guide을 원칙으로 한다.
- indent(인덴트, 들여쓰기) depth를 2를 넘지 않도록 구현한다. 1까지만 허용한다.
    - 예를 들어 while문 안에 if문이 있으면 들여쓰기는 2이다.
    - 힌트: indent(인덴트, 들여쓰기) depth를 줄이는 좋은 방법은 함수(또는 메서드)를 분리하면 된다.
- 3항 연산자를 쓰지 않는다.
- else 예약어를 쓰지 않는다.
    - else 예약어를 쓰지 말라고 하니 switch/case로 구현하는 경우가 있는데 switch/case도 허용하지 않는다.
    - 힌트: if문에서 값을 반환하는 방식으로 구현하면 else 예약어를 사용하지 않아도 된다.
- 모든 기능을 TDD로 구현해 단위 테스트가 존재해야 한다. 단, UI(System.out, System.in) 로직은 제외
    - 핵심 로직을 구현하는 코드와 UI를 담당하는 로직을 구분한다.
    - UI 로직을 InputView, ResultView와 같은 클래스를 추가해 분리한다.
- 함수(또는 메서드)의 길이가 10라인을 넘어가지 않도록 구현한다.
    - 함수(또는 메소드)가 한 가지 일만 하도록 최대한 작게 만들어라.
- 배열 대신 컬렉션을 사용한다.
- 모든 원시 값과 문자열을 포장한다
- 줄여 쓰지 않는다(축약 금지).
- 일급 컬렉션을 쓴다.
- 모든 엔티티를 작게 유지한다.
- 3개 이상의 인스턴스 변수를 가진 클래스를 쓰지 않는다.
- 도메인의 의존성을 최소한으로 구현한다.
- 한 줄에 점을 하나만 찍는다.
- 게터/세터/프로퍼티를 쓰지 않는다.
- 모든 객체지향 생활 체조 원칙을 잘 지키며 구현한다.
- 프로그래밍 체크리스트의 원칙을 지키면서 프로그래밍 한다.
