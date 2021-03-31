package chess.dto;

public class ScoreDto {

    private final double white;
    private final double black;

    public ScoreDto(final double white, final double black) {
        this.white = white;
        this.black = black;
    }

    public double getWhite() {
        return this.white;
    }

    public double getBlack() {
        return this.black;
    }
}
