package chess.domain.command;

import chess.domain.board.Score;
import chess.domain.piece.Color;
import java.util.Map;

public class StatusResult {

    private final double blackScore;
    private final double whiteScore;

    public StatusResult(Map<Color, Score> result) {
        this.blackScore = result.get(Color.BLACK).getScore();
        this.whiteScore = result.get(Color.WHITE).getScore();
    }

    public double getBlackScore() {
        return blackScore;
    }

    public double getWhiteScore() {
        return whiteScore;
    }
}
