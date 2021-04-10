package chess.domain.command;

import chess.domain.game.ChessGame;
import chess.domain.game.ScoreCalculator;

public class Status implements Command {

    private static final String STATUS_COMMAND = "status";

    private final ChessGame chessGame;
    private ScoreCalculator scoreCalculator;

    public Status(ChessGame chessGame) {
        this.chessGame = chessGame;
    }

    public String winner() {
        return this.chessGame.winner();
    }

    public double totalBlackScore() {
        return this.scoreCalculator.totalBlackScore();
    }

    public double totalWhiteScore() {
        return this.scoreCalculator.totalWhiteScore();
    }

    @Override
    public void execution(String text) {
        this.scoreCalculator = new ScoreCalculator(chessGame.board());
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
