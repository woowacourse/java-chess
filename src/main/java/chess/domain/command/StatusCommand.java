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
        execute(EMPTY_STRING);
        return whiteScore;
    }

    public double getBlackScore() {
        execute(EMPTY_STRING);
        return blackScore;
    }

    @Override
    public void execute(final String input) {
        whiteScore = chessGame.getWhiteScore();
        blackScore = chessGame.getBlackScore();
    }

    @Override
    public boolean isStatus() {
        return true;
    }

    @Override
    public String getCommand() {
        return COMMAND;
    }

}
