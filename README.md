# java-chess

체스 미션 저장소

## 우아한테크코스 코드리뷰

- [온라인 코드 리뷰 과정](https://github.com/woowacourse/woowacourse-docs/blob/master/maincourse/README.md)


## 1단계 체스판 초기화

1. 체스판은 8x8 사이즈이다.
2. 체스의 말 종류는 P, R, N, B, Q, K 이렇게 6가지다.
3. 진영은 대문자(검은색, 위쪽)와 소문자(흰색, 아래쪽)로 구분한다.
4. `start`를 입력하면 체스판을 초기화한다.

### Chess Board
Map<Location, Piece>
해당 위치에 어떤 말이 있는지 알려줌

### Location
행(a,b,c ...) -> File
열(1,2,3 ...) -> Rank 

### Piece
해당 말의 종류와 진영
