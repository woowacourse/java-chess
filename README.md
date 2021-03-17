# java-chess
체스 게임 구현을 위한 저장소

## 기능 목록

- [ ] 체스판을 초기화한다.
    - [x] 체스판의 x, y 좌표를 나눈다.
    - [x] 좌표를 캐싱한다.
    - [x] 좌표를 감싼 체스판을 만든다.
    - [x] 체스말을 만든다.
        - [x] 폰을 만든다.
            - [ ] 폰의 이동로직 '수직 한 칸'
            - [ ] 폰의 초기 이동로직 '수직 두 칸'
        - [x] 룩을 만든다.
            - [ ] 룩의 이동로직 '수직수평 무한'
        - [x] 나이트를 만든다.
            - [ ] 나이트의 이동로직 '수직수평 두 칸 후 대각선 한 칸'
        - [x] 비숍을 만든다.
            - [ ] 비숍의 이동로직 '대각선 무한'
        - [x] 퀸을 만든다.
            - [ ] 퀸의 이동로직 '수직수평 대각선 무한'
        - [x] 킹을 만든다.
            - [ ] 킹의 이동로직 '수직수평 대각선 한 칸'
        - [x] 좌표를 채우기 위한 빈말
    - [x] 체스말들을 체스판 좌표에 배치시킨다.
- [ ] 체스말을 이동시킨다.
    - [ ] source 좌표에 체스말이 존재하는지 확인한다.
        - 좌표가 체스판에 존재하는지
        - Empty 체스말을 둔 경우, 유효한 체스말인지 확인
        - [ ] 체스말이 없는 빈 좌표인 경우... (이동불가)
        - [ ] 적군 체스말이 존재하는 경우...  (이동불가)
        - [ ] 체스판을 벗어나는 좌표인 경우...(이동불가)
        - [ ] 아군 체스말이 존재하는 경우... (이동가능 + target 좌표 검사 진행)
            - [ ] target 좌표에 체스말이 존재하는지 확인한다.
                - [ ] 아군 체스말이 존재하는 경우... (이동불가)
                - [ ] 체스판을 벗어나는 좌표인 경우...(이동불가)
                - [ ] 체스말이 없는 빈 좌표인 경우... (이동가능)
                - [ ] 적군 체스말이 존재하는 경우...  (이동가능 + 적군 체스말을 제거하는 추가동작)
                    - 1. 내가 잡아먹은 체스 말이 '킹'인지 검사하는 방법
                    - 2. 현재 체스판에 '킹'이 2개 존재하는지 검사하는 방법
- [ ] 승패 및 점수를 계산한다
    - [ ] 'end' 가 입력되는 경우 게임을 종료한다.
    - [ ] 킹이 잡히는 경우 게임을 종료한다.
    - [ ] 체스판에 남아있는 체스말의 점수를 총합한다
        - [ ] 폰은 1점으로 계산한다.
        - [ ] 같은 색의 폰이 세로줄에 있는 경우 해당 폰들의 점수를 0.5로 계산한다.
        - [ ] 룩은 5점으로 계산한다.
        - [ ] 나이트는 2.5점으로 계산한다.
        - [ ] 비숍은 3점으로 계산한다.
        - [ ] 퀸은 9점으로 계산한다.
        - [ ] 킹은 0점으로 계산한다.
    