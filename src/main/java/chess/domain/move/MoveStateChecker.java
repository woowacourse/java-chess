package chess.domain.move;

import chess.domain.board.ChessBoard;
import chess.domain.state.MoveSquare;
import chess.domain.state.MoveState;

public class MoveStateChecker {

    private final MoveStateStrategy moveStateStrategy;

    public MoveStateChecker(MoveStateStrategy moveStateStrategy) {
        this.moveStateStrategy = moveStateStrategy;
    }

    public MoveState check(ChessBoard chessBoard, MoveSquare moveSquare) {
        return moveStateStrategy.getMoveState(chessBoard, moveSquare);
    }
}
