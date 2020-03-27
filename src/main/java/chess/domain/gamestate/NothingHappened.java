package chess.domain.gamestate;

import java.util.List;

public class NothingHappened implements GameState {

    private static final String GAME_NOT_RUNNING_MESSAGE = "아직 게임을 시작하지 않았습니다.";

    @Override
    public GameState move(String fromPosition, String toPosition) {
        throw new UnsupportedOperationException(GAME_NOT_RUNNING_MESSAGE);
    }

    @Override
    public List<List<String>> getBoard() {
        throw new UnsupportedOperationException(GAME_NOT_RUNNING_MESSAGE);
    }

    @Override
    public GameState finish() {
        throw new UnsupportedOperationException(GAME_NOT_RUNNING_MESSAGE);
    }

    @Override
    public double getWhiteTeamScore() {
        throw new UnsupportedOperationException(GAME_NOT_RUNNING_MESSAGE);
    }

    @Override
    public double getBlackTeamScore() {
        throw new UnsupportedOperationException(GAME_NOT_RUNNING_MESSAGE);
    }
}
