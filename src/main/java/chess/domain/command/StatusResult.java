package chess.domain.command;

import chess.domain.board.Score;
import chess.domain.piece.Color;
import java.util.Map;

public class StatusResult {

    private final Map<Color, Score> result;

    public StatusResult(Map<Color, Score> result) {
        this.result = result;
    }

    public Score getBlackScore() {
        return result.get(Color.BLACK);
    }

    public Score getWhiteScore() {
        return result.get(Color.WHITE);
    }
}
