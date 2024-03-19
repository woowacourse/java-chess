# java-chess

체스 미션 저장소

## 우아한테크코스 코드리뷰

- [온라인 코드 리뷰 과정](https://github.com/woowacourse/woowacourse-docs/blob/master/maincourse/README.md)


<hr>

## 구현 기능 목록

### 체스판
- [ ] 체스판의 크기는 `8 x 8`이다.
- [ ] 체스판에서 말의 위치 값은 다음과 같이 구현한다.
  - 가로 위치는 왼쪽부터 오른쪽으로 `a ~ h`
  - 세로 위치는 아래부터 위로 `1 ~ 8`
- [ ] 체스판에서 각 진영은 검은색(대문자)과 흰색(소문자) 편으로 구분한다.
- [ ] 게임 시작 시 검은색 말은 `7 ~ 8` 행에 위치 하고, 흰색 말은 `1 ~ 2` 행에 위치 한다.
- [ ] 각 기물의 초기 위치는 아래와 같다.
    ```
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

