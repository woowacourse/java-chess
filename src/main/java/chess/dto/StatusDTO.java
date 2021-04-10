package chess.dto;

public class StatusDTO {
    private final String whiteScore;
    private final String blackScore;
    private final String winner;
    private final boolean isKingDie;

    public StatusDTO(final String whiteScore, final String blackScore, final String winner, final boolean isKingDie) {
        this.whiteScore = whiteScore;
        this.blackScore = blackScore;
        this.winner = winner;
        this.isKingDie = isKingDie;
    }

    public String getWhiteScore() {
        return whiteScore;
    }

    public String getBlackScore() {
        return blackScore;
    }

    public String getWinner() {
        return winner;
    }

    public boolean isKingDie() {
        return isKingDie;
    }
}
