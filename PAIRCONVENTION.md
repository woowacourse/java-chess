### 특수 마법
- castling: 킹과 록 위치 바꿈. 모두 한 번도 움직인 적 없을 때
- promotion: 폰이 끝까지 도달 -> 폰, 킹 제외 승격 가능
- en passant: 지나가는 중에

### 무승부
- 스테일메이트
- 기물 부족(impossibility of checkmate)

### 1단계 단순히 체스판만 초기화
```html
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

public interface Piece {
}

Rook -> R,
Pawn -> P,
Bishop -> B,
Knight -> N,
Queen -> Q,
King -> K

player
up -> 대문자
down -> 소문자

view?

domain?
player 16개 piece

controller
Player UpPlayer = new Player(Direction.UP)
Player DownPlayer = new Player(Direction.DOWN)

bright, dark <- 인종 덜 민감한 용어

view
printResult(player, 위다)
printResult(player, 아래다)


Enum
rank 1, 2, 3, 4, 5, 6, 7, 8
column a, b, c, d, e, f, g, h

#### 각 말의 위치
폰 -> x, y
class Pawn {
Column column;
}

`...` <- 만들어져 있는 하나의 클래스냐? 출력할 때 없으면 쩜쩜쩜인가?
. <-
64 <- something
상태를 가질 수 있다
룩 폰 킹 퀸 아무것도 없음
r p k q .

필즈
말 <- 인터페이스
- 이동한다.
-
룩 폰 비숍 나이트 ... <- 구현체
- 이동한다. 각자 다르게
- 위치 좌표 가짐
-

```html
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



### 2단계 말 이동까지 가능하게

### 3단계 점수와 승패 계산


### 페어 프로그래밍 규약
- 정적 팩토리 메서드 보다는 생성자를 우선
- 식사시간 6시 ~ 6시 30분 부터
- 최대 2시간 프로그래밍 후 휴식시간 갖기
- 7분 간격으로 역할을 바꾼다.
- 커밋 단위를 README 기준으로 한다.
    - TDD로 구현할 때는 테스트와 기능 추가를 한꺼번에 커밋한다.
- 코드 컨벤션
    - 의미가 충분히 전달이 된다면 인스턴스 변수명으로 value를 쓴다.
    - boolean을 반환하는 메서드는 (is, has)로 시작하도록 (가능하면) 이름 짓는다.
- 화장실 타임은 자유롭게 말 끊고 간다. (기분 나빠하지 않는다.)
- 언제든지 코드를 이해하고 싶을 때는 "타임"을 외친다. "타임"을 누군가 외치면 코드 작성을 멈추고 둘다 코드를 이해한 후 다시 시작한다.

### 프로그래밍 요구사항
- 함수(또는 메소드)가 한 가지 일만 하도록 최대한 작게 만들어라.
- 함수(또는 메서드)의 길이가 10라인을 넘어가지 않도록 구현한다.
- 배열 대신 컬렉션을 사용한다.
- 모든 원시 값과 문자열을 포장한다
- 줄여 쓰지 않는다(축약 금지).
- 일급 컬렉션을 쓴다.
- 모든 엔티티를 작게 유지한다.
- 3개 이상의 인스턴스 변수를 가진 클래스를 쓰지 않는다.

### 추가된 요구사항
- 도메인의 의존성을 최소한으로 구현한다.
- 한 줄에 점을 하나만 찍는다.
- 게터/세터/프로퍼티를 쓰지 않는다.
- 모든 객체지향 생활 체조 원칙을 잘 지키며 구현한다.
- 프로그래밍 체크리스트의 원칙을 지키면서 프로그래밍 한다.