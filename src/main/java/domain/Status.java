package domain;

public class Status {

    private final double whiteScore;
    private final double blackScore;
    private final Player winner;

    public Status(double whiteScore, double blackScore) {
        this.whiteScore = whiteScore;
        this.blackScore = blackScore;
        this.winner = calculateScore();
    }

    private Player calculateScore() {
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

    public Player getWinner() {
        return winner;
    }
}
