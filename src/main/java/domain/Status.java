package domain;

public class Status {

    private final double whiteScore;
    private final double blackScore;

    public Status(double whiteScore, double blackScore) {
        this.whiteScore = whiteScore;
        this.blackScore = blackScore;
    }

    public Player winner() {
        if (this.whiteScore > this.blackScore) {
            return Player.WHITE;
        }
        if (this.whiteScore < this.blackScore) {
            return Player.BLACK;
        }
        return null;
    }

    public double getWhiteScore() {
        return whiteScore;
    }

    public double getBlackScore() {
        return blackScore;
    }
}
