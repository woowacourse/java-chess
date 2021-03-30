package chess.view.dto;

import chess.domain.command.StatusCommand;

public class ScoreDtos {

    private final double whiteScore;
    private final double blackScore;

    public ScoreDtos(final StatusCommand statusCommand) {
        blackScore = statusCommand.getBlackScore();
        whiteScore = statusCommand.getWhiteScore();
    }

    public double getWhiteScore() {
        return whiteScore;
    }

    public double getBlackScore() {
        return blackScore;
    }

}
