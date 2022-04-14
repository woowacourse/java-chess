package chess.dto;

import chess.game.Result;
import chess.piece.detail.Color;
import java.util.Map;

public class ResultDto {

    private final Map<Color, Double> score;
    private final Color winner;

    public ResultDto(final Map<Color, Double> score, final Color winner) {
        this.score = score;
        this.winner = winner;
    }

    public static ResultDto toDto(final Result result) {
        return new ResultDto(result.getResult(), result.getWinColor());
    }

    public Map<Color, Double> getScore() {
        return score;
    }

    public Color getWinner() {
        return winner;
    }
}
