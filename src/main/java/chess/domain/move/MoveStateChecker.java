package chess.domain.move;

import chess.domain.board.BoardSquare;
import chess.domain.board.ChessBoard;
import chess.domain.state.MoveSquare;
import chess.domain.state.MoveState;

public class MoveStateChecker {

    private static final BoardSquare DEFAULT_SQUARE = BoardSquare.of("a1");
    private final MoveStateStrategy moveStateStrategy;

    public MoveStateChecker(MoveStateStrategy moveStateStrategy) {
        this.moveStateStrategy = moveStateStrategy;
    }

    public MoveState check(ChessBoard chessBoard) {
        return check(chessBoard, new MoveSquare(DEFAULT_SQUARE, DEFAULT_SQUARE));
    }

    public MoveState check(ChessBoard chessBoard, MoveSquare moveSquare) {
        return moveStateStrategy.getMoveState(chessBoard, moveSquare);
    }
}
