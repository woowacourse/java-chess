# java-chess

체스 미션 저장소

## 체스 미션 목표

- 공통 목표
    - 객체간의 결합도가 낮은 코드 짜기
    - TDD 스럽게 구현하기
    - 에러를 받아도 사용자가 기분이 상하지 않게 구현하기

- 야호 목표
    - 객체의 클래스를 Final 이나 Abstract 으로!
    - 접근제어자를 적절하게 사용하기

- 아스피 목표
    - 리뷰어한테 칭찬 받아보기
    - 행동에 초점을 맞춰서 객체 구현

## 체스 프로그램 로직

1. 사용자가 start와 end 중에 입력한다.
2. start 일 경우 체스 판을 초기화 해서 생성하고 보여준다
    - 체스판 가로(rank)가 1~8
    - 체스판 세로(file)가 a~h
    - 체스판 초기화시 아래쪽에는 흰색 진영, 위쪽에는 검은색 진영이 생성된다.
    - `폰`은 8개, `룩, 비숍, 나이트`는 2개, `킹, 퀸`은 1개씩
    - 말이 없는 곳은 `.` 으로 초기화 된다.
3. end 일 경우 프로그램이 종료된다.

## 구현 기능 목록

### 1단계 기능 목록

- 체스판 초기화
    - 가로 위치 : 왼쪽부터 a~h
    - 세로 위치 : 아래부터 1~8
    - 흰색 : ♚♛♝♞♟♜
    - 검은색 : ♔♕♗♘♙♖

- 게임 시작/종료 입력
    - 시작 : start 로만 입력
        - 초기화 된 체스판 보여주기
    - 종료 : end 로만 입력
    - 지정된 명령어 외의 입력에 대한 예외처리

### 2단계 기능 목록

- 1단계에서 변경된 부분
    - 시작/종료 외에도 이동을 입력받는다.
        - 움직이고자 하는 말의 위치 (source)
            - 현재 순서의 팀의 말이 해당 위치에 있어야 한다.
        - 목표하는 위치 (target)
            - 같은 팀의 말이 있으면 안된다.
            - 현재 위치의 말이 움직일 수 있는 위치여야 한다.
        - 체스판 범위 내의 위치여야 한다.
        - 문자열 길이가 2여야 한다.
        - input 에서 대문자가 입력되면 lower case 로 변경해준다.

- 말들을 이동하는 기능
    - king : 전후좌우 대각선 1칸씩
    - queen : 전후좌우 대각선으로 칸 수 제한 없이
    - knight : 전후좌우 두칸 전진한 상태에서 양옆으로 한칸씩 이동 가능
    - bishop : 대각선으로 칸 수 제한 없이
    - rook : 전후좌우로 칸 수 제한 없이
    - pawn : 초기 상태에서는 한칸 혹은 두칸 전진 가능, 그 이후로는 한칸씩만 전진 가능, 잡는건 대각선으로만

### 3단계 기능 목록

- 2단계에서 변경된 부분
  - 흰 말, 검은 말 순으로 번갈아가면서 진행된다.
  - 에러 메세지를 사용자 친화적으로 작성한다. ^^
- 종료 조건
  - end : 즉시 프로그램 종료
  - king이 잡히면 status 나 end 만 입력 가능
    - game 은 종료
- status
  - 남아있는 말에 대한 점수를 계산
  - king = 0점
  - queen = 9점
  - rook = 5점
  - bishop = 3점
  - knight = 2.5점
  - pawn = 기본 1점 / 같은 세로줄에 같은 색의 폰이 있는 경우 0.5점

## 우아한테크코스 코드리뷰

- [온라인 코드 리뷰 과정](https://github.com/woowacourse/woowacourse-docs/blob/master/maincourse/README.md)