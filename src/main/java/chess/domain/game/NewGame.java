package chess.domain.game;

import chess.domain.board.Board;
import chess.domain.board.piece.Color;
import chess.dto.MoveCommandDto;
import chess.dto.BoardViewDto;
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
    public GameResult getResult() {
        throw new UnsupportedOperationException(GAME_NOT_STARTED_EXCEPTION_MESSAGE);
    }

    @Override
    public BoardViewDto boardView() {
        throw new UnsupportedOperationException(GAME_NOT_STARTED_EXCEPTION_MESSAGE);
    }
}
