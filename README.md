# java-chess

체스 미션 저장소

## 우아한테크코스 코드리뷰

- [온라인 코드 리뷰 과정](https://github.com/woowacourse/woowacourse-docs/blob/master/maincourse/README.md)

# 용어집

- 기물(Piece) : 체스판의 말

# 기능 목록

## 체스판 초기화

- [x] 8*8 크기의 체스판을 만든다
    - 열은 왼쪽부터 a ~ h이고, 행은 아래부터 위로 1 ~ 8로 설정한다
- [x] 기물을 배치한다
    - [x] 1,2행에는 백 진영을 놓는다
  ```
  pppppppp
  rnbqkbnr
  ```
    - [x] 7,8행에는 흑 진영을 놓는다
  ```
  RNBQKBNR
  PPPPPPPP
  ```

## 게임 명령어

- [x] start이면 체스 게임을 시작한다
- [x] end이면 체스 게임을 종료한다

## 입력

- [x] 게임 명령어를 입력한다

## 출력

- [x] 체스판을 출력한다
