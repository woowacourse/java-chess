package chess.domain.game;

import chess.domain.board.Board;
import chess.util.RenderingUtils;

public final class StatusResult implements Result {

    private final Board board;

    public StatusResult(final Board board) {
        this.board = board;
    }

    @Override
    public String visualAsString() {
        return RenderingUtils.renderBoard(board);
    }
}
