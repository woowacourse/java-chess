package chess.domain.gamestatus;

import java.util.List;

public interface GameStatus {
    GameStatus move(String fromPosition, String toPosition);

    List<List<String>> getBoard();

    GameStatus finish();

    double getWhiteTeamScore();

    double getBlackTeamScore();
}
