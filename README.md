# java-chess

체스 미션 저장소

## 우아한테크코스 코드리뷰

- [온라인 코드 리뷰 과정](https://github.com/woowacourse/woowacourse-docs/blob/master/maincourse/README.md)

## 구현 기능 목록

### File

- 체스판의 열 역할을 담당한다.
- A부터 H까지의 값을 가진다.
- 각 기물의 초기 포지션을 변환한다. 

### Rank

- 체스판의 행 역할을 담당한다.
- 1부터 8까지의 값을 가진다.

### Position

- 체스 기물의 위치를 나타내는 좌표 역할을 담당한다.
- File과 Rank를 가진다.

### Point

- 체스 기물의 점수를 나타내는 역할을 담당한다.

### Type

- 체스 기물의 종류 정보를 나타내는 역할을 담당한다.

### Piece

- 체스의 기물 역할을 담당한다.
- [x] File과 Rank를 이용해 Position 정보를 가진다.
- [x] Color를 통해 색 정보를 가진다.
- [x] 자신의 색 정보를 제공한다.
- [x] 자신의 좌표 정보를 제공한다.
- [x] 자신의 종류 정보를 제공한다.

### Board

- 체스판을 관리하는 역할을 담당한다.
- Position을 Key, Piece를 Value로 갖는 Map을 관리한다.


#### King

#### Queen

#### Rook

#### Bishop

#### Knight

#### Pawn

### None



### Board

- 체스의 보드 역할을 담당한다.

### PieceShape

- 체스 기물을 출력하기 위한 모양을 정의해두는 역할을 담당한다.


### DtoMapper

- dto를 매핑 역할을 담당한다.

### BoardInitiator

- 게임 보드판을 초기화하는 역할을 담당한다.

### PieceInfo

- 피스의 정보를 저장하는 역할을 담당한다.

### RankInfo

- 한 랭크의 피스들의 정보를 저장하는 역할을 담당한다.

### PieceShape

- 피스의 모양을 출력 모양으로 바꾸어주는 역할을 담당한다.
