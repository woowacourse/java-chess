# java-chess
체스 게임 구현을 위한 저장소

1. ChessGameController
    + 웹에서 전달받은 request를 처리
2. dao(ChessGameDAO, PieceDAO) 
    + database의 데이터를 가져오고, 저장
3. DatabaseConnection
    + database와 연결
    + Connection 생성
4. piece 패키지의 객체들
    + 해당 말의 이동 규칙 저장
    + 이동 최대 거리 저장
5. ChessBoard
    + 현재 존재하는 말 저장
    + 플레이어에 따라 점수 계산
6. ChessGame
    + 말 이동 
    + 게임 종료(킹이 잡혔을 때) 여부 확인
    + 우승자 계산
7. ChessInitialPosition
    + ChessBoard 초기화
8. ChessPiece
    + 말의 정보 저장
9. Coordinate
    + 좌표(x,y)를 나타냄
10. Direction
    + 말의 이동 할 수 있는 방향을 나타냄
11. MovementInfo
    + 말의 이동 방향 저장
    + 말의 최대 이동 거리 저장
12. Path
    + 말의 이동 경로를 나타냄
13. Player
    + 게임 사용자를 나타냄
14. Position
    + ChessBoard의 한 칸을 나타냄
15. Result
    + 게임 결과를 나타냄
16. Score
    + 말이 가지고 있는 점수를 나타냄
