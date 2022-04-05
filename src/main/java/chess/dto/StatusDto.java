package chess.dto;

import chess.domain.Score;
import chess.domain.WinResult;
import chess.domain.piece.PieceColor;

import java.util.Map;

public class StatusDto {

    private final Map<PieceColor, Score> scores;
    private final WinResult winResult;

    public StatusDto(Map<PieceColor, Score> scores) {
        this.scores = scores;
        Score blackScore = scores.get(PieceColor.BLACK);
        Score whiteScore = scores.get(PieceColor.WHITE);
        this.winResult = WinResult.of(blackScore, whiteScore);
    }

    public Map<PieceColor, Score> getScoresByColor() {
        return scores;
    }

    public WinResult getWinResult() {
        return winResult;
    }
}
