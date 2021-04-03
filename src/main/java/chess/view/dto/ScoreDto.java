package chess.view.dto;

import chess.domain.command.StatusCommand;
import chess.domain.game.ChessGame;

public class ScoreDto {

    private final double whiteScore;
    private final double blackScore;

    public ScoreDto(final StatusCommand statusCommand) {
        blackScore = statusCommand.getBlackScore();
        whiteScore = statusCommand.getWhiteScore();
    }

    public ScoreDto(final ChessGame chessGame) {
        blackScore = chessGame.getBlackScore();
        whiteScore = chessGame.getWhiteScore();
    }

    public double getWhiteScore() {
        return whiteScore;
    }

    public double getBlackScore() {
        return blackScore;
    }

}
