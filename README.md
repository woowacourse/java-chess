# java-chess
체스 게임 구현을 위한 저장소

### TODO
1. 좌표값을 저장하는 클래스를 생성한다
    * 좌표값을 캐싱하는 정적 리스트를 생성한다 
    * 유효성 검사
        * 범위 밖을 벗어나는지 확인 -> CoordinateOutOfBoundsException
    * 이동할 좌표값만큼 현재 좌표에서 더한다
    
2. 위치를 저장하는 클래스를 생성한다
    * 위치를 캐싱하는 정적 맵을 생성한다
    * 유효성 검사
        * 맵의 없는 키를 호출할 경우 예외처리 -> NotFoundPositionException
    * 주어진 방향으로 x, y 좌표값을 이동시킨다
    
3. 체스 Piece 구현 클래스를 생성한다
    * Piece의 타입을 저장한다
        * 쉽게 타입을 식별할 수 있는 문자를 저장한다
        * 타입별 점수를 저장한다
    * 팀의 정보를 저장한다
        * WHITE, BLACK 으로 팀을 나눈다
        * 각 팀은 1(BLACK), -1(WHITE)의 값을 저장한다
    * 이동가능한 모든 위치를 계산한다
        * 이동 방향을 정의한다
        * 나이트(KNIGHT)에 대한 이동 방향은 별도로 정의한다
    
4. 체스 보드판을 관리하는 클래스를 생성한다
    * 체스 Piece의 위치 상태를 저장한다
    * 체스 Piece의 위치를 이동시킨다