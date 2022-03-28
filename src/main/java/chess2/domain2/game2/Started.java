package chess2.domain2.game2;

import chess2.domain2.board2.Board;
import chess2.dto2.BoardViewDto;

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
    public final GameResult result() {
        return new GameResult(board.getBoard());
    }

    @Override
    public final BoardViewDto boardView() {
        return new BoardViewDto(board);
    }
}
