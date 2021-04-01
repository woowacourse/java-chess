package chess.domain.command;

import chess.domain.game.ChessGame;

public class StatusCommand extends CommandInit {
    private static final String COMMAND = "status";
    private static final String EMPTY_STRING = "";

    private double whiteScore;
    private double blackScore;

    public StatusCommand(final ChessGame chessGame) {
        super(chessGame);
    }

    public double getWhiteScore() {
        return whiteScore;
    }

    public double getBlackScore() {
        return blackScore;
    }

    @Override
    public void handle(final String input) {
        whiteScore = chessGame.getWhiteScore();
        blackScore = chessGame.getBlackScore();
    }

    @Override
    public boolean isAppropriateCommand(final String input) {
        return COMMAND.equals(input);
    }

    @Override
    public boolean isStatus() {
        return true;
    }

}
