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
