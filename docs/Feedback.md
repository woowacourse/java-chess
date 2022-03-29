# 체스 1, 2, 3단계 구현 피드백

## 1차 피드백

- [ ] `status` 입력 관련 요구사항 맞지 않음.
    - `status` 입력 시 발생하는 상황에 대한 자세한 명세 필요할 듯
- [ ] `BoardGenerator`를 인터페이스로 두고 구현한 이유
    - ChessBoard가 정적 팩토리 메서드를 가지고 있어도 되지 않나?
- [ ] Piece가 null인지 검사하는 코드가 다수 존재
    - Null Object Pattern 학습 및 적용 권장
- [ ] 말의 이동 방향 생성기(DirectionsGenerator)를 인터페이스로 구현하여 주입하는 이유
    - 클래스 파일이 많아지고 불필요한 의존성도 생기는 것 같음.
    - 각각의 Piece 클래스들이 이동 방향을 가지고 있어도 되지 않을까?
- [x] Enum 상수 대문자 수정
    - 관례적으로 Enum 상수는 대문자로 작성
- [ ] `Pawn`
    - [x] `calculateAvailablePosition()` null 대신 빈 컬렉션을 반환하는 것은 어떤지.
    - [x] `ChessBoard`에서 해당 Piece가 `Pawn`인지 검사하는 코드가 존재.
        - `Piece` 클래스에서 검사하도록 수정하는건 어떤지
    - [ ] `ChessBoard` - `validateTargetRoutePawn()` 폰의 기본 이동 규칙을 폰 내부로 옮길 수 있는 방법
    - [ ] `BlackPawn`, `WhitePawn`을 나눠야할 필요가 없음.
- [ ] `ChessBoard`
    - [ ] 관리하는 대상을 체스 보드만 관리할 수 있도록
        - Player는 `ChessGame`과 같은 객체가 관리하도록하는 것은 어떨지.
    - [ ] 생성자에서 `BoardGenerator`를 받는 것 보다 `Map`을 받는게 명확하지 않나.

## 참고 자료

- [Null Object Pattern](https://johngrib.github.io/wiki/pattern/null-object/)
