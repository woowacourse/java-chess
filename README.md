# java-chess

체스 미션 저장소

## 우아한테크코스 코드리뷰

- [온라인 코드 리뷰 과정](https://github.com/woowacourse/woowacourse-docs/blob/master/maincourse/README.md)

## STEP2 기능 명세

- [ ] source위치 target위치 입력 받기
    - [ ] 체스판에 없는 위치 검증
        - [ ] 랭크 검증
        - [ ] 파일 검증
- [ ] 제자리 이동 불가 - Table
- [ ] 가로막는 경로 검증(경로에서 Piece 존재 확인) (마지막 경로에 상대방일 경우 잡기) - Table
-
- [ ] 경로 구하기
    - [ ] 경로의 Square 리스트 구하기(못가면 빈 list 반환) - Piece
        - [ ] 타입에 맞는 움직임 - Piece
            - [x] 킹 : 상하좌우대각선 1칸
            - [x] 퀸 : 상하좌우대각선
            - [ ] 나이트 : 8가지 static int[][]
            - [ ] 폰 : 시작위치에서는 2칸 가능, 대각선 가능, 블랙은 밑으로만, 화이트는 위로만
                - [ ] : 먹을 때만 대각선 - Table 예외 케이스
            - [ ] 룩 : 상화좌우
            - [ ] 비숍 : 대각선
- [ ] 이동시 위치 변환
- [ ] 턴 전환(move 후 턴 전환)
- [ ] 내 말이 맞는지 검증(턴과 이동할 Piece 색 검증) - Table

## STEP1 기능 구현

### 체스말(Piece) -> VO

#### 색 (Color) (흑백) enum

### 타입

### 체스판 -> 체스 움직임을 담당

#### (Rank, File) -> Square
