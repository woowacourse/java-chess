# java-chess
체스 게임 구현을 위한 저장소

## 기능요구사항 - 1단계

- 콘솔 UI에서 체스 게임을 할 수 있는 기능을 구현한다.

- 1단계는 체스 게임을 할 수 있는 체스판을 초기화한다.

- 체스판에서 말의 위치 값은 가로 위치는 왼쪽부터 a ~ h이고, 세로는 아래부터 위로 1 ~ 8로 구현한다.

```
RNBQKBNR| 8 (rank 8) - Black(말)
PPPPPPPP| 7                       
........| 6
........| 5
........| 4
........| 3
pppppppp| 2
rnbqkbnr| 1 (rank 1) - White(말)
--------
abcdefgh (file - letter)

체스보드 색 : H8 - 검정 / A1 - 검정
선 : 화이트

piece - 피스
R - 룩
N - 나이트
B - 비숍
Q - 퀸
K - 킹
P - 폰
FreeSquare - 빈사각형 "."
```

- 체스판에서 각 진영은 검은색(대문자)과 흰색(소문자) 편으로 구분한다.

```
프로그램 실행 결과
체스 게임을 시작합니다.
게임 시작은 start, 종료는 end 명령을 입력하세요.
start
RNBQKBNR
PPPPPPPP
........
........
........
........
pppppppp
rnbqkbnr

end
```

## 기능목록 - 1단계

- 입력

  - [X] start - 게임시작 / end - 게임 종료
  
    - [X] 아니면 예외 발생
  
- 말

  - [X] 말 12개 캐싱
  
     - [X] 캐싱한 말만 사용
  
  - [X] 색깔 : Black / White

  - [X] 종류 : Rook / kNight / Bishop / Queen / King / Pawn
  
- 판

  - [X] 가로 8 세로 8
  
  - [X] 말 넣음 

- 출력

  - [X] 판 출력함
  
  - [X] 빈 것 '.' 처리
  
## 기능요구사항 - 2단계

- 콘솔 UI에서 체스 게임을 할 수 있는 기능을 구현한다.

- 체스 말의 이동 규칙을 찾아보고 체스 말이 이동할 수 있도록 구현한다.

- move source위치 target위치을 실행해 이동한다.

- 체스판의 위치 값은 가로 위치는 왼쪽부터 a ~ h이고, 세로는 아래부터 위로 1 ~ 8로 구현한다.

```
프로그램 실행 결과
> 체스 게임을 시작합니다.
> 게임 시작 : start
> 게임 종료 : end
> 게임 이동 : move source위치 target위치 - 예. move b2 b3
start
RNBQKBNR
PPPPPPPP
........
........
........
........
pppppppp
rnbqkbnr

move b2 b3
RNBQKBNR
PPPPPPPP
........
........
........
.p......
p.pppppp
rnbqkbnr
```

##### 기본 이동 정리

- 폰

  - 앞으로 한 칸 이동
  
  - 첫 타임에는 두 칸 이동 가능 
  
    - 한 칸 이동했을 때를 기점으로 잡힐 수 있음 : 앙파상
  
  - 상대방 말을 잡을 때는 대각선으로만 이동
  
  - 적 진영 끝에 위치시 : 프로모션 - 룩/나이트/비숍/퀸으로 변경 가능

- 룩(Major)

  - 가로 세로 직선 이동

- 나이트(Minor)

  - L자 이동(수평/수직 2칸 - 반대로 한칸)
  
  - 피스를 뛰어넘을 수 있는 유일한 피스

- 비숍(Minor)

  - 대각선 직선 이동

- 퀸(Major)

  - 가로 세로 대각선 직선 이동

