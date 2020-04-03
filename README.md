## 기능 구현 목록
- Position은 행(Rank)과 열(File)로 이뤄져있다.
    - File (A-H)
    - Rank (1-8)

- 말(Piece)은 팀, 이름으로 이뤄져있다.
    - MoveValidStrategy를 이용해 특정 지점에 이동할 수 있는지 확인한다.
    
- MoveValidStrategy는 해당 말의 이동 경로 사이 유효한지 확인한다.
    
- 체스판(Board)는 체스 판 위 모든 위치별 말의 정보를 저장하고 있다.
    - 특정 말의 위치를 변경할 수 있다.