# java-chess
체스 게임 구현을 위한 저장소

# 기능 요구사항
## step 1
- [x] 체스판을 구현한다.
- [x] 게임 시작 및 종료 명령어를 입력받는다.
    - [x] 명령어는 start, end만 입력 가능하다.
- [x] 명령어가 start인 경우 게임 실행 및 체스판을 초기화한다.
- [x] 명령어가 end인 경우에는 게임을 종료한다. 

## step 2
- [x] 말을 이동한다.
  - [x] 각 말의 전략에 따라 이동한다.
    - [x] 폰은 두 가지 이동 전략을 가진다.
  - [x] 방향을 계산한다.
  - [x] 좌표를 계산한다.
- [x] 말을 이동한 뒤 보드를 업데이트한다.

## step 3
- [x] 킹을 잡으면 게임을 끝낸다.
- [x] 킹이 잡힌 팀이 진다.
- [x] status를 입력하면 각 진영의 점수를 출력한다.
- [x] status를 입력하면 각 진영의 승패 결과를 출력한다. 
- [x] 각 말은 점수를 갖는다. 
- [ ] 리팩토링 

### 보드 객체 기능
- [x] 말 움직이기 (move)
- [x] 칸의 상태 확인하기 
- [x] 칸의 말을 가져오기
- [x] 칸이 어디에 위치해있는지 (좌표?)

### 수정 사항
- ChessController.java
  - try/catch 문 2개 추가
    - run 메소드에 추가하여 잘못된 명령어 입력 시 에러 메시지 출력 (ex: movement a2 a4, stat...)
    - move 메소드에 추가하여 잘못된 좌표 입력 시 에러 메시지 출력 (ex: move a0 a4, move a2 a10)
  - 애매한 에러 메시지 변경
    - 왕이 잡힌 후 move 명령어 수행 시 
      - "[ERROR] 게임이 초기화되지 않았습니다." -> "[ERROR] 게임이 초기화되지 않았거나 종료되었습니다."
  
- ChessGame.java
  - 유효성 검증 추가
    - move 명령어 이후에 좌표를 하나만 입력했을때 Index 오류 발생
      - splitSourceAndTargetPosition 메소드에 예외 throw 추가
  - 새로운 StringParser 객체 생성
    - String 형태의 command를 좌표로 변환해주는 로직을 객체로 따로 뺌
    - 기존 List<Position>으로 반환하던 것을 source와 target Position을 담고 있는 MovePath 객체를 생성하여 반환
  - pawn과 다른 말들의 이동을 나눴던 로직을 하나의 로직으로 통합
