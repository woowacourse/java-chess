package chess.domain.game;

import chess.domain.board.Board;
import chess.domain.event.Event;
import chess.domain.event.MoveCommand;
import chess.domain.game.statistics.GameResult;
import chess.dto.GameDto;
import chess.dto.board.ConsoleBoardViewDto;
import chess.util.BoardMapGeneratorUtil;

public final class NewGame implements Game {

    private static final String GAME_NOT_STARTED_EXCEPTION_MESSAGE = "아직 시작되지 않은 게임입니다.";

    @Override
    public Game play(Event event) {
        if (!event.isInit()) {
            throw new UnsupportedOperationException(GAME_NOT_STARTED_EXCEPTION_MESSAGE);
        }
        Board board = new Board(BoardMapGeneratorUtil.initFullChessBoard());
        return new WhiteTurn(board);
    }

    @Override
    public Game init() {
        Board board = new Board(BoardMapGeneratorUtil.initFullChessBoard());
        return new WhiteTurn(board);
    }

    @Override
    public Game moveChessmen(MoveCommand dto) {
        throw new UnsupportedOperationException(GAME_NOT_STARTED_EXCEPTION_MESSAGE);
    }

    @Override
    public boolean isEnd() {
        return false;
    }

    @Override
    public GameResult getResult() {
        throw new UnsupportedOperationException(GAME_NOT_STARTED_EXCEPTION_MESSAGE);
    }

    @Override
    public GameDto toDtoOf(int gameId) {
        throw new UnsupportedOperationException(GAME_NOT_STARTED_EXCEPTION_MESSAGE);
    }

    @Override
    public ConsoleBoardViewDto toConsoleView() {
        throw new UnsupportedOperationException(GAME_NOT_STARTED_EXCEPTION_MESSAGE);
    }
}
