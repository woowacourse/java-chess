package chess.controller.menu;

import chess.domain.board.Board;

public interface Menu {
    /**
     * Returns {@code true} if this collection contains no elements.
     *
     * @return {@code true} if this collection contains no elements
     */
    boolean play(Board board);
}
