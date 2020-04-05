package chess.domain.move;

import chess.domain.board.BoardSquare;
import chess.domain.board.Game;
import chess.domain.state.MoveSquare;
import chess.domain.state.MoveState;

public class MoveStateChecker {

    private static final BoardSquare DEFAULT_SQUARE = BoardSquare.of("a1");
    private final MoveStateStrategy moveStateStrategy;

    public MoveStateChecker(MoveStateStrategy moveStateStrategy) {
        this.moveStateStrategy = moveStateStrategy;
    }

    public MoveState check(Game game) {
        return check(game, new MoveSquare(DEFAULT_SQUARE, DEFAULT_SQUARE));
    }

    public MoveState check(Game game, MoveSquare moveSquare) {
        return moveStateStrategy.getMoveState(game, moveSquare);
    }
}
