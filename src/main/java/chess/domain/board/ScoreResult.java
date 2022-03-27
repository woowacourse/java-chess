package chess.domain.board;

import chess.domain.piece.Color;
import java.util.HashMap;
import java.util.Map;

public class ScoreResult {

    private final Map<Color, Score> result;

    public ScoreResult(Map<Color, Score> result) {
        this.result = result;
    }

    public Score getBlackScore() {
        return result.get(Color.BLACK);
    }

    public Score getWhiteScore() {
        return result.get(Color.WHITE);
    }
}
