package chess.domain;

public class GameResult {
    private final String winner;
    private final String loser;
    private final double aliveBlackPieceScoreSum;
    private final double aliveWhitePieceScoreSum;

    public GameResult(boolean isBlackKingKilled, boolean isWhiteKingKilled, double aliveBlackPieceScoreSum,
                      double aliveWhitePieceScoreSum) {
        this.winner = getWinner(isBlackKingKilled, isWhiteKingKilled);
        this.loser = getLoser(isBlackKingKilled, isWhiteKingKilled);
        this.aliveBlackPieceScoreSum = aliveBlackPieceScoreSum;
        this.aliveWhitePieceScoreSum = aliveWhitePieceScoreSum;
    }

    private String getWinner(boolean isBlackKingKilled, boolean isWhiteKingKilled) {
        if (isBlackKingKilled) {
            return "White";
        }
        if (isWhiteKingKilled) {
            return "Black";
        }
        return "";
    }

    private String getLoser(boolean isBlackKingKilled, boolean isWhiteKingKilled) {
        if (isBlackKingKilled) {
            return "Black";
        }
        if (isWhiteKingKilled) {
            return "White";
        }
        return "";
    }

    public String getWinner() {
        return winner;
    }

    public String getLoser() {
        return loser;
    }

    public double getAliveBlackPieceScoreSum() {
        return aliveBlackPieceScoreSum;
    }

    public double getAliveWhitePieceScoreSum() {
        return aliveWhitePieceScoreSum;
    }
}
