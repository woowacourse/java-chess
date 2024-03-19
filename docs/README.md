# java-chess

# 체스 게임 1단계 미션

## 1. 기능 요구사항

- 콘솔 UI 에서 체스 게임을 할 수 있는 기능을 구현한다.

- 1단계는 체스 게임을 할 수 있는 체스판을 초기화한다.

- 체스판에서 말의 위치 값은 가로 위치는 왼쪽부터 a ~ h이고, 세로는 아래부터 위로 1 ~ 8로 구현한다.

## 2. 기능 목록

### 2-1. 입력

- [ ] 게임 커맨드 입력 받기
    - [ ] start , end 만 허용
    - [ ] start , end 그 이외의 값은 예외처리를 발생하여 게임을 종료한다

### 2-2. 메인 로직 실행

- [ ] 체스판을 초기화 한다

### 2-3. 출력

- [ ] 초기화된 체스판 출력
  ```
  RNBQKBNR  
  PPPPPPPP  
  ........  
  ........  
  ........  
  ........  
  pppppppp  
  rnbqkbnr  
  ```
  대문자는 `검은색` , 소문자는 `흰색` 말을 의미한다.
    - R은 체스의 `Rook`을 의미한다.
    - N은 체스의 `Knight`을 의미한다.
    - B는 체스의 `Bishop`을 의미한다.
    - Q는 체스의 `Queen`을 의미한다.
    - K는 체스의 `King`을 의미한다.
    - P는 체스의 `Pawn`을 의미한다.

## 3. 실행 결과

```
체스 게임을 시작합니다.
게임 시작은 start, 종료는 end 명령을 입력하세요.
start
RNBQKBNR
PPPPPPPP
........
........
........
........
pppppppp
rnbqkbnr

end
```

## 우아한테크코스 코드리뷰

- [온라인 코드 리뷰 과정](https://github.com/woowacourse/woowacourse-docs/blob/master/maincourse/README.md)
