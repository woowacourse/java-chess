package chess.domain.state;

import chess.domain.board.Board;
import chess.domain.piece.Color;
import chess.domain.position.Position;
import chess.dto.StateType;

public class BlackTurn extends Running {

    private static final Color color = Color.BLACK;

    public BlackTurn(final Board board) {
        super(board, StateType.BLACK_TURN);
    }

    @Override
    public State move(final Position from, final Position to) {
        checkValidPosition(from, to, color);
        board.move(from, to);

        if (isGameOver(color)) {
            return new Ready(board);
        }
        return new WhiteTurn(board);
    }
}
