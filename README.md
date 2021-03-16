# 구현할 기능 목록

- 체스판 초기화
    - 초기 기물 위치 배정

- 기물 위치(Point)
    - 예외:  a~h, 1~8 사이가 아닌 입력인 경우
    

- 기물 이동(Board, Piece)
    - 입력: source 위치, target 위치
        - 예외: 옳지 않은 형식 ex) mov b2 b3
        - 예외: source와 target이 같은 경우
        - 예외: source에 기물이 없는 경우
        - 예외: target에 같은 색 기물이 있는 경우
        - 예외: 가는 경로에 다른 기물이 있는 경우
        - 예외: 기물이 이동할 수 없는 위치인 경우
            - 예외: 장외이동
    
    - PAWN
        - 이동 시엔 직선
        - 상대 기물 먹을 땐 대각선
    - BISHOP
        - 이동 시에 대각선 (X축 차 == Y축 차)
    - ROOK
        - 이동 시에 직선 (X축 == 현 X축 || Y축 == 현 Y축)
    - KNIGHT
        - X축 차^2 + Y축 차^2 == 5
    - KING
        - X축 차^2 + Y축 차^2 == 1 || == 2
    - QUEEN
        - BISHOP || ROOK
        
- 승패 및 점수
    - KING이 잡히면 게임 종료
    - 입력: status 입력 시 현재 남아 있는 말에 대한 점수 계산
        - 예외: 옳지 않은 입력
    - QUEEN:9, ROOK: 5, BISHOP: 3, KNIGHT: 2.5, PAWN: 1, KING: 0
    - 같은 세로줄에 같은 색 폰이 있으면 0.5