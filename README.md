# java-chess

체스 미션 저장소

## 우아한테크코스 코드리뷰

- [온라인 코드 리뷰 과정] (https://github.com/woowacourse/woowacourse-docs/blob/master/maincourse/README.md)

---

## 💡프로젝트 개요
- 체스 게임을 진행할 수 있는 프로젝트입니다.
---


## 📋 구현 기능 목록

## controller
- [x] 게임 시작 문구를 출력한다.
- [x] 명령어 안내 문구를 출력한다.
- [x] 사용자 명령어를 입력한다.
  - [x] 명령어는 start, status, end로 구성된다.
- [x] 체스판 현황을 출력한다.
- [x] 사용자의 입력을 받아 체스 게임을 진행시킨다.
- [x] 사용자의 입력을 받아 각 진영의 점수를 출력한다.
  - [x] 어느 진영이 이겼는지 결과를 볼 수 있어야 한다.
- [x] end가 입력되면 프로그램을 종료한다.

## model
- [x] 체스말을 생성할 수 있다.
  - [x] 폰을 생성할 수 있다.
  - [x] 룩을 생성할 수 있다.
  - [x] 비숍을 생성할 수 있다.
  - [x] 나이트 생성할 수 있다.
  - [x] 퀸 생성할 수 있다.
  - [x] 킹 생성할 수 있다.
- [x] 체스말은 색깔에 따라 다른 이름을 가진다.
- [x] 체스판을 초기화할 수 있다.
  - [x] 체스판의 가로 위치는 왼쪽부터 a~h이다.
  - [x] 체스판의 세로 위치는 아래부터 위로 1~8이다.
  - [x] 살아있는 말의 현황을 관리한다.
- [x] 말의 기본적인 이동은 8방향으로 이루어진다.
- [x] 출발 좌표와 도착 좌표가 주어지면 경로를 계산할 수 있다.
  - [x] 출발 좌표 기준으로 8방향 직선 상에 도착 좌표가 존재하면 경로를 계산한다.
  - [x] 출발 좌표 기준으로 8방향 직선 상에 도착 좌표가 존재하지 않으면 도착 좌표만 경로에 넣어준다.
- [x] 폰의 이동 규칙
  - [x] 검은 말은 행 좌표를 감소시켜 전진한다.
  - [x] 흰 말은 행 좌표를 증가시켜 전진한다.
  - [x] 초기 행에 위치해 있다면 1칸 혹은 2칸 전진할 수 있다.
  - [x] 초기 행에 위치하지 않는다면 1칸만 전진할 수 있다.
  - [x] 전진하는 방향의 대각선에 상대편 말이 존재하면 잡고 이동할 수 있다.
  - [x] 전진하는 경로에 다른 말이 존재하면 이동하지 못한다.
- [x] 룩의 이동 규칙
  - [x] 상하좌우에 위치한 모든 칸으로 이동할 수 있다.
- [x] 비숍의 이동 규칙
  - [x] 대각선에 위치한 모든 칸으로 이동할 수 있다.
- [x] 나이트의 이동 규칙
  - [x] 상하 중 한 방향으로 2칸 이동 후 좌우로 1칸 이동할 수 있다.
  - [x] 좌우 중 한 방향으로 2칸 이동 후 상하로 1칸 이동할 수 있다.
- [x] 킹의 이동 규칙
  - [x] 모든 방향으로 1칸 이동할 수 있다.
- [x] 퀸의 이동 규칙
  - [x] 모든 방향에 위치한 모든 칸으로 이동할 수 있다.
- [x] 출발 좌표부터 목표 좌표까지의 이동 경로를 반환한다.
  - [x] 직선 경로
  - [x] 대각선 경로
  - [x] 직선, 대각선 경로 밖에 목표 좌표가 위치하면 목표 좌표만을 경로로 반환한다.
- [x] 체스판에서 입력받은 위치로 말이 이동할 수 있는지 판단한다.
  - [x] 말이 이동할 수 있다면 이동시킨다.
    - [x] 목적지에 상대편 말이 있으면 잡고 이동할 수 있다.
    - [x] 이동 경로에 다른 말이 없을 때만 이동할 수 있다.
  - [x] 이동할 수 없다면 예외가 발생한다.
    - [x] 목적지에 자신의 말이 위치해 있을 경우 이동하지 못한다.
    - [x] 이동하는 경로에 다른 말이 있을 경우 이동하지 못한다.
- [x] 체스 게임은 한 쪽의 king이 잡히면 종료된다.
- [x] 체스 게임의 각 말은 기본 점수를 가진다.
  - [x] 킹은 잡히면 게임이 끝나므로 0점이다.
  - [x] 퀸은 9점이다.
  - [x] 룩은 5점이다.
  - [x] 비숍은 3점이다.
  - [x] 나이트는 2.5점이다.
  - [x] 폰은 기본적으로 1점이다.
- [x] 체스판 현황에 대한 각 진영의 점수를 계산할 수 있다.
  - [x] 같은 열에 같은 색 폰이 있는 경우 각 0.5점으로 계산한다.


## view
### 입력
- [x] 게임 시작 명령어를 입력 받는다.
- [x] 게임 종료 명령어를 입력 받는다.
- [x] 말 이동 명령어를 입력 받는다.

### 출력
- [x] 게임 시작 문구를 출력한다.
- [x] 명령어 안내 문구를 출력한다.
- [x] 체스판 현황을 출력한다.
  - [x] 상대 말을 대문자로 출력한다.
  - [x] 자신의 말은 소문자로 출력한다.
  - [x] 말이 존재하는 위치는 이니셜로 출력한다.
  - [x] 말이 없는 위치는 `.`으로 출력한다.

## Todo
- view에서 domain 의존하는 부분 수정하기
- command 입력 관련 controller 처리부분 리팩터링 (후순위)
- validateNoPieceAt 메서드에 position 좌표값을 예외처리 메세지에 넣어주기 (후순위)
- 체스 게임 진행 시 흑,백이 차례대로 둘 수 있게 하기 (후순위)
  
