# java-chess
체스 게임 구현을 위한 저장소

## 기능 목록

#### Direction

 - [x] 8방향 + knight의 움직임을 enum으로 가짐
 - [x] pawn의 경우 팀에 따라 올바른 전진 방향 기능 구현

#### Piece

 - [x] 기물 별로 움직일 수 있는 방향과 상대방 기물을 잡을 수 있는 방향 구현
 - [x] 기물 별로 팀 색을 부여
 - [x] 기물의 현재 위치를 부여
 - [x] 기물이 이동 가능한 List를 가지도록 설정
 - [x] pawn 경우 첫 이동 시 두칸까지 전진 가능하도록, 공격은 대각선만 가능하도록 설정
 
#### ChessGame
 
  - [x] 체스말을 초기 배치
  - [x] currentPosition에서 targetPosition으로 말을 이동시키는 기능 구현
  - [x] king이 죽었는지 체크 구현
  - [x] 팀 별로 totalScore를 계산해서 반환 구현

#### ChessAction

 - [x] 사용자 요구에 따라 start, move, end, status를 실행하도록 구현
 
