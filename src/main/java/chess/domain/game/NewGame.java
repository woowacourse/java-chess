package chess.domain.game;

import chess.db.entity.FullGameEntity;
import chess.domain.board.Board;
import chess.domain.board.piece.Color;
import chess.dto.request.MoveCommandDto;
import chess.dto.response.board.ConsoleBoardViewDto;
import chess.dto.response.board.WebBoardViewDto;
import chess.util.BoardMapGeneratorUtil;

public final class NewGame implements Game {

    private static final String GAME_NOT_STARTED_EXCEPTION_MESSAGE = "아직 시작되지 않은 게임입니다.";

    @Override
    public Game init() {
        Board board = new Board(BoardMapGeneratorUtil.initFullChessBoard());
        return new WhiteTurn(board);
    }

    @Override
    public Color getCurrentTurnColor() {
        throw new UnsupportedOperationException(GAME_NOT_STARTED_EXCEPTION_MESSAGE);
    }

    @Override
    public Game moveChessmen(MoveCommandDto dto) {
        throw new UnsupportedOperationException(GAME_NOT_STARTED_EXCEPTION_MESSAGE);
    }

    @Override
    public boolean isEnd() {
        return false;
    }

    @Override
    public GameState getState() {
        throw new UnsupportedOperationException(GAME_NOT_STARTED_EXCEPTION_MESSAGE);
    }

    @Override
    public FullGameEntity toEntityOf(int id) {
        throw new UnsupportedOperationException(GAME_NOT_STARTED_EXCEPTION_MESSAGE);
    }

    @Override
    public GameResult getResult() {
        throw new UnsupportedOperationException(GAME_NOT_STARTED_EXCEPTION_MESSAGE);
    }

    @Override
    public ConsoleBoardViewDto toConsoleView() {
        throw new UnsupportedOperationException(GAME_NOT_STARTED_EXCEPTION_MESSAGE);
    }

    @Override
    public WebBoardViewDto toBoardWebView() {
        throw new UnsupportedOperationException(GAME_NOT_STARTED_EXCEPTION_MESSAGE);
    }
}
