package chess.dto.domaintocontroller;

import chess.domain.piece.property.Color;

public class GameStatus {

    private final Color winningTeamColor;
    private final double blackScore;
    private final double whiteScore;

    public GameStatus(final Color winningTeamColor, final double blackScore, final double whiteScore) {
        this.winningTeamColor = winningTeamColor;
        this.blackScore = blackScore;
        this.whiteScore = whiteScore;
    }

    public Color getWinningTeamColor() {
        return winningTeamColor;
    }

    public double getBlackScore() {
        return blackScore;
    }

    public double getWhiteScore() {
        return whiteScore;
    }
}
