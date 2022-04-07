package chess.domain.player;

public enum Result {

    WIN("승"),
    LOSE("패"),
    DRAW("무승부");

    private final String value;

    Result(String value) {
        this.value = value;
    }

    public static Result from(final double currentPlayerScore, final double opponentPlayerScore,
            boolean hasKingCurrentPlayer, boolean hasKingOpponentPlayer) {
        if (hasKingCurrentPlayer && hasKingOpponentPlayer) {
            return calculateScore(currentPlayerScore, opponentPlayerScore);
        }
        if (hasKingCurrentPlayer) {
            return WIN;
        }
        if (hasKingOpponentPlayer) {
            return LOSE;
        }
        throw new IllegalArgumentException("올바르지 않은 결과입니다.");
    }

    private static Result calculateScore(final double currentPlayerScore, final double opponentPlayerScore) {
        if (currentPlayerScore > opponentPlayerScore) {
            return WIN;
        }
        if (currentPlayerScore < opponentPlayerScore) {
            return LOSE;
        }
        return DRAW;
    }

    public String getValue() {
        return value;
    }
}
