package chess.domain.game;

public class Score {

    private final double white;
    private final double black;

    public Score(final double white, final double black) {
        this.white = white;
        this.black = black;
    }

    public double white() {
        return this.white;
    }

    public double black() {
        return this.black;
    }
}
