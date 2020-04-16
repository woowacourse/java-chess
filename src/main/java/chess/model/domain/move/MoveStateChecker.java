package chess.model.domain.move;

import chess.model.domain.board.BoardSquare;
import chess.model.domain.board.ChessGame;
import chess.model.domain.state.MoveSquare;
import chess.model.domain.state.MoveState;

public class MoveStateChecker {

    private static final BoardSquare DEFAULT_SQUARE = BoardSquare.of("a1");
    private final MoveStateStrategy moveStateStrategy;

    public MoveStateChecker(MoveStateStrategy moveStateStrategy) {
        this.moveStateStrategy = moveStateStrategy;
    }

    public MoveState check(ChessGame chessGame) {
        return check(chessGame, new MoveSquare(DEFAULT_SQUARE, DEFAULT_SQUARE));
    }

    public MoveState check(ChessGame chessGame, MoveSquare moveSquare) {
        return moveStateStrategy.getMoveState(chessGame, moveSquare);
    }
}
