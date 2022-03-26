package chess.dto;

import chess.piece.Color;

public class ScoreDto {
    private static final String DRAW = "무승부";

    private final double black;
    private final double white;
    private final String winner;

    public ScoreDto(double black, double white, Color color) {
        this.black = black;
        this.white = white;
        winner = color.getColor();
    }

    public ScoreDto(double black, double white) {
        this.black = black;
        this.white = white;
        winner = DRAW;
    }

    public double getBlack() {
        return black;
    }

    public double getWhite() {
        return white;
    }

    public String getWinner() {
        return winner;
    }
}
