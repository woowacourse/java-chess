package chess.domain.game;

import chess.domain.board.Board;
import chess.dto.response.ConsoleBoardViewDto;
import chess.dto.response.WebBoardViewDto;

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
    public final ConsoleBoardViewDto toConsoleView() {
        return new ConsoleBoardViewDto(board);
    }

    @Override
    public WebBoardViewDto toBoardWebView() {
        return new WebBoardViewDto(board);
    }
}
