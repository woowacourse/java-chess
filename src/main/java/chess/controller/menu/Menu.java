package chess.controller.menu;

import chess.domain.board.Board;

public interface Menu {
    /**
     * Returns {@code true} if this interface implements class Start, Move, Status type.
     *
     * Returns {@code false} if this interface implements class End type Or Board status is checkmate.
     *
     * @return {@code true}
     */
    boolean play(Board board);
}
