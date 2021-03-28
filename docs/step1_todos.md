# 1차 피드백 리팩토링 목록

- [x] 캐싱된 ERROR의 처리 (범위를 벗어나는 경우의 체크로직)
- [x] 예외 메세지 적용
- [x] 추상클래스인데 폰만을 위한 메서드 정리
- [x] 각 piece가 Position을 가져야 할까? 에 대한 의견 작성
- [x] instanceOf 내부화
- [x] column, row Enum으로 변경
- [x] 사용하지 않는 메소드에 한해 UnsupportedOperationException 처리
- [x] com.google.common.collect.Table를 사용하여 테이블 형태의 자료구조 사용가능
- [ ] 팀별로 턴을 바꾸는 것 구현
- [ ] 누락된 테스트코드들 작성
---
## 각 Piece가 Position을 가져야 하는 이유
1. Piece는 하나의 객체로서 자신의 위치를 알아야한다. 
2. Piece가 위치를 가지고 있다면 팀별로 점수를 계산시 chessBoard를 모두 탐색할 필요 없이 자신이 가진 말만 탐색하면 된다.
3. isMovable을 수행시, 필요한 파라미터가 줄어든다.

### 특징
1. chessBoard와 Piece의 데이터 싱크가 깨질 위험이 있다. 
  -> 그렇다고 Piece에서 move자체를 해버리면 (각자 움직이게 하면) 다른 객체의 이동까지 통제할 수 없다. 따라서 전체적인 move (보드판 자체에서 이동시키고 죽이는 작업) 는 chessBoard가 한다.
2. chessBoard는 단순히 각 객체들의 연결통로의 역할을 가진다. (일종의 컬렉션 역할만 한다.)

## 각 Piece가 Position을 가지면 안 되는 이유
1. chessBoard가 Position과 Piece를 매칭할 수 있는데 불필요한 정보이다.

### 특징
1. chessBoard가 모든 것을 통제하는 객체가 될 것 같다.
2. Piece는 단순히 사물 이상의 역할을 하지 못할 것 같다.
