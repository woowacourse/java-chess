package chess.domain.gamestate;

import java.util.List;

public interface GameState {
    GameState move(String fromPosition, String toPosition);

    List<List<String>> getBoard();

    GameState finish();

    double getWhiteTeamScore();

    double getBlackTeamScore();

    boolean isRunning();
}
