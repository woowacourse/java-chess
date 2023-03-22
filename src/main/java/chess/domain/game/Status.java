package chess.domain.game;

import chess.domain.piece.Color;
import chess.domain.piece.Score;
import java.util.Map;

public final class Status {
    
    private final Map<Color, Score> scoreMap;
    
    private Status(final Map<Color, Score> scoreMap) {
        this.scoreMap = scoreMap;
    }
    
    public static Status from(final Map<Color, Score> scoreMap) {
        return new Status(scoreMap);
    }
    
    public Score getScore(final Color color) {
        return this.scoreMap.get(color);
    }
}
