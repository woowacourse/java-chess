# step1 피드백
- [x] guava 의존성 제거(spark에 포함되어 있음)
- [ ] null 대신 exception으로 처리하기
  -> exception을 날리면 예외 상황으로 처리됨. 내가 하고 싶은 것은 계속 움직이다가 움직일 수 없는 상태까지 가는 것! 
  (이 경우 막혔을 때 예외를 날리면 안 된다고 생각)
- [x] isKing, isPawn 오버라이드 방식으로 메소드 수정
- [x] Position isPawnLine -> NumberRows로 비교
- [x] TDD, 단위테스트, 통합테스트 학습하고 최대한 꼼꼼하게 작성하기
- [x] Position의 유무 다시 체크해보기
  
# step2

- [x] 웹 UI적용
- [x] 웹 서버 적용
    - [x] 웹 서버를 재시작하더라도 이전 게임을 이어 진행 가능
  
# step2 피드백
- [x] 컨트롤러는 상태를 갖지 않는다. static을 사용한다면 final과 같이 사용한다. 동시성 문제 때문 (컨트롤러가 상태를 갖지 않도록 수정하기)
- [x] ChessBoardDao를 정리한다. 중복되는 내용이 많음 (비지니스 로직은 Service Layer로 분리)
- [ ] DB의 다양한 자료구조를 활용한다. (ex. bigint, boolean, tinyint, ...)
- [x] gameId는 `long` 타입을 사용한다. -> 해당 인자 삭제
- [ ] SQL 컨벤션 적용한다. (snake case) 
- [x] 리소스는 정리한다. `try with resources` 사용한다. 이번 미션에서 사용하는 connection, statement, resultSet 등은 사용 후 반납(close)을 해야한다.

# todos
- [x] 패키지 변경, 패키지 구조 찾아보기 
- [x] VO, DTO 등의 차이 공부하기
- [x] Service Layer 공부하기
- [x] try with resources 구문 공부하기 
- [ ] DB 테이블 및 자료구조 공부하기 
- [ ] Test codes 마무리하기 (GameState)
- [x] 점수계산 로직 DB에 저장 이후로는 -4점(왠지 pawn이 0.5점으로 계산되는 듯하다.) -> Rook 대신 Bishop이 들어갔음 
- [ ] DAO, Repository pattern?!


