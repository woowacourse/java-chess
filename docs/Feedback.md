# 체스 1, 2, 3단계 구현 피드백

## 1차 피드백

- [x] `status` 입력 관련 요구사항 맞지 않음.
  `status` 입력 시 발생하는 상황에 대한 자세한 명세 필요할 듯
- [x] `BoardGenerator`를 인터페이스로 두고 구현한 이유
    - ChessBoard가 정적 팩토리 메서드를 가지고 있어도 되지 않나?
    - 불필요한 인터페이스 구현 -> 제거
- [x] Piece가 null인지 검사하는 코드가 다수 존재
    - Null Object Pattern 학습 및 적용 권장
- [x] 말의 이동 방향 생성기(DirectionsGenerator)를 인터페이스로 구현하여 주입하는 이유
    - 클래스 파일이 많아지고 불필요한 의존성도 생기는 것 같음.
        - 불필요한 의존성 : `List<Direction`이 필요하나 `DirectionGenerator`를 생성자로 받아서 `List<Direction>`을 생성한다면
          의존하지 않아도 된다.
    - 각각의 Piece 클래스들이 이동 방향을 가지고 있어도 되지 않을까?
- [x] Enum 상수 대문자 수정
    - 관례적으로 Enum 상수는 대문자로 작성
- [x] `Pawn`
    - [x] `calculateAvailablePosition()` null 대신 빈 컬렉션을 반환하는 것은 어떤지.
    - [x] `ChessBoard`에서 해당 Piece가 `Pawn`인지 검사하는 코드가 존재.
        - `Piece` 클래스에서 검사하도록 수정하는건 어떤지
    - [x] `ChessBoard` - `validateTargetRoutePawn()` 폰의 기본 이동 규칙을 폰 내부로 옮길 수 있는 방법
        - Pawn과 다른 기물의 차이점
            - Pawn
                - 대각선으로만 공격이 가능(즉, 대각선으로 이동하려면 Target이 상대방의 색이어야한다.)
                - 직진은 Target과 경로가 모두 비어있어야만 이동 가능하다.
                - 첫 이동이라면 2칸 이동할 수 있다.
            - 타 기물
                - 해당 Target까지의 경로가 비어있어야 이동 가능하다.
        - `수정`
            - Pawn
                - 처음이 아닐 때 2칸 이동하는 경우 -> `Pawn`에서 예외 처리
                - Target 포함된 방향의 이동 경로 전달 -> `Pawn`에서 반환
            - Piece
                - Target 제외 경로가 비어있어야 이동 가능 -> `Piece`에서 이동 가능한 List<Position> 가공
    - [x] `BlackPawn`, `WhitePawn`을 나눠야할 필요가 없음.
- [x] `ChessBoard`
    - [x] 관리하는 대상을 체스 보드만 관리할 수 있도록
        - Player는 `ChessGame`과 같은 객체가 관리하도록하는 것은 어떨지.
    - [x] 생성자에서 `BoardGenerator`를 받는 것 보다 `Map`을 받는게 명확하지 않나.
    - [ ] `ChessBoardGenerator`라면 `ChessBoard`를 반환해도 되지 않나.

## 2차 피드백

- `Piece`에서 `isBlank()`는 핵심 비즈니스 로직의 일부로 사용되기에 사용의 문제 없음.
    - [ ] King을 확인하는 메서드 또한 Piece에서 `isKing`과 같이 구현하는 것을 추천
    - [ ] 구현체 기물 클래스에서 `List<Direction>`은 필드가 아닌 상수로 선언해도 된다.
- [x] `gradlew.bat` : "@rem you may not use this file except in compliance with the License." 메세지
  - 체스보드 행,열(File, Rank 클래스)의 이름을 수정하는 과정에서 발생.
- [ ] enum 간의 비교는 `==`로 적용
- [ ] `Status`, 이미 계산된 값을 통해 객체를 생성하면 이 객체의 책임은 무엇인가.
    - [ ] 생성자는 유효성 검사 및 초기화하는 작업만 하도록 수정
        - 객체 생성 비용의 증가
        - 객체를 생성하고 사용하지 않는다면 불필요한 연산이다.
- [ ] `PieceScore`
    - [ ] Utils 패키지로 분류되는 것이 맞는지
    - [ ] Enum 내부에서는 `DUPLICATE_PAWN`을 사용하지 않음.
        - 점수를 계산하는 책임을 가진 객체가 가지고 있는 것이 좋음.
    - [ ] `Piece`에 `score` 추상 메서드를 구현하여 각 기물들이 점수를 구현하는 방법
- [ ] `Piece`
    - [ ] 이동규칙의 로직이 복잡하다. 단 하나의 추상 메서드만 제공하여 구현
        - `public abastract List<Position> move(Position source, Position target);`
- [ ] `ChessBoard`
    - [ ] `calculatePawnsInFile` : 다른 기물이 폰과 동일한 점수를 갖게된다면 계산이 잘못 될 수 있다.
        - `pieceScore -> pieceScore.score() == PieceScore.PAWN.score()` 수정 필요
    - [ ] 메서드의 분리 구현
        - File에 위치한 Player의 기물을 필터링 하는 메서드
        - 기물들 중 폰을 필터링하는 메서드
        - 기물들의 점수를 구하는 메서드
- [ ] 미사용 코드 제거
- [ ] `KnightTest` - DisplayName 수정
- [ ] `PawnTEst` - 첫 이동 시 두 칸이동에 대한 테스트 없음.

## 참고 자료

- [Null Object Pattern](https://johngrib.github.io/wiki/pattern/null-object/)
