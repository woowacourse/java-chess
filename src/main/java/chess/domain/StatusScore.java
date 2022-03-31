package chess.domain;

public class StatusScore {
    private final double white;
    private final double black;

    public StatusScore(final double statusOfWhite, final double statusOfBlack) {
        this.white = statusOfWhite;
        this.black = statusOfBlack;
    }

    public double getWhite() {
        return white;
    }

    public double getBlack() {
        return black;
    }
}
