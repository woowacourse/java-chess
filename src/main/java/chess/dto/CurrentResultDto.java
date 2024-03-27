package chess.dto;

import chess.domain.piece.Color;
import chess.domain.vo.Score;

public record CurrentResultDto(Score blackScore, Score whiteScore, Color winnerColor) {
    public double getBlackScore() {
        return blackScore.getValue();
    }

    public double getWhiteScore() {
        return whiteScore.getValue();
    }
}
