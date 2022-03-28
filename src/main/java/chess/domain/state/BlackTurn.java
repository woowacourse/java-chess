package chess.domain.state;

import chess.domain.board.Board;
import chess.domain.piece.Color;
import chess.domain.position.Position;

public final class BlackTurn extends Running {

    BlackTurn(final Board board) {
        this.board = board;
    }

    @Override
    public State move(final Position from, final Position to) {
        try {
            board.move(from, to, Color.BLACK);
            return new WhiteTurn(board);
        } catch (IllegalStateException e) {
            return new Finish(board, Color.BLACK);
        }
    }
}
