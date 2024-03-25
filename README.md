# java-chess

체스 미션 저장소

## STEP3 기능 명세

- [ ] King이 잡히면 게임 종료
- [x] 남아있는 말의 점수 계산
    - [x] queen: 9점, rook: 5점, bishop: 3점, knight: 2.5점, king: 0점
    - [x] pawn: 1점, 세로줄에 같은 색 pawn 있는 경우 0.5점
- [ ] 'status' 명령어 입력받기
- [x] 승패 계산
- [ ] 점수 출력
- [ ] 승패 출력

## STEP2 기능 명세

- [x] source위치 target위치 입력 받기
    - [x] 체스판에 없는 위치 검증
        - [x] 랭크 검증
        - [x] 파일 검증
- [x] 제자리 이동 불가 - Table
- [x] 가로막는 경로 검증(경로에서 Piece 존재 확인)
- [x] 마지막 경로에 상대방일 경우 잡기 - Table
-
- [x] 경로 구하기
    - [x] 경로의 Square 리스트 구하기(못가면 빈 list 반환) - Piece
        - [x] 타입에 맞는 움직임 - Piece
            - [x] 킹 : 상하좌우대각선 1칸
            - [x] 퀸 : 상하좌우대각선
            - [x] 나이트 : 8가지 static int[][]
            - [x] 폰 : 시작위치에서는 2칸 가능, 대각선 가능, 블랙은 밑으로만, 화이트는 위로만
                - [x] : 먹을 때만 대각선 - Table 예외 케이스
            - [x] 룩 : 상화좌우
            - [x] 비숍 : 대각선
- [x] 이동시 위치 변환
- [x] 턴 전환(move 후 턴 전환)
- [x] 내 말이 맞는지 검증(턴과 이동할 Piece 색 검증) - Table

## STEP1 기능 구현

### 체스말(Piece) -> VO

#### 색 (Color) (흑백) enum

### 타입

### 체스판 -> 체스 움직임을 담당

#### (Rank, File) -> Square
