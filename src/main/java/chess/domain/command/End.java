package chess.domain.command;

import chess.domain.game.ChessGame;

public class End implements Command {

    private static final String END_COMMAND = "end";

    private final ChessGame chessGame;

    public End(ChessGame chessGame) {
        this.chessGame = chessGame;
    }

    @Override
    public void execution(String text) {
        //chessGame.end();
    }

    @Override
    public boolean isMatchedCommand(String text) {
        return END_COMMAND.equalsIgnoreCase(text);
    }
}
