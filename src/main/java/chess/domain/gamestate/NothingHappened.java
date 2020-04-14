package chess.domain.gamestate;

import chess.domain.board.Board;
import chess.domain.board.BoardFactory;

import java.util.List;

public class NothingHappened implements GameState {
    private static final String GAME_NOT_RUNNING_MESSAGE = "아직 게임을 시작하지 않았습니다.";

    private Board board;

    public NothingHappened() {
        this.board = BoardFactory.createEmpty();
    }

    @Override
    public GameState move(String fromPosition, String toPosition) {
        throw new UnsupportedOperationException(GAME_NOT_RUNNING_MESSAGE);
    }

    @Override
    public List<List<String>> getBoardForPrint() {
        throw new UnsupportedOperationException(GAME_NOT_RUNNING_MESSAGE);
    }

    @Override
    public GameState finish() {
        return new NothingHappened();
    }

    @Override
    public Board getBoard() {
        return board;
    }

    @Override
    public double getWhiteTeamScore() {
        throw new UnsupportedOperationException(GAME_NOT_RUNNING_MESSAGE);
    }

    @Override
    public double getBlackTeamScore() {
        throw new UnsupportedOperationException(GAME_NOT_RUNNING_MESSAGE);
    }

    @Override
    public boolean isRunning() {
        return false;
    }
}
