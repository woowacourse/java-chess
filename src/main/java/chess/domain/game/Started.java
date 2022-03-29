package chess.domain.game;

import chess.domain.board.Board;
import chess.dto.BoardViewDto;

abstract class Started implements Game {

    protected final Board board;

    protected Started(Board board) {
        this.board = board;
    }

    @Override
    public final Game init() {
        return new NewGame().init();
    }

    @Override
    public final BoardViewDto boardView() {
        return new BoardViewDto(board);
    }
}
