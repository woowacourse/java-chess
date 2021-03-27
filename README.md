## 체스

## 기능 요구사항
- [x] 체스 보드 초기화하기
    - [x] 체스말 생성
    - [x] 보드 생성
    - [x] 보드에 체스말 올리기
    
- [x] 체스 보드 출력하기

## 프로그램 요구사항
- 기존 요구사항 만족

##TO-DO
1) 첫 실행시 start를 적지 않으면 아직 시작을 안했다고 하면서 명령어 다시 받게 하기
  - Game이 State를 가지고 있으면 되지 않을까? (Ready / Running / End)
  - 하지만 그럴려면 Game이 시작하자마자 있어야함...
2) game에서 이뤄지는 커맨드별 수행을 Command에서 다루게 하고 싶다
  - enum Command 에서 다루게 하자!
  - Command 패턴을 이용해 Command 객체를 만들어 수행하자!
3) MoveStrategy를 사용하게 만들고 싶다.. Path 로직을 수정해야 할 것 같음...
  - 아직 두루뭉실... piece.canMove로 moveStrategy에서 판단할 수 있음 좋겠는데.....
