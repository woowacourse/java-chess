package chess.domain.game;

import chess.domain.board.Board;
import chess.dto.response.GameDataDto;
import chess.dto.response.GameDto;
import chess.dto.response.board.ConsoleBoardViewDto;
import chess.dto.response.board.WebBoardViewDto;

public abstract class Started implements Game {

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
    public GameDto toDtoOf(int gameId) {
        return new GameDto(new GameDataDto(gameId, getState()), new WebBoardViewDto(board));
    }

    protected abstract GameState getState();
}
