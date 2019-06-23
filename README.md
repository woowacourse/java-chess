# java-chess
### Todo
* 전체적으로 Refactor
    * Move 중복 제거

### Done
* Board 초기화
* 체스 말 만들기
    * Rook, Knight, Bishop, Queen, King, Pawn
* 두 점 사이 거리 구하기
* 방향 구하기
* 체스 말 움직이기
    * Pawn, Knight, Rook, Bishop, Queen, King
* Board 에서 이동 제어
    * 움직일 수 있는 위치 받아옴
    * 크기가 0이 아니여야 함(리스트 크기)
    * 리스트를 돌면서 그 사이에 Piece가 있으면 못움직임
        * 도착지점에 상대팀 말 있으면 죽임
* 점수 계산 (status)
    * 일반적 계산
* 죽은 말이 King인지 체크
* 점수 계산 (status)
    * Pawn 점수 0.5점 체크
* Pawn 대각선 이동일 때 상대팀 말 있어야 이동 가능
    * 상대팀 말 없으면 이동 불가