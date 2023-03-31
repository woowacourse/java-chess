package chess.controller.dto;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import chess.domain.board.Score;
import chess.domain.board.ScoreBySide;
import chess.domain.piece.Side;

public class ScoreBySideDto {

    private final Map<String, BigDecimal> scoreBySideForPrint = new HashMap<>();

    public ScoreBySideDto(ScoreBySide scoreBySide) {
        Map<Side, Score> gotScoreBySide = scoreBySide.getScoreBySide();
        for (Side side : gotScoreBySide.keySet()) {
            Score score = gotScoreBySide.get(side);
            scoreBySideForPrint.put(side.getDisplayName(), score.getScore());
        }
    }

    public Map<String, BigDecimal> getScoreBySideForPrint() {
        return scoreBySideForPrint;
    }
}
