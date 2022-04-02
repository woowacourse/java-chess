package chess.domain.game;

import chess.domain.board.Board;
import chess.dto.response.ConsoleBoardViewDto;
import chess.dto.response.WebBoardViewDto;

abstract class Started implements Game {

    protected final Board board;
    private final GameState state;

    protected Started(Board board, GameState state) {
        this.board = board;
        this.state = state;
    }

    @Override
    public final Game init() {
        return new NewGame().init();
    }

    @Override
    public final GameState getState() {
        return state;
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
