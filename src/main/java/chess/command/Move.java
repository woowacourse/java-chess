package chess.command;

import chess.game.ChessGame;

public class Move extends Command {
    private static final String COMMAND = "move";

    public Move(String value, ChessGame chessGame) {
        super(value, chessGame::doMoveCommand);
    }

    public static boolean isMove(String command) {
        String lowerCaseCommand = command.toLowerCase();
        return lowerCaseCommand.substring(0, 4).equals(COMMAND);
    }
}
