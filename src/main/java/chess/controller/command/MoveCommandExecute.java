package chess.controller.command;

import chess.domain.chessGame.ChessGame;

public class MoveCommandExecute implements CommandExecute {
    private final ChessGame chessGame;

    public MoveCommandExecute(ChessGame chessGame) {
        this.chessGame = chessGame;
    }

    @Override
    public ChessGame execute(String current, String next) {
        chessGame.move(current, next);
        return chessGame;
    }
}
