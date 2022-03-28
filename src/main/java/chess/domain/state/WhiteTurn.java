package chess.domain.state;

import chess.domain.board.Board;
import chess.domain.piece.Color;
import chess.domain.position.Position;

public final class WhiteTurn extends Running {

    WhiteTurn(final Board board) {
        this.board = board;
    }

    @Override
    public State move(final Position from, final Position to) {
        try {
            board.move(from, to, Color.WHITE);
            return new BlackTurn(board);
        } catch (IllegalStateException e) {
            return new Finish(board, Color.WHITE);
        }
    }
}
