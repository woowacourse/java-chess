package chess.command;

import chess.game.ChessGame;

public class Move extends Command {
    public Move(String value, ChessGame chessGame) {
        super(value, chessGame::doMoveCommand);
    }
}
