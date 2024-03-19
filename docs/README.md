## [♟ 체스 기능 목록]
- [x] 커맨드 입력 기능
  - 게임 시작 : start
  - 게임 종료 : end
- [ ] 체스판 생성 기능
  - 체스판(ChessBoard)
      - 가로는 왼쪽에서 부터 a~h (8칸)
      - 세로는 아래에서 부터 1~8 (8칸)
  - 피스(Piece)
    - 속성
      - 열: ChessFile
        - 범위는 a~h까지만 허용된다.
      - 행: ChessRank
        - 범위는 1~8까지만 허용된다.
      - 색깔: Color
        - WHITE
        - BLACK
      - 이름: Name
        - white는 소문자
        - black은 대문자
    - 종류 및 이름
      - 폰(Pawn): p(white), P(black)
      - 룩(Rook): r(white), R(black)
      - 나이트(Knight): n(white), N(black)
      - 비숍(Bishop): b(white), B(black)
      - 킹(King): k(white), K(black)
      - 퀸(Queen): q(white), Q(black)
    - 피스 초기 위치 및 개수
      - 폰(Pawn):
        - white: 8개 [Rank 2 | File a~h]
        - black: 8개 [Rank 7 | File a~h]
      - 룩(Rook): 
        - white: 2개 [Rank 1 | File a,h]
        - black: 2개 [Rank 8 | File a,h]
      - 나이트(Knight):
        - white: 2개 [Rank 1 | File b,g]
        - black: 2개 [Rank 8 | File b,g]
      - 비숍(Bishop): 
        - white: 2개 [Rank 1 | File c,f]
        - black: 2개 [Rank 8 | File c,f]
      - 킹(King):
        - white: 1개 [Rank 1 | File e]
        - black: 1개 [Rank 8 | File e]
      - 퀸(Queen): 
        - white: 1개 [Rank 1 | File d]
        - black: 1개 [Rank 8 | File d]
- [ ] 체스판 출력 기능
  - [ ] 피스의 각 위치에 맞게 이름(Name) 출력
  - [ ] 체스판에서 피스가 없는 위치는 점(.) 출력
  
## 프로그래밍 요구 사항
- [ ] 자바 코드 컨벤션을 지키면서 프로그래밍한다.
- [ ] 기본적으로 Java Style Guide을 원칙으로 한다.
- [ ] indent(인덴트, 들여쓰기) depth를 2를 넘지 않도록 구현한다. 1까지만 허용한다.
- [ ] 힌트: indent(인덴트, 들여쓰기) depth를 줄이는 좋은 방법은 함수(또는 메서드)를 분리하면 된다.
- [ ] 3항 연산자를 쓰지 않는다.
- [ ] else 예약어를 쓰지 않는다.
- [ ] 모든 기능을 TDD로 구현해 단위 테스트가 존재해야 한다. 단, UI(System.out, System.in) 로직은 제외
- [ ] 핵심 로직을 구현하는 코드와 UI를 담당하는 로직을 구분한다.
- [ ] UI 로직을 InputView, ResultView와 같은 클래스를 추가해 분리한다.
- [ ] 함수(또는 메서드)의 길이가 10라인을 넘어가지 않도록 구현한다.
- [ ] 함수(또는 메소드)가 한 가지 일만 하도록 최대한 작게 만들어라.
- [ ] 배열 대신 컬렉션을 사용한다.
- [ ] 모든 원시 값과 문자열을 포장한다
- [ ] 줄여 쓰지 않는다(축약 금지).
- [ ] 일급 컬렉션을 쓴다.
- [ ] 모든 엔티티를 작게 유지한다.
- [ ] 3개 이상의 인스턴스 변수를 가진 클래스를 쓰지 않는다.
- [ ] 도메인의 의존성을 최소한으로 구현한다.
- [ ] 한 줄에 점을 하나만 찍는다.
- [ ] 게터/세터/프로퍼티를 쓰지 않는다.
- [ ] 모든 객체지향 생활 체조 원칙을 잘 지키며 구현한다.
- [ ] 프로그래밍 체크리스트의 원칙을 지키면서 프로그래밍 한다.

## 출력 형식
```
> 체스 게임을 시작합니다.
> 게임 시작 : start
> 게임 종료 : end
> 게임 이동 : move source위치 target위치 - 예. move b2 b3
start
RNBQKBNR
PPPPPPPP
........
........
........
........
pppppppp
rnbqkbnr

move b2 b3
RNBQKBNR
PPPPPPPP
........
........
........
.p......
p.pppppp
rnbqkbnr

```
