package chess.domain.command;

import chess.domain.game.ChessGame;
import chess.utils.ScoreCalculator;

public class Status implements Command {
    private static final String STATUS_COMMAND = "status";
    private final ChessGame chessGame;

    public Status(ChessGame chessGame) {
        this.chessGame = chessGame;
    }

    public double totalBlackScore() {
        return ScoreCalculator.totalBlackScore(chessGame.board());
    }

    public double totalWhiteScore() {
        return ScoreCalculator.totalWhiteScore(chessGame.board());
    }

    @Override
    public void execution(String text) {
    }

    @Override
    public boolean isMatchedCommand(String text) {
        return STATUS_COMMAND.equalsIgnoreCase(text);
    }

    @Override
    public boolean isStatus() {
        return true;
    }
}
