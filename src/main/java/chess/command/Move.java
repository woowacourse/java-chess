package chess.command;

import chess.game.ChessGame;

public class Move extends Command {
    public static final String COMMAND = "MOVE";

    public Move(String value, ChessGame chessGame) {
        super(value, chessGame::doMoveCommand);
    }
}
