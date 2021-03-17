# java-chess
체스 게임 구현을 위한 저장소

- [x] 체스판 구현
    - [x] 기본 세팅
        - ![체스 기본 세팅](https://mblogthumb-phinf.pstatic.net/20160119_249/xzizazhz2_14531957418980athc_JPEG/7_00001.jpg?type=w2 )
    - [x] 현재 위치의 상태 관리
- 체스 말 구현
    - 각 말의 이동 전략 관리
    - PAWN 말의 경우 첫 이동시에만 앞으로 두 칸 이동 가능
    - king, queen, bishop, knight, rook, pawn 
- 체스 말 이동 기능 구현
    - 체스 말의 이동 전략에 만족하지 않는 이동인 경우 예외 처리
    - 체스 판의 범위를 넘어간 경우 예외 처리
    - 다른 팀의 말을 이동하려고 한 경우 예외 처리
    - source 위치가 빈 공간일 경우 예외 처리
    - destination 에 같은 편 말이 있는 경우 예외 처리
- KING 이 잡혔을 때 게임 종료
- 명령어 입력 기능
    - start
    - end
    - move `source` `destination`
    - status
    - 이 외의 입력은 예외 처리
- 현재 상태의 점수 계산 기능
    - 자신 턴의 점수를 출력
    - 각 말에 대한 점수를 관리
    - 각 말의 점수 계산 규칙은 [링크](https://techcourse.woowahan.com/s/zmAj9jfu/ls/LM7qbRaj )를 확인 한다.
- 체스 게임 출력 기능
    - 검은색은 대문자, 흰색은 소문자로 표현한다.
    