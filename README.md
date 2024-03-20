# java-chess

체스 미션 저장소

## 우아한테크코스 코드리뷰

- [온라인 코드 리뷰 과정](https://github.com/woowacourse/woowacourse-docs/blob/master/maincourse/README.md)


# 기능 구현 목록

## 체스 게임
- [X] :  체스 게임 시작 문구를 출력한다.
- [X] : 시작 명령어를 입력 받는다.
  - [X] : start, end 가 아니면 예외를 터트린다.
  - [X] : start 입력 시 게임을 시작한다.
  - [X] : end 입력 시 게임을 종료한다.
- 
- [X] : 보드판을 초기화한다.
  - [X] : 소문자, 대문자로 팀을 구분한다.
  - [X] : 각 팀은 총 16개의 말을 갖는다.
    - [X] : 폰 8개, 룩 2개, 나이트 2개, 비숍 2개, 퀸 1개, 킹 1개
- [ ] : 사용자는 보드판에서 위치로 기물을 조회하여 기물을 이동시킬 수 있다.
  

## 체스 기물

### 방향
  - [X] : 북, 북동, 동, 동남, 남, 남서, 서, 북서쪽의 총 8방향을 갖는다. 
  - [X] : 방향에 따라 방향 가중치 (1, -1, 0)을 갖는다.

### 위치
  - [X] : 행, 열 enum을 갖는다.
  - [X] : 현재 위치를 기준으로 방향에 따른 최대 가중치를 계산한다.
  - [X] : 기물이 고유로 갖는 이동 방향과 가중치를 이용하여 이동할 수 있는 위치들을 반환한다.

### 기물
  - [X] : 기물은 기물 타입을 갖는다.
  - [X] : 기물은 팀 색깔을 갖는다.

### 기물 타입
  - [X] : 기물 타입은 이동 전략을 갖는다.
  - [ ] : 각 기물의 이동 전략 구현체는 기물의 현재 위치, 방향, 가중치를 가지고 이동할 수 있는 위치들을 계산한다.
    - [X] : 룩은 기본적으로 북, 동, 남, 서 방향으로 이동할 수 있다.
    - [ ] : 비숍은 기본적으로 북동, 동남, 남서, 북서 방향으로 이동할 수 있다.
    - [ ] : 퀸은 기본적으로 모든 방향으로 이동할 수 있다.
    - [ ] : 킹은 기본적으로 모든 방향으로 이동할 수 있다.
    - [ ] : 폰은 기본적으로 팀 색깔에 따라 북 또는 남 방향으로만 이동할 수 있다.
