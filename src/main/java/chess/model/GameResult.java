package chess.model;

public class GameResult {
    private ChessPieceColor winner;
    private double whiteScore;
    private double blackScore;

    public GameResult(final ChessPieceColor winner, final double whiteScore, final double blackScore) {
        this.winner = winner;
        this.whiteScore = whiteScore;
        this.blackScore = blackScore;
    }

    public ChessPieceColor getWinner() {
        return winner;
    }

    public double getWhiteScore() {
        return whiteScore;
    }

    public double getBlackScore() {
        return blackScore;
    }
}
