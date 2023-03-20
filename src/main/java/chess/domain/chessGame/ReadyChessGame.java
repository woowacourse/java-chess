package chess.domain.chessGame;

import chess.domain.Board;
import chess.domain.position.Position;

import java.util.Collections;
import java.util.Map;

public class ReadyChessGame implements ChessGame {

    private static final String GAME_NOT_START_ERROR_MESSAGE = "아직 게임을 시작하지 않았습니다.";

    @Override
    public Map<Position, String> move(String currentPosition, String nextPosition) {
        throw new IllegalArgumentException(GAME_NOT_START_ERROR_MESSAGE);
    }

    @Override
    public ChessGame start() {
        Board board = new Board();
        return new PlayingChessGame(board);
    }

    @Override
    public void end() {
        throw new IllegalArgumentException(GAME_NOT_START_ERROR_MESSAGE);
    }

    @Override
    public boolean isEnd() {
        return false;
    }

    @Override
    public Map<Position, String> getPrintingBoard() {
        return Collections.emptyMap();
    }
}
