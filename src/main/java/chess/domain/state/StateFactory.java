package chess.domain.state;

import chess.domain.board.Board;
import chess.domain.piece.Color;

public final class StateFactory {
    public static State of(final Color color, final Board board) {
        if (board.isRemovedKing()) {
            return new End(color.next(), board);
        }
        return new Running(color, board);
    }
}
