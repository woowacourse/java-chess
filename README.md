# java-chess

체스 미션 저장소

## 우아한테크코스 코드리뷰

- [온라인 코드 리뷰 과정](https://github.com/woowacourse/woowacourse-docs/blob/master/maincourse/README.md)

## 1단계 체스판 초기화 요구사항 도출

## 흐름

- [x]  게임 시작 안내문을 출력한다. (체스 게임을 시작합니다.)
- [x]  게임 메뉴를 입력받는다.(start/end)
- [x] `end` 면 게임을 종료한다.
- [x] `start` 면 게임을 시작한다.
- [x] 체스판을 생성한다.
    - [x] 가로를 `Row` 라고 하며, abcdefgh 로 이뤄져있다.
    - [x]  세로를 `Column` 이라고 하며 12345678 로 이뤄져있다.
    - [x] 체스판 한칸은 고유한 위치를 가진다. (Position)
- [x] 색깔 생성
    - [x] `흑과 백`으로 팀을 생성한다.
- [x] 말 생성
    - [x] 말의 종류는 `king`, `bishop`, `pawn`, `rook`, `queen` 총 6가지가 있다.
    - [x] 말은 이름과 팀을 가지고 있다.
- [x] 말 배치
    - [x] 체스 룰에 맞춰 배치한다.
    - [x] 흑은 상단, 백은 하단에 배치한다.
- [x] 체스 판 출력
    - [x] 체스판 전체를 출력한다.
    - [x] 흑말은 대문자로 표기하고, 백말은 소문자로 표기한다.

## 2단계 말 이동

- [x] 자신의 차례인지 검증한다. (WHITE 부터 시작)
- [x] `move 현재위치 도착위치`를 입력받는다.
    - [x] [예외] 입력 형식과 다르면 예외를 던진다.
    - [x] Row 와 Column 범위 내에 있어야 한다.
    - [x] [예외] 현재 위치에 본인 말이 없으면 예외를 던진다.
- [x] 현재 위치에 있는 말에게 도착 위치로 이동 명령을 내린다.
    - [x] 도착 위치에 말이 움직을 수 있으면 움직인다.
    - [x] [예외] 움직일 수 없으면 예외를 던진다.
- [x] 말 이동 가능 여부 확인 로직을 수행한다.
    - [x] `Rook` (여러칸 이동 가능)
        - [x] 현재 위치 -> 도착위치의 이동 경로를 구한다.(ex 위, 왼, 위, 왼)
            - [x] [예외] 말의 규칙과 경로가 일치하는지 확인한다.
            - [x] [예외] 이동 경로에 다른 말이 있다면 예외를 던진다.
            - [x] [예외] 도착 위치에 같은 팀의 말이 있다면 예외를 던진다.
        - [x] 도착 위치에 있던 말 대신, `이동한 말을 위치시킨다.`
    - [x] `Bishop` (여러칸 이동 가능)
        - [x] 현재 위치 -> 도착위치의 이동 경로를 구한다.(ex 위, 왼, 위, 왼)
            - [x] [예외] 말의 규칙과 경로가 일치하는지 확인한다.
            - [x] [예외] 이동 경로에 다른 말이 있다면 예외를 던진다.
            - [x] [예외] 도착 위치에 같은 팀의 말이 있다면 예외를 던진다.
        - [x] 도착 위치에 있던 말 대신, `이동한 말을 위치시킨다.`
    - [x] `Queen` (여러칸 이동 가능)
        - [x] 현재 위치 -> 도착위치의 이동 경로를 구한다.(ex 위, 왼, 위, 왼)
            - [x] [예외] 말의 규칙과 경로가 일치하는지 확인한다.
            - [x] [예외] 이동 경로에 다른 말이 있다면 예외를 던진다.
            - [x] [예외] 도착 위치에 같은 팀의 말이 있다면 예외를 던진다.
        - [x] 도착 위치에 있던 말 대신, `이동한 말을 위치시킨다.`
    - [x] `Knight` (말을 뛰어넘을 수 있음)
        - [x] 현재 위치 -> 도착위치의 이동 경로를 구한다.(ex 위, 위, 오)
            - [x] [예외] 말의 규칙과 경로가 일치하는지 확인한다.
            - [x] [예외] 도착 위치에 같은 팀의 말이 있다면 예외를 던진다.
        - [x] 도착 위치에 있던 말 대신, `이동한 말을 위치시킨다.`
    - [x] `Pawn`
        - [x] 첫수
            - [x] 스타트 지점에서 1칸 또는 2칸 이동이 가능하다.
            - [x] [예외] 이동 경로에 다른 말이 있다면 예외를 던진다.
        - [x] 현재 위치 -> 도착위치의 이동 경로를 구한다.(ex 위, 왼, 위, 왼)
        - [x] [예외] 말의 규칙과 경로가 일치하는지 확인한다.
        - [x] 앞으로 전진
            - [x] [예외] 도착 위치에 상대방 말이 있으면 예외를 던진다.
            - [x] [예외] 도착 위치에 말이 있다면 예외를 던진다.
        - [x] 대각선 전진
            - [x] [예외] 도착 위치에 `상대편 팀의 말이 없다면` 예외를 던진다.
            - [x] [예외] 도착 위치에 같은 팀의 말이 있다면 예외를 던진다.
        - [x] 도착 위치에 있던 말 대신, `이동한 말을 위치시킨다.`
    - [x] `King`
        - [x] 현재 위치 -> 도착위치의 이동 경로를 구한다.(ex 위, 왼, 위, 왼)
            - [x] [예외] 말의 규칙과 경로가 일치하는지 확인한다.
            - [x] [예외] 도착 위치에 같은 팀의 말이 있다면 예외를 던진다.
        - [x] 도착 위치에 있던 말 대신, `이동한 말을 위치시킨다.`

## 3단계 점수 계산

- [x] status 명령어 입력 시 점수를 계산한다.
- [x] 각 기물의 점수는 팀별로 계산
    - [x] `King`: 0, `Queen`: 9, `Rook`: 5, `Bishop`: 3, `Knight`: 2.5, `Pawn`: 1으로 계산한다.
    - [x] 단, 같은 세로줄에 같은 팀의 `Pawn`이 존재하면 0.5로 계산한다.
- [x] `King`이 checkmate 이면 게임을 종료해야 한다.
    - [x] check: 다음 턴에 킹을 잡을 수 있음
    - [x] checkmate: 체크에서 벗어날 수 없는 상황

## 4단계 web + server 구현

- [x] get start 구현
- [x] post move 구현
- [x] get status 구현
- [x] get end 구현
- [x] 예외 발생 시 alter 로 보여주기

## 5단계 docker - mysqlfh

- [] db 설계하기
- [] table 구현
- [] dao 구현
- [] dao 테스트
- [] 전체 동작 확인

## 페어 프로그래밍 룰

- 타이머는 10분으로 한다.
- 쉬는 시간은 1시간 30분 마다 10분씩 가진다.
