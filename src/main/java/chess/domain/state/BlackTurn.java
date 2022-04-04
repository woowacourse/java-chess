package chess.domain.state;

import chess.domain.board.Board;
import chess.domain.piece.Color;
import chess.domain.position.Position;

public class BlackTurn extends Running {

    private static final Color color = Color.BLACK;

    BlackTurn(final Board board) {
        super(board);
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
