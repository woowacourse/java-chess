package chess.dto;

import chess.domain.Winner;

public class ResultDto {
    private final String scoreOfWhite;
    private final String scoreOfBlack;
    private final String winner;

    public ResultDto(String scoreOfWhite, String scoreOfBlack, String winner) {
        this.scoreOfWhite = scoreOfWhite;
        this.scoreOfBlack = scoreOfBlack;
        this.winner = winner;
    }

    public static ResultDto of(double statusOfWhite, double statusOfBlack, Winner winner) {
        return new ResultDto(String.format("%.1f", statusOfWhite), String.format("%.1f", statusOfBlack), winner.name());
    }

    public String getScoreOfWhite() {
        return scoreOfWhite;
    }

    public String getScoreOfBlack() {
        return scoreOfBlack;
    }

    public String getWinner() {
        return winner;
    }
}
