# java-chess

체스 미션 저장소

## 우아한테크코스 코드리뷰

- [온라인 코드 리뷰 과정](https://github.com/woowacourse/woowacourse-docs/blob/master/maincourse/README.md)

# 체스 요구사항 분석

## 1. 입력

- [x]  게임 여부를 입력 받는다.
    - [x]  게임 시작은 start, 게임 종료는 end

## 2. 출력

- [x]  안내 문구를 출력한다.
- [x]  체스판을 출력한다.

## 3. 비즈니스

- [x]  체스판을 생성
    - [x]  8 X 8 크기를 갖는다.
    - [x]  검은색, 하얀색 진영으로 나뉜다.
    - [x]  각 진영은 폰(8개), 퀸(1개), 킹(1개), 나이트(2개), 비숍(2개), 루크(2개) 를 가진다.
- [x]  말 이동
    - [x]  말은 각각의 방법에 따라 이동한다.
    - [x]  이동하려는 위치가 체스판을 넘을 수 없다.
    - [x]  이동하려는 위치에 같은 진영의 말이 있을 경우 움직을 수 없다.
    - [x]  나이트를 제외한 말은 다른 말을 넘어갈 수 없다.
- [ ] 킹이 잡히면 게임이 종료된다.
- [ ] 현재 남아있는 기물에 대한 점수를 반환한다.

### Piece ( class ) : 기물

- [ ] Member1 : PieceState
- [ ] Member2 : PathValidator
- [ ] command의 정보를 따라 움직일 수 있는지 검증한다.

  ### - PieceState ( class )

    - [ ] Member1 : PieceType
    - [ ] Member2 : Camp

      ### - PieceType ( enum )

        - [ ] Member1 : Score
        - [ ] Member2 : PossibleDirections

      ### - Camp ( enum )

### PathValidator ( class )

- [ ] Member1 : 검증해야할 대상들
- [ ] 경로를 검증한다.
- [ ] 체크해야 할 정보

### ValidateTarget ( enum )

|                  | rook | bishop | queen | king | knight | pawn |
|:----------------:|:----:|:------:|:-----:|:----:|:------:|:----:|
|    Direction     |  O   |   O    |   O   |  O   |   O    |  O   |
| DestinationPiece |  O   |   O    |   O   |  O   |   O    |  O   |
|     Blocked      |  O   |   O    |   O   |  X   |   X    |  O   |
|    MoveCount     |  X   |   X    |   X   |  O   |   O    |  O   |
|  StartLocation   |  X   |   X    |   X   |  X   |   X    |  O   |
|       Camp       |  X   |   X    |   X   |  X   |   X    |  O   |

### Path ( class )

- [ ] Member1 : PieceMove
- [ ] Member2 : 경로에 존재하는 기물들 ( 기물의 갯수는 움직인 횟수랑 동일 )

### PieceMove ( class )

- [ ] Member1 : 시작 위치
- [ ] Member2 : 끝 위치

### Direction ( enum )

- [ ] PieceMove를 인자로 받아 해당되는 방향 반환

### InputView ( class )

- [ ] 커맨드를 입력을 받는다.
    - [ ] START : 게임을 시작한다 ( 도중에는 재시작)
    - [ ] END : 게임을 종료한다
    - [ ] STATUS : 점수를 조회한다.
    - [ ] MOVE : 기물을 움직인다 ( start, end 위치에 대한 추가 입력이 존재한다.)
