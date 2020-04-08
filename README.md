# java-chess
체스 게임 구현을 위한 저장소

## 구현할 기능 목록
- 게임 시작 및 종료를 위한 명령어 안내문 출력하기 (콘솔)
- 게임 시작, 종료, 게임 진행상황 확인을 위한 명령어 입력받기 (콘솔)
- 체스판과 기물을 초기화하기
- 체스판 출력하기 (콘솔 UI)
- 체스판 출력하기 (웹 UI)
- 기물 이동 요청이 적절한지 검사하기
- 기물을 이동시키기
- 점수 및 승리팀 계산하기
    - 게임이 끝났는지 여부와 관계없이 가능해야 한다.
- status 명령으로 각 팀의 점수 및 승패를 확인하기 (콘솔)

## 용어 정리
- Rank : 체스판의 가로축. 왼쪽부터 차례로 a, b, c, d, e, f, g, h 로 표현됨.
- File : 체스판의 세로축. 아래부터 차례로 1, 2, 3, 4, 5, 6, 7, 8 로 표현됨.
- piece (=기물) : 체스 말 각각
- board : 체스 판
- Score : 각 팀의 점수 및 승자가 누구인지, 무승부인지를 포함함
- Piece Type (=기물 종류) : 폰, 룩, 나이트, 비숍, 퀸, 킹이 있다.
- start (=from) : 기물을 A 위치에서 B 위치로 이동시킬 때, A 위치
- end (=to, target) : 기물을 A 위치에서 B 위치로 이동시킬 때, B 위치
- acronym : 번역하면 "두문자어". 예를들면 WHITE 팀 Pawn 의 acronym 은 p 이고, BLACK 팀 BISHOP 은 B 이다.

## 데이터베이스 정보
- DB 이름 : chess
- DB 서버주소 : localhost
- DB 권한 user 이름 : chess
- DB 권한 user 패스워드 : chess
- 테이블
    - 테이블 명 : commandlog
    - 테이블 생성문 : create table commandlog (execute_order int primary key, command varchar(60) not null);
    - 테이블 구조  

|컬럼명|타입|설명|  
|-----|---|--------------------------|  
|execute_order|int|명령이 실행된 순서(앞번호부터 차례대로 수행된 것임)|  
|command|varchar(60)|move 명령|  
     
