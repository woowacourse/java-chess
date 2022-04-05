package refactorChess.domain.game;

import java.util.Objects;
import refactorChess.domain.piece.PieceColor;

public class Status {
    private final Score whiteScore;
    private final Score blackScore;
    private final PieceColor winnerColor;

    public Status(Score whiteScore, Score blackScore, PieceColor winnerColor) {
        this.whiteScore = whiteScore;
        this.blackScore = blackScore;
        this.winnerColor = winnerColor;
    }

    public Score getWhiteScore() {
        return whiteScore;
    }

    public Score getBlackScore() {
        return blackScore;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Status)) {
            return false;
        }
        Status status = (Status) o;
        return Objects.equals(whiteScore, status.whiteScore) && Objects.equals(blackScore,
                status.blackScore) && winnerColor == status.winnerColor;
    }

    @Override
    public int hashCode() {
        return Objects.hash(whiteScore, blackScore, winnerColor);
    }
}
