package chess.domain.state;

import chess.domain.board.Board;
import chess.domain.piece.Color;
import chess.domain.position.Position;
import chess.dto.StateType;

public class WhiteTurn extends Running {

    private static final Color color = Color.WHITE;

    public WhiteTurn(final Board board) {
        super(board, StateType.WHITE_TURN);
    }

    @Override
    public State move(final Position from, final Position to) {
        checkValidPosition(from, to, color);
        board.move(from, to);

        if (isGameOver(color)) {
            return new Ready(board);
        }
        return new BlackTurn(board);
    }
}
