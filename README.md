# java-chess
체스 게임 구현을 위한 저장소

### TODO
1. 좌표값을 저장하는 클래스를 생성한다
    * 좌표값을 캐싱하는 정적 리스트를 생성한다 
    * 유효성 검사
        * 범위 밖을 벗어나는지 확인 -> CoordinateOutOfBoundsException
    
2. 위치를 저장하는 클래스를 생성한다
    * 위치를 캐싱하는 정적 맵을 생성한다
    * 유효성 검사
        * 맵의 없는 키를 호출할 경우 예외처리 -> NotFoundPositionException