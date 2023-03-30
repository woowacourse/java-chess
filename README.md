# java-chessTest

체스 미션 저장소

## 우아한테크코스 코드리뷰

- [온라인 코드 리뷰 과정](https://github.com/woowacourse/woowacourse-docs/blob/master/maincourse/README.md)

---

- [x] 체스게임
  - [x] 보드를 가지고 있다.
  - [x] 상태를 변경할 수 있다.
  - [x] 팀별 점수를 계산한다.

- [x] 버튼
  - [x] 상태를 가지고 있다.

- [x] 상태
  - [x] 시작 상태
  - [x] 종료 상태
  - [x] 블랙 상태
    - [x] 진행 시 king이 잡힐 경우 종료 상태로 전이한다.
  - [x] 화이트 상태
    - [x] 진행 시 king이 잡힐 경우 종료 상태로 전이한다.

- [x] 체스판 생성 팩토리
    - [x] 모든 좌표 값을 가진다. (초기화)
    - [x] 기물을 초기 위치에 생성한다

- [x] 보드
    - [x] 모든 기물들의 위치를 저장한다.
    - [x] 소스에 기물이 있는지 확인한다.
        - [x] 우리팀일 경우 진행한다.
    - [x] 타겟에 기물이 있는지 확인한다.
        - [x] 우리팀일 경우 진행하지 않고, 상대팀일 경우 진행한다.
    - [x] 소스 좌표의 기물이 타겟 좌표로 이동 가능한지 물어본다.
      - [x] 폰인 경우 타겟 에 기물이 있을 경우에만 대각선을 허용한다.
    - [x] 기물들의 진행 경로의 기물 유무를 검증한다.
        - [x] 나이트는 제외한다.
        - [x] 경로에 기물이 있을 경우 진행하지 않는다.
    - [x] 타겟 좌표로 소스 좌표의 기물을 이동한다.
    - [x] king이 존재하는지 여부를 확인한다.
    - [x] 보드내에 있는 유닛들의 점수를 계산한다.
      - [x] pawn의 경우 세로줄에 같은색이 있는 경우 점수는 1/2 이다.

- [x] 팀
    - [x] 팀은 검은팀과 흰팀만 존재한다.
    - [x] 검은색 팀은 이름이 대문자이다
    - [x] 흰색 팀은 이름이 소문자이다

- [x] 좌표
    - [x] 파일과 랭크로 이루어짐.
    - [x] 두 좌표가 수직관계인지 확인한다.
    - [x] 두 좌표가 수평관계인지 확인한다.
    - [x] 두 좌표가 대각선인지 확인한다.
    - [x] 두 좌표가 왕의 움직임인지 확인한다.
    - [x] 두 좌표가 나이트의 움직임인지 확인한다.
    - [x] 두 좌표가 폰의 움직임인지 확인한다.

- [x] 세로줄 (파일)
    - [x] 파일간의 거리를 계산한다.

- [x] 가로줄 (랭크)
    - [x] 랭크간의 거리를 계산한다.

- [x] 기물들
    - [x] 이동정책을 가진다.
    - [x] 팀을 가져야 한다.
        - [x] 검은 팀 이면 출력 시 대문자 이름을 가진다.
        - [x] 흰색 팀 이면 출력 시 소문자 이름을 가진다.
    - [x] 공격정책이 있을 수도 있다.
    - [x] 기물들이 점수를 갖는다.
  
    - [x] 킹
        - [x] 상하좌우대각선 1칸씩 이동가능.

    - [x] 퀸
        - [x] 상하좌우대각선 이동거리 제한 없음.

    - [x] 룩
        - [x] 상하좌우 이동거리 제한 없음.

    - [x] 비숍
        - [x] 대각선으로 제한없음.

    - [x] 나이트
        - [x] 상하좌우 1칸에 좌우 대각선 1칸이동.
        - [x] 건너뛰기 가능

    - [x] 폰
        - [x] 시작할때 2/1칸 선택가능,
        - [x] 블랙팀은 아래로만, 흰팀은 위로만 이동할 수 있다.
        - [x] 공격시에는 대각선으로 한칸 이동 가능.

- [x] 커맨드
  - [x] 사용자 입력 값을 가진다.
    - [x] 사용자 입력 값을 검증한다.
      - [x] start
      - [x] end
      - [x] move
        - [x] 길이와 좌표를 검증한다.
      - [x] status
  - [x] move일 경우 좌표를 생성한다.
--

- [x] 인풋뷰
    - [x] 게임 시작 여부를 입력 받는다
    - [x] 게임 종료 여부를 입력 받는다
    - [x] 기물의 소스와 타겟좌표를 입력받는다

- [x] 아웃풋 뷰
    - [x] 체스판을 출력한다
    - [x] End상태에서 승리 팀을 출력한다.
    - [x] 팀별 점수를 출력한다.


## 데이터 베이스

- [x] DB연결을 한다.
- [x] Game
  -[x] 게임의 이름, team 순서를 저장한다.
  -[x] 게임의 이름, team 순서를 읽어온다.
 
- [x] board
  - [x] board를 삽입한다.
  - [x] board를 읽어온다.
  - [x] board를 업데이트한다.

## db 시나리오
- [x] 사용자가 게임방을 입력한다.
  - [x] 기존 게임이 있을 경우 불러온다.
  - [x] 기존 게임이 없을 경우 새로 생성한다.
    - [x] 새로 생성할 경우 원래 있던 방은 삭제한다.
  
- [x] 사용자가 게임을 진행 하던 중 end를 입력한다.
  - [x] 게임을 저장한다.

## how to run 
- docker-compose 파일을 이용하여 DB 띄운다.
- 자동으로 ddl 파일이 입력된다.
- 게임을 시작한다.
- 게임 이름을 입력한다.
  - 게임 이름이 존재할 경우 저장되어 있는 특정 게임이 불러와진다.
  - 게임 이름이 존재하지 않을경우 새로운 게임이 시작된다.
- start 후 게임을 진행하고 end를 입력 할 경우 DB에 게임이 저장된다.
- 왕이 잡혀 게임이 end 되었을 경우 DB에 게임이 저장되지 않는다.
