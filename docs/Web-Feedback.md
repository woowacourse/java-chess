# 체스 4, 5단계 구현 피드백

## 1차 피드백

- [ ] `WebApplication`
    - [x] 이동 로직 코드 `WebChessController` 내부로 이동
    - [x] 하나의 라우터로 완전한 기능을 제공해야 하는 부분이 개별적으로 분리됨.
        - `GET /` 게임 생성 화면에서 게임 이름을 입력 받아 `POST /save`를 호출하여 게임이 없으면 게임 생성 및 시작 처리
        - `GET /play/:gameName`으로 redirect
    - [x] `GET /play/:gameName` 게임 불러올 때마다 점수도 함께 응답하면 어떤지
    - [ ] `ChassGame`을 필드로 가지고 있는다면 여러 사용자가 동시에 게임을 할 경우에 문제가 발생할 수 있다.
        - 어떤 문제가 발생할 수 있을까?
        - 두개이상의 게임을 실행하게되면 하나의 ChessGame이 적용되어 동시 접근하게됨. 즉, 개별적으로 게임 불가.
- [ ] `game` DB
    - [ ] `name` -> `id` auto_increment 컬럼 추가하여 id로 구분
- [ ] 게임 방 기능 구현
- [x] `Direction`
    - [x] 코드 추가를 통해 `MovableRangePiece`, `SpecificLocationPiece` 클래스 제거
        - `Direction.diagonal(file, rank)`, `Direction.straight(finle, rank)`
- [ ] `Pawn`
    - [ ] `getDirectitons`를 통해 `move()` override 없이 구현
- [ ] `Piece`
    - [ ] subList가 꼭 필요한가
    - [x] 미사용 상수 제거
- [x] `Position`
    - `null 반환`이 아닌 `예외` 적용
