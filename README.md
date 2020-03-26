# java-chess

- 체스판을 초기화한다.
    - 말의 위치 값은 가로 위치는 왼쪽부터 a ~ h이고, 세로는 아래부터 위로 1 ~ 8로 구현한다.
    - 각 진영은 검은색(대문자)과 흰색(소문자) 편으로 구분한다.

```shell script
RNBQKBNR  8 (rank 8)
PPPPPPPP  7
........  6
........  5
........  4
........  3
pppppppp  2
rnbqkbnr  1 (rank 1)

abcdefgh
```
## 기능 요구사항

- [x] 빈 판을 생성한다.
    - [x] 체스말들을 생성한다.
    - [x] 체스말들을 초기 위치에 놓는다.
    - [x] 콘솔 출력

- [x] 체스 말이 이동할 수 있도록 구현한다.
    - [x] `move source위치 target위치`을 실행해 이동한다.
    - King
        - 전방향 1칸 이동
        - start와 target의 (x좌표 끼리의 차이)^2와 (y좌표 끼리의 차이)^2의 합이 2 이하
    - Queen
        - 전방향 무제한 이동
        - Rook or Bishop
    - Rook
        - 상하좌우 무제한 이동
        - start와 target의 x좌표 끼리의 차이 또는 y좌표 끼리의 차이 중하나가 0
    - Bishop
        - 대각선 무제한 이동
        - start와 target의 x좌표 끼리의 차이와 y좌표 끼리의 차이 절대값이 같음
    - Knight
        - 상하좌우 1칸 + 대각선 1칸 이동
        - 다른 말 건너뛰기 가능
        - start와 target의 (x좌표 끼리의 차이)^2와 (y좌표 끼리의 차이)^2의 합이 5
    - Pawn
        - 전방 1칸 이동
        - target의 y좌표 - start의 y좌표가 WHITE면 1, BLACK이면 -1
        - 초기 위치에 있을 시에만 2칸 이동 가능
        
