package chess.domain.board;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import chess.domain.piece.Side;

public class ScoreBySide {

    private final Map<Side, Score> scoreBySide = new HashMap<>();

    public ScoreBySide() {
        initTotalScoreBySide();
    }

    private void initTotalScoreBySide() {
        scoreBySide.put(Side.WHITE, new Score(new BigDecimal("0.0")));
        scoreBySide.put(Side.BLACK, new Score(new BigDecimal("0.0")));
    }

    public void updateScore(Side side, Score scoreToUpdate) {
        scoreBySide.replace(side, scoreToUpdate);
    }

    public Map<Side, Score> getScoreBySide() {
        return Map.copyOf(scoreBySide);
    }
}