- 킹
  
  - 가로 세로 대각선 한 칸 이동
  
  - 단 상대방 킹의 이동 범위 내로 이동할 수 없음
  
  - 킹은 상대편의 킹과 퀸을 잡을 수 없음
  
  - 킹과 룩이 모두 이동하지 않은 상태에서,
   킹과 룩 사이에 피스가 없는 경우, 
   그리고 움직였을 때 킹이 잡히지 않을 경우 캐슬링 가능
  
    - 퀸사이드 캐슬링 : 왼쪽으로 두 칸 이동, 왼쪽 룩이 킹의 오른쪽에 위치
    
    - 킹사이드 캐슬링 : 오른쪽으로 두 칸 이동, 오른쪽 룩이 킹의 왼쪽에 위치

- 기타

  - 체크 : 내가 피스를 옮겼고, 옮긴 피스가 다음 턴에 (상대방의 킹 위치가 지금과 같을 때), 킹을 잡을 수 있음
  
  - 체크메이트 : 상대방이 체크를 당한 상태에서 내가 피스를 옮겼고, 상대방의 킹을 이동할 수 없는 경우
  
  - 스테일메이트 : 상대방이 체크가 아닌 상태에서 내가 피스를 옮겼고, 상대방의 킹을 이동할 수 없는 경우
  
## 기능목록 - 2단계

- [x] 입력

  - [x] 옮기기 전 칸 위치 -> 옮긴 후의 칸 위치
  
- [x] 출력

  - [x] 옮긴 후 내용 출력할 것

- [X] 칸(Square)

  - [X] 64개 캐싱해 a1 > h8 (MAP)
  
- [x] 보드

  - [x] 32개의 칸(Square)과 말을 매칭해서 가지고 있다.

  - [x] 입력받은 옮기기 전 칸에 위치한 말에게 움직일 수 있는 곳을 물어본다.

    - [x] 물어볼 때, 옮기기 전 칸 위치와 전체 칸-말 매칭을 넘겨준다.
    
  - [x] 말이 응답한 움직일 수 있는 곳과 옮긴 후의 칸 위치(희망)가 동일하다면 옮겨준다.
  
- [x] 말

  - [x] 자기 칸과 64개의 칸과 말을 매칭한 것을 넘겨줬을 때, 자기가 이동할 수 있는 칸 리스트를 넘겨준다.
  
    - [x] 자기가 갈 수 있는 범위 정의
 
    - [x] 자기가 갈 수 없는 범위를 제거
      
## 기능 요구사항 - 3단계 

- 체스 게임은 상대편 King이 잡히는 경우 게임에서 진다.

  - King이 잡혔을 때 게임을 종료해야 한다.

- 체스 게임은 현재 남아 있는 말에 대한 점수를 구할 수 있어야 한다.

  - "status" 명령을 입력하면 각 진영의 점수를 출력하고 어느 진영이 이겼는지 결과를 볼 수 있어야 한다.

##### 점수 계산 규칙

```
.KR.....  8
P.PB....  7
.P..Q...  6
........  5
.....nq.  4
.....p.p  3
.....pp.  2
....rk..  1

abcdefgh
```

- 위와 같은 체스 판의 점수 결과는 검은색(대문자)은 20점. 흰색(소문자)은 19.5점이 된다.

- 검은색은 5(rook) + 3(bishop) + 9(queen) + 3(pawn) + 0(king) = 20점

- 흰색은 5(rook) + 2.5(knight) + 9(queen) + 3(pawn, 4개의 pawn이 있지만 세로로 있어 각 0.5이 된다.) + 0(king) = 19.5점

## 기능 목록

- [x] 입/출력

  - [x] "status" 명령을 입력
  
    - [x] 각 진영의 점수, 어느 진영이 이겼는지 결과를 출력
 
- [x] 타입별 점수

  - [x] queen은 9점, rook은 5점, bishop은 3점, knight는 2.5점, pawn은 1점, king은 0점
  
- [x] 점수 계산

  - [x] 같은 팀끼리 계산
  
  - [x] 같은 팀의 폰끼리 같은 세로줄에 있다면 개당 0.5점
  
- [x] 게임

  - [X] King이 하나라도 죽으면 게임 종료
  
  - [x] 종료되면 결과 출력해주기
  
  