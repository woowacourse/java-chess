package chess.domain.chesspiece.movestrategy;

import chess.domain.chessboard.ChessBoard;
import chess.domain.chessboard.Square;

public interface MoveStrategy {

    void move(ChessBoard chessBoard, Square startSquare, Square targetSquare);

    // 1. moveSource에 source가 존재하는지 확인
    // 2. moveSource 기준으로 각 체스말의 움직일 수 있는 범위 계산
    // 3. 움직일 수 있는 범위 내에 moveTarget이 없을 경우 예외 발생
    // 4. moveSource 부터 moveTarget 까지 정해진 패턴으로 움직이며, 경로상에 다른 말 확인
    // 5. target 위치에 도착하면 말 이동 요청
}
