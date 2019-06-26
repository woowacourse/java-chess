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
>
> 현재 설계는 게임이 단 하나만 존재한다고 가정하였습니다.

1. dao
    - BoardDao
        게임의 말 위치를 테이블 board와 연결하는 클래스 
    - TurnDao
        현재 게임의 turn을 저장하는 테이블 turn과 연결하는 클래스
    - ResultDao
        현재 잡은 말의 결과를 저장하는 테이블 result와 연결하는 클래스

> 각 테이블 별로 하나의 DAO를 만들었습니다.

2. dto
현재 도메인 객체에서 값을 빼올때 get 메서드를 많이 쓰는 번거로움이 있어
각 테이블에 저장하는 데이터마다 dto를 만들어 이를 보완하기로 했습니다.

## 시연 캡쳐 화면

1. 초기화면 (`/`)
![초기화면](https://user-images.githubusercontent.com/30178507/60082050-05bd4e80-976e-11e9-87cf-dac1b3e0090c.PNG)

초기화면에는 환영 문구와 게임 시작 페이지로 이동하는 링크가 있습니다.

2. 게임시작 (`/game_play`)
![게임시작](https://user-images.githubusercontent.com/30178507/60082065-0950d580-976e-11e9-9253-c33948083d80.PNG)

초기화면에서 게임시작 링크를 눌렀을때 나타나는 화면입니다. 이때, 서비스 객체에서 게임을 관장하는 객체인
`ChessBoard`를 만들어 초기화합니다.

3. 말이동
![말이동 현황](https://user-images.githubusercontent.com/30178507/60082072-0bb32f80-976e-11e9-85c7-ba88d0aa6ddd.PNG)

위 화면은 WHITE 팀의 Pawn 하나와 BLACK 팀의 Knight 하나가 움직인 결과입니다.
아래 `move` 버튼 옆에 있는 input 박스를 통해 이동을 구현하였습니다. 왼쪽 박스는 이동시킬 말의 위치, 오른쪽 박스는 도착지점입니다.
위 사진에서 BLACK팀의 Knight가 이동한 것을 예를 들면 왼쪽 박스에 `b8`, 오른쪽 박스에 `c6`을 입력하여 move하면 위 화면과 같이 Knight가 움직입니다.

4. 결과
![마지막 말 잡을때](https://user-images.githubusercontent.com/30178507/60084717-00163780-9773-11e9-8c60-023dc0c10c21.PNG)
위 화면은 마지막으로 WHITE 팀의 Knight가 BLACK 팀의 King을 잡을 때 화면입니다.
King을 잡으면 게임이 끝나므로 아래와 같은 결과 화면이 나타나게 됩니다.

![결과화면](https://user-images.githubusercontent.com/30178507/60084719-00163780-9773-11e9-9290-7daf5c6600ac.PNG)
현재 BLACK 팀은 Pawn 1개로 1점, WHITE 팀은 Rook 1개, Pawn 2개로 7점을 받은 것을 확인할 수 있습니다.
