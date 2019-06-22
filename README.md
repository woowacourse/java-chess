# java-chess

## 1단계

### 기능 구현 목록

1. 체스판

2. 위치
    
### 2단계

1. 말
    - Pawn
    - King
    - Queen
    - Knight
    - Bishop
    - Rook
    
2. 말 이동시마다 체스판에 보여주는 기능

    - move command 추가

### 3단계

1. 게임 종료 기능
    - King이 잡혔을 때
    - status 커멘드 입력 시

2. 상대방 말을 잡는 경우

3. 턴제 구현

4. 결과 도출 기능
    - 승자 계산
    - 점수 계산

### 06.20 목요일
[x] Knight 이동 로직 오류 처리

[x] 점수 계산 로직 구현

[x] 게임 종료 (King이 잡힌 경우, status 커멘드)

[x] 폰 팀에 따라 이동 방향 조정, 폰이 상대방 말을 잡을 때는 대각선 방향

### 4단계

> 게임 한판을 진행하는 간단한 flow로 구현

1. index.html

    게임 시작 버튼으로 게임 play 페이지로 이동할 수 있도록 구현
    
2. game_play.html (/game_play)

    게임을 실제로 진행하는 페이지
    - move 버튼
    - end 버튼
    - status 버튼

3. result.html (/result)
    
    최종 게임의 결과를 보여주는 페이지
    
    - game_play 페이지에서 move로 king을 잡은 경우
    - end / status 버튼을 누른 경우

### 5단계

> DB 적용하여 웹서버를 다시 시작하더라도 이전 게임을 다시 시작할 수 있게 구현

### 브레인스토밍 메모장

1. ChessBoard 는 처음에 게임을 시작하는 경우에만 있으면 됨
 -> 이 말은 즉 처음에 게임을 시작할 때 ChessBoard 를 생성함과 동시에 DB에 올려줘야 한다는 말임
 -> 그리고 DAO 들을 static 을 빼주어야 함.
 -> 문제는 DB에 저장된 것들을 어떻게 객체로 다시 가져오느냐인데, 이건 WEB 로직을 살짝 바꾸면 가능할 것 같음.
 -> Util 을 만들어야 하나?

2. 게임을 끝내기 전(/end 에서 수행)까지는 전부 데이터 베이스에 저장되기 때문에 ChessBoard 를 static 으로 뺄 필요가 없음

3. 처음 게임을 시작하는 get /game_play 에서 1. 에서 생성한 ChessBoard 를 데이터베이스에 올려줘야 함.

4. 3.에서 데이터베이스에 올려줬기 때문에 나머지들, post /game_play, /status 에서는 그냥 DAO, DTO 를 통해 DB에서 가져오면 될 것 같

5. 말이 이동하면 테이블을 비우고 다시 BoardDAO 를 업로드, 즉 update 는 필요 없음, delete 와 add 를 반복하여 update 흉내를 내자.
- 매우 비효율적이다 라는 피드백이 예상된다.

6. 나머지 데이터들도 마찬가지 delete add 반복