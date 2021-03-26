package chess.domain.command;

import chess.domain.game.ChessGame;

public class Finish implements Command {

    private static final String FINISH_COMMAND = "finish";

    private final ChessGame chessGame;

    public Finish(ChessGame chessGame) {
        this.chessGame = chessGame;
    }

    @Override
    public void execution(String text) {
        this.chessGame.finish();
    }

    @Override
    public boolean isMatchedCommand(String text) {
        return FINISH_COMMAND.equalsIgnoreCase(text);
    }

    @Override
    public boolean isStatus() {
        return false;
    }
}
