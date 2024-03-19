# 기능 요구사항

- 콘솔 UI에서 체스 게임을 할 수 있는 기능을 구현한다.
- 1단계는 체스 게임을 할 수 있는 체스판을 초기화한다.
- 체스판에서 말의 위치 값은 가로 위치는 왼쪽부터 a ~ h이고, 세로는 아래부터 위로 1 ~ 8로 구현한다.

- 체스 말의 이동 규칙을 찾아보고 체스 말이 이동할 수 있도록 구현한다.
- move source위치 target위치을 실행해 이동한다.

# 기능 구현 목록

## ChessBoard

- 체스판의 정보를 저장한다.

## ChessBoardGenerator

- 체스판를 초기화한다.

## ChessPiece

- 기물의 정보를 저장한다.

## OutputView

- 체스판의 정보를 출력한다.
- 넘버링과 레터링을 출력한다.

## PieceMover

- 체스판의 칸과 기물의 위치를 매핑한다.
- Source 위치의 기물을 Target 위치로 이동시킨다.
    - Source 위치에 기물이 없다면 에러를 반환한다.
    - Source 위치에 기물이 있다면 Target 위치에 Piece가 있는 지 확인한다.
        - Piece가 있다면
            - Target 위치의 Piece를 제거한다.
            - Source 위치의 Piece를 Target 위치로 복사한다.
            - Source 위치의 Piece를 제거한다.
        - Piece가 없다면
            - Source 위치의 Piece를 Target 위치로 복사한다.
            - Source 위치의 Piece를 제거한다.
