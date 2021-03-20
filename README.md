# java-chess
체스 게임 구현을 위한 저장소

## 기능 목록

#### Direction
- [x] 8방향 + knight의 움직임을 enum으로 표현
- [x] pawn의 경우 팀에 따른 올바른 전진 방향 기능 구현

#### Piece
- [x] 기물 별 생성
- [x] 기물 별 움직일 수 있는 방향과 상대방 기물을 잡을 수 있는 방향 구현
- [x] 기물 별 팀 색 부여

#### PieceFactory
- [x] 초기 배치된 기물들 생성

#### ChessGame
- [x] currentPosition 과 targetPosition 을 받아 체스말 움직이기
    - [x] currentPosition 에 아무 말이 없다면 예외
    - [x] targetPosition 으로 이동이 불가능하다면 예외
    - [x] 다른 팀 색의 말을 선택할 시 예외
    - [x] targetPosition 에 상대 말이 있다면 상대말은 사라진다. 
