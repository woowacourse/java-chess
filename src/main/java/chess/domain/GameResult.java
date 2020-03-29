package chess.domain;

public class GameResult {
    private final boolean isBlackKingKilled;
    private final boolean isWhiteKingKilled;
    private final double aliveBlackPieceScoreSum;
    private final double aliveWhitePieceScoreSum;

    public GameResult(boolean isBlackKingKilled, boolean isWhiteKingKilled, double aliveBlackPieceScoreSum,
                      double aliveWhitePieceScoreSum) {
        this.isBlackKingKilled = isBlackKingKilled;
        this.isWhiteKingKilled = isWhiteKingKilled;
        this.aliveBlackPieceScoreSum = aliveBlackPieceScoreSum;
        this.aliveWhitePieceScoreSum = aliveWhitePieceScoreSum;
    }

    public boolean isBlackWin() {
        return isWhiteKingKilled;
    }

    public boolean isWhiteWin() {
        return isBlackKingKilled;
    }

    public double getAliveBlackPieceScoreSum() {
        return aliveBlackPieceScoreSum;
    }

    public double getAliveWhitePieceScoreSum() {
        return aliveWhitePieceScoreSum;
    }
}
