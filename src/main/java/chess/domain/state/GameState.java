package chess.domain.state;

import chess.domain.piece.Color;
import java.util.List;

public interface GameState {

    GameState play(List<String> inputCommand);

    boolean isEnd();

    double calculateScore(Color color);

    Color getWinnerColor();
}
